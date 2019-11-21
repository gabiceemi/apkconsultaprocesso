package apprpv.grupo3rpv.Model.Repository;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import apprpv.grupo3rpv.Conexao.ConexaoWebService;
import apprpv.grupo3rpv.Model.Domain.Andamento;
import apprpv.grupo3rpv.Model.Domain.Processo;

public class RepositoryProcesso {
    private static RepositoryProcesso instancia;

    private String xmlPrefeitura;
    private final RepositoryCidadao repositoryCidadao;
    private final RepositoryDepartamento repositoryDepartamento;
    private final RepositoryFuncionario repositoryFuncionario;
    private final RepositoryTipo repositoryTipo;
    private final ConexaoWebService webService;


    /**
     * Método getInstance que garante que apenas uma instância desse repositório será usado na aplicação
     * @param context
     * @return uma instância da RepositoryProcesso
     */
    public static synchronized RepositoryProcesso getInstance(Context context) {
        if (instancia == null) {
            instancia = new RepositoryProcesso(context.getApplicationContext());
        }
        return instancia;
    }

    /**
     * Método construtor que recebe um context e instancia as outras repositorys e as DAOs Processo e Andamento.
     * @param context
     */
    private RepositoryProcesso(Context context) {
        //Context context1 = context;
        this.repositoryCidadao = RepositoryCidadao.getInstance(context);
        this.repositoryDepartamento = RepositoryDepartamento.getInstance(context);
        this.repositoryFuncionario = RepositoryFuncionario.getInstance(context);
        this.repositoryTipo = RepositoryTipo.getInstance(context);
        this.webService = new ConexaoWebService(context);
    }

    /**
     * Método que vai chamar a consulta com o WebService
     * @param nProcesso
     * @param nDocumento
     */
    public Processo consultaWebService(String nProcesso, String nDocumento) throws Exception{
        this.xmlPrefeitura = this.webService.buscaProcessoWebService(nProcesso, nDocumento);
        return this.parseXML(xmlPrefeitura);
    }

    /**
     * Método que vai montar o processo
     * @return
     */
    public Processo parseXML(String resultadoBusca) throws Exception{

        ArrayList<Andamento> listaAndamentos = new ArrayList<>();

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(new StringReader(resultadoBusca.toString()));
        try {

            int contadorData = 0;
            int contadorDpto = 0;
            int contadorHora = 0;
            int contadorAndamento = 0;
            Processo p = new Processo();
            Andamento a;
            String dtAndamento = "";
            String dptoAndamento = "";
            String ocorrAndamento = "";
            String despachoAndamento = "";
            String titularProcesso = "";
            String docTitularProcesso = "";

            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {

                final int event = xpp.getEventType();

                if (event == XmlPullParser.START_TAG && xpp.getName().equals("numcodproc")) {
                    xpp.next();
                    if (xpp.getEventType() == XmlPullParser.TEXT) {
                        Log.e("Código do Processo ***", xpp.getText());
                        p.setNumProcesso(xpp.getText());
                    }
                } else if (event == XmlPullParser.START_TAG && xpp.getName().equals("numproc")) {
                    xpp.next();
                    if (xpp.getEventType() == XmlPullParser.TEXT) {
                        Log.e("Numero do Processo ***", xpp.getText());
                        p.setNumCtrlProcesso(Integer.parseInt(xpp.getText()));
                    }
                }  else if (event == XmlPullParser.START_TAG && xpp.getName().equals("dtproc")) {
                    xpp.next();
                    if (contadorData == 0) {
                        contadorData++;
                        Log.e("Data Processo ***", xpp.getText());
                        p.setData(xpp.getText());
                    } else {
                        Log.e("Data Andamento ___", xpp.getText());
                        dtAndamento = xpp.getText();
                    }
                } else if (event == XmlPullParser.START_TAG && xpp.getName().equals("tipoproc")) {
                    xpp.next();
                    if (xpp.getEventType() == XmlPullParser.TEXT) {
                        Log.e("Tipo Processo ***", xpp.getText());
                        p.setTipo(repositoryTipo.retornaTipoProcesso(xpp.getText()));
                    }
                } else if (event == XmlPullParser.START_TAG && xpp.getName().equals("departamento")) {
                    xpp.next();
                    if (xpp.getEventType() == XmlPullParser.TEXT) {
                        if (contadorDpto == 0) {
                            contadorDpto++;
                            Log.e("Departamento Proc ***", xpp.getText());
                            p.setDepartamento(repositoryDepartamento.retornaDepartamentoProcesso(xpp.getText()));
                        } else {
                            Log.e("Departamento Anda ___", xpp.getText());
                            dptoAndamento = xpp.getText();
                        }
                    }
                } else if (event == XmlPullParser.START_TAG && xpp.getName().equals("obs")) {
                    xpp.next();
                    if (xpp.getEventType() == XmlPullParser.TEXT) {
                        Log.e("observacao Processo ***", xpp.getText());
                        p.setObsProcesso(xpp.getText());
                    }
                } else if (event == XmlPullParser.START_TAG && xpp.getName().equals("titular")) {
                    xpp.next();
                    if (xpp.getEventType() == XmlPullParser.TEXT) {
                        Log.e("Titular Processo ***", xpp.getText());
                        titularProcesso = xpp.getText();
                    }
                } else if (event == XmlPullParser.START_TAG && xpp.getName().equals("cgccpf")) {
                    xpp.next();
                    if (xpp.getEventType() == XmlPullParser.TEXT) {
                        Log.e("DocTitular Processo ***", xpp.getText());
                        docTitularProcesso = xpp.getText();
                        p.setInteressado(repositoryCidadao.retornaCidadaoInteressadoProcesso(titularProcesso, docTitularProcesso));
                    }
                } else if (event == XmlPullParser.START_TAG && xpp.getName().equals("requente")) {
                    xpp.next();
                    if (xpp.getEventType() == XmlPullParser.TEXT) {
                        Log.e("Requerente Processo ***", xpp.getText());
                        p.setRequerente(repositoryCidadao.retornaCidadaoRequerenteProcesso(xpp.getText()));
                    }
                } else if (event == XmlPullParser.START_TAG && xpp.getName().equals("atendente")) {
                    xpp.next();
                    if (xpp.getEventType() == XmlPullParser.TEXT) {
                        Log.e("Atendente Processo ***", xpp.getText());
                        p.setAtendente(repositoryFuncionario.retornaFuncionarioProcesso(xpp.getText()));
                    }
                } else if (event == XmlPullParser.START_TAG && xpp.getName().equals("horaproc")) {
                    xpp.next();
                    if (contadorHora == 0) {
                        contadorHora++;
                        Log.e("Hora do Processo ***", xpp.getText());
                        p.setHora(xpp.getText());
                    }
                } else if (event == XmlPullParser.START_TAG && xpp.getName().equals("ocorrencia")) {
                    xpp.next();
                    Log.e("Ocorrencia Anda ___", xpp.getText());
                    ocorrAndamento = xpp.getText();
                } else if (event == XmlPullParser.START_TAG && xpp.getName().equals("despacho")) {
                    xpp.next();
                    contadorAndamento++;
                    if (xpp.getText() == null){
                        Log.e("Despacho Anda ___", "-----");
                        despachoAndamento = "-----";
                    } else {
                        Log.e("Despacho Anda ___", xpp.getText());
                        despachoAndamento = xpp.getText();
                    }
                    a = new Andamento();
                    a.setData(dtAndamento);
                    a.setDepto(dptoAndamento);
                    a.setOcorrencia(ocorrAndamento);
                    a.setDespacho(despachoAndamento);
                    listaAndamentos.add(a);
                } else {
                    xpp.next();
                }
            }

            p.setListAndamentos(listaAndamentos);
            listaAndamentos = new ArrayList<>();
            return p;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
