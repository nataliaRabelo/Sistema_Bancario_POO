package br.winxbank.sistemabancario;

import java.util.ArrayList;

/**
 * @author Dani
 * Esta classe é responsável por representar uma entidade ContaCorrente.
 */
public class ContaCorrente extends Conta implements OperacoesAutomaticas{

    private CartaoCredito cartaoCredito;

    /**
     * Construtor padrão da classe conta.
     *
     * @param saldo
     * @param cartaoDebito
     * @param dividaDeEmprestimo
     */
    public ContaCorrente(int numeroConta, double saldo, Cartao cartaoDebito, double dividaDeEmprestimo, CartaoCredito cartaoCredito) {
        super(numeroConta, saldo, cartaoDebito, dividaDeEmprestimo);
        this.cartaoCredito = cartaoCredito;
    }
    

    /**
     * Método responsável por pagar fatura com o saldo da conta.
     * @param valor
     */
    public void pagarFatura(double valor){
        this.saldo-=valor;
        this.cartaoCredito.setFatura(-valor);

    }

    /**
     * TODO: método de descontar taxa, pegar taxa de manutencao de conta da interface.
     */
    public void descontarTaxa(){
        this.saldo -= taxaManutencaoConta;
        movimentacaoBancaria(taxaManutencaoConta);
    }

    @Override
    public void movimentacaoBancaria(double valor) {
        Banco.getInstancia().setReceitas(valor);
    }


    public CartaoCredito getCartaoCredito() {
        return cartaoCredito;
    }

    public String getTipoDaConta() {
        String tipoDaConta = "Corrente";
        return tipoDaConta;
    }
}
