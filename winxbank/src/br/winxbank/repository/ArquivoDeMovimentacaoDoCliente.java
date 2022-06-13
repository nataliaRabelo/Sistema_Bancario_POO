package br.winxbank.repository;

import br.winxbank.sistemabancario.Movimentacao;
import br.winxbank.sistemaclientes.Cliente;

import java.io.*;

/**
 * @author Natália.
 * Classe responsável por persistir dados de movimentacoes de clientes em um arquivo para cada cliente.
 */
public class ArquivoDeMovimentacaoDoCliente {

    //TODO: TENTAR TIRAR NUMEROCONTA DO PARAMETRO E PEGAR DE OUTRA FORMA.
    public void lerMovimentacaoDeArquivo(Cliente cliente, int numeroConta){
        String nomeDoCliente = cliente.getNome();
        String cpfDoCliente = cliente.getCpf();
        try{
            FileInputStream fileStream = new FileInputStream("movimentacoesDe" + nomeDoCliente + "Conta" + numeroConta + ".txt");
            ObjectInputStream objStream = new ObjectInputStream(fileStream);

            Movimentacao movimentacao = (Movimentacao) objStream.readObject();
            //TODO: LINKAR ISSO DE ALGUMA FORMA AO GERAR EXTRATO
            //TODO: FAZER UM FILTRO DE ALGUMA FORMA E LINKAR ISSO AO INFORME DE RENDIMENTO
            movimentacao.getNomeDoCliente();
            movimentacao.getCpf();
            movimentacao.getNumeroConta();
            movimentacao.getNumeroCartao();
            movimentacao.getDinheiroMovimentado();
            movimentacao.getRendimento();
            movimentacao.getDesconto();
            movimentacao.getTipoDaMovimentacao();
            objStream.close();

        }catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
            //TODO: Colocar mensagem decente depois
        }
    }
    public void salvarMovimentacaoEmArquivo(Cliente cliente, int numeroConta, int numeroCartao, double dinheiroMovimentado) throws IOException {
        //TODO: CRIAR A MOVIMENTACAO CONFORME O QUE ESTIVER ROLANDO NO MOMENTO
        //TODO: Colocar tudo nesse construtor de baixo.
        String nomeDoCliente = cliente.getNome();
        String cpfDoCliente = cliente.getCpf();
        //TODO: FAZER METODO DE CALCULAR RENDIMENTO E CALCULAR DESCONTO E INSERIR NO CONSTRUTOR.
        //TODO: VER O QUE FAZER PARA DECIDIR SE OPERACAO EH ENUM ENTRADA OU SAIDA.
        Movimentacao movimentacao = new Movimentacao();
        try {
            FileOutputStream file = new FileOutputStream ("movimentacoesDe" + nomeDoCliente + "Conta" + numeroConta + ".txt");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(movimentacao);
            output.close();
        }catch (IOException e){
            System.out.println(e.getMessage()); //TODO: COLOCAR UMA MENSAGEM DECENTE
        }
    }
}
