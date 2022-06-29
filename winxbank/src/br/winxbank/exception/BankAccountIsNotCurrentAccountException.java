package br.winxbank.exception;

/**
 * @author Dani
 * Exceção deve ser lançada quando a conta recuperada não for uma conta corrente em um momento que é obrigatoriamente necessário o retorno de uma conta do tipo corrente.
 */
public class BankAccountIsNotCurrentAccountException extends ClassCastException{

    public BankAccountIsNotCurrentAccountException(){
        super("Conta bancaria selecionada nao e conta corrente.");
    }
}
