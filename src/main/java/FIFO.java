import memoriaCache.MemoriaCache;
import java.util.ArrayList;
import java.util.Scanner;

public class FIFO {
   public static void main (String args[]) {

      // Definicao de variaveis e objetos
      MemoriaCache novaMemoriaCache = new MemoriaCache();
      int valorEntradaAtual, valorRemovido, quantidadeSubstituicao = 0, blocoMemoriaAtual = 0;
      ArrayList<Integer> listaFIFO = new ArrayList<>();
      boolean espacoVazio = false;

      // Faz a leitura do tamanho total da memoria
      Scanner leituraTamanhoMemoria = new Scanner(System.in);
      System.out.println("Digite o tamanho da memoria Cache: ");
      novaMemoriaCache.tamanho = Integer.valueOf(leituraTamanhoMemoria.nextLine());

      // Valores para teste
      //novaMemoriaCache.tamanho = 4;
      
      // Cria os blocos da memoria conforme a quantidade desejada
      MemoriaCache.Bloco [] blocoMemoria = new MemoriaCache.Bloco[novaMemoriaCache.tamanho];
      for (int a = 0; a < novaMemoriaCache.tamanho; a++) {
         blocoMemoria[a] = new MemoriaCache.Bloco();
      }

      // Le a quantidade de dados que serao inseridos na memoria cache
      Scanner quantidadeValoresEntrada = new Scanner(System.in);
      System.out.println("Digite quantos valores serão inseridos na memoria cache: ");
      int quantidadeEntrada = Integer.valueOf(quantidadeValoresEntrada.nextLine());

      // Valores para testes
      //int[] arrayValoresEntrada = {1,3,4,7,6,4,3,6,8,3,4,1,8,3,4,9,8}; 

      // Cria um array com os dados que serao inseridos na memoria
      Scanner valorEntrada = new Scanner(System.in);
      int[] arrayValoresEntrada = new int[quantidadeEntrada];
      for (int b=0; b<quantidadeEntrada; b++) {
         System.out.println("Digite o valor a serem inseridos na memoria cache: ");
         arrayValoresEntrada[b] = Integer.valueOf(valorEntrada.nextLine());
      }

      // Inicia o acesso a memoria
      for (int c=0; c < arrayValoresEntrada.length; c++) {
      
         valorEntradaAtual = arrayValoresEntrada[c];

         // Verifica se o valor ja esta na memoria
         for (int d=0; d < novaMemoriaCache.tamanho; d++) {
            if (valorEntradaAtual == blocoMemoria[d].valorArmazenado) {
               novaMemoriaCache.cacheHit++;
            }
         }

         // Verifica se a memoria esta vazia
         if (novaMemoriaCache.cacheHit == 0) {
            for ( int e = 0; e < novaMemoriaCache.tamanho; e++) {
               if ( blocoMemoria[e].valorArmazenado == 0) {
                  espacoVazio = true;
                  blocoMemoriaAtual = e;
                  break;
               }
            }

            // Se espaco de memoria esta vazio, armazena o valor
            if ( espacoVazio == true) {
               blocoMemoria[blocoMemoriaAtual].valorArmazenado = valorEntradaAtual;
               listaFIFO.add(arrayValoresEntrada[c]);
            }
            else {

               // Adiciona os valores na fila FIFO
               listaFIFO.add(arrayValoresEntrada[c]);
               valorRemovido = listaFIFO.get(0);
               listaFIFO.remove(0);

               // Substitui o valor quando a memoria estiver cheia
               for (int f = 0; f < novaMemoriaCache.tamanho; f++) {
                  if (valorRemovido == blocoMemoria[f].valorArmazenado) {
                     blocoMemoria[f].valorArmazenado = valorEntradaAtual;
                     quantidadeSubstituicao++;
                  }
               }
            }
         }

         // Faz o reset das variaveis auxiliares
         espacoVazio = false;
         novaMemoriaCache.cacheHit = 0;
      }

      // Apresenta a memoria cache no final da execucao do algoritmo FIFO
      System.out.println("Memoria cache: ");
      for (int f = 0; f < novaMemoriaCache.tamanho; f++)
      {
         System.out.println(blocoMemoria[f].valorArmazenado);
      }

      // Apresenta a fila no final da execucao do algoritmo FIFO
      System.out.println("Fila FIFO: ");
      int g = 0;
      for (int elemento: listaFIFO) {
         System.out.println(elemento);
         g++;
      }

      // Apresenta o numero de substituicoes realizadas na memoria CACHE
      System.out.println("Quantidade de substituicoes realizadas: " + quantidadeSubstituicao);
   }
}
