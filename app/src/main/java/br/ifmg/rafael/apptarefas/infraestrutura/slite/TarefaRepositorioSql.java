package br.ifmg.rafael.apptarefas.infraestrutura.slite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.ifmg.rafael.apptarefas.infraestrutura.BancoContrato;
import br.ifmg.rafael.apptarefas.modelo.Tarefa;
import br.ifmg.rafael.apptarefas.modelo.TarefasRepositorio;

import static br.ifmg.rafael.apptarefas.infraestrutura.BancoContrato.Tarefa.*;


/**
 * Created by Rafael on 23/05/2017.
 */

public class TarefaRepositorioSql implements TarefasRepositorio {
    private SQLiteDatabase sqliteConnection;
    private Context context;

    public TarefaRepositorioSql(Context context) {
        this.context = context;

    }

    @Override
    public List<Tarefa> obterTarefas() {
        List <Tarefa> tarefas = new ArrayList<>();
        TarefaDbHelper helper = new TarefaDbHelper(context);
        SQLiteDatabase database = helper.getReadableDatabase();
        String[] columns = new String[]{_ID,COL_DESCRICAO, COL_FINALIZADA};

        Cursor returnDB = database.query(TABELA,columns, null, null,null,null,null);
        if (returnDB.moveToFirst()){
            do{
                Tarefa tarefa = new Tarefa();
                tarefa.setId(returnDB.getInt(returnDB.getColumnIndex(_ID)));
                tarefa.setDescricao(returnDB.getString(returnDB.getColumnIndex(COL_DESCRICAO)));
                tarefa.setFinalizada(returnDB.getInt(2) == 0 ? false: true );
                tarefas.add(tarefa);
            }while (returnDB.moveToNext());
        }

        returnDB.close();
        database.close();
        return tarefas;
    }

    @Override
    public Tarefa obterTarefaPorId(int id) {
        List<Tarefa> tarefas = obterTarefas();
        for (Tarefa t : tarefas) {
            if (id == t.getId())
                return t;
        }
        return null;
    }

    @Override
    public List<Tarefa> obterTarefasOrdenadas() {
        List <Tarefa> tarefas = new ArrayList<>();
        Collections.sort(tarefas, new Comparator<Tarefa>() {
            @Override
            public int compare(Tarefa t1, Tarefa t2) {
                return t1.getDescricao()
                        .compareTo(t2.getDescricao());
            }
        });
        return tarefas;
    }

    @Override
    public void criarTarefa(Tarefa tarefa) {
        ContentValues valores = new ContentValues();
        valores.put(COL_DESCRICAO, tarefa.getDescricao());
        valores.put(BancoContrato.Tarefa.COL_FINALIZADA, tarefa.isFinalizada());

        TarefaDbHelper helper = new TarefaDbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.insert(BancoContrato.Tarefa.TABELA, null, valores);
        db.close();
        helper.close();
    }

    @Override
    public void atualizarTarefa(Tarefa tarefa) {
        ContentValues valores = new ContentValues();
        valores.put(COL_DESCRICAO, tarefa.getDescricao());
        valores.put(COL_FINALIZADA, tarefa.isFinalizada());
        TarefaDbHelper helper = new TarefaDbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.update(BancoContrato.Tarefa.TABELA,valores,"_id="+tarefa.getId(),null);
        db.close();
        helper.close();
    }

    @Override
    public void excluirTarefa(Tarefa tarefa) {
        TarefaDbHelper helper = new TarefaDbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(BancoContrato.Tarefa.TABELA,"_id="+tarefa.getId(),null);
        db.close();
        helper.close();


    }
}
