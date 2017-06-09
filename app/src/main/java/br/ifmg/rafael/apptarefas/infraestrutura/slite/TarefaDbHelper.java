package br.ifmg.rafael.apptarefas.infraestrutura.slite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.ifmg.rafael.apptarefas.infraestrutura.BancoContrato;

/**
 * Created by Rafael on 23/05/2017.
 */

public class TarefaDbHelper extends SQLiteOpenHelper {
    public static final String BANCO = "tarefas.db";
    public static final int VERSAO = 1;

    public TarefaDbHelper(Context context) {
        super(context, BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BancoContrato.Tarefa.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BancoContrato.Tarefa.TABELA);
        onCreate(db);
    }
}
