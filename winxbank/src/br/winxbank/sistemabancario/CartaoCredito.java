package br.winxbank.sistemabancario;

import br.winxbank.tempo.Ano;

import java.util.Scanner;

/**
 * @author Carol
 * Esta classe é responsável por representar uma entidade CartaoCredito.
 */
public class CartaoCredito extends Cartao implements OperacoesAutomaticas{

    private double fatura;
    private String mesDaFatura;
    private int indexMesDaFatura;
    private boolean faturaPaga;
    private double limite;

    /**
     * Construtor padrão do cartão de crédito
     * @param numero
     * @param csv
     */
    public CartaoCredito(int numero, int csv) {
        super(numero, csv);
        this.limite = 1000; //as contas correntes iniciam com esse limite quando criadas pela primeira vez
    }


    /**
     * Construtor alternativo para leitura de json.
     * @param fatura
     * @param indexMesDaFatura
     * @param faturaPaga
     * @param limite
     * @param numero
     * @param csv
     */
    public CartaoCredito(double fatura, int indexMesDaFatura, boolean faturaPaga, double limite, int numero, int csv) {
        super(numero, csv);
        this.fatura = fatura;
        this.indexMesDaFatura = indexMesDaFatura;
        this.faturaPaga = faturaPaga;
        this.limite = limite;

    }

    /**
     * Método responsável por creditar um valor da fatura do cartão de crédito.
     * Quando esse método for chamado, é atribuído um mês referente àquela fatura.
     * @param valor
     */
    public void creditar(double valor){
        setFatura(valor);
        this.indexMesDaFatura = Ano.getInstancia().getIndexMesAtual();
        this.mesDaFatura = Ano.getInstancia().getMesAtual();
    }

    /**
     * Método responsável por ajustar o limite do cartão
     */
    public void ajustarLimite(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o valor do limite do seu cartão que deseja ajustar: ");
        double novoLimite = sc.nextDouble();
        this.limite = novoLimite;

    }

    /**
     * Setter com regra de negócio de que se o valor setado for menor ou igual ao limite, permitir modificação.
     * Além disso, há mudança de status de fatura paga dependendo do valor setado no momento neste setter.
     * @param valor
     */
    public void setFatura(double valor) {
        if(valor + fatura <= this.limite){
            this.fatura += valor;
        }
        if(this.fatura <= 0){
            this.faturaPaga = true;
        }
        else{
            this.faturaPaga = false;
        }
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
