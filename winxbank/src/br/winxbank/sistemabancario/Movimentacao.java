package br.winxbank.sistemabancario;

import br.winxbank.tempo.Ano;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Natália.
 * Classe responsável por representar uma entidade movimentacao bancária.
 */
public class Movimentacao implements Serializable {

    @SerializedName("mesAtual")
    private String mesAtual;
    @SerializedName("dinheiroMovimentado")
    private double dinheiroMovimentado;
    @SerializedName("tipoDaMovimentacao")
    private TipoDaMovimentacao tipoDaMovimentacao;

    /**
     * Construtor para leitura de json
     * @param mesAtual
     * @param dinheiroMovimentado
     * @param tipoDaMovimentacao
     */
    public Movimentacao(String mesAtual, double dinheiroMovimentado, String tipoDaMovimentacao) {
        this.mesAtual = mesAtual;
        this.dinheiroMovimentado = dinheiroMovimentado;
        this.tipoDaMovimentacao = TipoDaMovimentacao.valueOf(tipoDaMovimentacao);
    }


    public enum TipoDaMovimentacao{
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
     * @param dinheiroMovimentado
     * @param tipoDaMovimentacao
     */
    public Movimentacao(double dinheiroMovimentado, TipoDaMovimentacao tipoDaMovimentacao){
        this.mesAtual = Ano.getInstancia().getMesAtual();
        this.dinheiroMovimentado = dinheiroMovimentado;
        this.tipoDaMovimentacao = tipoDaMovimentacao;

    }


    public double getDinheiroMovimentado() {
        return dinheiroMovimentado;
    }


    public TipoDaMovimentacao getTipoDaMovimentacao() {
        return tipoDaMovimentacao;
    }

    public String getMesAtual() {
        return mesAtual;
    }
}
