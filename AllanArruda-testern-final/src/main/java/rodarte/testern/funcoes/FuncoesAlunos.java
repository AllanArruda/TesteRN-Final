package rodarte.testern.funcoes;

import java.sql.Connection;
import java.util.List;

import rodarte.testern.banco.BancoAluno;
import rodarte.testern.banco.BancoConexao;
import rodarte.testern.modelos.ModeloAluno;
import rodarte.testern.modelos.ModeloAlunoNotas;

public class FuncoesAlunos {

	public void resetarImportacao() throws Exception {
		
		Connection con = null;
		
		try {
			
			con = BancoConexao.abrir(BancoConexao.NOME_BANCO_DADOS_PRINCIPAL);
			
			new BancoAluno(con).resetarAlunos();
			
		} finally {

			BancoConexao.fechar(con, null, null);
			
		}
		
	}
	
	public List<ModeloAluno> buscarAlunos() throws Exception {
		
		Connection con = null;
		
		try {
			
			con = BancoConexao.abrir(BancoConexao.NOME_BANCO_DADOS_PRINCIPAL);
			
			return new BancoAluno(con).buscarAlunos();
			
		} finally {

			BancoConexao.fechar(con, null, null);
			
		}
		
	}

	public List<ModeloAlunoNotas> buscarAlunoseNotas() throws Exception {

		Connection con = null;

		try {

			con = BancoConexao.abrir(BancoConexao.NOME_BANCO_DADOS_PRINCIPAL);

			return new BancoAluno(con).buscarAlunoseNotas();

		} finally {

			BancoConexao.fechar(con, null, null);

		}

	}
	
}
