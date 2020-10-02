
package apu;

/**
 * Yksinkertainen j‰rjestett‰v‰ taulukkolista. 
 * Listan loppuun voi lis‰t‰ ja lopusta voi poistaa alkion ja listalta voi hakea alkion indeksin perusteella.
 * Listan voi j‰rjest‰‰ ja listan voi k‰‰nt‰‰ toisinp‰in.
 * @param <O> Listan alkioiden tulee toteuttaa Comparable-rajapinta.
 */

public class Lista<O extends Comparable<O>> {
    
    private Object[] lista;
    private int loppu;
    private int koko;
    
    public Lista(){
        koko = 20;
        lista = new Object[koko];
        loppu = 0;
    }
    
    public O hae(int indeksi) {
        if(indeksi < 0 || indeksi >= loppu) return null;
        return (O) lista[indeksi];
    }
    
    public O poista() {
        if(loppu == 0) return null;
        loppu--;
        return (O) lista[loppu];
    }
    
    public void lisaa(O o){
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
        return loppu;
    }
    
    public String toString() {
        String s = "[";
        for(int i = 0; i < loppu; i++){
            if(i == loppu - 1) s += lista[i];
            else s += lista[i].toString() + ", ";
        }
        s += "]";
        return s;
    }
    
    public void jarjesta() {
        if(loppu <= 20) lisaysjarjesta();
        else {
            Object[] apu = new Object[loppu];
            lomitusjarjesta(0, loppu - 1, apu);
        }
    }
    
    private void lomitusjarjesta(int a, int b, Object[] apu) {
        if(a == b) return;
        int k = (a + b)/2;
        lomitusjarjesta(a, k, apu);
        lomitusjarjesta(k + 1, b, apu);
        lomita(a, k, k + 1, b, apu);
    }
    
    private void lomita(int a1, int b1, int a2, int b2, Object[] apu){
        int a = a1;
        int b = b2;
        for(int i = a; i <= b; i++) {
            O ob1 = (O) lista[a1];
            O ob2 = (O) lista[a2];
            if((a2 > b2) || (a1 <= b1 && ob1.compareTo(ob2) < 0)){
                apu[i] = lista[a1];
                a1++;
            } else {
                apu[i] = lista[a2];
                a2++;
            }
        }
        for(int i = a; i <= b; i++){
            lista[i] = apu[i];
        }
    }
    
    private void lisaysjarjesta() {
        for(int i = 1; i < loppu; i++){
            int j = i - 1;
            O ob1 = (O) lista[j];
            O ob2 = (O) lista[j+1];
            while(j >= 0 && ob1.compareTo(ob2) > 0){
                Object apu = lista[j];
                lista[j] = lista[j+1];
                lista[j+1] = apu;
                j--;
                if(j >= 0){
                    ob1 = (O) lista[j];
                    ob2 = (O) lista[j+1];
                }
            }
        }
    }
    
    public void kaanna() {
        for(int i = 0; i < loppu/2; i++) {
            Object apu = lista[i];
            lista[i] = lista[(loppu - 1) - i];
            lista[(loppu - 1) - i] = apu;
        }
    }
}
