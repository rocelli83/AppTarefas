package br.ifmg.rafael.apptarefas.visao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import br.ifmg.rafael.apptarefas.R;
import br.ifmg.rafael.apptarefas.modelo.Tarefa;

/**
 * Created by Rafael on 05/05/2017.
 */

public class TarefaAdaptador extends BaseAdapter {

    private Context contexto;
    private List<Tarefa> tarefas;

    public TarefaAdaptador(Context contexto, List<Tarefa> tarefas) {
        this.contexto = contexto;
        this.tarefas = tarefas;
    }

    @Override
    public int getCount() {
        return tarefas.size();
    }

    @Override
    public Object getItem(int i) {
        return tarefas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return tarefas.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {
            //inflar layout do item
            view = LayoutInflater.from(contexto).inflate(R.layout.item_tarefa, null);
            //pegar os compontes visuais
            TextView tvDescricao = (TextView) view.findViewById(R.id.tvDescricao);
            CheckBox cbFinalizada = (CheckBox) view.findViewById(R.id.cbFinalizada);
            // criar o viewHolder
            holder = new ViewHolder(tvDescricao, cbFinalizada);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
            //atualizar componetes
        Tarefa tarefa = tarefas.get(i);
        holder.tvDescricao.setText(tarefa.getDescricao());
        holder.cbFinalizada.setChecked(tarefa.isFinalizada());
        holder.cbFinalizada.setTag(tarefa);
        return view;
    }
    class  ViewHolder{
        TextView tvDescricao;
        CheckBox cbFinalizada;

        public ViewHolder(TextView tvDescricao , CheckBox cbFinalizada){
            this.tvDescricao = tvDescricao;
            this.cbFinalizada = cbFinalizada;
        }
    }
}
