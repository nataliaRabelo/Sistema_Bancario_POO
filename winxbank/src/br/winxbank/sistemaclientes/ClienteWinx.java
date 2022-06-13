package br.winxbank.sistemaclientes;

import br.winxbank.sistemabancario.Conta;

/**
 * @author Dani
 * Esta classe é responsável por representar uma entidade ClienteWinx.
 * Um tipo de cliente sem vantagens no banco.
 */
public class ClienteWinx extends Cliente {

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
    }

    public int getPontosDeCompra() {
        return pontosDeCompra;
    }

    public void obterPontosDeCompra(){
        this.pontosDeCompra++;
    }

    /**
     * Método responsável por converter os pontos do ClienteWinx em saldo na conta.
     * @param conta
     */
    public void converterPontosEmSaldo(Conta conta){
        float saldoConvertido = this.pontosDeCompra * this.BONUSDECOMPRA;
        conta.setSaldo(saldoConvertido);
    }
}
