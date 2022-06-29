package br.winxbank.exception;

/**
 * @author Natália.
 * Exceção deve ser lançada quando cliente winx encontrado não for cliente winx.
 */
public class ClientFoundIsNotClientWinxException extends ClassCastException{

    public ClientFoundIsNotClientWinxException(){
        super("Cliente encontrado nao e um cliente winx.");
    }
}
