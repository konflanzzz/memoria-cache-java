import memoriaCache.memoriaCache;
import java.util.Scanner;

public class algoritmos {
   public static void main (String args[]) {

      memoriaCache novaMemoriaCache = new memoriaCache();

      // Faz a leitura do tamanho total da memoria
      Scanner leituraTamanhoMemoria = new Scanner(System.in);
      System.out.println("Digite o tamanho da memoria Cache: ");
      novaMemoriaCache.tamanho = Integer.valueOf(leituraTamanhoMemoria.nextLine());

      // Cria os blocos da memoria conforme a quantidade desejada
      memoriaCache.Bloco [] blocoMemoria = new memoriaCache.Bloco[novaMemoriaCache.tamanho];
      //memoriaCache.Memoria [] Memoria = new memoriaCache.Memoria[0];
      for (int a = 0; a < novaMemoriaCache.tamanho; a++) {
         blocoMemoria[a] = new memoriaCache.Bloco();
      }

      //Atribui uma identificação aos blocos de memoria gerados
      for (int a = 0; a < novaMemoriaCache.tamanho; a++) {
         blocoMemoria[a] = new memoriaCache.Bloco();
         blocoMemoria[a].id = a;
      }

      // Le a quantidade de dados que serão inseridos na memoria cache
      Scanner quantidadeValoresEntrada = new Scanner(System.in);
      System.out.println("Digite qauntos valores serão inseridos na memoria cache: ");
      int quantidadeEntrada = Integer.valueOf(quantidadeValoresEntrada.nextLine());

      // Cria um array com os dados que serão inseridos na memoria
      Scanner valorEntrada = new Scanner(System.in);
      int[] arrayValoresEntrada = new int[quantidadeEntrada];
      for (int b=0; b<quantidadeEntrada; b++) {
         System.out.println("Digite o valor a serem inseridos na memoria cache: ");
         arrayValoresEntrada[b] = Integer.valueOf(valorEntrada.nextLine());
      }

      System.out.println();
   }
}
