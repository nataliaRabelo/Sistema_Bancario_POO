package br.winxbank.sistemabancario;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Carol
 * Esta classe é responsável por representar uma entidade Cartao.
 */
public class Cartao implements Serializable {
    @SerializedName("numero")
    protected int numero;
    @SerializedName("csv")
    protected int csv;
    private static final long serialVersionUID = 1L;

    /**
     * Construtor padrão do cartão
     * @param numero
     * @param csv
     */
    public Cartao(int numero, int csv){
        this.numero = numero;
        this.csv = csv;
    }

    public Cartao(String numero, String csv) {
        this.numero = Integer.parseInt(numero);
        this.csv = Integer.parseInt(csv);
    }


    public int getNumero() {
        return numero;
    }

    public int getCsv() {
        return csv;
    }
}
