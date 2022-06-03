package br.winxbank.sistemaclientes;

import br.winxbank.sistemabancario.Conta;

import java.util.ArrayList;

/**
 * @author Dani
 * Esta classe é responsável por representar uma entidade Cliente.
 */
public class Cliente {
    protected String nome;
    protected String cpf;
    protected ArrayList<Conta> contas;

    /**
     * Construtor padrão do cliente.
     * @param nome
     * @param cpf
     * @param contas
     */
    public Cliente(String nome, String cpf, ArrayList<Conta> contas){
        this.nome = nome;
        this.cpf = cpf;
        this.contas = contas;

    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }
}
