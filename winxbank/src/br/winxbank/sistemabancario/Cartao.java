package br.winxbank.sistemabancario;

import java.io.Serializable;

/**
 * @author Carol
 * Esta classe é responsável por representar uma entidade Cartao.
 */
public class Cartao implements Serializable {

    protected int numero;
    protected int csv;
    protected Conta conta;
    private static final long serialVersionUID = 1L;

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

    public int getNumero() {
        return numero;
    }

    public int getCsv() {
        return csv;
    }
}
