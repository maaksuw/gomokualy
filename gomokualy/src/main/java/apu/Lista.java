
package apu;

/**
 * Taulukkolista jonka loppuun voi lisätä alkion ja josta voi hakea alkion indeksin perusteella.
 */

public class Lista {
    
    private Object[] lista;
    private int loppu;
    private int koko;
    
    public Lista(){
        koko = 10;
        lista = new Object[koko];
        loppu = 0;
    }
    
    public Object hae(int indeksi) {
        if(indeksi < 0 || indeksi >= loppu) return null;
        return lista[indeksi];
    }
    
    public Object poista() {
        if(loppu == 0) return null;
        loppu--;
        return lista[loppu];
    }
    
    public void lisaa(Object o){
        lista[loppu] = o;
        loppu++;
        if(loppu == (int)(koko*0.75)) suurennaListaa();
    }
    
    private void suurennaListaa(){
        int uusikoko = koko*2;
        Object[] uusilista = new Object[uusikoko];
        for(int i = 0; i < loppu; i++) uusilista[i] = lista[i];
        koko = uusikoko;
        lista = uusilista;
    }
    
    public int pituus() {
        return koko;
    }
    
    public String toString() {
        String s = "[";
        for(int i = 0; i < loppu; i++){
            if(i == loppu - 1) s += lista[i];
            else s += lista[i] + ", ";
        }
        s += "]";
        return s;
    }
    
    public void jarjesta() {
        
    }
}
