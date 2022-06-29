package br.winxbank.sistemabancario;

import br.winxbank.repository.ArquivoExtrato;
import br.winxbank.repository.ArquivoInformeRendimento;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author Natália
 * Esta classe é responsável por representar uma entidade abstrata Conta.
 */
public abstract class Conta implements OperacoesAutomaticas{

    protected int numeroConta;
    protected double saldo;
    protected Cartao cartao;
    protected double dividaDeEmprestimo;
    ArrayList<Movimentacao> extrato = new ArrayList<>();

    /**
     * Construtor padrão da classe conta.
     *
     * @param saldo
     * @param cartao
     * @param dividaDeEmprestimo
     */
    public Conta(int numeroConta, double saldo, Cartao cartao, double dividaDeEmprestimo) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.cartao = cartao;
        this.dividaDeEmprestimo = dividaDeEmprestimo;
    }

    /**
     * Construtor alternativo para leitura de arquivo json.
     * @param numeroConta
     * @param saldo
     * @param cartao
     * @param dividaDeEmprestimo
     * @param movimentacoes
     */
    public Conta(int numeroConta, double saldo, Cartao cartao, double dividaDeEmprestimo, ArrayList<Movimentacao> movimentacoes) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.cartao = cartao;
        this.dividaDeEmprestimo = dividaDeEmprestimo;
        this.extrato.addAll(movimentacoes);
    }


    /**
     * Método responsável por substrair o valor da dívida de empréstimo.
     *
     * @param valor
     */
    public void pagarParcelaDeEmprestimo(double valor) {
        this.dividaDeEmprestimo -= valor;
    }

    /**
     * Método responsável por somar um valor à dívida de empréstimo.
     *
     * @param valor
     */
    public void requisitarEmprestimo(double valor) {
        this.dividaDeEmprestimo += valor;
    }

    /**
     * Método responsável por cobrar jurus de um emprestimo conforme meses passados.
     */
    public void cobrarJurusEmprestimo(){
        if(this.dividaDeEmprestimo > 0){
            double resultado = dividaDeEmprestimo / taxaJurus;
            this.dividaDeEmprestimo -= resultado;
        }
    }

    /**
     * Método responsável por gerar um extrato.
     */
    public void gerarExtrato() throws FileNotFoundException {
        ArquivoExtrato.getInstancia().gerarDocumento(this);
    }

    /**
     * Método responsável por realizar uma transferência via pix a uma conta.
     */
    public void fazerPix(Conta conta, double valor) {
        conta.saldo+=valor;
    }

    /**
     * Método responsável por realizar uma compra.
     *
     * @param valor
     */
    public abstract void comprar(double valor);

    /**
     * Método responsável por sacar um valor da conta.
     * @param valor
     */
    public void sacar(double valor) {
        this.saldo -= valor;
        System.out.println("Você está sacando o valor de: " + valor);
    }

    /**
     * Método responsável por depositar um valor na conta.
     * @return valor.
     */
    public double depositar(double valor){
        setSaldo(valor);
        return valor;
    }


    public double getSaldo() {
        return saldo;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public double getDividaDeEmprestimo() {
        return dividaDeEmprestimo;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public ArrayList<Movimentacao> getExtrato() {
        return extrato;
    }

    public void setSaldo(double valor) {
            this.saldo += valor;
    }

    public void setExtrato(Movimentacao movimentacao){
        this.extrato.add(movimentacao);
    }

}
