package br.winxbank.exception;

/**
 * @author Natália
 * Exceção deve ser lançada quando os pontos de compra de um cliente winx são insuficientes para realizar uma conversão.
 */
public class NotEnaughPurchasePoints extends RuntimeException{

    public NotEnaughPurchasePoints(){
        super("Pontos de compra insuficientes para a conversao.");
    }
}