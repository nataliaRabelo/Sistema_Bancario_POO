package br.winxbank.random;

import java.util.HashSet;

/**
 * @author Natália Bruno Rabelo.
 * Esta classe é responsável por conter métodos relacionados à geração de números aleatórios e controlar a repetição desses numeros.
 */
public class MathRandomNumCartaoWithSeed {
    static HashSet<Integer> numeros = new HashSet<Integer>();
    /**
     * Este método é responsável por determinar um número mínimo, máximo e inicializar uma seed para geração de numeros aleatórios a partir dessa seed.
     * Se o numero aleatorio gerado existir no HashSet, retorna para executar a função novamente por recursão, caso contrário, retorna o valor aleatório.
     * @return int random;
     */
    public static int generateRandom(){

        int min = 1000;
        int max = 9999;
        int seed = 3;

        int random = randomNext(min, max, seed);
        while (numeros.contains(random)){
            random = randomNext(min, max, seed);
        }
        return random;
    }

    /**
     * Este método é responsável por gerar números aleatórios a partir do mínimo, máximo e a seed determinadas na função generateRandomFatura().
     * @param min
     * @param max
     * @param seed
     * @return
     */
    private static int randomNext(int min, int max, int seed){

        int count = (max - min) / seed;

        int random = ((int)(count * Math.random()) * seed) + min;

        return random;
    }
}
