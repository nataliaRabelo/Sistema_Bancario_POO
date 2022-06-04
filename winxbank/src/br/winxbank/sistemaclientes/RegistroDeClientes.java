package br.winxbank.sistemaclientes;

import java.util.ArrayList;

/**
 * @author Natália
 * Esta classe é responsável por administrar um registro de clientes.
 */
public class RegistroDeClientes {

    private static RegistroDeClientes instancia;
    private ArrayList<Cliente> clientes;

    /**
     * Este método é responsável por cadastrar um cliente no registro de clientes.
     * @param cliente
     */
    public void cadastrarCliente(Cliente cliente){
        clientes.add(cliente);
    }

    /**
     * Este método é responsável por remover um cliente do registro de clientes.
     * @param cliente
     */
    public void removerCliente(Cliente cliente){
        clientes.remove(cliente);
    }

    /**
     * Este método é responsável por visualizar detalhes de um cliente do registro.
     */
    public void visualizarDetalhesDoCliente(Cliente clienteSelecionado){
        for(Cliente cliente : clientes){
            if(cliente.getClass() == ClienteWinx.class && cliente.cpf.equals(clienteSelecionado.cpf)){
                System.out.println("Nome: " + cliente.getNome() + "CPF: " + cliente.getCpf() + "Contas: " + cliente.getContas() + "Pontos por compra" + ((ClienteWinx) cliente).getPontosDeCompra());
            }
            else if(cliente.getClass() == Cliente.class && cliente.cpf.equals(clienteSelecionado.cpf)){
                System.out.println("Nome: " + cliente.getNome() + "CPF: " + cliente.getCpf() + "Contas: " + cliente.getContas());
            }


        }
    }

    public void printarListaDeClientes(){
        for(Cliente cliente : clientes){
            if(cliente.getClass() == ClienteWinx.class){
                System.out.println("Nome: " + cliente.getNome() + "CPF: " + cliente.getCpf() + "Contas: " + cliente.getContas() + "Pontos por compra" + ((ClienteWinx) cliente).getPontosDeCompra());
            }
            else{
                System.out.println("Nome: " + cliente.getNome() + "CPF: " + cliente.getCpf() + "Contas: " + cliente.getContas());
            }


        }
    }

    /**
     * TODO: criar método de atulização de arquivo de registro de clientes.
     */
    public void atualizarArquivo(){

    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Singleton que só permite uma instância do objeto ser criada, quando o atributo estático instanciaEvento tem o valor nulo.
     * @return
     */
    public static RegistroDeClientes getInstancia() {
        if (instancia == null) {
            instancia = new RegistroDeClientes();
        }
        return instancia;
    }
}
