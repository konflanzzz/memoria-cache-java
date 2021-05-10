package memoriaCache;
import java.util.ArrayList;

public class MemoriaCache {
    public int tamanho;
    public ArrayList<Bloco> listaBlocos;
    public int cacheHit;
    //public Bloco bloco;

    public ArrayList<Bloco> inicializarListaBlocos(Integer tamanho){
        this.listaBlocos = new ArrayList<>();
        for (int a = 0; a < tamanho; a++) {
            Bloco blocoMemoria = new Bloco();
            blocoMemoria.id = a;
            listaBlocos.add(blocoMemoria);
        }
        return listaBlocos;
    }


    // Classe para criar os blocos da memoria
    public static class Bloco {
        public int valorArmazenado;
        public int blocoHit;
        public int blocoMiss;
        public int id;
    }


}
