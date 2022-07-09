package br.winxbank;

import br.winxbank.exception.*;
import br.winxbank.repository.ArquivoBanco;
import br.winxbank.repository.ArquivoDeClientes;
import br.winxbank.repository.ArquivoDeMesAtual;
import br.winxbank.sistemabancario.*;
import br.winxbank.sistemaclientes.Cliente;
import br.winxbank.sistemaclientes.ClienteWinx;
import br.winxbank.sistemaclientes.RegistroDeClientes;
import br.winxbank.tempo.Ano;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Natália, Dani, Carol
 * Classe de execução do programa.
 */
public class Main {

    public static void visualizarClienteAtual(Cliente clienteAtual){
        System.out.println("------------------------------------------------");
        System.out.println("Cliente atual: ");
        RegistroDeClientes.getInstancia().visualizarDetalhesDoCliente(clienteAtual.getCpf()) ;
        System.out.println("------------------------------------------------");
    }
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        int decisao = 1;
        Cliente clienteAtual = new Cliente();
        ArquivoDeClientes.getInstancia().readjason();
        ArquivoDeMesAtual.getInstancia().lerMesAtual();
        ArquivoBanco.getInstancia().construirBanco();
        RegistroDeClientes.getInstancia().printarListaDeClientes();
            while (decisao == 1 || decisao == 2 || decisao == 3 || decisao == 4 || decisao == 5 || decisao == 6 || decisao == 7 || decisao == 8 || decisao == 9 || decisao == 10 || decisao == 11 || decisao == 12 || decisao == 13 || decisao == 14 || decisao == 15 || decisao == 16 || decisao == 17 || decisao == 18 || decisao == 19){
                Scanner sc = new Scanner(System.in);
                if(clienteAtual.getNome() != null){
                    visualizarClienteAtual(clienteAtual);
                    System.out.println("Mes atual: " + Ano.getInstancia().getMesAtual());
                    System.out.println("          Bem-vindo ao WinxBank!\n      Ola, "+ clienteAtual.getNome() + " Digite o que deseja fazer:\n----------------- MENU INICIAL ----------------- \n0 - (encerrar programa) \n1 - (criar um usuario) \n2 - (logar em um usuario)\n--------------------- MENU ---------------------\n3 - (abrir conta)\n4 - (fechar conta)\n5 - (apagar usuario)\n6 - (depositar)\n7 - (comprar)\n8 - (fazer pix)\n9 - (sacar)\n10 - (pagar fatura)\n11 - (ajustar limite)\n12 - (pagar parcela emprestimo)\n13 - (requisitar emprestimo)\n14 - (converter pontos em saldo)\n15 - (gerar extrato)\n16 - (gerar informe rendimento)\n17 - (exibir clientes)\n18 - (limpar clientes)\n19 - (visualizar dados do banco)");

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
                            RegistroDeClientes.getInstancia().printarListaDeClientes();
                            System.out.println("Digite o cpf do usuario que deseja logar:");
                            sc.nextLine();
                            String cpf = sc.nextLine();
                            try{
                                Cliente cliente = RegistroDeClientes.getInstancia().retornarCliente(cpf);
                                if(cliente.getClass() == ClienteWinx.class){
                                    clienteAtual = new ClienteWinx(cliente);
                                    clienteAtual.setContas(cliente.getContas());
                                }
                                else{
                                    clienteAtual = new Cliente(cliente);
                                    clienteAtual.setContas(cliente.getContas());
                                }
                            }catch (NullPointerException e){
                                System.out.println("Cliente inexistente.");
                            }
                            break;
                        // --------------------- MENU --------------------- OBS: Depois de haver usuario logado
                        case 3: // ABRIR CONTA
                            try {
                                if(clienteAtual.getCpf() == null){
                                    throw new YouAreNotLoggedInException();
                                }
                                try {
                                    Conta conta = Banco.getInstancia().abrirNovaConta();
                                    Movimentacao movimentacao = new Movimentacao(conta.getSaldo(), Movimentacao.TipoDaMovimentacao.ENTRADA);
                                    conta.setExtrato(movimentacao);
                                    if(conta.getClass() == ContaPoupanca.class){
                                        ((ContaPoupanca) conta).setInformeRendimento(movimentacao);
                                    }
                                    clienteAtual.setContas(conta);
                                    if(conta.getSaldo() >= 100000 || clienteAtual.acessarContas().getSaldo() >= 100000){
                                        System.out.println("Parabens, voce tem direito a ser ClienteWinx!");
                                        ClienteWinx clienteWinx = new ClienteWinx(clienteAtual.getNome(), clienteAtual.getCpf(), 0);
                                        clienteWinx.setContas(clienteAtual.getContas());
                                        clienteAtual = clienteWinx;
                                    }
                                }catch (NullPointerException e){
                                    System.out.println("Opcao invalida. Digite outro valor.");
                                }finally {
                                    RegistroDeClientes.getInstancia().atualizarCliente(clienteAtual);
                                }
                            }catch (YouAreNotLoggedInException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 4: // FECHAR CONTA
                            try {
                                if(clienteAtual.getCpf() == null){
                                    throw new YouAreNotLoggedInException();
                                }
                                visualizarClienteAtual(clienteAtual);
                                Banco.getInstancia().fecharConta(clienteAtual);
                                RegistroDeClientes.getInstancia().atualizarCliente(clienteAtual);
                            }catch (BankAccountNotFoundException | YouAreNotLoggedInException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 5: // APAGAR USUARIO
                            try {
                                if(clienteAtual.getCpf() == null){
                                    throw new YouAreNotLoggedInException();
                                }
                                RegistroDeClientes.getInstancia().removerCliente(clienteAtual);
                                clienteAtual = new Cliente();
                            }catch (YouAreNotLoggedInException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 6: // DEPOSITAR
                            try {
                                if(clienteAtual.getCpf() == null){
                                    throw new YouAreNotLoggedInException();
                                }
                                visualizarClienteAtual(clienteAtual);
                                System.out.println("Visualize suas contas acima. Digite o numero da conta que deseja depositar:");
                                int numeroConta = sc.nextInt();

                                Conta conta = clienteAtual.selecionarConta(numeroConta);
                                if(conta == null){
                                    throw new BankAccountNotFoundException();
                                }
                                System.out.println("Digite o valor que deseja depositar na sua conta:");
                                double valor = sc.nextDouble();
                                conta.depositar(valor);
                                Movimentacao movimentacao = new Movimentacao(valor, Movimentacao.TipoDaMovimentacao.ENTRADA);
                                conta.setExtrato(movimentacao);
                                RegistroDeClientes.getInstancia().atualizarCliente(clienteAtual);
                            }catch (BankAccountNotFoundException | YouAreNotLoggedInException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 7: // COMPRAR
                            try {
                                if(clienteAtual.getCpf() == null){
                                    throw new YouAreNotLoggedInException();
                                }
                                visualizarClienteAtual(clienteAtual);
                                System.out.println("Visualize suas contas acima. Digite o numero da conta que deseja realizar a compra:");
                                int numeroConta = sc.nextInt();
                                Conta conta = clienteAtual.selecionarConta(numeroConta);
                                if(conta == null){
                                    throw new BankAccountNotFoundException();
                                }
                                System.out.println("Digite o valor da sua compra:");
                                double valorCompra = sc.nextDouble();
                                if(valorCompra > conta.getSaldo()){
                                    throw new ValueIsHigherThanBalanceException();
                                }
                                if(clienteAtual.getClass() == ClienteWinx.class){
                                    ((ClienteWinx) clienteAtual).obterPontosDeCompra();
                                }
                                conta.comprar(valorCompra);
                                Movimentacao movimentacao = new Movimentacao(valorCompra, Movimentacao.TipoDaMovimentacao.SAIDA);
                                conta.setExtrato(movimentacao);
                                RegistroDeClientes.getInstancia().atualizarCliente(clienteAtual);

                            }catch (BankAccountNotFoundException | ValueIsHigherThanBalanceException | YouAreNotLoggedInException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 8: // FAZER PIX
                            try {
                                if(clienteAtual.getCpf() == null){
                                    throw new YouAreNotLoggedInException();
                                }
                                visualizarClienteAtual(clienteAtual);
                                System.out.println("Visualize suas contas acima. Digite o numero da conta que deseja selecionar para retirada de saldo:");
                                int numeroConta = sc.nextInt();
                                Conta conta = clienteAtual.selecionarConta(numeroConta);
                                if(conta == null){
                                    throw new BankAccountNotFoundException();
                                }
                                System.out.println("Digite o numero da CPF de quem deseja fazer o pix:");
                                sc.nextLine();
                                String cpf2 = sc.nextLine();
                                Cliente cliente2 = RegistroDeClientes.getInstancia().retornarCliente(cpf2);
                                if(cliente2 == null){
                                    throw new ClientNotFoundException();
                                }
                                RegistroDeClientes.getInstancia().visualizarDetalhesDoCliente(cpf2);
                                System.out.println("Digite o numero da conta de quem deseja fazer o pix:");
                                int numConta = sc.nextInt();
                                Conta conta2 = cliente2.selecionarConta(numConta);
                                if(conta2 == null){
                                    throw new BankAccountNotFoundException();
                                }
                                System.out.println("Digite o valor do pix:");
                                double valor = sc.nextDouble();
                                if(valor > conta2.getSaldo()){
                                    throw new ValueIsHigherThanBalanceException();
                                }
                                conta.fazerPix(conta2, valor);
                                Movimentacao movimentacao = new Movimentacao(valor, Movimentacao.TipoDaMovimentacao.SAIDA);
                                Movimentacao movimentacao2 = new Movimentacao(valor, Movimentacao.TipoDaMovimentacao.ENTRADA);
                                conta.setExtrato(movimentacao);
                                conta2.setExtrato(movimentacao2);
                                RegistroDeClientes.getInstancia().atualizarCliente(clienteAtual);
                                RegistroDeClientes.getInstancia().atualizarCliente(cliente2);
                            }catch (ClientNotFoundException | BankAccountNotFoundException | YouAreNotLoggedInException | ValueIsHigherThanBalanceException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 9: // SACAR
                            try {
                                if(clienteAtual.getCpf() == null){
                                    throw new YouAreNotLoggedInException();
                                }
                                visualizarClienteAtual(clienteAtual);
                                System.out.println("Visualize suas contas acima. Digite o numero da conta que deseja gerar o extrato:");
                                int numeroConta3 = sc.nextInt();
                                Conta conta = clienteAtual.selecionarConta(numeroConta3);
                                if(conta == null){
                                    throw new BankAccountNotFoundException();
                                }
                                System.out.println("Digite o valor do saque:");
                                double valorDoSaque = sc.nextDouble();
                                if(valorDoSaque > conta.getSaldo()){
                                    throw new ValueIsHigherThanBalanceException();
                                }
                                conta.sacar(valorDoSaque);
                                Movimentacao movimentacao = new Movimentacao(valorDoSaque, Movimentacao.TipoDaMovimentacao.SAIDA);
                                conta.setExtrato(movimentacao);
                                RegistroDeClientes.getInstancia().atualizarCliente(clienteAtual);
                            }catch (BankAccountNotFoundException | ValueIsHigherThanBalanceException | YouAreNotLoggedInException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 10: // PAGAR FATURA
                            try{
                                if(clienteAtual.getCpf() == null){
                                    throw new YouAreNotLoggedInException();
                                }
                                visualizarClienteAtual(clienteAtual);
                                System.out.println("Visualize suas contas acima. Digite o numero da conta corrente a qual deseja pagar a fatura:");
                                int numeroConta4 = sc.nextInt();
                                ContaCorrente contaCorrente = (ContaCorrente) clienteAtual.selecionarConta(numeroConta4);
                                if(contaCorrente == null){
                                    throw new BankAccountNotFoundException();
                                }
                                else if(contaCorrente.getClass() != ContaCorrente.class){
                                    throw new BankAccountIsNotCurrentAccountException();
                                }
                                System.out.println("Digite o valor que deseja pagar da sua fatura:");
                                double valorPagoDaFatura = sc.nextDouble();
                                contaCorrente.pagarFatura(valorPagoDaFatura);
                                Movimentacao movimentacao = new Movimentacao(valorPagoDaFatura, Movimentacao.TipoDaMovimentacao.SAIDA);
                                contaCorrente.setExtrato(movimentacao);
                                RegistroDeClientes.getInstancia().atualizarCliente(clienteAtual);
                            }catch (BankAccountNotFoundException | BankAccountIsNotCurrentAccountException | YouAreNotLoggedInException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 11: // AJUSTAR LIMITE
                            try{
                                if(clienteAtual.getCpf() == null){
                                    throw new YouAreNotLoggedInException();
                                }
                                visualizarClienteAtual(clienteAtual);
                                System.out.println("Visualize suas contas acima. Digite o numero da conta corrente a qual deseja ajustar limite do cartao de credito:");
                                int numeroConta = sc.nextInt();
                                Conta conta = clienteAtual.selecionarConta(numeroConta);
                                if(conta == null){
                                    throw new BankAccountNotFoundException();
                                }
                                else if(conta.getClass() != ContaCorrente.class){
                                    throw new BankAccountIsNotCurrentAccountException();
                                }
                                ((ContaCorrente) conta).getCartaoCredito().ajustarLimite();
                            }catch (BankAccountNotFoundException | BankAccountIsNotCurrentAccountException | YouAreNotLoggedInException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 12: // PAGAR PARCELA DE EMPRESTIMO
                            try {
                                if(clienteAtual.getCpf() == null){
                                    throw new YouAreNotLoggedInException();
                                }
                                visualizarClienteAtual(clienteAtual);
                                System.out.println("Visualize suas contas acima. Digite o numero da conta que deseja pagar parcela de emprestimo:");
                                int numeroConta = sc.nextInt();
                                Conta conta = clienteAtual.selecionarConta(numeroConta);
                                if(conta == null){
                                    throw new BankAccountNotFoundException();
                                }
                                System.out.println("Digite o valor que deseja pagar:");
                                double valorParcelaEmprestimoPaga = sc.nextDouble();
                                conta.pagarParcelaDeEmprestimo(valorParcelaEmprestimoPaga);
                                Movimentacao movimentacao = new Movimentacao(valorParcelaEmprestimoPaga, Movimentacao.TipoDaMovimentacao.SAIDA);
                                conta.setExtrato(movimentacao);
                                RegistroDeClientes.getInstancia().atualizarCliente(clienteAtual);
                            }catch (YouAreNotLoggedInException | BankAccountNotFoundException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 13: // REQUISITAR EMPRESTIMO
                            try {
                                if(clienteAtual.getCpf() == null){
                                    throw new YouAreNotLoggedInException();
                                }
                                visualizarClienteAtual(clienteAtual);
                                System.out.println("Visualize suas contas acima. Digite o numero da conta que deseja requisitar emprestimo:");
                                int numeroConta6 = sc.nextInt();
                                Conta conta7 = clienteAtual.selecionarConta(numeroConta6);
                                if(conta7 == null){
                                    throw new BankAccountNotFoundException();
                                }
                                System.out.println("Digite o valor do emprestimo que deseja requisitar:");
                                double valorEmprestimoRequisitado = sc.nextDouble();
                                conta7.requisitarEmprestimo(valorEmprestimoRequisitado);
                                RegistroDeClientes.getInstancia().atualizarCliente(clienteAtual);
                            }catch (YouAreNotLoggedInException | BankAccountNotFoundException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 14: // CONVERTER PONTOS EM SALDO
                            try{
                                if(clienteAtual.getCpf() == null){
                                    throw new YouAreNotLoggedInException();
                                }
                                else if(clienteAtual.getClass() != ClienteWinx.class){
                                    throw new ClientFoundIsNotClientWinxException();
                                }
                                else if(((ClienteWinx) clienteAtual).getPontosDeCompra() == 0){
                                    throw new NotEnaughPurchasePoints();
                                }
                                visualizarClienteAtual(clienteAtual);
                                System.out.println("Visualize suas contas acima. Digite o numero da conta que deseja converter pontos em saldo");
                                int numeroConta7 = sc.nextInt();
                                Conta conta8 = clienteAtual.selecionarConta(numeroConta7);
                                if(conta8 == null){
                                    throw new BankAccountNotFoundException();
                                }
                                ((ClienteWinx) clienteAtual).converterPontosEmSaldo(conta8);
                                RegistroDeClientes.getInstancia().atualizarCliente(clienteAtual);
                            }catch (YouAreNotLoggedInException | ClientFoundIsNotClientWinxException | NotEnaughPurchasePoints | BankAccountNotFoundException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 15: // GERAR EXTRATO
                            try {
                                if(clienteAtual.getCpf() == null){
                                    throw new YouAreNotLoggedInException();
                                }
                                visualizarClienteAtual(clienteAtual);
                                System.out.println("Visualize suas contas acima. Digite o numero da conta que deseja gerar extrato:");
                                int numeroConta = sc.nextInt();
                                Conta conta = clienteAtual.selecionarConta(numeroConta);
                                if(conta == null){
                                    throw new BankAccountNotFoundException();
                                }
                                conta.gerarExtrato();
                            }catch (YouAreNotLoggedInException | BankAccountNotFoundException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 16: // GERAR INFORME DE RENDIMENTO
                            try {
                                if(clienteAtual.getCpf() == null){
                                    throw new YouAreNotLoggedInException();
                                }
                                visualizarClienteAtual(clienteAtual);
                                System.out.println("Visualize suas contas acima. Digite o numero da conta que deseja gerar extrato:");
                                int numeroConta = sc.nextInt();
                                Conta conta = clienteAtual.selecionarConta(numeroConta);
                                if(conta == null){
                                    throw new BankAccountNotFoundException();
                                }
                                else if(conta.getClass() != ContaPoupanca.class){
                                    throw new BankAccountIsNotSavingsAccountException();
                                }
                                try {
                                    ((ContaPoupanca) conta).gerarInformeRendimento();
                                }catch (ClassCastException e){
                                    System.out.println("Conta bancaria nao e poupanca.");
                                }

                            }catch (YouAreNotLoggedInException | BankAccountNotFoundException | BankAccountIsNotSavingsAccountException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 17: // EXIBIR LISTA DE CLIENTES
                            RegistroDeClientes.getInstancia().printarListaDeClientes();
                            break;
                        case 18: // RESETAR LISTA DE CLIENTES
                            RegistroDeClientes.getInstancia().limparListaDeClientes();
                            break;
                        case 19: // EXIBIR DADOS DO BANCO
                            Banco.getInstancia().printarBanco();
                            break;
                        default:
                            break;
                    }
                }catch (InputMismatchException e){
                    System.out.println("Opcao invalida. Digite um novo valor.");
                }finally {
                    ArquivoDeClientes.getInstancia().escreverJson(RegistroDeClientes.getInstancia().getClientes());
                    ArquivoDeMesAtual.getInstancia().escreverMesAtual();
                    ArquivoBanco.getInstancia().atualizarArquivo(Banco.getInstancia());
                }
            }
    }
}
