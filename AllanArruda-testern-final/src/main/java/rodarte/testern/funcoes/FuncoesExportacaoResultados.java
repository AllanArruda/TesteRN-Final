package rodarte.testern.funcoes;

import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import rodarte.testern.enums.EnumSexo;
import rodarte.testern.modelos.ModeloAluno;
import rodarte.testern.modelos.ModeloNotas;

public class FuncoesExportacaoResultados extends FuncoesExportacoes {

	private List<ModeloAluno> lsAlunos;
	
	public FuncoesExportacaoResultados(List<ModeloAluno> lsAlunos) {
		
		this.lsAlunos = lsAlunos;
		
	}

	public void gerarRelatorio() {
		List<ModeloAluno> lsAlunosListagem = lsAlunos.stream().collect(Collectors.toList());
		for(ModeloAluno aluno : lsAlunosListagem) {

		}
	}

	public void gerarArquivo(OutputStream arquivo) throws Exception {
		
		XSSFWorkbook wb = null;
		
		try {
			
			wb = new XSSFWorkbook();
			
			definirEstilos(wb);
			
			XSSFSheet aba1 = wb.createSheet("Planilha 1");
			criaAba1(aba1);
			
			XSSFSheet aba2 = wb.createSheet("Planilha 2");
			criaAba2(aba2);
			
			XSSFSheet aba3 = wb.createSheet("Planilha 3");
			criaAba3(aba3);
			
			wb.write(arquivo);
			
		} finally {

			if(wb != null)
				wb.close();
			
		}
		
	}
	
	private void criaAba1(XSSFSheet aba) throws Exception {
		
		linhaAtual = 0;
		colunaAtual = 0;
		
		escreverCabecalhoAba1(aba);
		escreverConteudoAba1(aba);
		
		configurarAba1(aba);
		
	}
	
	private void escreverCabecalhoAba1(XSSFSheet aba) throws Exception {
		
		linha = pegaLinha(aba, ++linhaAtual);
		
		celula = linha.createCell(++colunaAtual);
		celula.setCellValue("Identificação");
		celula.setCellStyle(ESTILO_CABECALHO_COMUM);
		
		celula = linha.createCell(++colunaAtual);
		celula.setCellValue("Nome");
		celula.setCellStyle(ESTILO_CABECALHO_COMUM);
		
		celula = linha.createCell(++colunaAtual);
		celula.setCellValue("Sexo");
		celula.setCellStyle(ESTILO_CABECALHO_COMUM);
		
		celula = linha.createCell(++colunaAtual);
		celula.setCellValue("Data Nascimento");
		celula.setCellStyle(ESTILO_CABECALHO_COMUM);
		
		celula = linha.createCell(++colunaAtual);
		celula.setCellValue("Nota 1º Trimestre");
		celula.setCellStyle(ESTILO_CABECALHO_COMUM);
		
		celula = linha.createCell(++colunaAtual);
		celula.setCellValue("Nota 2º Trimestre");
		celula.setCellStyle(ESTILO_CABECALHO_COMUM);
		
		celula = linha.createCell(++colunaAtual);
		celula.setCellValue("Nota 3º Trimestre");
		celula.setCellStyle(ESTILO_CABECALHO_COMUM);
		
		colunaAtual = 0;
		
	}
	
	private void escreverConteudoAba1(XSSFSheet aba) throws Exception {
		
		List<ModeloAluno> lsAlunosListagem = lsAlunos.stream().sorted((a1, a2) -> a1.getNome().compareTo(a2.getNome())).collect(Collectors.toList());
		
		for(ModeloAluno aluno : lsAlunosListagem) {
			
			colunaAtual = 0;
			
			linha = pegaLinha(aba, ++linhaAtual);
			
			celula = linha.createCell(++colunaAtual);
			if(verificaMatriculaNumerica(aluno.getIdentificacao())) {
				celula.setCellValue(Integer.parseInt(aluno.getIdentificacao()));
			}else {
				celula.setCellValue(aluno.getIdentificacao());
			}
			celula.setCellStyle(ESTILO_CONTEUDO_COMUM_CENTRALIZADO);
			
			celula = linha.createCell(++colunaAtual);
			celula.setCellValue(aluno.getNome());
			celula.setCellStyle(ESTILO_CONTEUDO_COMUM);
			
			celula = linha.createCell(++colunaAtual);
			celula.setCellValue(aluno.getSexo().toString());
			celula.setCellStyle(ESTILO_CONTEUDO_COMUM_CENTRALIZADO);
			
			celula = linha.createCell(++colunaAtual);
			celula.setCellValue(aluno.getDataNascimento());
			celula.setCellStyle(ESTILO_CONTEUDO_DATA);
			
			for(ModeloNotas nota : aluno.getLsNotas()) {
				
				celula = linha.createCell(++colunaAtual);
				celula.setCellValue(nota.getNota());
				celula.setCellStyle(ESTILO_CONTEUDO_DOUBLE);
				
			}
			
		}
		
	}
	
	private void configurarAba1(XSSFSheet aba) {
		
		int columnIndex = 0;
		
		aba.setColumnWidth(++columnIndex, 3330);//B
		aba.setColumnWidth(++columnIndex, 4440);//C
		aba.setColumnWidth(++columnIndex, 1850);//D
		aba.setColumnWidth(++columnIndex, 2770);//E
		aba.setColumnWidth(++columnIndex, 2400);//F
		aba.setColumnWidth(++columnIndex, 2400);//G
		aba.setColumnWidth(++columnIndex, 2400);//H
		
	}
	
	private void criaAba2(XSSFSheet aba) throws Exception {
		
		linhaAtual = 0;
		colunaAtual = 0;
		
		escreverCabecalhoAba2(aba);
		escreverConteudoAba2(aba);
		
		configurarAba2(aba);
		
	}
	
	private void escreverCabecalhoAba2(XSSFSheet aba) throws Exception {
		
		linha = pegaLinha(aba, ++linhaAtual);
		
		celula = linha.createCell(++colunaAtual);
		celula.setCellValue("Identificação");
		celula.setCellStyle(ESTILO_CABECALHO_COMUM);
		
		celula = linha.createCell(++colunaAtual);
		celula.setCellValue("Nome");
		celula.setCellStyle(ESTILO_CABECALHO_COMUM);
		
		celula = linha.createCell(++colunaAtual);
		celula.setCellValue("Idade");
		celula.setCellStyle(ESTILO_CABECALHO_COMUM);
		
		celula = linha.createCell(++colunaAtual);
		celula.setCellValue("Média das Notas");
		celula.setCellStyle(ESTILO_CABECALHO_COMUM);
		
		colunaAtual = 0;
		
	}
	
	private void escreverConteudoAba2(XSSFSheet aba) throws Exception {
		
		List<ModeloAluno> lsAlunosListagem = lsAlunos.stream().sorted((a1, a2) -> Long.compare(a1.getIdade(), a2.getIdade())).collect(Collectors.toList());
		
		for(ModeloAluno aluno : lsAlunosListagem) {
			
			colunaAtual = 0;
			
			linha = pegaLinha(aba, ++linhaAtual);
			
			celula = linha.createCell(++colunaAtual);
			if(verificaMatriculaNumerica(aluno.getIdentificacao())) {
				celula.setCellValue(Integer.parseInt(aluno.getIdentificacao()));
			}else {
				celula.setCellValue(aluno.getIdentificacao());
			}
			celula.setCellStyle(ESTILO_CONTEUDO_COMUM_CENTRALIZADO);
			
			celula = linha.createCell(++colunaAtual);
			celula.setCellValue(aluno.getNome());
			celula.setCellStyle(ESTILO_CONTEUDO_COMUM);
			
			celula = linha.createCell(++colunaAtual);
			celula.setCellValue(aluno.getIdade());
			celula.setCellStyle(ESTILO_CONTEUDO_COMUM_CENTRALIZADO);
			
			celula = linha.createCell(++colunaAtual);
			celula.setCellValue(aluno.getMediaNotas());
			celula.setCellStyle(ESTILO_CONTEUDO_DOUBLE);
			
		}
		
	}
	
	private void configurarAba2(XSSFSheet aba) {
		
		int columnIndex = 0;
		
		aba.setColumnWidth(++columnIndex, 3330);//B
		aba.setColumnWidth(++columnIndex, 4440);//C
		
	}
	
	private void criaAba3(XSSFSheet aba) throws Exception {
		
		linhaAtual = 0;
		colunaAtual = 1;
		
		escreverCabecalhoAba3(aba);
		escreverConteudoAba3(aba);
		
		configurarAba3(aba);
		
	}
	
	private void escreverCabecalhoAba3(XSSFSheet aba) throws Exception {
		
		linha = pegaLinha(aba, ++linhaAtual);
		celula = linha.createCell(colunaAtual);
		celula.setCellValue("Estatísticas");
		celula.setCellStyle(ESTILO_CABECALHO_COMUM);
		
		celula = linha.createCell(colunaAtual + 1);
		celula.setCellStyle(ESTILO_CABECALHO_COMUM);
		
		aba.addMergedRegion(new CellRangeAddress(linhaAtual, linhaAtual, colunaAtual, colunaAtual + 1));
		
		linha = pegaLinha(aba, ++linhaAtual);
		celula = linha.createCell(colunaAtual);
		celula.setCellValue("Percentual de alunos do sexo masculino");
		celula.setCellStyle(ESTILO_CONTEUDO_COMUM);
		
		linha = pegaLinha(aba, ++linhaAtual);
		celula = linha.createCell(colunaAtual);
		celula.setCellValue("Percentual de alunos do sexo feminino");
		celula.setCellStyle(ESTILO_CONTEUDO_COMUM);
		
		linha = pegaLinha(aba, ++linhaAtual);
		celula = linha.createCell(colunaAtual);
		celula.setCellValue("Percentual de alunos com menos de 30 anos");
		celula.setCellStyle(ESTILO_CONTEUDO_COMUM);
		
		linha = pegaLinha(aba, ++linhaAtual);
		celula = linha.createCell(colunaAtual);
		celula.setCellValue("Percentual de alunos aprovados");
		celula.setCellStyle(ESTILO_CONTEUDO_COMUM);
		
		linha = pegaLinha(aba, ++linhaAtual);
		celula = linha.createCell(colunaAtual);
		celula.setCellValue("Média de nota dos alunos com mais de 30 anos");
		celula.setCellStyle(ESTILO_CONTEUDO_COMUM);
		
		linha = pegaLinha(aba, ++linhaAtual);
		celula = linha.createCell(colunaAtual);
		celula.setCellValue("Média de nota dos alunos do sexo masculino");
		celula.setCellStyle(ESTILO_CONTEUDO_COMUM);
		
		linha = pegaLinha(aba, ++linhaAtual);
		celula = linha.createCell(colunaAtual);
		celula.setCellValue("Média de nota dos alunos do sexo feminino");
		celula.setCellStyle(ESTILO_CONTEUDO_COMUM);
		
		linha = pegaLinha(aba, ++linhaAtual);
		celula = linha.createCell(colunaAtual);
		celula.setCellValue("Média de idade dos participantes da base");
		celula.setCellStyle(ESTILO_CONTEUDO_COMUM);
		
		linhaAtual = 1;
		
	}
	
	private void escreverConteudoAba3(XSSFSheet aba) throws Exception {
			
		colunaAtual++;
		
		linha = pegaLinha(aba, ++linhaAtual);
		celula = linha.createCell(colunaAtual);
		celula.setCellValue((double)lsAlunos.stream().filter(a -> a.getSexo().equals(EnumSexo.M)).collect(Collectors.toList()).size() / (double)lsAlunos.size());
		celula.setCellStyle(ESTILO_CONTEUDO_PERCENTUAL);
		
		linha = pegaLinha(aba, ++linhaAtual);
		celula = linha.createCell(colunaAtual);
		celula.setCellValue((double)lsAlunos.stream().filter(a -> a.getSexo().equals(EnumSexo.F)).collect(Collectors.toList()).size() / (double)lsAlunos.size());
		celula.setCellStyle(ESTILO_CONTEUDO_PERCENTUAL);
		
		linha = pegaLinha(aba, ++linhaAtual);
		celula = linha.createCell(colunaAtual);
		celula.setCellValue((double)lsAlunos.stream().filter(a -> a.getIdade() < 30).collect(Collectors.toList()).size() / (double)lsAlunos.size());
		celula.setCellStyle(ESTILO_CONTEUDO_PERCENTUAL);
		
		linha = pegaLinha(aba, ++linhaAtual);
		celula = linha.createCell(colunaAtual);
		celula.setCellValue((double)lsAlunos.stream().filter(a -> a.getSomaNotas() >= 70).collect(Collectors.toList()).size() / (double)lsAlunos.size());
		celula.setCellStyle(ESTILO_CONTEUDO_PERCENTUAL);
		
		linha = pegaLinha(aba, ++linhaAtual);
		celula = linha.createCell(colunaAtual);
		celula.setCellValue(lsAlunos.stream().filter(a -> a.getIdade() > 30).mapToDouble(a -> a.getMediaNotas()).average().orElse(0));
		celula.setCellStyle(ESTILO_CONTEUDO_DOUBLE);
		
		linha = pegaLinha(aba, ++linhaAtual);
		celula = linha.createCell(colunaAtual);
		celula.setCellValue(lsAlunos.stream().filter(a -> a.getSexo().equals(EnumSexo.M)).mapToDouble(a -> a.getMediaNotas()).average().orElse(0));
		celula.setCellStyle(ESTILO_CONTEUDO_DOUBLE);
		
		linha = pegaLinha(aba, ++linhaAtual);
		celula = linha.createCell(colunaAtual);
		celula.setCellValue(lsAlunos.stream().filter(a -> a.getSexo().equals(EnumSexo.F)).mapToDouble(a -> a.getMediaNotas()).average().orElse(0));
		celula.setCellStyle(ESTILO_CONTEUDO_DOUBLE);
		
		linha = pegaLinha(aba, ++linhaAtual);
		celula = linha.createCell(colunaAtual);
		celula.setCellValue(lsAlunos.stream().mapToDouble(a -> a.getIdade()).average().orElse(0));
		celula.setCellStyle(ESTILO_CONTEUDO_DOUBLE);
		
	}
	
	private void configurarAba3(XSSFSheet aba) {
		
		int columnIndex = 0;
		
		aba.setColumnWidth(++columnIndex, 8700);//B
		
	}
	
}
