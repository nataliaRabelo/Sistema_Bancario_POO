package br.winxbank;

import br.winxbank.sistemabancario.Banco;
import br.winxbank.sistemaclientes.Cliente;
import br.winxbank.sistemaclientes.RegistroDeClientes;
import br.winxbank.tempo.Ano;

import java.util.Scanner;

/**
 * Aqui você conta a sua história.
 */
public class Main {

    public static void main(String[] args) {

        RegistroDeClientes.getInstancia();
        Banco.getInstancia();
        int decisao = 1;
        Cliente clienteAtual = new Cliente();
        while (decisao == 1 || decisao == 2){
            Scanner sc = new Scanner(System.in);
            if(clienteAtual.getNome() != null){
                System.out.println("          Bem-vindo ao Winx Bank!\n      Ola, "+ clienteAtual.getNome() + " Digite o que deseja fazer:\n----------------- MENU INICIAL -----------------\n1 - (criar um usuario) \n2 - (logar em um usuario)\n--------------------- MENU ---------------------\n");
            }
            else if(clienteAtual.getNome() == null){
                System.out.println("------------------------------------------------");
                System.out.println("             Bem-vindo ao WinxBank!\n           Digite o que deseja fazer:\n----------------- MENU INICIAL -----------------\n1 - (criar um usuario) \n2 - (logar em um usuario)\n------------------------------------------------");
            }
            decisao =  sc.nextInt();
            switch (decisao){
                case 1:
                    RegistroDeClientes.getInstancia().cadastrarCliente();
                    RegistroDeClientes.getInstancia().printarListaDeClientes();
                    Ano.getInstancia().fazerMesPassar();
                    break;
                case 2:
                    System.out.println("Digite o cpf da conta que deseja logar:");
                    sc.nextLine();
                    String cpf = sc.nextLine();
                    System.out.println("------------------------------------------------");
                    System.out.println("Cliente atual: ");
                    Cliente cliente = RegistroDeClientes.getInstancia().visualizarDetalhesDoCliente(cpf);
                    System.out.println("------------------------------------------------");
                    clienteAtual = new Cliente(cliente);
                    clienteAtual.setContas(cliente.acessarContas());
                    Ano.getInstancia().fazerMesPassar();
                    break;
                default:
                    break;
            }
        }


    }
}
