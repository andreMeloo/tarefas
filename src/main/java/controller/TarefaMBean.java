package controller;

import dao.TarefaDAO;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import model.Tarefa;
import lombok.Getter;
import lombok.Setter;



import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
@Getter
@Setter
public class TarefaMBean implements Serializable {

    private List<Tarefa> tarefas;
    private Tarefa tarefa = new Tarefa();
    private String mensagemSucesso;
    private String mensagemErro;
    private Long campoId;
    private String tituloDescricao;
    private String responsavel;
    private boolean status;


    public String cadastrar() {
        tarefa = new Tarefa();
        tarefa.setDescricao("Ola");
        return tarefa.getDescricao();
    }

    public void inativar() {
        TarefaDAO trfDAO = new TarefaDAO();
        try {
            if (tarefa != null) {
                trfDAO.inativar(tarefa);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            mensagemSucesso = "Operação realizada com sucesso!";
        }
    }

    public void alterar() {
        TarefaDAO trfDAO = new TarefaDAO();
        try {
            if (tarefa != null) {
                trfDAO.alterar(tarefa);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            mensagemSucesso = "Operação realizada com sucesso!";
        }
    }

    public void listar() {
        TarefaDAO trfDAO = new TarefaDAO();
        try {
            tarefas = trfDAO.findAllByIdTituloDescricaoResponsavelStatus(campoId, tituloDescricao, responsavel, status);
        } catch (Exception e) {
            System.out.println(e);
        }
        if (tarefas.isEmpty()) {
            mensagemErro = "Busca sem resultados.";
        }
    }
}
