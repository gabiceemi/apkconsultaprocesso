package apprpv.grupo3rpv.Controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import apprpv.grupo3rpv.R;

/**
 * Classe que controla a fragment dos dados do processo
 */
public class ProcessoFragment extends Fragment {

    private Integer numCP;
    private String numProc;
    private String data;
    private String tipo;
    private String departamento;
    private String instituicao;
    private String requerente;
    private String observacao;
    private String nome;
    private String hora;
    private String atendente;
    private View rootView;
    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_processo, container, false);

        bundle = this.getActivity().getIntent().getExtras();

        return rootView;

    }

        @Override
        public void onStart() {
            super.onStart();
            pegarDadosProcesso();
            apresentarDadosProcesso();

        }

    /**
     * Método responsável por pegar os dados do Bundle da janela de Consulta
     */
    private void pegarDadosProcesso(){
            numCP = bundle.getInt("numCP");
            numProc = bundle.getString("numProc");
            data = bundle.getString("data");
            tipo = bundle.getString("tipo");
            departamento = bundle.getString("departamento");
            instituicao = bundle.getString("instituicao");
            requerente = bundle.getString("requerente");
            observacao = bundle.getString("observacao");
            nome = bundle.getString("nome");
            hora = bundle.getString("hora");
            atendente = bundle.getString("atendente");

        }

    /**
     * Método responsável por apresentar os dados do processo nos seus respectivos campos
     */
    private void apresentarDadosProcesso(){
        TextView textViewNumCP = (TextView) rootView.findViewById(R.id.TextViewNumCP);
            textViewNumCP.setText(numCP.toString());
        TextView textViewNumProc = (TextView) rootView.findViewById(R.id.TextViewNumProc);
            textViewNumProc.setText(numProc);
        TextView textViewData = (TextView) rootView.findViewById(R.id.TextViewData);
            textViewData.setText(data);
        TextView textViewTipo = (TextView) rootView.findViewById(R.id.TextViewTipo);
            textViewTipo.setText(tipo);
        TextView textViewDepartamento = (TextView) rootView.findViewById(R.id.TextViewDepartamento);
            textViewDepartamento.setText(departamento);
        TextView textViewInstituicao = (TextView) rootView.findViewById(R.id.TextViewInstituicao);
            textViewInstituicao.setText(instituicao);
        TextView textViewRequerente = (TextView) rootView.findViewById(R.id.TextViewRequerente);
            textViewRequerente.setText(requerente);
        TextView textViewObservacao = (TextView) rootView.findViewById(R.id.TextViewObservacao);
            textViewObservacao.setText(observacao);
        TextView textViewNome = (TextView) rootView.findViewById(R.id.TextViewNome);
            textViewNome.setText(nome);
        TextView textViewHora = (TextView) rootView.findViewById(R.id.TextViewHora);
            textViewHora.setText(hora);
        TextView textViewAtendente = (TextView) rootView.findViewById(R.id.TextViewAtendente);
            textViewAtendente.setText(atendente);

        }

}
