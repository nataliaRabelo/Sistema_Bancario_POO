package br.winxbank.repository;

import br.winxbank.sistemaclientes.Cliente;
import br.winxbank.sistemaclientes.RegistroDeClientes;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Carol
 * Classe responsável por persistir dados de clientes em um arquivo.
 */
public class ArquivoDeClientes {

    private static ArquivoDeClientes instancia;

    /**
     * Método responsável por atualizar o arquivo conforme este for chamado em outros métodos de manipulação do array de registro de clientes na classe CadastroDeClientes.
     * @param clientes
     * @throws FileNotFoundException
     */
    public void atualizarArquivo(ArrayList<Cliente> clientes) throws FileNotFoundException {
        ObjectOutputStream ous = null;

        try {
            ous = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("clientes.txt")));

            try {

                ous.writeObject(clientes);

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
     * Método responsável por construir o array de registro de clientes conforme o arquivo salvo.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void construirArrayDeOndeParou() throws IOException, ClassNotFoundException {
        ObjectInputStream ins = null;
        ArrayList<Cliente> clientes = null;

        try {
            ins = new ObjectInputStream(new BufferedInputStream(new FileInputStream("clientes.txt")));

            try {
                clientes = (ArrayList<Cliente>)ins.readObject();

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
            RegistroDeClientes.getInstancia().setClientes(clientes);
        }

    }


    /**
     * Singleton que só permite uma instância do objeto ser criada, quando o atributo estático instancia tem o valor nulo.
     * @return
     */
    public static ArquivoDeClientes getInstancia() {
        if (instancia == null) {
            instancia = new ArquivoDeClientes
                    ();
        }
        return instancia;
    }

}
