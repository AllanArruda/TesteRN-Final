package rodarte.testern.viewmodel;

import rodarte.testern.funcoes.FuncoesAlunos;
import rodarte.testern.funcoes.FuncoesExportacaoResultados;
import rodarte.testern.modelos.ModeloAluno;
import rodarte.testern.modelos.ModeloAlunoNotas;
import rodarte.testern.modelos.ModeloNotas;

import java.util.List;

public class VmPageRelatorios {

    private FuncoesAlunos funcaoAluno= new FuncoesAlunos();

    public List<ModeloAlunoNotas> getAlunosNotas() throws Exception {
        return funcaoAluno.buscarAlunoseNotas();
    }

}
