package logiikka;

/**
 * Pelilauta.
 */

public class Lauta {
    
    private final int pituus;
    private char[][] lauta;
    private int[][] suunnat;
    private char vuorossa;
    private int vuoroja;
    private boolean tasapeli;
    
    public Lauta() {
        pituus = 15;
        lauta = new char[pituus][pituus];
        suunnat = new int[][]{{0, 1, 0, -1}, {1, 0, -1, 0}, {-1, -1, 1, 1}, {-1, 1, 1, -1}};
        alustaLauta();
        vuorossa = 'X';
        vuoroja = 0;
        tasapeli = false;
    }

    /**
     * Kertoo onko peli päättynyt tasapeliin.
     * @return true, jos peli on päättynyt tasapeliin ja false muuten.
     */
    public boolean getTasapeli() {
        return tasapeli;
    }

    /**
     * Kertoo pelilaudan sivun pituuden.
     * @return pelilaudan sivun pituus.
     */
    public int getPituus() {
        return pituus;
    }

    /**
     * Palauttaa pelilautaa kuvaavan char[][]-taulukon.
     * @return pelilauta.
     */
    public char[][] getLauta() {
        return lauta;
    }

    /**
     * Kertoo kumman pelaajan vuoro on, rastin vai nollan.
     * @return X, jos on rastin vuoro ja O, jos on nollan vuoro.
     */
    public char getVuoro() {
        return vuorossa;
    }
    
    /**
     * Sijoittaa vuorossa olevan pelaajan merkin laudalle annettuihin koordinaatteihin.
     * @param x koordinaatti.
     * @param y koordinaatti.
     * @return true, jos peli päättyi ja false, jos peli jatkuu.
     */
    public boolean sijoita(int x, int y) {
        lauta[x][y] = vuorossa;
        vuoroja++;
        int tulos = tarkistaVoitto(x, y);
        if (tulos >= 0) {
            if (tulos == 0) tasapeli = true;
            return true;
        }
        vaihdaVuoro();
        return false;
    }
    
    private void vaihdaVuoro() {
        if (vuorossa == 'X') vuorossa = 'O';
        else vuorossa = 'X';
    }
    
    private int tarkistaVoitto(int x, int y) {
        boolean voitto = false;
        char merkki = lauta[x][y];
        for (int i = 0; i < 4; i++) {
            if (laskePisinSuora(x, y, suunnat[i], merkki) >= 5) {
                voitto = true;
                break;
            }
        }
        if (voitto) return 1;
        else if (vuoroja == pituus * pituus) return 0;
        return -1;
    }
    
    private int laskePisinSuora(int x, int y, int[] suunta, char merkki) {
        int summa = 0;
        int alkux = x;
        int alkuy = y;
        while (laudalla(alkux, alkuy) && lauta[alkux][alkuy] == merkki) {
            summa++;
            alkux += suunta[0];
            alkuy += suunta[1];
        }
        alkux = x + suunta[2];
        alkuy = y + suunta[3];
        while (laudalla(alkux, alkuy) && lauta[alkux][alkuy] == merkki) {
            summa++;
            alkux += suunta[2];
            alkuy += suunta[3];
        }
        return summa;
    }
    
    private void alustaLauta() {
        for (int i = 0; i < pituus; i++) {
            for (int j = 0; j < pituus; j++) {
                lauta[i][j] = '+';
            }
        }
    }
    
    private boolean laudalla(int x, int y) {
        if (x >= 0 && x < pituus && y >= 0 && y < pituus) return true;
        return false;
    }
    
    /**
     * Muuttaa pelitilannetta kuvaavan char[][]-taulukon merkkijonoksi.
     * @param lauta
     * @return pelitilanne merkkijonona.
     */
    public static String muutaMerkkijonoksi(char[][] lauta) {
        char[] merkkijono = new char[lauta.length * lauta.length];
        int idx = 0; 
        for (int i = 0; i < lauta.length; i++) {
            for (int j = 0; j < lauta.length; j++) {
                merkkijono[idx] = lauta[i][j];
                idx++;
            }
        }
        return new String(merkkijono);
    }
    
}