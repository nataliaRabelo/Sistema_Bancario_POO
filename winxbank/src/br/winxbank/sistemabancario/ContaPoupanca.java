package br.winxbank.sistemabancario;

import java.io.Serializable;

/**
 * @author Dani.
 * Classe responsável por representar uma conta poupança
 */
public class ContaPoupanca extends Conta implements OperacoesAutomaticas, Serializable {


    private final String tipoDaConta = "Poupanca";
    private static final long serialVersionUID = 5L;
    /**
     * Construtor padrão da classe conta.
     *
     * @param saldo
     * @param cartao
     * @param dividaDeEmprestimo
     */
    public ContaPoupanca(int numeroConta, double saldo, Cartao cartao, double dividaDeEmprestimo) {
        super(numeroConta, saldo, cartao, dividaDeEmprestimo);
    }

    public ContaPoupanca(){

    }

    /**
     * Método responsável por acrescentar rendimento sobre o saldo contido nesta conta.
     */
    public void acrescentarRendimento(){
        this.saldo /= rendimentoMensalPoupanca;
        double rendimentoDesteCaso = this.saldo * rendimentoMensalPoupanca;
        movimentacaoBancaria(rendimentoDesteCaso);

    }

    public String getTipoDaConta() {
        return tipoDaConta;
    }

    @Override
    public void movimentacaoBancaria(double valor) {
        Banco.getInstancia().setDespesas(valor);
    }
}
