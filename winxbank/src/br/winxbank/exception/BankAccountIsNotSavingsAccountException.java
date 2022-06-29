package br.winxbank.exception;

/**
 * @author Natália.
 * Exceção deve ser lançada quando uma conta de banco não é conta poupança.
 */
public class BankAccountIsNotSavingsAccountException extends ClassCastException{

    public BankAccountIsNotSavingsAccountException(){
        super("Conta selecionada nao e conta poupanca.");
    }

}
