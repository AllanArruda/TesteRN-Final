package rodarte.testern.banco;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import rodarte.testern.enums.EnumSexo;
import rodarte.testern.modelos.ModeloAluno;
import rodarte.testern.modelos.ModeloAlunoNotas;
import rodarte.testern.modelos.ModeloNotas;

public class BancoAluno {

	private Connection con = null;
	
	public BancoAluno(Connection con) {

		this.con = con;
		
	}
	
	/**
	 * Método responsável por inserir os dados dos alunos no banco.
	 * @param lsAlunos - Lista com os dados dos alunos a serem inseridos.
	 * @throws Exception
	 */
	public void inserirAluno(List<ModeloAluno> lsAlunos) throws Exception {
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "INSERT INTO `aluno`(`identificacao`, `nome`, `sexo`, `data_nascimento`, `status`) VALUES (?,?,?,?,?)";
			pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			for(ModeloAluno aluno : lsAlunos) {

				int parameterIndex = 0;
				
				pstm.setString(++parameterIndex, aluno.getIdentificacao());
				pstm.setString(++parameterIndex, aluno.getNome());
				pstm.setString(++parameterIndex, aluno.getSexo().toString());
				pstm.setDate(++parameterIndex, Date.valueOf(aluno.getDataNascimento()), Calendar.getInstance(TimeZone.getDefault()));
				pstm.setBoolean(++parameterIndex, true);
				pstm.execute();
				
				/** INSERÇÃO DAS NOTAS **/
				rs = pstm.getGeneratedKeys();
				rs.next();
				
				inserirNota(aluno.getLsNotas(), rs.getLong(1));
				
			}
			
		} catch (Exception ex) {

			throw new Exception("Não foi possível inserir os alunos! Erro: " + ex.getMessage());
			
		} finally {
			
			BancoConexao.fechar(null, pstm, rs);
			
		}
		
	}
	
	/**
	 * Método responsável por inserir as notas dos alunos no banco de dados.
	 * @param lsNotas - Lista com as notas a serem inseridas.
	 * @param idAluno - ID do aluno relacionado às notas.
	 * @throws Exception
	 */
	private void inserirNota(List<ModeloNotas> lsNotas, long idAluno) throws Exception {
		
		PreparedStatement pstm = null;

		try {
			
			String sql = "INSERT INTO `notas`(`periodo`, `nota`, `id_aluno`) VALUES (?,?,?)";
			pstm = con.prepareStatement(sql);
			
			for(ModeloNotas nota : lsNotas) {
				
				int parameterIndex = 0;
				
				pstm.setInt(++parameterIndex, nota.getPeriodo());
				pstm.setDouble(++parameterIndex, nota.getNota());
				pstm.setLong(++parameterIndex, idAluno);
				pstm.execute();
				
			}
			
		} catch (Exception ex) {

			throw new Exception("Não foi possível inserir as notas! Erro: " + ex.getMessage());
			
		} finally {
			
			BancoConexao.fechar(null, pstm, null);
			
		}
		
	}
	
	/**
	 * Método responsável por resetar os dados inseridos para os alunos.
	 * @throws Exception
	 */
	public void resetarAlunos() throws Exception {
		
		PreparedStatement pstm = null;
		
		try {
			
			String sql = "DELETE FROM `aluno`";
			pstm = con.prepareStatement(sql);
			pstm.execute();
			
		} catch (Exception ex) {
			
			throw new Exception("Não foi possível resetar os alunos! Erro: " + ex.getMessage());
			
		} finally {
			
			BancoConexao.fechar(null, pstm, null);
			
		}
		
	}
	
	/**
	 * Método responsável por buscar os dados dos alunos.
	 * @return - Retorna uma lista com os dados encontrados.
	 * @throws Exception
	 */
	public List<ModeloAluno> buscarAlunos() throws Exception {
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			List<ModeloAluno> lsAlunos = new ArrayList<ModeloAluno>();
			
			String sql = "SELECT * FROM `aluno`";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				lsAlunos.add(new ModeloAluno(rs.getLong("id"), 
													rs.getString("identificacao"), 
													rs.getString("nome"), 
													EnumSexo.valueOf(rs.getString("sexo")), 
													rs.getDate("data_nascimento").toLocalDate(), 
													rs.getBoolean("status"), 
													buscarNotasPorAluno(rs.getLong("id"))));
				
			}
			
			return lsAlunos;
			
		} catch (Exception ex) {

			throw new Exception("Não foi possível buscar os alunos! Erro: " + ex.getMessage());
			
		} finally {
			
			BancoConexao.fechar(null, pstm, rs);
			
		}
		
	}
	
	/**
	 * Método responsável por buscar as notas de um determinado aluno.
	 * @param idAluno - ID do aluno que terá suas notas buscadas.
	 * @return - Retorna uma lista com os dados encontrados.
	 * @throws Exception
	 */
	private List<ModeloNotas> buscarNotasPorAluno(long idAluno) throws Exception {
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			List<ModeloNotas> lsNotas = new ArrayList<ModeloNotas>();
			
			String sql = "SELECT * FROM `notas` WHERE `id_aluno` = ? ORDER BY `periodo`";
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, idAluno);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				lsNotas.add(new ModeloNotas(rs.getLong("id"), 
											rs.getInt("periodo"), 
											rs.getDouble("nota")));
				
			}
			
			return lsNotas;
			
		} catch (Exception ex) {

			throw new Exception("Não foi possível buscar as notas por aluno! Erro: " + ex.getMessage());
			
		} finally {
			
			BancoConexao.fechar(null, pstm, rs);
			
		}
		
	}

	/**
	 * Novo método criado por mim, utilizando uma query SQL para montar a tabela com Alunos e notas
	 */
	public List<ModeloAlunoNotas> buscarAlunoseNotas() throws Exception {

		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			List<ModeloAlunoNotas> lsAlunoNotas = new ArrayList<ModeloAlunoNotas>();

			String sql = "SELECT AlunoNota.id, AlunoNota.identificacao, AlunoNota.nome, AlunoNota.sexo, AlunoNota.data_nascimento, MAX(IF(AlunoNota.periodo = 1, AlunoNota.nota, NULL)) Nota_1Semestre, MAX(IF(AlunoNota.periodo = 2, AlunoNota.nota, NULL)) Nota_2Semestre, MAX(IF(AlunoNota.periodo = 3, AlunoNota.nota, NULL)) Nota_3Semestre FROM (SELECT a.id, a.identificacao, a.nome, a.sexo, a.data_nascimento, n.periodo, n.nota, n.id_aluno FROM aluno a, notas n WHERE a.id = n.id_aluno ORDER BY a.id) AlunoNota GROUP BY AlunoNota.id";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();

			while(rs.next()) {

				lsAlunoNotas.add(new ModeloAlunoNotas(rs.getLong("id"),
						rs.getString("identificacao"),
						rs.getString("nome"),
						EnumSexo.valueOf(rs.getString("sexo")),
						rs.getDate("data_nascimento").toLocalDate(),
						rs.getDouble("Nota_1Semestre"),
						rs.getDouble("Nota_2Semestre"),
						rs.getDouble("Nota_3Semestre")));
			}

			return lsAlunoNotas;

		} catch (Exception ex) {

			throw new Exception("Não foi possível buscar os alunos e notas! Erro: " + ex.getMessage());

		} finally {

			BancoConexao.fechar(null, pstm, rs);

		}

	}

}
