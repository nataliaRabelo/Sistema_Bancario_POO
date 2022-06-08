package br.winxbank.sistemaclientes;

import br.winxbank.sistemabancario.Banco;
import br.winxbank.sistemabancario.Conta;
import br.winxbank.sistemabancario.ContaCorrente;
import br.winxbank.sistemabancario.ContaPoupanca;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Natália
 * Esta classe é responsável por administrar um registro de clientes.
 */
public class RegistroDeClientes {

    private static RegistroDeClientes instancia;
    private ArrayList<Cliente> clientes = new ArrayList<>();

    /**
     * Este método é responsável por cadastrar um cliente no registro de clientes.
     * Se o cliente criar uma conta com mais de 100 mil, ele se torna ClienteWix.
     */
    public void cadastrarCliente(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Você está cadastrando um cliente\nDigite o nome:");
        String nome = sc.nextLine();
        System.out.println("Digite o cpf:");
        String cpf = sc.nextLine();
        Cliente cliente = new Cliente(nome, cpf);
        Conta conta = Banco.getInstancia().abrirNovaConta();
        cliente.setContas(conta);
        if(cliente.acessarContas().getSaldo() >= 100000){
            System.out.println("Parabéns, você tem direito a ser ClienteWinx!");
            ClienteWinx clienteWinx = new ClienteWinx(nome, cpf, 0);
            clienteWinx.setContas(conta);
            clientes.add(clienteWinx);
        }
        else if(cliente.acessarContas().getSaldo() < 100000) {
            clientes.add(cliente);
        }

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
     * @param cpf
     * @return
     */
    public Cliente visualizarDetalhesDoCliente(String cpf){
        for(Cliente cliente : clientes){
            if(cliente.getClass() == ClienteWinx.class && cliente.cpf.equals(cpf)){
                System.out.println("Nome: " + cliente.getNome() + "CPF: " + cliente.getCpf() + "Pontos por compra" + ((ClienteWinx) cliente).getPontosDeCompra() + "\nContas:");
                for(Conta conta : cliente.getContas()){
                    if(conta.getClass() == ContaPoupanca.class){
                        System.out.println("[ Conta" + ((ContaPoupanca) conta).getTipoDaConta() + "no: " + conta.getNumeroConta() + " | Saldo:" + conta.getSaldo() + " | DividaEmprestimo:" + conta.getDividaDeEmprestimo() + "| Cartao Debito: " + conta.getCartao() + "]");
                        return cliente;
                    }
                    else if(conta.getClass() == ContaCorrente.class){
                        System.out.println("[ Conta" + ((ContaCorrente) conta).getTipoDaConta() + "no: " + conta.getNumeroConta() + " | Saldo:" + conta.getSaldo() + " | DividaEmprestimo:" + conta.getDividaDeEmprestimo() + "| Cartao Debito: " + conta.getCartao() + "| Cartao Credito: " + ((ContaCorrente) conta).getCartaoCredito() + "]");
                        return cliente;
                    }
                }
            }
            else if(cliente.getClass() == Cliente.class && cliente.cpf.equals(cpf)){
                System.out.println("Nome: " + cliente.getNome() + "| CPF: " + cliente.getCpf() + "\nContas:");
                for(Conta conta : cliente.getContas()){
                    if(conta.getClass() == ContaPoupanca.class){
                        System.out.println("[ Conta" + ((ContaPoupanca) conta).getTipoDaConta() + "no: " + conta.getNumeroConta() + " | Saldo:" + conta.getSaldo() + " | DividaEmprestimo:" + conta.getDividaDeEmprestimo() + "| Cartao Debito: " + conta.getCartao() + "]");
                        return cliente;
                    }
                    else if(conta.getClass() == ContaCorrente.class){
                        System.out.println("[ Conta" + ((ContaCorrente) conta).getTipoDaConta() + "no: " + conta.getNumeroConta() + " | Saldo:" + conta.getSaldo() + " | DividaEmprestimo:" + conta.getDividaDeEmprestimo() + "| Cartao Debito: " + conta.getCartao() + "| Cartao Credito: " + ((ContaCorrente) conta).getCartaoCredito() + "]");
                        return cliente;
                    }
                }
            }

        }
        return null;
    }

    /**
     * Este método é responsável por printar lista de clientes.
     */
    public void printarListaDeClientes(){
        System.out.println("------------------ Clientes --------------------");
        for(Cliente cliente : clientes){
            if(cliente.getClass() == ClienteWinx.class){
                System.out.println("Nome: " + cliente.getNome() + "| CPF: " + cliente.getCpf() + "| Contas: " + cliente.getContas() + "Pontos por compra" + ((ClienteWinx) cliente).getPontosDeCompra());
            }
            else{
                System.out.println("Nome: " + cliente.getNome() + "| CPF: " + cliente.getCpf() + "| Contas: " + cliente.getContas());
            }
            System.out.println("------------------------------------------------");

        }
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Singleton que só permite uma instância do objeto ser criada, quando o atributo estático instancia tem o valor nulo.
     * @return
     */
    public static RegistroDeClientes getInstancia() {
        if (instancia == null) {
            instancia = new RegistroDeClientes();
        }
        return instancia;
    }
}
