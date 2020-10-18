
package logiikka;

/**
 * Yhdist‰‰ kokonaisen pelitilanteen ja sille lasketun numeroarvon yhdeksi luokaksi.
 * Pelitilanne on pelilauta merkkijonomuodossa ja numeroarvo on tilanteelle annettu hyvyysarvio.
 */
public class Tilanne implements Comparable<Tilanne> {
    
    private String lauta;
    private int arvo;
    
    public Tilanne(String lauta, int a) {
        this.lauta = lauta;
        arvo = a;
    }

    public String getLauta() {
        return lauta;
    }
    
    public int getArvo() {
        return arvo;
    }

    public void setArvo(int arvo) {
        this.arvo = arvo;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Tilanne)) return false;
        Tilanne t = (Tilanne) o;
        if (t.getArvo() == this.getArvo() && t.getLauta().equals(this.getLauta())) return true;
        return true;
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
        if (this.arvo - t.arvo < 0) return -1;
        else if (this.arvo - t.arvo > 0) return 1;
        else return 0;
    }
    
}
