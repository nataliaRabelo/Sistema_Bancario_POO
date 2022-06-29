package br.winxbank.random;

import java.util.HashSet;
import java.util.Random;

/**
 * @author Natália.
 * Esta classe é responsável por conter métodos relacionados à geração de números aleatórios e controlar a repetição desses numeros.
 */
public class RandomNumberGenerator {
    private static HashSet<Integer> numerosCsv = new HashSet<Integer>();
    private static HashSet<Integer> numerosConta = new HashSet<Integer>();
    private static HashSet<Integer> numerosCartao = new HashSet<Integer>();
    /**
     * Este método é responsável por determinar um número mínimo, máximo e inicializar uma seed para geração de numeros aleatórios a partir dessa seed.
     * Se o numero aleatorio gerado existir no HashSet, retorna para executar a função novamente por recursão, caso contrário, retorna o valor aleatório.
     * @return int random;
     */


    public static void printarHashSets(){
        System.out.println("HashSetCsv:");
        for(Integer n : numerosCsv){
            System.out.println(n);
        }
        System.out.println("HashSetConta:");
        for(Integer n : numerosConta){
            System.out.println(n);
        }
        System.out.println("HashSetCartao:");
        for(Integer n : numerosCartao){
            System.out.println(n);
        }
    }

    public static int gerarCsv(){
        Random randomNum = new Random();
        int random = randomNum.nextInt(100, 999);
        while (numerosCsv.contains(random)){
            random = randomNum.nextInt(100, 999);
        }
        numerosCsv.add(random);
        return random;
    }

    public static int gerarNumConta(){
        Random randomNum = new Random();
        int random = randomNum.nextInt(10000, 99999);
        while (numerosConta.contains(random)){
            random = randomNum.nextInt(10000, 99999);
        }
        numerosConta.add(random);
        return random;
    }

    public static int gerarNumCartao(){
        Random randomNum = new Random();
        int random = randomNum.nextInt(1000, 9999);
        while (numerosCartao.contains(random)){
            random = randomNum.nextInt(1000, 9999);
        }
        numerosCartao.add(random);
        return random;
    }

    public static void setNumerosCsv(int numero) {
        numerosCsv.add(numero);
    }

    public static void setNumerosConta(int numero) {
        numerosConta.add(numero);
    }

    public static void setNumerosCartao(int numero) {
        numerosCartao.add(numero);
    }

    public static HashSet<Integer> getNumerosCartao() {
        return numerosCartao;
    }

    public static HashSet<Integer> getNumerosConta() {
        return numerosConta;
    }

    public static HashSet<Integer> getNumerosCsv() {
        return numerosCsv;
    }
}
