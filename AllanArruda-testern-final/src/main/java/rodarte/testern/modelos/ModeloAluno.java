package rodarte.testern.modelos;

import rodarte.testern.enums.EnumSexo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ModeloAluno {

	private long id;
	private String identificacao;
	private String nome;
	private EnumSexo sexo;
	private LocalDate dataNascimento;
	private boolean status;
	
	private List<ModeloNotas> lsNotas = new ArrayList<ModeloNotas>();

	public ModeloAluno(long id, String identificacao, String nome, EnumSexo sexo, LocalDate dataNascimento, boolean status, List<ModeloNotas> lsNotas) {
		this.id = id;
		this.identificacao = identificacao;
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.status = status;
		this.lsNotas = lsNotas;
	}

	public ModeloAluno() {
	}

	public double getMediaNotas() {
		
		return lsNotas.stream().mapToDouble(n -> n.getNota()).average().orElse(0);
		
	}
	
	public double getSomaNotas() {
		
		return lsNotas.stream().mapToDouble(n -> n.getNota()).sum();
		
	}
	
	public long getIdade() {
		
		return ChronoUnit.YEARS.between(dataNascimento, LocalDate.now());
		
	}

	public long getId() {
		return this.id;
	}

	public String getIdentificacao() {
		return this.identificacao;
	}

	public String getNome() {
		return this.nome;
	}

	public EnumSexo getSexo() {
		return this.sexo;
	}

	public LocalDate getDataNascimento() {
		return this.dataNascimento;
	}

	public boolean isStatus() {
		return this.status;
	}

	public List<ModeloNotas> getLsNotas() {
		return this.lsNotas;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSexo(EnumSexo sexo) {
		this.sexo = sexo;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setLsNotas(List<ModeloNotas> lsNotas) {
		this.lsNotas = lsNotas;
	}
}