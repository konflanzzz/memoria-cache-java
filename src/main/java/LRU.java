import memoriaCache.MemoriaCache;

import java.util.ArrayList;
import java.util.Scanner;

public class LRU {
    public static void main(String args[]) {

        // Definicao de variaveis e objetos
        MemoriaCache novaMemoriaCache = new MemoriaCache();
        int valorEntradaAtual, valorRemovido, quantidadeSubstituicao = 0, blocoMemoriaAtual = 0, maiorMiss = 0;
        boolean espacoVazio = false;

        // Faz a leitura do tamanho total da memoria
//        Scanner leituraTamanhoMemoria = new Scanner(System.in);
//        System.out.println("Digite o tamanho da memoria Cache: ");
//        novaMemoriaCache.tamanho = Integer.valueOf(leituraTamanhoMemoria.nextLine());
        novaMemoriaCache.tamanho = 4;

        // Cria os blocos da memoria conforme a quantidade desejada
        MemoriaCache.Bloco[] blocoMemoria = new MemoriaCache.Bloco[novaMemoriaCache.tamanho];
        for (int a = 0; a < novaMemoriaCache.tamanho; a++) {
            blocoMemoria[a] = new MemoriaCache.Bloco();
        }

        //Atribui uma identificação aos blocos de memoria gerados
        for (int b = 0; b < novaMemoriaCache.tamanho; b++) {
            blocoMemoria[b].id = b;
        }

        //Le a quantidade de dados que serão inseridos na memoria cache
//        Scanner quantidadeValoresEntrada = new Scanner(System.in);
//        System.out.println("Digite quantos valores serão inseridos na memoria cache: ");
//        int quantidadeEntrada = Integer.valueOf(quantidadeValoresEntrada.nextLine());
        int quantidadeEntrada = 17;

        // Cria um array com os dados que serão inseridos na memoria
//        Scanner valorEntrada = new Scanner(System.in);
//        int[] arrayValoresEntrada = new int[quantidadeEntrada];
//        for (int c = 0; c < quantidadeEntrada; c++) {
//            System.out.println("Digite o valor a serem inseridos na memoria cache: ");
//            arrayValoresEntrada[c] = Integer.valueOf(valorEntrada.nextLine());
//        }
        int[] arrayValoresEntrada = {1,3,4,7,6,4,3,6,8,3,4,1,8,3,4,9,8};

        // Inicia o acesso a memoria
        for (int d = 0; d < arrayValoresEntrada.length; d++) {
            valorEntradaAtual = arrayValoresEntrada[d];

            // Ve se a memoria esta vazia
            if (novaMemoriaCache.cacheHit == 0) {
                for (int e = 0; e < novaMemoriaCache.tamanho; e++) {
                    if (blocoMemoria[e].valorArmazenado == 0) {
                        espacoVazio = true;
                        blocoMemoriaAtual = e;
                        break;
                    }
                }

                //Se espaço de memória está vazio, armazena valor
                if (espacoVazio == true && blocoMemoria[d].id > 0) {
                    blocoMemoria[blocoMemoriaAtual].valorArmazenado = valorEntradaAtual;
                    for (int f = 0; f < blocoMemoriaAtual; f++) {
                        blocoMemoria[f].blocoMiss++;
                    }
                }

                // espacos vazios iniciais
                if (espacoVazio == true) {
                    blocoMemoria[blocoMemoriaAtual].valorArmazenado = valorEntradaAtual;
                }

                // se memoria estiver cheia
                else {
                    // Verifica se o numero ja esta na memoria
                    for (int g = 0; g < novaMemoriaCache.tamanho; g++) {
                        // loop
                        if (valorEntradaAtual == blocoMemoria[g].valorArmazenado) {
                            novaMemoriaCache.cacheHit++;
                            for (int h = 0; h < novaMemoriaCache.tamanho; h++) {
                                if (blocoMemoria[h].id == g) {
                                    blocoMemoria[h].blocoMiss = 0;
                                } else {
                                    blocoMemoria[g].blocoMiss++;
                                }
                            }break; //já achou o numero na memoria
                        }
                        else { // se ele nao estiver na memoria
                            // vai substituir por quem tem o maior Miss
                            for (int i = 0; i < novaMemoriaCache.tamanho; i++) {
                                //descobre quem tem o maior Miss
                                for (int j = 0; j < novaMemoriaCache.tamanho; j++) {
                                    if (blocoMemoria[j].blocoMiss > maiorMiss) {
                                        maiorMiss = blocoMemoria[j].blocoMiss;
                                    }
                                }
                                // substitui os valores
                                if (blocoMemoria[i].blocoMiss == maiorMiss) {
                                    blocoMemoria[i].valorArmazenado = valorEntradaAtual;
                                    for (int l = 0; l < novaMemoriaCache.tamanho; l++) {
                                        if (blocoMemoria[l].id == i) {
                                            blocoMemoria[i].blocoMiss = 0;
                                        } else {
                                            blocoMemoria[l].blocoMiss++;
                                        }
                                    }
                                }break;
                            }
                        }break;
                    }
                    maiorMiss = 0;
                }
            }
            espacoVazio = false;
            novaMemoriaCache.cacheHit = 0;
        }

        //Memória cache no final da execução do algoritmo FIFO
        System.out.println("Memória cache: ");
        for (int f = 0; f < novaMemoriaCache.tamanho; f++) {
            System.out.println(blocoMemoria[f].valorArmazenado);
        }

        //Número de substituições realizadas
        System.out.println("Quantidade de substituições: " + quantidadeSubstituicao);
    }
}
