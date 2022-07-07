package br.winxbank.repository;

import br.winxbank.sistemabancario.Banco;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Natália.
 * Classe responsável por persistir dados referentes à organização bancária.
 */
public class ArquivoBanco {

    private static ArquivoBanco instancia;

    /**
     * Método responsável por atualizar a única instância do objeto banco em um arquivo binário.
     * @param banco
     * @throws FileNotFoundException
     */
    public void atualizarArquivo(Banco banco) throws FileNotFoundException {
        ObjectOutputStream ous = null;

        try {
            ous = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("banco.txt")));

            try {

                ous.writeObject(banco);

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
     * Método responsável por atualizar os atributos da instância única de banco conforme instância salva em arquivo ao iniciar o programa.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void construirBanco() throws IOException, ClassNotFoundException {
        ObjectInputStream ins = null;
        ArrayList<Banco> banco = new ArrayList<>();

        try {
            ins = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("banco.txt"))));

            try {
                banco.add((Banco) ins.readObject());

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
            for(Banco banco2 : banco){
                Banco.getInstancia().setBanco(banco2);
            }
        }

    }

    /**
     * Singleton que só permite uma instância do objeto ser criada, quando o atributo estático instancia tem o valor nulo.
     * @return
     */
    public static ArquivoBanco getInstancia() {
        if (instancia == null) {
            instancia = new ArquivoBanco();
        }
        return instancia;
    }
}
