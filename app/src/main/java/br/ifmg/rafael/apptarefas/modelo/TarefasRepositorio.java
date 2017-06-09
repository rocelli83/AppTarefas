package br.ifmg.rafael.apptarefas.modelo;

import java.util.List;

/**
 * Created by Rafael on 12/05/2017.
 */

public interface TarefasRepositorio {
    List<Tarefa> obterTarefas();
    Tarefa obterTarefaPorId(int id);
    List<Tarefa> obterTarefasOrdenadas();

    void criarTarefa(Tarefa tarefa);
    void atualizarTarefa(Tarefa tarefa);
    void excluirTarefa(Tarefa tarefa);

}
