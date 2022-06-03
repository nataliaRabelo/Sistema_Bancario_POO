package br.winxbank.sistemabancario;

import br.winxbank.sistemaclientes.Cliente;

/**
 * @author Natália
 * Esta classe é responsável por representar uma entidade abstrata Conta.
 */
public abstract class Conta {

    protected int numeroConta;
    protected double saldo;
    protected Cartao cartao;
    protected double dividaDeEmprestimo;
    //TODO: Informe de rendimento.

    /**
     * Construtor padrão da classe conta.
     * @param saldo
     * @param cartao
     * @param dividaDeEmprestimo
     */
    public Conta(double saldo, Cartao cartao, double dividaDeEmprestimo){
        this.saldo = saldo;
        this.cartao = cartao;
        this.dividaDeEmprestimo = dividaDeEmprestimo;
    }

    /**
     * Método responsável por substrair o valor da dívida de empréstimo.
     * @param valor
     */
    public void pagarParcelaDeEmprestimo(double valor){
        this.dividaDeEmprestimo-= valor;
    }

    /**
     * Método responsável por somar um valor à dívida de empréstimo.
     * @param valor
     */
    public void requisitarEmprestimo(double valor){
        this.dividaDeEmprestimo+= valor;
    }

    /**
     * Método responsável por gerar um extrato.
     * TODO: fazer método responsável por gerar extrato.
     */
    public void gerarExtrato(){

    }

    /**
     * Método responsável por realizar uma transferência via pix a uma conta.
     */
    public void fazerPix(Conta conta, double valor){
        this.saldo-= valor;
        conta.saldo+=valor;
    }

    /**
     * Método responsável por realizar uma compra.
     * @param valor
     */
    public void comprar(double valor){
        this.saldo-= valor;
    }

    /**
     * Método responsável por selecionar uma conta.
     * @param cliente
     * @param numeroConta
     * @return
     */
    public Conta selecionarConta(Cliente cliente, int numeroConta){
        for(Conta conta : cliente.getContas()){
            if(conta.numeroConta == numeroConta){
                return conta;
            }
        }
        return null;
    }

    /**
     * Método responsável por sacar um valor da conta.
     * @param valor
     */
    public void sacar(double valor){
        this.saldo-=valor;
        System.out.println("Você está sacando o valor de: " + valor);
    }

    public void debitar(double valor){
        if(valor <= this.saldo){
            this.saldo += valor;
        }
    }

    public double getSaldo() {
        return saldo;
    }

}
