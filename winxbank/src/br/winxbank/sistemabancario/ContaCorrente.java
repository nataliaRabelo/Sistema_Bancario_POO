package br.winxbank.sistemabancario;
import java.util.Scanner;

/**
 * @author Dani
 * Esta classe é responsável por representar uma entidade ContaCorrente.
 */
public class ContaCorrente extends Conta implements OperacoesAutomaticas{

    private CartaoCredito cartaoCredito;

    /**
     * Construtor padrão da classe conta.
     *
     * @param saldo
     * @param cartaoDebito
     * @param dividaDeEmprestimo
     */
    public ContaCorrente(int numeroConta, double saldo, Cartao cartaoDebito, double dividaDeEmprestimo, CartaoCredito cartaoCredito) {
        super(numeroConta, saldo, cartaoDebito, dividaDeEmprestimo);
        this.cartaoCredito = cartaoCredito;
    }
    

    /**
     * Método responsável por pagar fatura com o saldo da conta.
     * @param valor
     */
    public void pagarFatura(double valor){
        this.saldo-=valor;
        this.cartaoCredito.setFatura(-valor);

    }

    /**
     * Método responsável por descontar a taxa de uma conta corrente.
     */
    public void descontarTaxa(){
        this.saldo -= taxaManutencaoConta;
        movimentacaoBancaria(taxaManutencaoConta);
        Movimentacao movimentacao = new Movimentacao(taxaManutencaoConta, Movimentacao.TipoDaMovimentacao.SAIDA);
        this.setExtrato(movimentacao);

    }

    /**
     * Método da interface MovimentacaoBancaria sobrescrito responsável por movimentar dinheiro ao banco.
     * @param valor
     */
    @Override
    public void movimentacaoBancaria(double valor) {
        Banco.getInstancia().setReceitas(valor);
    }


    public CartaoCredito getCartaoCredito() {
        return cartaoCredito;
    }

    public String getTipoDaConta() {
        String tipoDaConta = "Corrente";
        return tipoDaConta;
    }

    /**
     * Método abstrato da classe conta sobrescrito responsável por realizar uma compra.
     * @param valor
     */
    @Override
    public void comprar(double valor) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Você deseja pagar no debito ou no credito? 1 (debito) ou 2 (credito)");
        int decisao = sc.nextInt();
        if (decisao == 1){
            System.out.println("------------------------------------------------");
            System.out.println(this.cartao.getNumero() + "\n" + this.cartao.csv);
            System.out.println("------------------------------------------------");
            System.out.println("Este e o cartao que deseja utilizar? Digite 1 (confirmar)");
            int decisao2 = sc.nextInt();
            if(decisao2 == 1){
                cartao.debitar(this, valor);
                System.out.println("Valor debitado.");
            }
            else{
                System.out.println("Compra cancelada. Efetue a compra novamente.");
            }
        }
        else if(decisao == 2){
            System.out.println("------------------------------------------------");
            System.out.println(this.cartaoCredito.getNumero() + "\n" + this.cartaoCredito.csv);
            System.out.println("------------------------------------------------");
            System.out.println("Este e o cartao que deseja utilizar? Digite 1 (confirmar)");
            int decisao2 = sc.nextInt();
            if(decisao2 == 1){
                this.cartaoCredito.creditar(valor);
                System.out.println("Valor creditado.");
            }
            else{
                System.out.println("Compra cancelada. Efetue a compra novamente.");
            }
        }
    }
}
