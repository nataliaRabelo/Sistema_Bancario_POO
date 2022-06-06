package br.winxbank.sistemabancario;


import br.winxbank.random.MathRandomCsvWithSeed;
import br.winxbank.random.MathRandomNumCartaoWithSeed;
import br.winxbank.random.MathRandomNumContaWithSeed;

import java.util.Scanner;

/**
 * @author Natália.
 * Classe responsável por administrar um banco.
 */
public class Banco {

    public final String nome = "WinxBank";
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
            //TODO: tornar csv, numeroCartao e numeroConta randomicos e verificar se ja existe o codigo em alguma conta no registro.
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
            return contaCorrente;
        }
        else if(decisao == 2){
            //TODO: tornar csv, numeroCartao e numeroConta randomicos e verificar se ja existe o codigo em alguma conta no registro.
            System.out.println("Você está criando uma conta poupanca...");
            int numeroCartao = MathRandomNumCartaoWithSeed.generateRandom();
            int csv = MathRandomCsvWithSeed.generateRandom();
            Cartao cartao = new Cartao(numeroCartao, csv);
            int numeroConta = MathRandomNumContaWithSeed.generateRandom();
            System.out.println("Digite o saldo que deseja colocar na sua conta ");
            double saldo = sc.nextDouble();
            ContaPoupanca contaPoupanca = new ContaPoupanca(numeroConta, saldo, cartao, 0);
            cartao.setConta(contaPoupanca);
            return contaPoupanca;
        }
    return null;
    }
    /**
     * Singleton que só permite uma instância do objeto ser criada, quando o atributo estático instanciaEvento tem o valor nulo.
     * @return
     */
    public static Banco getInstancia() {
        if (instancia == null) {
            instancia = new Banco();
        }
        return instancia;
    }
}
