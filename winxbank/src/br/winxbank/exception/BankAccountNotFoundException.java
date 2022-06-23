package br.winxbank.exception;

/**
 * @author Carol.
 * Esta exceção deve ser lançada quando a conta de um usuario logado não for encontrada.
 */
public class BankAccountNotFoundException extends NullPointerException{

    public BankAccountNotFoundException(){
        super("Conta bancaria nao encontrada.");
    }
}
