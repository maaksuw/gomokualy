package apu;

/**
 * Yksinkertainen j‰rjestett‰v‰ taulukkolista. 
 * Listan loppuun voi lis‰t‰ alkion ja listalta voi hakea alkion indeksin perusteella.
 * Listan voi j‰rjest‰‰ ja listan voi k‰‰nt‰‰ toisinp‰in.
 * @param <O> Listan alkioiden tulee toteuttaa Comparable<O>-rajapinta.
 */

public class Lista<O extends Comparable<O>> {
    
    private Object[] lista;
    private int loppu;
    private int koko;
    private final double taytto;
    
    public Lista() {
        koko = 10;
        lista = new Object[koko];
        loppu = 0;
        taytto = 0.9;
    }
    
    /**
     * Kertoo listan pituuden.
     * @return listan pituus.
     */
    public int pituus() {
        return loppu;
    }
    
    /**
     * Lis‰‰ alkion listan loppuun.
     * @param o 
     */
    public void lisaa(O o) {
        lista[loppu] = o;
        loppu++;
        if (loppu == (int) (koko * taytto)) suurennaListaa();
    }
    
    /**
     * Palauttaa annetussa indeksiss‰ olevan alkion.
     * Jos annetulla indeksill‰ ei ole alkiota, metodi palauttaa null.
     * @param indeksi
     * @return alkio.
     */
    public O hae(int indeksi) {
        if (indeksi < 0 || indeksi >= loppu) return null;
        return (O) lista[indeksi];
    }
    
    private void suurennaListaa() {
        int uusikoko = koko * 2;
        Object[] uusilista = new Object[uusikoko];
        for (int i = 0; i < loppu; i++) uusilista[i] = lista[i];
        koko = uusikoko;
        lista = uusilista;
    }
    
    /**
     * J‰rjest‰‰ listan alkiot pienimm‰st‰ suurimpaan.
     */
    public void jarjesta() {
        if (loppu <= 20) lisaysjarjesta();
        else {
            Object[] apu = new Object[loppu];
            lomitusjarjesta(0, loppu - 1, apu);
        }
    }
    
    private void lomitusjarjesta(int a, int b, Object[] apu) {
        if (a == b) return;
        int k = (a + b) / 2;
        lomitusjarjesta(a, k, apu);
        lomitusjarjesta(k + 1, b, apu);
        lomita(a, k, k + 1, b, apu);
    }
    
    private void lomita(int a1, int b1, int a2, int b2, Object[] apu) {
        int a = a1;
        int b = b2;
        for (int i = a; i <= b; i++) {
            O ob1 = (O) lista[a1];
            O ob2 = (O) lista[a2];
            if ((a2 > b2) || (a1 <= b1 && ob1.compareTo(ob2) < 0)) {
                apu[i] = lista[a1];
                a1++;
            } else {
                apu[i] = lista[a2];
                a2++;
            }
        }
        for (int i = a; i <= b; i++) {
            lista[i] = apu[i];
        }
    }
    
    private void lisaysjarjesta() {
        for (int i = 1; i < loppu; i++) {
            int j = i - 1;
            O ob1 = (O) lista[j];
            O ob2 = (O) lista[j + 1];
            while (j >= 0 && ob1.compareTo(ob2) > 0) {
                Object apu = lista[j];
                lista[j] = lista[j + 1];
                lista[j + 1] = apu;
                j--;
                if (j >= 0) {
                    ob1 = (O) lista[j];
                    ob2 = (O) lista[j + 1];
                }
            }
        }
    }
    
    /**
     * K‰‰nt‰‰ listan ymp‰ri.
     */
    public void kaanna() {
        for (int i = 0; i < loppu / 2; i++) {
            Object apu = lista[i];
            lista[i] = lista[(loppu - 1) - i];
            lista[(loppu - 1) - i] = apu;
        }
    }
}
