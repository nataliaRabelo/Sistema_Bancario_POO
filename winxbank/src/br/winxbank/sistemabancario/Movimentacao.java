package br.winxbank.sistemabancario;

import br.winxbank.tempo.Ano;

import java.io.Serializable;

/**
 * @author Natália.
 * Classe responsável por representar uma entidade movimentacao bancária.
 */
public class Movimentacao implements Serializable {

    private String mesAtual;
    private String nomeDoCliente;
    private String cpf;
    private int numeroConta;
    private int numeroCartao;
    private double dinheiroMovimentado;
    private double rendimento;
    private double desconto;
    private TipoDaMovimentacao tipoDaMovimentacao;
    private enum TipoDaMovimentacao{
        ENTRADA,
        SAIDA;
    }

    /**
     * Construtor temporário para debug.
     */
    public Movimentacao(){

    }

    /**
     * Construtor padrão da classe Movimentacao
     *
     * @param nomeDoCliente
     * @param cpf
     * @param numeroConta
     * @param numeroCartao
     * @param dinheiroMovimentado
     * @param rendimento
     * @param desconto
     * @param tipoDaMovimentacao
     */
    public Movimentacao(String mesAtual, String nomeDoCliente, String cpf, int numeroConta, int numeroCartao, double dinheiroMovimentado, double rendimento, double desconto, TipoDaMovimentacao tipoDaMovimentacao){
        this.mesAtual = Ano.getInstancia().getMesAtual();
        this.nomeDoCliente = nomeDoCliente;
        this.cpf = cpf;
        this.numeroConta = numeroConta;
        this.numeroCartao = numeroCartao;
        this.dinheiroMovimentado = dinheiroMovimentado;
        this.rendimento = rendimento;
        this.desconto = desconto;
        this.tipoDaMovimentacao = tipoDaMovimentacao;

    }
    public String getNomeDoCliente() {
        return nomeDoCliente;
    }

    public String getCpf() {
        return cpf;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public int getNumeroCartao() {
        return numeroCartao;
    }

    public double getDinheiroMovimentado() {
        return dinheiroMovimentado;
    }

    public double getRendimento() {
        return rendimento;
    }

    public double getDesconto() {
        return desconto;
    }

    public TipoDaMovimentacao getTipoDaMovimentacao() {
        return tipoDaMovimentacao;
    }

}
