package apprpv.grupo3rpv.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import apprpv.grupo3rpv.Model.Domain.Andamento;
import apprpv.grupo3rpv.R;
/**
 * Created by GRUPO 3.
 */

public class ListAdapterAndamento extends ArrayAdapter<Andamento> implements AdapterView.OnItemClickListener{
    private final Context context;


    public ListAdapterAndamento(Context context, ArrayList<Andamento> andamentos){
        super(context,0,andamentos);
        this.context = context;
        ArrayList<Andamento> andamentos1 = andamentos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Andamento andamentoPosicao = this.getItem(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.list_adapter_andamento , null);

        TextView textViewData = (TextView) convertView.findViewById(R.id.dataAndamento);
        textViewData.setText(andamentoPosicao.getData());

        TextView textViewDpto = (TextView) convertView.findViewById(R.id.departamentoAndamento);
        if(andamentoPosicao.getDepto().length() > 27) {
            textViewDpto.setText(andamentoPosicao.getDepto().substring(0, 27) + "...");
        } else {
            textViewDpto.setText(andamentoPosicao.getDepto());
        }

        return convertView;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(context, DetalhesAndamentoActivity.class);
        context.startActivity(intent);
    }
}
