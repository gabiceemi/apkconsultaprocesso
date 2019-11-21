package apprpv.grupo3rpv.Model.Domain;

import java.util.List;

/**
 * Classe que define a estrutura e comportamento de um PROCESSO
 */
public class Processo {
    private int numCtrlProcesso;
    private String numProcesso;
    private String data;
    private String hora;
    private String obsProcesso;

    private Tipo tipo;
    private Departamento departamento;
    private Cidadao requerente;
    private Cidadao interessado;
    private Funcionario atendente;
    private List<Andamento> listAndamentos;
    private static final String instituicao = "Prefeitura Municipal do Alegrete";

    public Processo() {

    }

    public int getNumCtrlProcesso() {
        return numCtrlProcesso;
    }

    public void setNumCtrlProcesso(int numCtrlProcesso) { this.numCtrlProcesso = numCtrlProcesso; }

    public String getNumProcesso() {
        return numProcesso;
    }

    public void setNumProcesso(String numProcesso) {
        this.numProcesso = numProcesso;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Cidadao getRequerente() {
        return requerente;
    }

    public void setRequerente(Cidadao requerente) {
        this.requerente = requerente;
    }

    public String getObsProcesso() {
        return obsProcesso;
    }

    public void setObsProcesso(String obsProcesso) {
        this.obsProcesso = obsProcesso;
    }

    public Cidadao getInteressado() {
        return interessado;
    }

    public void setInteressado(Cidadao interessado) {
        this.interessado = interessado;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Funcionario getAtendente() {
        return atendente;
    }

    public void setAtendente(Funcionario atendente) {
        this.atendente = atendente;
    }

    public List<Andamento> getListAndamentos() {
        return listAndamentos;
    }

    public void setListAndamentos(List<Andamento> listAndamentos) {
        this.listAndamentos = listAndamentos;
    }

    public String getInstituicao() { return instituicao; }
}
