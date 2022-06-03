package br.winxbank.sistemabancario;

/**
 * @author Carol
 * Esta classe é responsável por representar uma entidade CartaoCredito.
 */
public class CartaoCredito extends Cartao{

    private double fatura;
    private boolean faturaPaga;

    public CartaoCredito(int numero, int csv) {
        super(numero, csv);
    }

    public void setFatura(double valor) {
        this.fatura += valor;
        if (this.fatura == 0){
            this.faturaPaga = true;
        }
        else if(this.fatura > 0){
            this.faturaPaga = false;
        }

    }

    public double getFatura() {
        return fatura;
    }
}
