package apprpv.grupo3rpv.Model.Repository;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import apprpv.grupo3rpv.Model.Domain.Funcionario;

public class RepositoryFuncionario {
    private static RepositoryFuncionario instancia;

    /**
     * Método getInstance que garante que apenas uma instância desse repositório será usado na aplicação
     * @param context
     * @return uma instância da RepositoryFuncionario
     */
    public static synchronized RepositoryFuncionario getInstance(Context context) {
        if (instancia == null) {
            instancia = new RepositoryFuncionario(context.getApplicationContext());
        }
        return instancia;
    }

    /**
     * Método construtor que recebe um context e instancia a DAOFuncionario
     * @param context
     */
    private RepositoryFuncionario(Context context) {
        Context context1 = context;
        HashMap<String, Funcionario> map = new HashMap<>();
    }

    /**
     * Método que retorna o funcionário do processo
     * @param atributo
     * @return
     */
    public Funcionario retornaFuncionarioProcesso (String atributo){
        try {
            Funcionario funcionario = new Funcionario();
            funcionario.setNome(atributo);
            return funcionario;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
