package apprpv.grupo3rpv.Model.Domain;

import java.io.Serializable;

/**
 * Classe que define a estrutura e comportamento do um ANDAMENTO de um processo
 */
public class Andamento implements Serializable {
    private String data;
    private String depto;
    private String ocorrencia;
    private String despacho;

    public  Andamento(String ocorrencia, String despacho, String data, String depto) {
        this.data = data;
        this.depto = depto;
        this.ocorrencia = ocorrencia;
        this.despacho = despacho;
    }

    public Andamento() {

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    public String getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(String ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public String getDespacho() {
        return despacho;
    }

    public void setDespacho(String despacho) {
        this.despacho = despacho;
    }
}
