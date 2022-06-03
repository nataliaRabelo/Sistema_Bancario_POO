package br.winxbank.sistemabancario;

/**
 * @author Dani
 * Esta classe é responsável por representar uma entidade ContaCorrente.
 */
public class ContaCorrente extends Conta{

    private CartaoCredito cartaoCredito;

    /**
     * Construtor padrão da classe conta.
     *
     * @param saldo
     * @param cartaoDebito
     * @param dividaDeEmprestimo
     */
    public ContaCorrente(double saldo, Cartao cartaoDebito, double dividaDeEmprestimo, CartaoCredito cartaoCredito) {
        super(saldo, cartaoDebito, dividaDeEmprestimo);
        this.cartaoCredito = cartaoCredito;
    }

    /**
     * Método responsável por pagar fatura com o saldo da conta.
     * @param valor
     */
    public void pagarFatura(double valor){
        this.saldo-=valor;
        this.cartaoCredito.setFatura(valor);

    }

    /**
     * Método responsável por creditar o valor da fatura do cartão de crédito.
     * @param valor
     */
    public void creditar(double valor){
        this.cartaoCredito.setFatura(valor);
    }

    /**
     * TODO: método de descontar taxa.
     */
    public void descontarTaxa(){

    }
}
