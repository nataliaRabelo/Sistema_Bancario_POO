package br.winxbank.tempo;

import br.winxbank.sistemabancario.Banco;

/**
 * @author Dani
 * Esta classe é responsável por representar e controlar o passar dos meses de um ano.
 */
public class Ano {

    private static Ano instancia;
    private String mesAtual;
    private int indexMesAtual;
    private String[] meses = {"Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
    private static long count = 0;

    /**
     * Este método é responsável por fazer os meses do ano passar conforme um index de uma lista de meses que incrementa na medida em que o ususario interaje com o programa.
     * Se o atributo contador for multiplo de 5, o index muda e o valor do mês atual também muda. Este controle foi atribuído para que os meses não passem tão rápido conforme cada loop.
     * Além disso, caso o contador alcance o numero máximo que um long pode armazenar, este é zerado.
     * @return indexMesAtual
     */
    public int fazerMesPassar(){
        if(count % 5 == 0){
            if (this.indexMesAtual == 11){
                this.indexMesAtual = 0;
            }
            else{
                this.indexMesAtual++;
            }
            Banco.getInstancia().movimentarEntreBancoConta();
        }
        if(count == Long.MAX_VALUE){
            count = 0;
        }
        else{
            count++;
        }
        this.mesAtual = this.meses[this.indexMesAtual];
        return this.indexMesAtual;
    }


    public String getMesAtual(){
        return mesAtual;
    }

    public int getIndexMesAtual() {
        return indexMesAtual;
    }

    /**
     * setter usado para leitura de arquivo e atribuição do mes atual com base neste.
     * @param mes
     */
    public void setMesAtual(String mes){
        mesAtual = mes;
        for(int i = 0; i < meses.length; i++){
            if(mes.equals(meses[i])){
                indexMesAtual = i;
            }
        }
    }

    /**
     * Singleton que só permite uma instância do objeto ser criada, quando o atributo estático instancia tem o valor nulo.
     * @return
     */
    public static Ano getInstancia() {
        if (instancia == null) {
            instancia = new Ano();
        }
        return instancia;
    }
}
