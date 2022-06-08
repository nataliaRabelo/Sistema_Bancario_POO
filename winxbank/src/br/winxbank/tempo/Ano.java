package br.winxbank.tempo;

/**
 * @author Dani
 * Esta classe é responsável por representar e controlar o passar dos meses de um ano.
 */
public class Ano {

    //TODO: Registrar mes atual em arquivo desde onde o programa parou.
    private int indexInicial = 0; //index usado na primeira vez que rodar o programa e não existir nenhum arquivo com o registro do mes atual.
    private static Ano instancia;
    private String mesAtual;
    private int indexMesAtual;
    private String[] meses = {"Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    /**
     * Este método é responsável por fazer os meses do ano passar conforme um index de uma lista de meses que incrementa na medida em que o ususario interaje com o programa.
     * @return indexMesAtual
     */
    public int fazerMesPassar(){
        if(this.indexInicial == 0){
            this.indexMesAtual = this.indexInicial;
            this.indexInicial = 1;
        }
        else if (this.indexMesAtual == 11){
            this.indexMesAtual = 0;
        }
        else{
            this.indexMesAtual++;
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
