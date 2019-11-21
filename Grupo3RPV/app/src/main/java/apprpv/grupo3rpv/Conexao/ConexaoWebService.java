package apprpv.grupo3rpv.Conexao;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Classe responsável da comunicação com o WebService
 */
public class ConexaoWebService {

    public ConexaoWebService(Context context) {

    }

    /**
     * Método responsável por verificar se o processo existe
     * @param nProcesso numero do processo buscado
     * @param nDocumento numero do cpf ou cnpj pertencente ao processo buscado
     * @return os dados do processo
     */
    public String buscaProcessoWebService(String nProcesso, String nDocumento) throws Exception{
        URL url;
        HttpURLConnection connection = null;
        String linha = "";
        int resposta;
        StringBuilder resul = new StringBuilder();

        try {
            url = new URL("http://sistemas.alegrete.rs.gov.br:63558/ws/processows.php?cgccpf="+nDocumento+"&codproc="+nProcesso);
            Log.e("URL = ", url.toString());
            connection = (HttpURLConnection) url.openConnection();
            resposta = connection.getResponseCode();
            resul.append(linha);
            if (resposta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((linha = reader.readLine()) != null) {
                    resul.append(linha).append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }


        Log.e("XML PROCESSO = ", resul.toString());
        return resul.toString();

    }
}
