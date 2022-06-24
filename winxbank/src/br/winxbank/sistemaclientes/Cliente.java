package br.winxbank.sistemaclientes;

import br.winxbank.sistemabancario.Conta;

import java.util.ArrayList;

/**
 * @author Dani
 * Esta classe é responsável por representar uma entidade Cliente.
 */
public class Cliente{
    protected String nome;
    protected String cpf;
    protected ArrayList<Conta> contas = new ArrayList<>();

    /**
     * Construtor padrão do cliente.
     * @param nome
     * @param cpf
     */
    public Cliente(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;

    }

    /**
     * Cosntrutor alternativo para salvar um determinado cliente atual no sistema de login.
     * @param cliente
     */
    public Cliente(Cliente cliente){
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
    }
    /**
     * Cosntrutor alternativo vazio para inicializar a variável clienteatual no programa principal do cliente atual no sistema de login.
     */
    public Cliente(){

    }

    /**
     * Construtor alternativo para leitura de arquivo json.
     * @param nome
     * @param cpf
     * @param contas
     */
    public Cliente(String nome, String cpf, Object contas) {
        this.nome = nome;
        this.cpf = cpf;
        setContas((Conta) contas);
    }

    public void apagarConta(Conta conta) {
        this.contas.remove(conta);
    }

    /**
     * Método responsável por selecionar uma conta.
     * @param numeroConta
     * @return
     */
    public Conta selecionarConta(int numeroConta) {
        for (Conta conta : this.contas) {
            if (conta.getNumeroConta() == numeroConta) {
                return conta;
            }
        }
        return null;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Conta acessarContas() {
        for (Conta conta : this.contas){
            return conta;
        }
        return null;
    }
    public ArrayList<Conta> getContas() {
        return contas;
    }

    public void setContas(Conta conta) {
        this.contas.add(conta);
    }

    public void setContas(ArrayList<Conta> contas) {
        this.contas.addAll(contas);
    }
}
