package rodarte.testern.funcoes;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import rodarte.testern.banco.BancoAluno;
import rodarte.testern.banco.BancoConexao;
import rodarte.testern.enums.EnumSexo;
import rodarte.testern.modelos.ModeloAluno;
import rodarte.testern.modelos.ModeloNotas;

public class FuncoesImportacaoAlunos extends FuncoesImportacoes {

	private List<ModeloAluno> lsAlunos = new ArrayList<ModeloAluno>();
	
	public List<String> iniciarImportacao(InputStream arquivo, String nomeArquivo) throws Exception {
		
		Connection con = null;
		
		try {
			
			importarAlunos(arquivo, nomeArquivo);
			
			if(lsErros.isEmpty()) {
				
				con = BancoConexao.abrir(BancoConexao.NOME_BANCO_DADOS_PRINCIPAL);
				
				new BancoAluno(con).inserirAluno(lsAlunos);
				
			}
			
			return lsErros;
			
		} finally {
			
			if(arquivo != null)
				arquivo.close();
			
			BancoConexao.fechar(con, null, null);
			
		}
		
	}
	
	private void importarAlunos(InputStream arquivo, String nomeArquivo) throws Exception {
		
		numLinhasVazias = 0;
		
		Cell cellIdentificacao = null;
		Cell cellNome = null;
		Cell cellSexo = null;
		Cell cellDataNascimento = null;
		Cell cellNota1 = null;
		Cell cellNota2 = null;
		Cell cellNota3 = null;
		
		XSSFWorkbook wb = null;
		
		try {
			
			wb = new XSSFWorkbook(arquivo);
			
			for(Row linha : wb.getSheetAt(0)) {
				
				if(linha.getRowNum() > 0) {
					
					int indiceColuna = -1;
					
					cellIdentificacao = linha.getCell(++indiceColuna);
					cellNome = linha.getCell(++indiceColuna);
					cellSexo = linha.getCell(++indiceColuna);
					cellDataNascimento = linha.getCell(++indiceColuna);
					cellNota1 = linha.getCell(++indiceColuna);
					cellNota2 = linha.getCell(++indiceColuna);
					cellNota3 = linha.getCell(++indiceColuna);
					
					if(isLinhaVazia(cellIdentificacao, cellNome, cellSexo, cellDataNascimento, cellNota1, cellNota2, cellNota3)) {
						
						numLinhasVazias++;
						
						if(numLinhasVazias > LIMITE_LINHAS_VAZIAS)
							break;
						
					} else {
						
						ModeloAluno aluno = new ModeloAluno();
						
						aluno.setIdentificacao(lerStringOuNumero(cellIdentificacao, lsErros, "Identificacao", nomeArquivo, null, null, linha.getRowNum()));
						aluno.setNome(lerString(cellNome, lsErros, "Nome", nomeArquivo, null, null, linha.getRowNum()));
						
						String sexo = lerString(cellSexo, lsErros, "Sexo", nomeArquivo, EnumSexo.restricoesSexo(), EnumSexo.valoresDisponiveis(), linha.getRowNum());
						if(! sexo.equals(""))
							aluno.setSexo(EnumSexo.valueOf(sexo.toUpperCase()));
						
						aluno.setDataNascimento(lerData(cellDataNascimento, lsErros, "Data Nascimento", nomeArquivo, null, null, linha.getRowNum()));
						
						double nota1 = lerDouble(cellNota1, lsErros, "Nota 1º Trimestre", nomeArquivo, null, null, linha.getRowNum());
						double nota2 = lerDouble(cellNota2, lsErros, "Nota 2º Trimestre", nomeArquivo, null, null, linha.getRowNum());
						double nota3 = lerDouble(cellNota3, lsErros, "Nota 3º Trimestre", nomeArquivo, null, null, linha.getRowNum());
						
						aluno.getLsNotas().add(new ModeloNotas(1, nota1));
						aluno.getLsNotas().add(new ModeloNotas(2, nota2));
						aluno.getLsNotas().add(new ModeloNotas(3, nota3));
						
						if(lsErros.isEmpty())
							lsAlunos.add(aluno);
						
					}
					
				}
				
			}
			
		} catch (Exception ex) {
		
			throw new Exception("Não foi possível importar os alunos! Erro: " + ex.getMessage());
			
		} finally {
			
			fecharWorkbook(wb);
			
		}
		
	}
	
}
