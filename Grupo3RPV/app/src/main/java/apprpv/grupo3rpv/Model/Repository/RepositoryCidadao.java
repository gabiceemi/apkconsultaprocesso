package apprpv.grupo3rpv.Model.Repository;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import apprpv.grupo3rpv.Model.Domain.Cidadao;

public class RepositoryCidadao {
    private static RepositoryCidadao instancia;

    /**
     * Método getInstance que garante que apenas uma instância desse repositório será usado na aplicação
     * @param context
     * @return uma instância da RepositoryCidadao
     */
    public static synchronized RepositoryCidadao getInstance(Context context) {
        if (instancia == null) {
            instancia = new RepositoryCidadao(context.getApplicationContext());
        }
        return instancia;
    }

    /**
     * Método construtor que recebe um context e instancia a DAOCidadao
     * @param context
     */
    private RepositoryCidadao(Context context) {
        Context context1 = context;
        HashMap<String, Cidadao> map = new HashMap<>();
    }

    /**
     * Método que retorna o requerente do processo
     * @param atributo
     * @return
     */
    public Cidadao retornaCidadaoRequerenteProcesso(String atributo) {
        try {
            Cidadao req = new Cidadao();
            req.setNome(atributo);
            req.setCpfCnpj(null);
            return req;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * método que retorna o interessado do processo
     * @param nome
     * @param documento
     * @return
     */
    public Cidadao retornaCidadaoInteressadoProcesso(String nome, String documento) {
        try {
            Cidadao tit = new Cidadao();
            tit.setNome(nome);
            tit.setCpfCnpj(documento);
            return tit;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
