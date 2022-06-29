package br.winxbank.exception;

/**
 * @author Dani.
 * Esta exceção deve ser lançada quando um cliente não é encontrado no registro de clientes.
 */
public class ClientNotFoundException extends NullPointerException{

    public ClientNotFoundException(){
        super("Cliente inexistente.");
    }
}
