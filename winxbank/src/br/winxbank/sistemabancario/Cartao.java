package br.winxbank.sistemabancario;

/**
 * @author Carol
 * Esta classe é responsável por representar uma entidade Cartao.
 */
public class Cartao{
    protected int numero;
    protected int csv;

    /**
     * Construtor padrão do cartão
     * @param numero
     * @param csv
     */
    public Cartao(int numero, int csv){
        this.numero = numero;
        this.csv = csv;
    }

    /**
     * Método responsável por debitar uma conta.
     * @param conta
     * @param valor
     */
    public void debitar(Conta conta, double valor){
        conta.setSaldo(-valor);
    }

    public int getNumero() {
        return numero;
    }

    public int getCsv() {
        return csv;
    }
}
