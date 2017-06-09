package br.ifmg.rafael.apptarefas;

import org.junit.Test;

import br.ifmg.rafael.apptarefas.infraestrutura.TarefasRepositorioRAM;
import br.ifmg.rafael.apptarefas.modelo.Tarefa;
import br.ifmg.rafael.apptarefas.modelo.TarefasRepositorio;

import static org.junit.Assert.*;

public class ExampleUnitTest {
    @Test
    public void criarTarefaTest() throws Exception {
        TarefasRepositorio repo = new TarefasRepositorioRAM();

        Tarefa t = new Tarefa();
        t.setDescricao("Nova Tarefa");

        repo.criarTarefa(t);

        assertTrue("id é maior que zero", t.getId() > 0);

        Tarefa t2 = repo.obterTarefaPorId(t.getId());
        assertEquals(t, t2);
    }
}