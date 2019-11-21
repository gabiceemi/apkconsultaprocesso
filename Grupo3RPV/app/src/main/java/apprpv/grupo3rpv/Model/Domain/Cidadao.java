package apprpv.grupo3rpv.Model.Domain;

/**
 * Classe que define a estrutura e comportamento de um CIDADAO
 */
public class Cidadao {
    private String nome;
    private String cpfCnpj;
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    public Cidadao() {

    }

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean validarCPF(String cpf) {
        if ((cpf == null) || (cpf.length() != 11)) {
            return false;
        } else {
            try {
                Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
                Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
                return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
            }
            catch (NumberFormatException e){
                return false;
            }

        }
    }

    public static boolean validarCNPJ(String cnpj) {
        if ((cnpj==null)||(cnpj.length()!=14)){
            return false;
        }
        else {
            try{
                Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
                Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
                return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
            }
            catch (NumberFormatException e){
                return false;
            }
        }
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Cidadao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
