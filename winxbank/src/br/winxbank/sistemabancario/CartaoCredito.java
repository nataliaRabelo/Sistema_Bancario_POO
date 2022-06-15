package br.winxbank.sistemabancario;

import br.winxbank.tempo.Ano;

import java.io.Serializable;

/**
 * @author Carol
 * Esta classe é responsável por representar uma entidade CartaoCredito.
 */
public class CartaoCredito extends Cartao implements OperacoesAutomaticas, Serializable {

    private double fatura;
    private String mesDaFatura;
    private int indexMesDaFatura;
    private boolean faturaPaga;
    private double limite;
    private Conta conta;
    private static final long serialVersionUID = 2L;

    /**
     * Construtor padrão do cartão de crédito
     * @param numero
     * @param csv
     */
    public CartaoCredito(int numero, int csv) {
        super(numero, csv);
    }

    /**
     * Método responsável por creditar um valor da fatura do cartão de crédito.
     * Quando esse método for chamado, é atribuído um mês referente àquela fatura.
     * @param valor
     */
    public void creditar(double valor){
        this.indexMesDaFatura = Ano.getInstancia().getIndexMesAtual();
        this.mesDaFatura = Ano.getInstancia().getMesAtual();
        setFatura(valor);
    }

    /**
     * TODO: AJUSTAR LIMITE
     * Método responsável por ajustar o limite do cartão
     */
    public void ajustarLimite(){
        System.out.println("Digite o valor do limite do seu cartão que deseja ajustar: ");

    }

    /**
     * Setter com regra de negócio de que se o valor setado for menor ou igual ao limite, permitir modificação.
     * Além disso, há mudança de status de fatura paga dependendo do valor setado no momento neste setter.
     * @param valor
     */
    public void setFatura(double valor) {
        if(valor <= this.limite){
            this.fatura += valor;
        }
        if (this.fatura == 0){
            this.faturaPaga = true;
        }
        else if(this.fatura > 0){
            this.faturaPaga = false;
        }

    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public double getFatura() {
        return fatura;
    }

    /**
     * Método responsável por cobrar jurus de uma fatura conforme meses passados.
     */
    public void cobrarJurus(){
        if (this.faturaPaga == false && Ano.getInstancia().getIndexMesAtual() > this.indexMesDaFatura){
            double faturaAnterior = this.fatura;
            this.fatura *= taxaJurus;
            movimentacaoBancaria(this.fatura - faturaAnterior);
        }

    }

    /**
     * Método responsável por gerar receita ao banco do valor pago a mais da cobrança de jurus em cima de uma fatura.
     * @param valor
     */
    public void movimentacaoBancaria(double valor) {
        Banco.getInstancia().setReceitas(valor);
    }

}
