import memoriaCache.MemoriaCache;

import java.util.ArrayList;
import java.util.Scanner;

public class LRU {
    public static void main(String args[]) {

        // Definicao de variaveis e objetos
        MemoriaCache novaMemoriaCache = new MemoriaCache();
        int valorEntradaAtual, valorRemovido, quantidadeSubstituicao = 0, blocoMemoriaAtual = 0;
        boolean espacoVazio = false;

        // Faz a leitura do tamanho total da memoria
        Scanner leituraTamanhoMemoria = new Scanner(System.in);
        System.out.println("Digite o tamanho da memoria Cache: ");
        novaMemoriaCache.tamanho = Integer.valueOf(leituraTamanhoMemoria.nextLine());

        // Cria os blocos da memoria conforme a quantidade desejada
        MemoriaCache.Bloco[] blocoMemoria = new MemoriaCache.Bloco[novaMemoriaCache.tamanho];
        for (int a = 0; a < novaMemoriaCache.tamanho; a++) {
            blocoMemoria[a] = new MemoriaCache.Bloco();
        }

        //Atribui uma identificação aos blocos de memoria gerados
        for (int a = 0; a < novaMemoriaCache.tamanho; a++) {
            blocoMemoria[a].id = a;
        }

        //Le a quantidade de dados que serão inseridos na memoria cache
        Scanner quantidadeValoresEntrada = new Scanner(System.in);
        System.out.println("Digite quantos valores serão inseridos na memoria cache: ");
        int quantidadeEntrada = Integer.valueOf(quantidadeValoresEntrada.nextLine());

        // Cria um array com os dados que serão inseridos na memoria
        Scanner valorEntrada = new Scanner(System.in);
        int[] arrayValoresEntrada = new int[quantidadeEntrada];
        for (int b = 0; b < quantidadeEntrada; b++) {
            System.out.println("Digite o valor a serem inseridos na memoria cache: ");
            arrayValoresEntrada[b] = Integer.valueOf(valorEntrada.nextLine());
        }

        // Inicia o acesso a memoria
        for (int c = 0; c < arrayValoresEntrada.length; c++) {
            valorEntradaAtual = arrayValoresEntrada[c];
            int maiorMiss = 0;

            // Ve se a memoria esta vazia
            if (novaMemoriaCache.cacheHit == 0) {
                for (int d = 0; d < novaMemoriaCache.tamanho; d++) {
                    if (blocoMemoria[d].valorArmazenado == 0) {
                        espacoVazio = true;
                        blocoMemoriaAtual = d;
                        break;
                    }
                }
                //Se espaço de memória está vazio, armazena valor
                if (espacoVazio == true && blocoMemoria[c].id > 0) {
                    blocoMemoria[blocoMemoriaAtual].valorArmazenado = valorEntradaAtual;
                    for (int e = 0; e < blocoMemoriaAtual; e++) {
                        blocoMemoria[e].blocoMiss++;
                    }
                }
                if (espacoVazio == true) {
                    blocoMemoria[blocoMemoriaAtual].valorArmazenado = valorEntradaAtual;
                }
                else {
                    // Verifica se o numero ja esta na memoria
                    for (int f = 0; f < novaMemoriaCache.tamanho; f++) {
                        if (valorEntradaAtual == blocoMemoria[f].valorArmazenado) {
                            novaMemoriaCache.cacheHit++;
                            for (int g = 0; g < novaMemoriaCache.tamanho; g++) {
                                if (blocoMemoria[g].id == f) {
                                    blocoMemoria[f].blocoMiss = 0;
                                } else {
                                    blocoMemoria[g].blocoMiss++;
                                }
                            }
                        } else {
                            // vai substituir quem esta a mais tempo sem ser utilizado
                            for ( int h = 0; h < novaMemoriaCache.tamanho; h++){
                                while (blocoMemoria[h].blocoMiss >= maiorMiss){
                                    maiorMiss = blocoMemoria[h].blocoMiss;
                                    break;
                                }
//                                for (int i = 0; i < novaMemoriaCache.tamanho; i++) {
//                                    if (valorEntradaAtual == blocoMemoria[i].valorArmazenado) {
//                                        novaMemoriaCache.cacheHit++;
//                                        for (int j = 0; j < novaMemoriaCache.tamanho; j++) {
//                                            if (blocoMemoria[j].id != i) {
//                                                blocoMemoria[i].blocoMiss = 0;
//                                            } else {
//                                                blocoMemoria[j].blocoMiss++;
//                                            }
//                                        }
//                                    }
//                                }
                                if (blocoMemoria[h].blocoMiss == maiorMiss){
                                    blocoMemoria[h].valorArmazenado = valorEntradaAtual;
                                    for (int g = 0; g < novaMemoriaCache.tamanho; g++) {
                                        if (blocoMemoria[g].id == f) {
                                            blocoMemoria[f].blocoMiss = 0;
                                        } else {
                                            blocoMemoria[g].blocoMiss++;
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
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
