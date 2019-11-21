package apprpv.grupo3rpv.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

import apprpv.grupo3rpv.Model.Domain.Andamento;
import apprpv.grupo3rpv.R;

/**
 * Classe que controla a fragment dos andamentos
 */

public class AndamentosFragment extends Fragment {

    private ArrayList<Andamento> andamentos;
    private Bundle bundle;
    private View rootView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_andamentos, container, false);

        bundle = this.getActivity().getIntent().getExtras();

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                atualizarAndamentos();
            }
        });

        return rootView;

    }

    private void atualizarAndamentos() {
    }

    @Override
    public void onStart() {
        super.onStart();
        apresentarDadosAndamentos();

    }

    /**
     * Método responsável por apresentar os andamentos
     */
    public void apresentarDadosAndamentos(){
        andamentos = (ArrayList<Andamento>) bundle.getSerializable("lista");

        ListView listView = (ListView) rootView.findViewById(R.id.listViewF);
        ListAdapterAndamento adapterAndamento = new ListAdapterAndamento(this.getActivity().getBaseContext(), andamentos);
        listView.setAdapter(adapterAndamento);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity().getBaseContext(), DetalhesAndamentoActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("data", andamentos.get(position).getData());
                bundle.putString("departamento", andamentos.get(position).getDepto());
                bundle.putString("ocorrencia", andamentos.get(position).getOcorrencia());
                bundle.putString("despacho", andamentos.get(position).getDespacho());
                i.putExtras(bundle);

                startActivity(i);
            }
        });
    }
}
