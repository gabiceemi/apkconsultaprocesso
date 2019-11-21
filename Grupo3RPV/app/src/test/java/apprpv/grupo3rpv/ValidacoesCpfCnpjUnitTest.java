package apprpv.grupo3rpv;

import org.junit.*;

import apprpv.grupo3rpv.Model.Domain.Cidadao;

import static org.junit.Assert.*;

/**
 * Created by Dienefer Fialho on 30/03/2017.
 *
 * Classe que testa os métodos de validar cpf e cnpj
 */
public class ValidacoesCpfCnpjUnitTest {
    private Cidadao cidadao;

    @Before
    public void iniciaObjValidacoes(){

        cidadao = new Cidadao();
    }
    @After
    public void terminaObjValidacoes(){
        cidadao = null;
    }
    @Test
    public void cpf12digitosTest() {
        assertFalse("CPF 1 digito maior que 11", cidadao.validarCPF("123456789012"));
    }
    @Test
    public void cpf10digitosTest(){
        assertFalse("CPF 1 digito menor que 11 ", cidadao.validarCPF("1234567890"));
    }
    @Test
    public void cpfValidoTest(){

        assertTrue("CPF valido", cidadao.validarCPF("02834768005"));
    }
    @Test
    public void cpfInvalidoTest(){
        assertFalse("11 digitos porem invalido", cidadao.validarCPF("12345678901"));
    }
    @Test
    public void cnpj15digitosTest(){
        assertFalse("CNPJ 1 digito maior que 14", cidadao.validarCNPJ("123456789012345"));
    }
    @Test
    public void cnpj13digitosTest(){
        assertFalse("CNPJ 1 digito menor que 14", cidadao.validarCNPJ("1234567890123"));
    }
    @Test
    public void cnpjValidoTest(){
        assertTrue("CNPJ valido", cidadao.validarCNPJ("24682557000118"));
    }
    @Test
    public void cnpjInvalidoTest(){
        assertFalse("CNPJ 14 digitos porem invalido", cidadao.validarCNPJ("12345678901234"));
    }
    @Test
    public void cnpjValidoNoMetodoValidarCPFTest(){
        assertFalse("Testar se o metodo de validar CPF aceita um CNPJ valido", cidadao.validarCPF("24682557000118"));
    }
    @Test
    public void cpfValidoNoMetodoValidarCNPJTest(){
        assertFalse("Testar se o metodo de validar CNPJ aceita um CPF valido", cidadao.validarCNPJ("02834768005"));
    }
    @Test
    public void cpfLetrasTest(){
        assertFalse("Testar se o metodo validar CPF aceita letras", cidadao.validarCPF("abcdefghijk"));
    }
    @Test
    public void cnpjLetrasTest(){
        assertFalse("Testar se o metodo validar CNPJ aceita letras", cidadao.validarCNPJ("abcdefghijklmn"));
    }
    @Test
    public void cpfLetrasNumerosTest(){
        assertFalse("Testar se o metodo validar CPF aceita letras e numeros", cidadao.validarCPF("123456abcde"));
    }
    @Test
    public void cnpjLetrasNumerosTest(){
        assertFalse("Testar se o metodo validar CNPJ aceita letras e numeros", cidadao.validarCNPJ("1234567abcdefg"));
    }
    @Test
    public void cpfCaracterEspecialTest(){
        assertFalse("Testar se o metodo validar CPF aceita caracteres especiais", cidadao.validarCPF("!!!!!!!!!!!"));
    }
    @Test
    public void cnpjCaracterEspecialTest(){
        assertFalse("Testar se o metodo validar CNPJ aceita caracteres especiais", cidadao.validarCNPJ("!!!!!!!!!!!!!!"));
    }
    @Test
    public void cpfCaracteresNumerosTest(){
        assertFalse("Testar se o metodo validar CPF aceita caracteres e numeros", cidadao.validarCPF("!!!!!123456"));
    }
    @Test
    public void cnpjCaracteresNumerosTest(){
        assertFalse("Testar se o metodo validar CNPJ aceita caracteres e numeros", cidadao.validarCNPJ("!!!!!!!1234567"));
    }
    @Test
    public void cpfCaracteresLetrasTest(){
        assertFalse("Testar se o metodo validar CPF aceita caracteres e letras", cidadao.validarCPF("!!!!!abcdef"));
    }
    @Test
    public void cnpjCaracteresLetrasTest(){
        assertFalse("Testar se o metodo validar CNPJ aceita caracteres e letras", cidadao.validarCNPJ("!!!!!!!abcdefg"));
    }
    @Test
    public void cpfCaracteresLetrasNumerosTest(){
        assertFalse("Testar se o metodo validar CPF aceita caracteres, letras e numeros", cidadao.validarCPF("!!!!abcd123"));
    }
    @Test
    public void cnpjCaracteresLetrasNumerosTest(){
        assertFalse("Testar se o metodo validar CNPJ aceita caracteres, letras e numeros", cidadao.validarCNPJ("!!!!abcde12345"));
    }
}