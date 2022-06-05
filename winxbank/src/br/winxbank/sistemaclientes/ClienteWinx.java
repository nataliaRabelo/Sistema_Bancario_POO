package br.winxbank.sistemaclientes;

import br.winxbank.sistemabancario.Conta;
import br.winxbank.sistemaclientes.Cliente;

import java.util.ArrayList;

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

    public int getPontosDeCompra() {
        return pontosDeCompra;
    }

    public void converterPontosEmSaldo(){
        float saldoConvertido = this.pontosDeCompra * this.BONUSDECOMPRA;
        //TODO: setar saldo convertido em saldo da conta
    }
}
