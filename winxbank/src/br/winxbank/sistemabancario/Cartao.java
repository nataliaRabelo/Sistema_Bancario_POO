package br.winxbank.sistemabancario;

/**
 * @author Carol
 * Esta classe é responsável por representar uma entidade Cartao.
 */
public class Cartao {

    protected int numero;
    protected int csv;
    protected Conta conta;

    /**
     * Construtor padrão do cartão
     * @param numero
     * @param csv
     */
    public Cartao(int numero, int csv){
        this.numero = numero;
        this.csv = csv;
    }

    public void debitarConta(double valor){
        conta.setSaldo(valor);
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
