package br.winxbank.sistemabancario;

/**
 * @author Dani.
 * Classe responsável por representar uma conta poupança
 */
public class ContaPoupanca extends Conta implements OperacoesAutomaticas{


    private final String tipoDaConta = "Poupanca";
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

    /**
     * Método responsável por acrescentar rendimento sobre o saldo contido nesta conta.
     */
    public void acrescentarRendimento(){
        this.saldo *= taxaJurus;
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
