package apprpv.grupo3rpv.Model.Repository;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import apprpv.grupo3rpv.Model.Domain.Tipo;

public class RepositoryTipo {
    private static RepositoryTipo instancia;

    /**
     * Método getInstance que garante que apenas uma instância desse repositório será usado na aplicação
     * @param context
     * @return uma instância da RepositoryTipo
     */
    public static synchronized RepositoryTipo getInstance(Context context) {
        if (instancia == null) {
            instancia = new RepositoryTipo(context.getApplicationContext());
        }
        return instancia;
    }

    /**
     * Método construtor que recebe um context e instancia a DAOTipo
     * @param context
     */
    private RepositoryTipo(Context context) {
        Context context1 = context;
        HashMap<String, Tipo> map = new HashMap<>();
    }

    /**
     * Método que retorna o tipo do processo
     * @param atributo
     * @return
     */
    public Tipo retornaTipoProcesso (String atributo){
        try {
            Tipo tipo = new Tipo();
            tipo.setNome(atributo);
            return tipo;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
