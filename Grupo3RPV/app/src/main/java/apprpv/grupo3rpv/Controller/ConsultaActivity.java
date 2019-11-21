package apprpv.grupo3rpv.Controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import java.io.Serializable;

import apprpv.grupo3rpv.Model.Domain.Cidadao;
import apprpv.grupo3rpv.Model.Domain.Processo;
import apprpv.grupo3rpv.Model.Repository.RepositoryProcesso;
import apprpv.grupo3rpv.R;

/**
 * Classe que controla os eventos na tela de busca de um processo
 */
public class ConsultaActivity extends AppCompatActivity {
    private EditText editTextCPFouCNPJ, editTextNumProcesso;
    private RepositoryProcesso repProcesso;
    private CheckBox salvarDados;
    private String UNUMPROC, UCPFOUCNPJ;
    private static final String PREF_NAME = "preferences", PREF_UNAME = "NumContProc", PREF_CPFOUCNPJ = "CpfOuCnpj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        TextInputLayout campoCPFouCNPJ = (TextInputLayout) findViewById(R.id.textInputLayoutCPFouCNPJ);
        TextInputLayout campoNumProcesso = (TextInputLayout) findViewById(R.id.textInputLayoutNumProcesso);
        editTextCPFouCNPJ = campoCPFouCNPJ.getEditText();
        editTextNumProcesso = campoNumProcesso.getEditText();
        salvarDados = (CheckBox) findViewById(R.id.salvarLogin);
        salvarDados.setChecked(true);
        repProcesso = RepositoryProcesso.getInstance(this);
    }

    /**
     * Método para validar os campos digitados e chamar o método que irá acessar o WebService
     * @param view
     */
    public void buscarProcesso(View view) {
        String numContDoProcessoText = editTextNumProcesso.getText().toString();
        String cpfCnpjText = editTextCPFouCNPJ.getText().toString();

        if (numContDoProcessoText.isEmpty() && cpfCnpjText.isEmpty()) {
            editTextNumProcesso.setError("Campo obrigatório!");
            editTextCPFouCNPJ.setError("Campo obrigatório!");
        } else if (numContDoProcessoText.isEmpty()) {
            editTextNumProcesso.setError("Campo obrigatório!");
        } else if (cpfCnpjText.isEmpty()) {
            editTextCPFouCNPJ.setError("Campo obrigatório!");
        } else if (editTextCPFouCNPJ.getText().length() == 11 && !Cidadao.validarCPF(editTextCPFouCNPJ.getText().toString())) {
            editTextCPFouCNPJ.setError("O CPF é inválido!");
        } else if (editTextCPFouCNPJ.getText().length() == 14 && !Cidadao.validarCNPJ(editTextCPFouCNPJ.getText().toString())) {
            editTextCPFouCNPJ.setError("O CNPJ é inválido!");
        } else if (editTextCPFouCNPJ.getText().length() != 11 && editTextCPFouCNPJ.getText().length() != 14){
            System.out.println(editTextCPFouCNPJ.getText().length());
            editTextCPFouCNPJ.setError("O número informado não é válido para CPF ou CNPJ!");
        } else{
            try {
                if (verificaConexao()){
                    buscaProcessoAsyncTask processoBusca = new buscaProcessoAsyncTask(this);
                    processoBusca.execute();
                } else {
                    AlertDialog.Builder dlg2 = new AlertDialog.Builder(this);
                    dlg2.setTitle("ATENÇÃO");
                    dlg2.setIcon(R.drawable.ic_info);
                    dlg2.setMessage("Verifique sua conexão com a internet!");
                    dlg2.setPositiveButton("Fechar", null);
                    dlg2.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método que realiza a busca no web service em paralelo a aplicação
     */
    private void consultarProcesso() throws Exception {
        String numControleProcesso = editTextNumProcesso.getText().toString();
        String cpfOuCnpj = editTextCPFouCNPJ.getText().toString();

        Log.e("NUM_PROC", numControleProcesso);
        Log.e("DOC_PROC", cpfOuCnpj);
        System.out.println(Cidadao.validarCNPJ(cpfOuCnpj));

        Processo processo = repProcesso.consultaWebService(numControleProcesso, cpfOuCnpj);;

        salvarDados();
        Intent i = new Intent(this, TabsActivity.class);
        enviaDadosParaVisualizarProcesso(processo, i);
        startActivity(i);
    }

    /**
     * Método responsável por verificar se o celular está conectado na internet
     * @return status de conexão
     */
    public  boolean verificaConexao() {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }

    /**
     * Método que envia os dados do processo para a visualização na tela de detalhes
     * @param processo que foi buscado no banco
     * @param i
     */
    private void enviaDadosParaVisualizarProcesso(Processo processo, Intent i) {
        Bundle bundle = new Bundle();
        bundle.putInt("numCP", processo.getNumCtrlProcesso());
        bundle.putString("numProc", processo.getNumProcesso());
        bundle.putString("requerente", processo.getRequerente().getNome());
        bundle.putString("data", processo.getData());
        bundle.putString("hora", processo.getHora());
        bundle.putString("atendente", processo.getAtendente().getNome());
        bundle.putString("tipo", processo.getTipo().getNome());
        bundle.putString("departamento", processo.getDepartamento().getNome());
        bundle.putString("instituicao", processo.getInstituicao());
        bundle.putString("nome", processo.getInteressado().getNome());
        bundle.putString("observacao", processo.getObsProcesso());
        bundle.putSerializable("lista", (Serializable) processo.getListAndamentos());
        i.putExtras(bundle);
    }

    /**
     * Método para criar o arquivo com as preferências do usuário e salvar os dados que serão lembrados
     */
    private void salvarDados() {
        SharedPreferences settings = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        if (salvarDados.isChecked()) {
            UNUMPROC = editTextNumProcesso.getText().toString();
            UCPFOUCNPJ = editTextCPFouCNPJ.getText().toString();
            editor.putString(PREF_UNAME, UNUMPROC);
            editor.putString(PREF_CPFOUCNPJ, UCPFOUCNPJ);
            editor.commit();
        } else {
            editor.putString(PREF_UNAME, "");
            editor.putString(PREF_CPFOUCNPJ, "");
            editor.commit();
        }
    }

    /**
     * Método que carrega as preferências caso o usuário já tiver usando o método para salvar as preferências
     */
    private void carregarDados() {
        SharedPreferences settings = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String defaultUNUMPROC = "";
        UNUMPROC = settings.getString(PREF_UNAME, defaultUNUMPROC);
        String defaultUCPFOUCNPJ = "";
        UCPFOUCNPJ = settings.getString(PREF_CPFOUCNPJ, defaultUCPFOUCNPJ);
        editTextNumProcesso.setText(UNUMPROC);
        editTextCPFouCNPJ.setText(UCPFOUCNPJ);
        if (UCPFOUCNPJ != defaultUCPFOUCNPJ) {
            salvarDados.setChecked(true);
        }
    }

    /**
     * Salva as preferências quando a aplicação for "congelada"
     */
    @Override
    public void onPause() {
        super.onPause();
        salvarDados();
    }

    /**
     * Carrega as preferências continuando de onde parou a aplicação
     */
    @Override
    public void onResume() {
        super.onResume();
        carregarDados();
    }

    /**
     * Exibe as informações do grupo de desenvolvedores da aplicação
     * @param view
     */
    public void informacoes(View view){
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("Sobre o aplicativo");
        dlg.setMessage("Versão do app: 0.3 \n \n Equipe de desenvolvimento: \n Dienefer Fialho \n Gabriela Medeiros \n Jonnathan Riquelmo \n \n UNIPAMPA - Campus Alegrete");
        dlg.setPositiveButton("Fechar", null);
        dlg.show();
    }

    /**
     * Classe interna responsável por criar uma execução paralela da interface
     */
    class buscaProcessoAsyncTask extends android.os.AsyncTask<Void, Void, Boolean> {
        private ProgressDialog dlg;
        private AlertDialog.Builder dlg2;
        private Context context;

        public buscaProcessoAsyncTask(Context mContext) {
            this.context = mContext;
        }

        /**
         * Método que é chamado após o resultado do método doInBackground
         * @param result
         */
        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                this.dlg.dismiss();
            } else {
                this.dlg.dismiss();
                dlg2 = new AlertDialog.Builder(context);
                dlg2.setTitle("AVISO");
                dlg2.setMessage("Não foi encontrado nenhum processo com os dados fornecidos na busca...");
                dlg2.setIcon(R.drawable.ic_info);
                dlg2.setPositiveButton("Fechar", null);
                dlg2.show();
            }
        }

        /**
         * Método responsável para apresentar um feedback ao usuário durante a execução em segundo plano da tarefa de busca
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dlg = new ProgressDialog(ConsultaActivity.this);
            this.dlg.setCancelable(true);
            this.dlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            this.dlg.setIcon(R.drawable.brasao_alegrete);
            this.dlg.setMessage("Buscando processo...");
            this.dlg.show();
        }

        /**
         * Método responsável pela tarefa em segundo plano de buscar o processo no Web Service
         * @param params
         * @return
         */
        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                consultarProcesso();
                this.dlg.dismiss();
                return true;
            } catch (Exception e) {
                this.dlg.dismiss();
                e.printStackTrace();
                return false;
            }
        }
    }
}