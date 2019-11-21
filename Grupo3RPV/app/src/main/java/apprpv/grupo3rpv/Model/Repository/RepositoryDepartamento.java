package apprpv.grupo3rpv.Model.Repository;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import apprpv.grupo3rpv.Model.Domain.Departamento;

public class RepositoryDepartamento {
    private static RepositoryDepartamento instancia;
    private final HashMap<String, Departamento> map;

    /**
     * Método getInstance que garante que apenas uma instância desse repositório será usado na aplicação
     * @param context
     * @return uma instância da RepositoryDepartamento
     */
    public static synchronized RepositoryDepartamento getInstance(Context context) {
        if (instancia == null) {
            instancia = new RepositoryDepartamento(context.getApplicationContext());
        }
        return instancia;
    }

    /**
     * Método construtor que recebe um context e instancia a DAODepartamento
     * @param context
     */
    private RepositoryDepartamento(Context context) {
        Context context1 = context;
        this.map = new HashMap<>();
    }

    public HashMap<String, Departamento> getMap() {
        return map;
    }

    /**
     * Método que retorna o departamento do processo
     * @param atributo
     * @return
     */
    public Departamento retornaDepartamentoProcesso (String atributo){
        try {
            Departamento departamento = new Departamento();
            departamento.setNome(atributo);
            return departamento;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
