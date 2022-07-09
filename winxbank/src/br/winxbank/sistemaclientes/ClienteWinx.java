package br.winxbank.sistemaclientes;

import br.winxbank.sistemabancario.Conta;
import br.winxbank.sistemabancario.Movimentacao;

/**
 * @author Dani
 * Esta classe é responsável por representar uma entidade ClienteWinx.
 * Um tipo de cliente sem vantagens no banco.
 */
public class ClienteWinx extends Cliente{

    private int pontosDeCompra;
    private final int BONUSDECOMPRA = 3;

    /**
     * Construtor padrão do cliente.
     *
     * @param nome
     * @param cpf
     */
    public ClienteWinx(String nome, String cpf, int pontosDeCompra) {
        super(nome, cpf);
        this.pontosDeCompra = pontosDeCompra;
    }

    /**
     * Cosntrutor alternativo para salvar um determinado cliente atual no sistema de login.
     * @param cliente
     */
    public ClienteWinx(Cliente cliente){
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.pontosDeCompra = ((ClienteWinx) cliente).getPontosDeCompra();
    }

    public int getPontosDeCompra() {
        return pontosDeCompra;
    }

    /**
     * Método responsável por converter pontos de compra em saldo.
     */
    public void obterPontosDeCompra(){
        this.pontosDeCompra = this.pontosDeCompra + 1;
    }

    /**
     * Método responsável por converter os pontos do ClienteWinx em saldo na conta.
     * @param conta
     */
    public void converterPontosEmSaldo(Conta conta){
        float saldoConvertido = this.pontosDeCompra * this.BONUSDECOMPRA;
        conta.setSaldo(saldoConvertido);
        this.pontosDeCompra = 0;
        Movimentacao movimentacao = new Movimentacao(saldoConvertido, Movimentacao.TipoDaMovimentacao.ENTRADA);
        conta.setExtrato(movimentacao);
    }
}
