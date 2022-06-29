package br.winxbank.exception;

/**
 * @author Carol.
 * Esta excecao deve ser lançada quando um valor solicitado for maior que o saldo da conta.
 */
public class ValueIsHigherThanBalanceException extends RuntimeException{
    public ValueIsHigherThanBalanceException(){
        super("O valor solicitado e maior que o saldo da conta.");
    }
}
