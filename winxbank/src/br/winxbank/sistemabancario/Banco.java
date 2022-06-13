package br.winxbank.sistemabancario;


import br.winxbank.random.MathRandomCsvWithSeed;
import br.winxbank.random.MathRandomNumCartaoWithSeed;
import br.winxbank.random.MathRandomNumContaWithSeed;
import br.winxbank.sistemaclientes.Cliente;
import br.winxbank.sistemaclientes.RegistroDeClientes;

import java.util.Scanner;

/**
 * @author Natália.
 * Classe responsável por administrar um banco.
 */
public class Banco {

    public double basileia;
    public double receitas;
    public double despesas;
    private static Banco instancia;


    //TODO: ver como fazer, talvez tirar esse método.
    public void ressarcirClientesEmCasoDeFalencia(){

    }
    //TODO: ver como fazer o calculo em cima de despesas e receitas.
    public void calcularBasileia(){

    }

    /**
     * Classe responsável por abrir uma nova conta.
     */
    public Conta abrirNovaConta() {
        Scanner sc = new Scanner(System.in);
        int decisao = 0;
        System.out.println("Qual tipo de conta conta deseja abrir? Digite 1 (Corrente) ou 2 (Poupanca)");
        decisao = sc.nextInt();
        if(decisao == 1){
            System.out.println("Você está criando uma conta corrente...");
            int numeroCartao = MathRandomNumCartaoWithSeed.generateRandom();
            int csv = MathRandomCsvWithSeed.generateRandom();
            Cartao cartao = new Cartao(numeroCartao, csv);
            CartaoCredito cartaoCredito = new CartaoCredito(numeroCartao, csv);
            int numeroConta = MathRandomNumContaWithSeed.generateRandom();
            System.out.println("Digite o saldo que deseja colocar na sua conta ");
            double saldo = sc.nextDouble();
            ContaCorrente contaCorrente = new ContaCorrente(numeroConta, saldo, cartao, 0, cartaoCredito);
            cartao.setConta(contaCorrente);
            cartaoCredito.setConta(contaCorrente);
            System.out.println("Sua conta corrente foi criada com sucesso!");
            return contaCorrente;
        }
        else if(decisao == 2){
            System.out.println("Você está criando uma conta poupanca...");
            int numeroCartao = MathRandomNumCartaoWithSeed.generateRandom();
            int csv = MathRandomCsvWithSeed.generateRandom();
            Cartao cartao = new Cartao(numeroCartao, csv);
            int numeroConta = MathRandomNumContaWithSeed.generateRandom();
            System.out.println("Digite o saldo que deseja colocar na sua conta:");
            double saldo = sc.nextDouble();
            ContaPoupanca contaPoupanca = new ContaPoupanca(numeroConta, saldo, cartao, 0);
            cartao.setConta(contaPoupanca);
            System.out.println("Sua conta poupanca foi criada com sucesso!");
            return contaPoupanca;
        }
    return null;
    }

    /**
     * Método responsável por encerrar uma conta de um cliente.
     * @param cliente
     */
    public void fecharConta(Cliente cliente){
        System.out.println("Digite o numero da conta que deseja apagar:");
        Scanner sc = new Scanner(System.in);
        int numeroConta = sc.nextInt();
        Conta contaSelecionada = cliente.selecionarConta(numeroConta);
        cliente.apagarConta(contaSelecionada);
        System.out.println("Sua conta foi apagada com sucesso!");
    }

    /**
     * Método responsável por realizar as movimentações entre banco e conta.
     * Tais quais: acrescentar rendimento em uma poupança e cobrar jurus da fatura do cartao de credito e descontar taxa de uma conta corrente.
     */
    public void movimentarEntreBancoConta(){
        if(!(RegistroDeClientes.getInstancia().getClientes().isEmpty())){
            for(Cliente cliente : RegistroDeClientes.getInstancia().getClientes()){
                for(Conta conta : cliente.getContas()){
                    if(conta.getClass() == ContaPoupanca.class){
                        ((ContaPoupanca) conta).acrescentarRendimento();
                    }
                    else if(conta.getClass() == ContaCorrente.class){
                        ((ContaCorrente) conta).descontarTaxa();
                        ((ContaCorrente) conta).getCartaoCredito().cobrarJurus();
                    }
                }
            }
        }
    }
    public void setReceitas(double valor) {
        if(valor >= 0){
            this.receitas += valor;
        }
    }

    public void setDespesas(double valor) {
        if(valor >= 0){
            this.despesas += valor;
        }
    }

    /**
     * Singleton que só permite uma instância do objeto ser criada, quando o atributo estático instancia tem o valor nulo.
     * @return
     */
    public static Banco getInstancia() {
        if (instancia == null) {
            instancia = new Banco();
        }
        return instancia;
    }
}
