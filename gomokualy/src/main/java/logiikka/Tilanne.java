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

    /**
     * Palauttaa tilannetta kuvaavan pelilaudan.
     * @return pelilauta.
     */
    public String getLauta() {
        return lauta;
    }
    
    /**
     * Palauttaa tilanteen arvon.
     * @return arvo.
     */
    public int getArvo() {
        return arvo;
    }

    /**
     * Asettaa tilanteelle arvon.
     * @param arvo 
     */
    public void setArvo(int arvo) {
        this.arvo = arvo;
    }
    
    /**
     * Kertoo onko parametrina saatu olio sama kuin t‰m‰ tilanne.
     * Kaksi tilannetta ovat samat, jos niiden arvot ja laudat ovat samat.
     * @param o
     * @return true, jos tilanteet ovat samat ja false muuten.
     */
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
     * @param t tilanne, johon oliota verrataan.
     * @return -1, jos tilanteen arvo on pienempi kuin parametrin‰ saadun tilanteen arvo, 1 jos arvo on suurempi
     * kuin parametrin arvo ja 0 muuten.
     */
    @Override
    public int compareTo(Tilanne t) {
        if (this.arvo - t.arvo < 0) return -1;
        else if (this.arvo - t.arvo > 0) return 1;
        else return 0;
    }
    
}
