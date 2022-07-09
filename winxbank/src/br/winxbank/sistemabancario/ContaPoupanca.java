package br.winxbank.sistemabancario;

import br.winxbank.geradordedocumentos.ArquivoInformeRendimento;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Dani.
 * Classe responsável por representar uma conta poupança
 */
public class ContaPoupanca extends Conta implements OperacoesAutomaticas{

    ArrayList<Movimentacao> informeRendimento = new ArrayList<>();
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
     * Método abstrato da classe conta sobrescrito responsável por realizar uma compra.
     * @param valor
     */
    @Override
    public void comprar(double valor) {
        Scanner sc = new Scanner(System.in);
        System.out.println("A conta sera debitada...");
            System.out.println("------------------------------------------------");
            System.out.println(this.cartao.getNumero() + "\n" + this.cartao.csv);
            System.out.println("------------------------------------------------");
            System.out.println("Este e o cartao que deseja utilizar? Digite 1 (confirmar)");
            int decisao2 = sc.nextInt();
            if(decisao2 == 1){
                cartao.debitar(this, valor);
                System.out.println("Valor debitado.");
            }
            else{
                System.out.println("Compra cancelada. Efetue a compra novamente.");
        }
    }

    public void setInformeRendimento(Movimentacao movimentacao){
        informeRendimento.add(movimentacao);
    }

    public ArrayList<Movimentacao> getInformeRendimento() {
        return informeRendimento;
    }

    /**
     * Método responsável por acrescentar rendimento sobre o saldo contido nesta conta.
     */
    public void acrescentarRendimento(){
        double rendimentoDesteCaso = this.saldo / rendimentoMensalPoupanca;
        Movimentacao movimentacao = new Movimentacao(rendimentoDesteCaso-this.saldo, Movimentacao.TipoDaMovimentacao.ENTRADA);
        this.setInformeRendimento(movimentacao);
        this.saldo += rendimentoDesteCaso;
        movimentacaoBancaria(rendimentoDesteCaso);

    }

    /**
     * Método responsável por escrever um arquivo de um informe de rendimento.
     */
    public void gerarInformeRendimento() throws FileNotFoundException {
        ArquivoInformeRendimento.getInstancia().gerarDocumento(this);
    }

    public ArrayList<Movimentacao> getInformeDeRendimento(){
        return this.informeRendimento;
    }

    /**
     * Método da interface MovimentacaoBancaria sobrescrito responsável por movimentar dinheiro ao banco.
     * @param valor
     */
    @Override
    public void movimentacaoBancaria(double valor) {
        Banco.getInstancia().setDespesas(valor);
    }

    public String getTipoDaConta() {
        String tipoDaConta = "Poupanca";
        return tipoDaConta;
    }
}
