
package logiikka;

/**
 * Yhdist‰‰ kokonaisen pelitilanteen ja sille lasketun numeroarvon yhdeksi luokaksi.
 * Pelitilanne on pelilauta merkkijonomuodossa ja numeroarvo on tilanteelle annettu hyvyysarvio.
 */
public class Tilanne implements Comparable<Tilanne> {
    
    private String lauta;
    private long arvo;
    
    public Tilanne(String s, long a) {
        lauta = s;
        arvo = a;
    }

    public String getLauta() {
        return lauta;
    }
    
    public long getArvo() {
        return arvo;
    }

    public void setArvo(long arvo) {
        this.arvo = arvo;
    }

    /**
     * Tilanteet j‰rjestet‰‰n niiden arvon mukaan.
     * Metodi palauttaa -1, jos Tilanteen arvo on pienempi kuin parametrin‰ saadun Tilanteen arvo, 1 jos arvo on suurempi
     * kuin parametrin arvo ja 0 muuten.
     * @param t Tilanne, johon oliota verrataan.
     * @return -1, 0 tai 1.
     */
    @Override
    public int compareTo(Tilanne t) {
        if(this.arvo - t.arvo < 0) return -1;
        else if (this.arvo - t.arvo > 0) return 1;
        else return 0;
    }
    
}
