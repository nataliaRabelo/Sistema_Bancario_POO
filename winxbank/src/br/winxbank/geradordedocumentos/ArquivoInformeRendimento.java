package br.winxbank.geradordedocumentos;

import br.winxbank.exception.BankAccountIsNotSavingsAccountException;
import br.winxbank.sistemabancario.Conta;
import br.winxbank.sistemabancario.ContaPoupanca;
import br.winxbank.sistemabancario.Movimentacao;
import br.winxbank.tempo.Ano;

import java.io.*;
import java.text.DecimalFormat;

/**
 * @author Natália.
 * Classe responsável por gerar arquivos de informes de rendimento.
 */
public class ArquivoInformeRendimento {

    private static ArquivoInformeRendimento instancia;

    /**
     * Método responsável por atualizar o arquivo conforme este for chamado em outros métodos de manipulação do array de registro de clientes na classe CadastroDeClientes.
     *
     * @throws FileNotFoundException
     */
    public void gerarDocumento(Conta conta) throws FileNotFoundException {
        try {
            if(conta.getClass() != ContaPoupanca.class){
                throw new BankAccountIsNotSavingsAccountException();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(conta.getNumeroConta() + "informe.txt"))) {
                writer.write("Mes atual: " + Ano.getInstancia().getMesAtual() + "\n");
                writer.write("Saldo atual: " + new DecimalFormat("0.00").format(conta.getSaldo()) + "\n");
                writer.write("Rendimentos mensais:\n");
                for (Movimentacao movimentacao : ((ContaPoupanca)conta).getInformeDeRendimento()) {
                    writer.write("----------------------------------\n");
                    writer.write(movimentacao.getMesAtual() + "\n");
                    writer.write(new DecimalFormat("0.00").format(movimentacao.getDinheiroMovimentado()) + "\n");
                    writer.write(movimentacao.getTipoDaMovimentacao().toString() + "\n");
                    writer.write("----------------------------------");
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (BankAccountIsNotSavingsAccountException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Singleton que só permite uma instância do objeto ser criada, quando o atributo estático instancia tem o valor nulo.
     * @return
     */
    public static ArquivoInformeRendimento getInstancia() {
        if (instancia == null) {
            instancia = new ArquivoInformeRendimento();
        }
        return instancia;
    }
}
