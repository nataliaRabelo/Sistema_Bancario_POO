package br.winxbank.sistemabancario;

import java.io.Serializable;
import java.util.Scanner;

/**
 * @author Natália
 * Esta classe é responsável por representar uma entidade abstrata Conta.
 */
public abstract class Conta {

    protected int numeroConta;
    protected double saldo;
    protected Cartao cartao;
    protected double dividaDeEmprestimo;
    private static final long serialVersionUID = 3L;
    //TODO: Informe de rendimento.

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

    public Conta(){

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
     * Método responsável por gerar um extrato.
     * TODO: fazer método responsável por gerar extrato.
     */
    public void gerarExtrato() {

    }

    /**
     * Método responsável por realizar uma transferência via pix a uma conta.
     */
    public void fazerPix(Conta conta) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o valor do pix:");
        double valor = sc.nextDouble();
        this.saldo -= valor;
        conta.saldo += valor;
    }

    /**
     * Método responsável por realizar uma compra.
     *
     * @param valor
     */
    public void comprar(double valor) {
        //TODO: fazer opções de crédito e débito
        this.saldo -= valor;
    }

    /**
     * Método responsável por sacar um valor da conta.
     * @param valor
     */
    public void sacar(double valor) {
        this.saldo -= valor;
        System.out.println("Você está sacando o valor de: " + valor);
    }

    /**
     * Método responsável por depositar um valor na conta
     */
    public void depositar(){
        System.out.println("Digite o valor que deseja depositar na sua conta:");
        Scanner sc = new Scanner(System.in);
        double valor = sc.nextDouble();
        setSaldo(valor);
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

    public void setSaldo(double valor) {
        if(valor-this.saldo < 0){
            this.saldo += saldo;
        }
    }
}
