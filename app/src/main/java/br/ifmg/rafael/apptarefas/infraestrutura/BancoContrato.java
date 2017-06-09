package br.ifmg.rafael.apptarefas.infraestrutura;

import android.provider.BaseColumns;

/**
 * Created by Rafael on 23/05/2017.
 */

public class BancoContrato {

    public static class Tarefa implements BaseColumns{
        public static final String TABELA = "tarefa";
        public static final String COL_DESCRICAO = "descricao";
        public static final String COL_FINALIZADA = "finalizada";
        public static final String SQL_CREATE =
                String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s varchar(45), %s BOOLEAN);", TABELA,_ID,COL_DESCRICAO,COL_FINALIZADA);
    }
}
