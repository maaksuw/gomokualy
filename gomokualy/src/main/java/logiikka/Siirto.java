
package logiikka;

/**
 * Yhdist‰‰ yksitt‰isen siirron ja sille lasketun numeroarvon yhdeksi luokaksi.
 * Siirto kuvataan koordinaateilla ja numeroarvo on siirrolle annettu hyvyysarvio.
 */
public class Siirto implements Comparable<Siirto> {
    
    private int x;
    private int y;
    private long arvo;
    
    public Siirto(long arvo, int x, int y) {
        this.arvo = arvo;
        this.x = x;
        this.y = y;
    }

    public long getArvo() {
        return arvo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setArvo(int arvo) {
        this.arvo = arvo;
    }
    
    /**
     * Siirrot j‰rjestet‰‰n niiden arvon mukaan.
     * Metodi palauttaa -1, jos Siirron arvo on pienempi kuin parametrin‰ saadun Siirron arvo, 1 jos arvo on suurempi
     * kuin parametrin arvo ja 0 muuten.
     * @param k Siirto, johon oliota verrataan.
     * @return -1, 0 tai 1.
     */
    public int compareTo(Siirto k) {
        if(this.arvo -  k.arvo < 0) return -1;
        else if (this.arvo -  k.arvo > 0) return 1;
        return 0; 
    }

    @Override
    public String toString() {
        return "(" + arvo + ", " + x + ", " + y + ")";
    }
    
}
