package br.winxbank.exception;

/**
 * @author Natália.
 * Esta exceção deve ser lançada quando um usuario tentar acessar uma das opções do menu para usuarios logados sem estar logado.
 */
public class YouAreNotLoggedInException extends RuntimeException{

    public YouAreNotLoggedInException(){
        super("Nao e possivel acessar essa opcao. Voce nao esta logado.");
    }
}
