
package logiikka;

/**
 * Yhdist‰‰ yksitt‰isen siirron ja sille lasketun numeroarvon yhdeksi luokaksi.
 * Siirto kuvataan koordinaateilla ja numeroarvo on siirrolle annettu hyvyysarvio.
 */
public class Siirto implements Comparable<Siirto> {
    
    private int x;
    private int y;
    private int arvo;
    
    public Siirto(int arvo, int x, int y) {
        this.arvo = arvo;
        this.x = x;
        this.y = y;
    }

    /**
     * Kertoo siirron x-koordinaatin.
     * @return siirron x-koordinaatti.
     */
    public int getX() {
        return x;
    }

    /**
     * Kertoo siirron y-koordinaatin.
     * @return siirron y-koordinaatti.
     */
    public int getY() {
        return y;
    }
    
    /**
     * Kertoon siirron arvon.
     * @return siirron arvo.
     */
    public int getArvo() {
        return arvo;
    }

    /**
     * Kertoo onko parametrina annettu olio sama kuin t‰m‰ siirto.
     * Kaksi siirtoa ovat samat, jos niiden arvot, x-koordinaatit ja y-koordinaatit ovat samat.
     * @param o
     * @return true, jos siirrot ovat samat ja false muuten.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Siirto)) return false;
        Siirto s = (Siirto) o;
        if (s.getArvo() == this.getArvo() && s.getX() == this.getX() && s.getY() == this.getY()) return true;
        return false;
    }
    
    /**
     * Siirrot j‰rjestet‰‰n niiden arvon mukaan.
     * @param s siirto, johon oliota verrataan.
     * @return -1, jos t‰m‰ siirto tulee j‰rjest‰ess‰ ennen parametrina annettua siirtoa, 0 jos j‰rjestyksell‰ ei ole v‰li‰ ja 1 muuten.
     */
    public int compareTo(Siirto s) {
        return this.arvo - s.arvo; 
    }

    /**
     * Palauttaa siirron merkkijonomuodon.
     * @return siirto merkkijonona.
     */
    @Override
    public String toString() {
        return "(" + arvo + ", " + x + ", " + y + ")";
    }
    
}
