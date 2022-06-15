package br.winxbank.sistemabancario;

/**
 * @author Carol
 * Interface responsável por realizar as operações automáticas do banco.
 */
public interface OperacoesAutomaticas {

    double taxaJurus = 12.75;
    double taxaManutencaoConta = 13.00;
    double rendimentoMensalPoupanca = 0.8;

    void movimentacaoBancaria(double valor);

}
