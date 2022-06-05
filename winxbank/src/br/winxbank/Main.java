package br.winxbank;

import br.winxbank.sistemabancario.Banco;
import br.winxbank.sistemaclientes.Cliente;
import br.winxbank.sistemaclientes.RegistroDeClientes;

import java.util.Locale;
import java.util.Scanner;

/**
 * Aqui você conta a sua história.
 */
public class Main {

    public static void main(String[] args) {

        RegistroDeClientes.getInstancia();
        Banco.getInstancia();
        int decisao = 1;
        final Locale myLocale = new Locale("pt", "BR");
        Cliente clienteAtual = new Cliente();
        while (decisao == 1 || decisao == 2){
            Scanner sc = new Scanner(System.in);
            if(clienteAtual.getNome() != null){
                System.out.println("      Bem-vindo ao Winx Bank!\n\n      Olá, "+ clienteAtual.getNome() + "\n\nCPF: " + clienteAtual.getCpf() + "\nConta nº: "+ clienteAtual.acessarContas().getNumeroConta() +"\nSaldo: "+clienteAtual.acessarContas().getSaldo() + "\n\n      Digite o que deseja fazer:\n----------------- MENU INICIAL -----------------\n1 - (criar um usuario) \n2 - (logar em um usuario)\n--------------------- MENU ---------------------\n");
            }
            else if(clienteAtual.getNome() == null){
                System.out.println("      Bem-vindo ao Winx Bank!\n\n      Digite o que deseja fazer:\n----------------- MENU INICIAL -----------------\n1 - (criar um usuario) \n2 - (logar em um usuario)\n--------------------- MENU ---------------------\n");
            }
            decisao =  sc.nextInt();
            switch (decisao){
                case 1:
                    RegistroDeClientes.getInstancia().cadastrarCliente();
                    RegistroDeClientes.getInstancia().printarListaDeClientes();
                    break;
                case 2:
                    System.out.println("Digite o cpf para logar:");
                    sc.nextLine();
                    String cpf = sc.nextLine();
                    Cliente cliente = RegistroDeClientes.getInstancia().visualizarDetalhesDoCliente(cpf);
                    clienteAtual = new Cliente(cliente);
                    break;
            }
        }


    }
}
