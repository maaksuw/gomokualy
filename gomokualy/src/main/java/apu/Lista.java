
package apu;

public class Lista {
    
    private Object[] lista;
    private int loppu;
    private int koko;
    
    public Lista(){
        koko = 200;
        lista = new Object[koko];
        loppu = 0;
    }
    
    public Object hae(int indeksi) {
        return lista[indeksi];
    }
    
    public void lisaa(Object o){
        lista[loppu] = o;
        loppu++;
        if(loppu == koko - 50) suurennaListaa();
    }
    
    private void suurennaListaa(){
        int koko2 = koko*2;
        Object[] lista2 = new Object[koko2];
        for(int i = 0; i < koko; i++){
            lista2[i] = lista[i];
        }
        koko = koko2;
        lista = lista2;
    }
    
    public void jarjesta() {
        
    }
}
