package br.ifmg.rafael.apptarefas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import br.ifmg.rafael.apptarefas.infraestrutura.slite.TarefaRepositorioSql;
import br.ifmg.rafael.apptarefas.modelo.Tarefa;
import br.ifmg.rafael.apptarefas.modelo.TarefasRepositorio;
import br.ifmg.rafael.apptarefas.visao.TarefaAdaptador;

public class TarefasActivity extends AppCompatActivity {

    private TarefasRepositorio repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Botão Incluir Tarefa
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fbIncluir);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText etNovaTarefa = new EditText(TarefasActivity.this);
                new AlertDialog.Builder(TarefasActivity.this)
                        .setTitle("Nova Tarefa")
                        .setView(etNovaTarefa)
                        .setPositiveButton("Criar Tarefa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Tarefa t = new Tarefa();
                                t.setDescricao(etNovaTarefa.getText().toString());
                                repo.criarTarefa(t);
                                carregarTarefas();
                                Toast.makeText(TarefasActivity.this, "Criou", Toast.LENGTH_LONG);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                }
                        )
                        .create()
                        .show();
            }
        });


        // obter tarefas do repositorio
        repo = new TarefaRepositorioSql(this);

        // criar o adaptador
        carregarTarefas();

    }

    private void carregarTarefas() {
        TarefaAdaptador adaptador = new TarefaAdaptador(this, repo.obterTarefas());

        // vincular a listview
        ListView lvTarefas = (ListView) findViewById(R.id.lvTarefas);
        lvTarefas.setAdapter(adaptador);

        lvTarefas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, final long id) {
                final EditText editaTarefa = new EditText(TarefasActivity.this);
                new AlertDialog.Builder(TarefasActivity.this)
                        .setTitle("Editar Tarefa")
                        .setView(editaTarefa)
                        .setPositiveButton("Editar Tarefa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Tarefa t = new Tarefa();
                                t.setId((int)id);
                                t.setDescricao(editaTarefa.getText().toString());
                                repo.atualizarTarefa(t);
                                carregarTarefas();
                            }
                        })
                        .setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Tarefa t = new Tarefa();
                                        t.setId((int)id);
                                        repo.excluirTarefa(t);
                                        carregarTarefas();
                                    }
                                }
                        )

                        .setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .create()
                        .show();


                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tarefas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void finalizarTarefa(View view) {
        Tarefa t = (Tarefa) view.getTag();

        if (t.isFinalizada() == true){
            t.setFinalizada(false);
        }else{
            t.setFinalizada(true);
        }
        repo.atualizarTarefa(t);

    }
}
