package br.winxbank.repository;

import br.winxbank.tempo.Ano;

import java.io.*;

/**
 * @author Natália.
 * Classe responsável por ler e escrever o mês atual durante a última execução do programa.
 */
public class ArquivoDeMesAtual {

    private static ArquivoDeMesAtual instancia;

    public void escreverMesAtual(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("mesAtual.txt"))) {
            writer.write(Ano.getInstancia().getMesAtual());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void lerMesAtual(){
        try(BufferedReader reader = new BufferedReader(new FileReader("mesAtual.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = reader.readLine();
            }
            String fileAsString = sb.toString().replace("\n", "");
            Ano.getInstancia().setMesAtual(fileAsString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Singleton que só permite uma instância do objeto ser criada, quando o atributo estático instancia tem o valor nulo.
     * @return
     */
    public static ArquivoDeMesAtual getInstancia() {
        if (instancia == null) {
            instancia = new ArquivoDeMesAtual();
        }
        return instancia;
    }
}
