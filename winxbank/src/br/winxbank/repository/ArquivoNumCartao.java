package br.winxbank.repository;

import br.winxbank.random.MathRandomCsvWithSeed;

import java.io.*;
import java.util.HashSet;

public class ArquivoNumCartao {

    private static ArquivoNumCartao instancia;

    /**
     * Método responsável por atualizar o arquivo de numeros dos cartoes.
     * @throws FileNotFoundException
     */
    public void atualizarArquivo(HashSet<Integer> csv) throws FileNotFoundException {
        ObjectOutputStream ous = null;

        try {
            ous = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("numCartao.txt")));

            try {

                ous.writeObject(csv);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } finally {

            if (ous != null) {
                try {
                    ous.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Método responsável por construir o hashset de numeros dos cartoes conforme o arquivo salvo.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void construirArrayDeOndeParou() throws IOException, ClassNotFoundException {
        ObjectInputStream ins = null;
        HashSet<Integer> numeros = null;

        try {
            ins = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("numCartao.txt"))));

            try {
                numeros = (HashSet<Integer>)ins.readObject();

            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            MathRandomCsvWithSeed.setNumeros(numeros);
        }

    }


    /**
     * Singleton que só permite uma instância do objeto ser criada, quando o atributo estático instancia tem o valor nulo.
     * @return
     */
    public static ArquivoNumCartao getInstancia() {
        if (instancia == null) {
            instancia = new ArquivoNumCartao();
        }
        return instancia;
    }
}
