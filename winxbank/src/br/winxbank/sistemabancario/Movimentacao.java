package br.winxbank.sistemabancario;

/**
 * @author Natália.
 * Classe responsável por representar uma entidade movimentacao bancária.
 */
public class Movimentacao {

    private double dinheiroMovimentado;
    private double rendimento;
    private double desconto;
    private enum tipoDaMovimentacao{
        ENTRADA,
        SAIDA;
    }
}
