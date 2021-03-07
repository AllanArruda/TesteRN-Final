package rodarte.testern.modelos;

import rodarte.testern.enums.EnumSexo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModeloAlunoNotas {

    private long id;
    private String identificacao;
    private String nome;
    private EnumSexo sexo;
    private LocalDate dataNascimento;
    private double Nota_1Semestre;
    private double Nota_2Semestre;
    private double Nota_3Semestre;

    public ModeloAlunoNotas(long id, String identificacao, String nome, EnumSexo sexo, LocalDate dataNascimento, double Nota_1Semestre, double Nota_2Semestre, double Nota_3Semestre ) {
        this.id = id;
        this.identificacao = identificacao;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.Nota_1Semestre = Nota_1Semestre;
        this.Nota_2Semestre = Nota_2Semestre;
        this.Nota_3Semestre = Nota_3Semestre;
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

    public double getNota_1Semestre() {
        return this.Nota_1Semestre;
    }

    public double getNota_2Semestre() {
        return this.Nota_2Semestre;
    }

    public double getNota_3Semestre() {
        return this.Nota_3Semestre;
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

    public void setNota_1Semestre(double Nota_1Semestre) { this.Nota_1Semestre = Nota_1Semestre; }

    public void setNota_2Semestre(double Nota_2Semestre) { this.Nota_2Semestre = Nota_2Semestre; }

    public void setNota_3Semestre(double Nota_3Semestre) { this.Nota_3Semestre = Nota_3Semestre; }


}

