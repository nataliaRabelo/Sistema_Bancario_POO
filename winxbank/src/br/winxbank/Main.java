package br.winxbank;

import br.winxbank.repository.ArquivoDeClientes;
import br.winxbank.sistemabancario.Banco;
import br.winxbank.sistemabancario.Conta;
import br.winxbank.sistemabancario.ContaCorrente;
import br.winxbank.sistemabancario.Movimentacao;
import br.winxbank.sistemaclientes.Cliente;
import br.winxbank.sistemaclientes.ClienteWinx;
import br.winxbank.sistemaclientes.RegistroDeClientes;
import br.winxbank.tempo.Ano;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Natália, Dani, Carol
 * Aqui você conta a sua história.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        int decisao = 1;
        Cliente clienteAtual = new Cliente();
        ArquivoDeClientes.getInstancia().readjason();
        RegistroDeClientes.getInstancia().printarListaDeClientes();
            while (decisao == 1 || decisao == 2 || decisao == 3 || decisao == 4 || decisao == 5 || decisao == 6 || decisao == 7 || decisao == 8 || decisao == 9 || decisao == 10 || decisao == 11 || decisao == 12 || decisao == 13 || decisao == 14 || decisao == 15 || decisao == 16 || decisao == 17){
                Scanner sc = new Scanner(System.in);
                if(clienteAtual.getNome() != null){
                    System.out.println("------------------------------------------------");
                    System.out.println("Cliente atual: ");
                    RegistroDeClientes.getInstancia().visualizarDetalhesDoCliente(clienteAtual.getCpf()) ;
                    System.out.println("------------------------------------------------");
                    System.out.println("Mes atual: " + Ano.getInstancia().getMesAtual());
                    System.out.println("          Bem-vindo ao WinxBank!\n      Ola, "+ clienteAtual.getNome() + " Digite o que deseja fazer:\n----------------- MENU INICIAL -----------------\n1 - (criar um usuario) \n2 - (logar em um usuario)\n--------------------- MENU ---------------------\n3 - (abrir conta)\n4 - (fechar conta)\n5 - (apagar usuario)\n6 - (depositar)\n7 - (comprar)\n8 - (fazer pix)\n9 - (sacar)\n10 - (pagar fatura)\n11 - (ajustar limite)\n12 - (pagar parcela emprestimo)\n13 - (requisitar emprestimo)\n14 - (converter pontos em saldo)\n15 - (gerar extrato)\n16 - (gerar informe rendimento)");

                }
                else if(clienteAtual.getNome() == null){
                    System.out.println("------------------------------------------------");
                    System.out.println("Mes atual: " + Ano.getInstancia().getMesAtual());
                    System.out.println("             Bem-vindo ao WinxBank!\n           Digite o que deseja fazer:\n----------------- MENU INICIAL -----------------\n1 - (criar um usuario) \n2 - (logar em um usuario)\n------------------------------------------------");
                }
                try {
                    decisao =  sc.nextInt();
                    Ano.getInstancia().fazerMesPassar();
                    switch (decisao){
                        // ----------------- MENU INICIAL ----------------- OBS: Antes e depois de haver usuario logado
                        case 1: // CADASTRAR USUARIO
                            RegistroDeClientes.getInstancia().cadastrarCliente();
                            RegistroDeClientes.getInstancia().printarListaDeClientes();
                            break;
                        case 2: // LOGAR
                            System.out.println("Digite o cpf do usuario que deseja logar:");
                            sc.nextLine();
                            String cpf = sc.nextLine();
                            try{
                                Cliente cliente = RegistroDeClientes.getInstancia().retornarCliente(cpf);
                                if(cliente.getClass() == ClienteWinx.class){
                                    clienteAtual = new ClienteWinx(cliente);
                                    clienteAtual.setContas(cliente.acessarContas());
                                }
                                else{
                                    clienteAtual = new Cliente(cliente);
                                    clienteAtual.setContas(cliente.acessarContas());
                                }
                            }catch (NullPointerException e){
                                System.out.println("Cliente inexistente.");
                            }

                            break;
                        // --------------------- MENU --------------------- OBS: Depois de haver usuario logado
                        case 3: // ABRIR CONTA
                            Conta conta = Banco.getInstancia().abrirNovaConta();
                            Movimentacao movimentacao = new Movimentacao(conta.getSaldo(), Movimentacao.TipoDaMovimentacao.ENTRADA);
                            conta.setExtrato(movimentacao);
                            clienteAtual.setContas(conta);
                            try {
                                if(conta.getSaldo() >= 100000 || clienteAtual.acessarContas().getSaldo() >= 100000){
                                    System.out.println("Parabens, voce tem direito a ser ClienteWinx!");
                                    ClienteWinx clienteWinx = new ClienteWinx(clienteAtual.getNome(), clienteAtual.getCpf(), 0);
                                    clienteWinx.setContas(clienteAtual.getContas());
                                    RegistroDeClientes.getInstancia().atualizarCliente(clienteWinx);
                                    clienteAtual = clienteWinx;
                                }
                                else{
                                    RegistroDeClientes.getInstancia().atualizarCliente(clienteAtual);
                                }
                            }catch (NullPointerException e){
                                System.out.println("Opcao invalida. Digite outro valor.");
                            }
                            break;
                        case 4: // FECHAR CONTA
                            Banco.getInstancia().fecharConta(clienteAtual);
                            RegistroDeClientes.getInstancia().atualizarCliente(clienteAtual);
                            break;
                        case 5: // APAGAR USUARIO
                            RegistroDeClientes.getInstancia().removerCliente(clienteAtual);
                            clienteAtual = new Cliente();
                            break;
                        case 6: // DEPOSITAR
                            //TODO: fazer contas do cliente aparecerem nesse momento
                            System.out.println("Digite o numero da conta que deseja apagar:");
                            int numeroConta = sc.nextInt();
                            Conta conta2 = clienteAtual.selecionarConta(numeroConta);
                            conta2.depositar();

                            RegistroDeClientes.getInstancia().atualizarCliente(clienteAtual);
                            break;
                        case 7: // COMPRAR
                            System.out.println("Você está comprando...");
                            //TODO: FAZER METODO DE COMPRAR, LINKAR COM DEBITAR E CREDITAR DOS CARTOES
                            if(clienteAtual.getClass() == ClienteWinx.class){
                                ((ClienteWinx) clienteAtual).obterPontosDeCompra();
                            }
                            RegistroDeClientes.getInstancia().atualizarCliente(clienteAtual);
                            break;
                        case 8: // FAZER PIX
                            //TODO: fazer contas do cliente aparecerem nesse momento
                            System.out.println("Digite o numero da conta que deseja selecionar para retirada de saldo:");
                            int numeroConta2 = sc.nextInt();
                            Conta conta3 = clienteAtual.selecionarConta(numeroConta2);
                            System.out.println("Digite o numero da CPF de quem deseja fazer o pix:");
                            sc.nextLine();
                            String cpf2 = sc.nextLine();
                            try{
                                Cliente cliente2 = RegistroDeClientes.getInstancia().retornarCliente(cpf2);
                                RegistroDeClientes.getInstancia().visualizarDetalhesDoCliente(cpf2);
                                System.out.println("Digite o numero da conta de quem deseja fazer o pix:");
                                int numConta = sc.nextInt();
                                Conta conta4 = cliente2.selecionarConta(numConta);
                                conta3.fazerPix(conta4);
                                RegistroDeClientes.getInstancia().atualizarCliente(clienteAtual);
                            }catch (NullPointerException e){
                                System.out.println("Cliente inexistente.");
                            }

                            break;
                        case 9: // SACAR
                            //TODO: fazer contas do cliente aparecerem nesse momento
                            System.out.println("Digite o numero da conta que deseja realizar o saque:");
                            int numeroConta3 = sc.nextInt();
                            Conta conta5 = clienteAtual.selecionarConta(numeroConta3);
                            System.out.println("Digite o valor do saque:");
                            double valorDoSaque = sc.nextDouble();
                            conta5.sacar(valorDoSaque);
                            RegistroDeClientes.getInstancia().atualizarCliente(clienteAtual);
                            break;
                        case 10: // PAGAR FATURA
                            //TODO: fazer contas do cliente aparecerem nesse momento
                            System.out.println("Digite o numero da conta corrente a qual deseja pagar a fatura:");
                            int numeroConta4 = sc.nextInt();
                            ContaCorrente contaCorrente = (ContaCorrente) clienteAtual.selecionarConta(numeroConta4);
                            System.out.println("Digite o valor que deseja pagar da sua fatura:");
                            double valorPagoDaFatura = sc.nextDouble();
                            contaCorrente.pagarFatura(valorPagoDaFatura);
                            RegistroDeClientes.getInstancia().atualizarCliente(clienteAtual);
                            break;
                        case 11: // AJUSTAR LIMITE
                            //TODO: AJUSTAR LIMITE
                            break;
                        case 12: // PAGAR PARCELA DE EMPRESTIMO
                            //TODO: COBRAR JURUS TAMBÉM DA PARCELA DE EMPRÉSTIMO
                            System.out.println("Digite o numero da conta que deseja pagar parcela de emprestimo:");
                            int numeroConta5 = sc.nextInt();
                            Conta conta6 = clienteAtual.selecionarConta(numeroConta5);
                            System.out.println("Digite o valor que deseja pagar:");
                            double valorParcelaEmprestimoPaga = sc.nextDouble();
                            conta6.pagarParcelaDeEmprestimo(valorParcelaEmprestimoPaga);
                            RegistroDeClientes.getInstancia().atualizarCliente(clienteAtual);
                            break;
                        case 13: // REQUISITAR EMPRESTIMO
                            System.out.println("Digite o numero da conta que deseja requisitar emprestimo:");
                            int numeroConta6 = sc.nextInt();
                            Conta conta7 = clienteAtual.selecionarConta(numeroConta6);
                            System.out.println("Digite o valor do emprestimo que deseja requisitar:");
                            double valorEmprestimoRequisitado = sc.nextDouble();
                            conta7.requisitarEmprestimo(valorEmprestimoRequisitado);
                            RegistroDeClientes.getInstancia().atualizarCliente(clienteAtual);
                            break;
                        case 14: // CONVERTER PONTOS EM SALDO
                            System.out.println("Digite o numero da conta que deseja converter pontos em saldo");
                            int numeroConta7 = sc.nextInt();
                            Conta conta8 = clienteAtual.selecionarConta(numeroConta7);
                            try {
                                ((ClienteWinx) clienteAtual).converterPontosEmSaldo(conta8);
                                RegistroDeClientes.getInstancia().atualizarCliente(clienteAtual);
                            }catch (ClassCastException e){
                                System.out.println("O cliente logado nao eh cliente winx. Conversao de pontos em saldo nao pode ser efetuada.");
                            }
                            break;
                        case 15: // GERAR EXTRATO
                            // TODO: Usar esse espaço para gerar extrato
                            break;
                        case 16: // GERAR INFORME DE RENDIMENTO
                            // TODO: Usar esse espaço para gerar informe
                            break;
                        case 17: // EXIBIR LISTA DE CLIENTES
                            RegistroDeClientes.getInstancia().printarListaDeClientes();
                            break;
                        default:
                            break;
                    }
                }catch (InputMismatchException e){
                    System.out.println("Opcao invalida. Digite um novo valor.");
                }
                ArquivoDeClientes.getInstancia().escreverJson(RegistroDeClientes.getInstancia().getClientes());
            }
    }
}
