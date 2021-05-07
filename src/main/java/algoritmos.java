import memoriaCache.MemoriaCache;

import java.util.ArrayList;
import java.util.Scanner;

public class algoritmos {
   public static void main (String args[]) {

      // Definicao de variaveis e objetos
      MemoriaCache novaMemoriaCache = new MemoriaCache();
      int valorEntradaAtual, valorRemovido, quantidadeSubstituicao=0, blocoMemoriaAtual=0;
      ArrayList<Integer> listaFIFO = new ArrayList<>();
      boolean espacoVazio = false;

      // Faz a leitura do tamanho total da memoria
      Scanner leituraTamanhoMemoria = new Scanner(System.in);
      System.out.println("Digite o tamanho da memoria Cache: ");
      novaMemoriaCache.tamanho = Integer.valueOf(leituraTamanhoMemoria.nextLine());

      // Cria os blocos da memoria conforme a quantidade desejada
      MemoriaCache.Bloco [] blocoMemoria = new MemoriaCache.Bloco[novaMemoriaCache.tamanho];
      for (int a = 0; a < novaMemoriaCache.tamanho; a++) {
         blocoMemoria[a] = new MemoriaCache.Bloco();
      }

      //Atribui uma identificação aos blocos de memoria gerados
      for (int a = 0; a < novaMemoriaCache.tamanho; a++) {
         blocoMemoria[a].id = a;
      }

      // Le a quantidade de dados que serão inseridos na memoria cache
//      Scanner quantidadeValoresEntrada = new Scanner(System.in);
//      System.out.println("Digite quantos valores serão inseridos na memoria cache: ");
//      int quantidadeEntrada = Integer.valueOf(quantidadeValoresEntrada.nextLine());
      int quantidadeEntrada = 17;

      // Cria um array com os dados que serão inseridos na memoria

//      Scanner valorEntrada = new Scanner(System.in);
//      int[] arrayValoresEntrada = new int[quantidadeEntrada];
//      for (int b=0; b<quantidadeEntrada; b++) {
//         System.out.println("Digite o valor a serem inseridos na memoria cache: ");
//         arrayValoresEntrada[b] = Integer.valueOf(valorEntrada.nextLine());
//      }
      int arrayValoresEntrada[] = {1,3,4,7,6,4,3,6,8,3,4,1,8,3,4,9,8};

      // Inicia o acesso a memoria
      for (int c=0; c < arrayValoresEntrada.length; c++) {
         valorEntradaAtual = arrayValoresEntrada[c];

         // Verifica se o valorEntradaAtual já está na memória
         for (int d=0; d< novaMemoriaCache.tamanho; d++) {
            if (valorEntradaAtual == blocoMemoria[d].valorArmazenado) {
               novaMemoriaCache.cacheHit++;
            }
         }

         // Se espaço de memória está vazio, armazena valor
         if (novaMemoriaCache.cacheHit == 0 && c < novaMemoriaCache.tamanho ) {
            listaFIFO.add(valorEntradaAtual);
            blocoMemoria[c].valorArmazenado = valorEntradaAtual;
         }

         // Substitui valor quando a memoria estiver cheia
         if ((novaMemoriaCache.cacheHit == 0) && (c > novaMemoriaCache.tamanho) ) {
            listaFIFO.add(valorEntradaAtual);
            valorRemovido = listaFIFO.get(0);
            listaFIFO.remove(0);

            for (int e = 0; e < novaMemoriaCache.tamanho; e++) {
               if (valorRemovido == blocoMemoria[e].valorArmazenado) {
                  blocoMemoria[e].valorArmazenado = valorEntradaAtual;
                  quantidadeSubstituicao++;
               }
            }
         }
         novaMemoriaCache.cacheHit = 0;
      }

      //Memória cache no final da execução do algoritmo FIFO
      System.out.println("Memória cache: ");
      for (int f = 0; f < novaMemoriaCache.tamanho; f++)
      {
         System.out.println(blocoMemoria[f].valorArmazenado);
      }

      //Fila no final da execução do algoritmo FIFO
      System.out.println("Fila FIFO: ");
      int g = 0;
      for (int elemento: listaFIFO) {
         System.out.println(elemento);
         g++;
      }

      //Número de substituições realizadas
      System.out.println("Quantidade de substituições: " + quantidadeSubstituicao);
   }
}
