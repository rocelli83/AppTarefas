package br.ifmg.rafael.apptarefas.infraestrutura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.ifmg.rafael.apptarefas.modelo.Tarefa;
import br.ifmg.rafael.apptarefas.modelo.TarefasRepositorio;

/**
 * Created by Rafael on 12/05/2017.
 */

public class TarefasRepositorioRAM implements TarefasRepositorio {

    private static int ultimoId = 0;

    private List<Tarefa> tarefas;

    public TarefasRepositorioRAM() {
        tarefas = new ArrayList<>();
        tarefas.add(new Tarefa(1, "tarefa 1", false));
        tarefas.add(new Tarefa(2, "comprar pão", false));
        tarefas.add(new Tarefa(3, "ligar para advogado", true));
        tarefas.add(new Tarefa(4, "terminar exercício", true));
    }

    @Override
    public List<Tarefa> obterTarefas() {
        return tarefas;
    }
    @Override
    public void criarTarefa(Tarefa tarefa) {
        tarefa.setId(++ultimoId);
        tarefas.add(tarefa);
    }

    @Override
    public void atualizarTarefa(Tarefa tarefa) {

    }

    @Override
    public void excluirTarefa(Tarefa tarefa) {

    }

    @Override
    public Tarefa obterTarefaPorId(int id) {
        for (Tarefa t : tarefas)
            if (id == t.getId())
                return t;
        return null;
    }

    @Override
    public List<Tarefa> obterTarefasOrdenadas() {
        Collections.sort(tarefas, new Comparator<Tarefa>() {
            @Override
            public int compare(Tarefa t1, Tarefa t2) {
                return t1.getDescricao()
                        .compareTo(t2.getDescricao());
            }
        });
        return tarefas;
    }
}
