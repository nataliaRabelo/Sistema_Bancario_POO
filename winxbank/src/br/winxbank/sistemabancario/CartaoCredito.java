package br.winxbank.sistemabancario;

/**
 * @author Carol
 * Esta classe é responsável por representar uma entidade CartaoCredito.
 */
public class CartaoCredito extends Cartao{

    private double fatura;
    private boolean faturaPaga;
    private double limite;
    private Conta conta;

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
     * @param valor
     */
    public void creditar(double valor){
        setFatura(valor);
    }

    /**
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
}
