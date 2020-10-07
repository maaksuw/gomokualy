
package logiikka;

/**
 * Pelilauta.
 */

public class Pelilauta {
    
    private int pituus;
    private char[][] lauta;
    private int[][] suunnat;
    private int vuorossa;
    private int vuoroja;
    
    public Pelilauta() {
        pituus = 19;
        lauta = new char[pituus][pituus];
        suunnat = new int[4][4];
        alustaLauta();
        vuorossa = 1;
        vuoroja = 0;
        alustaSuunnat(suunnat);
    }
    
    /**
     * Metodi, joka alustaa pelilaudan aloitustilanteeseen, kun pelaaja haluaa aloittaa uuden pelin ilman ett� ohjelma suljetaan v�liss�.
     */
    public void alustaPeli() {
        pituus = 19;
        alustaLauta();
        vuorossa = 1;
        vuoroja = 0;
    }

    public void setPituus(int pituus) {
        this.pituus = pituus;
    }

    public char[][] getLauta() {
        return lauta;
    }

    public int getVari() {
        return vuorossa;
    }
    
    /**
     * Metodi muuttaa annetun Gomoku-pelilaudan kirjaimen ohjelman k�ytt�m�n taulukon y-koordinaatiksi.
     * @param ykoordinaatti Gomoku-pelilaudan kirjain.
     * @return annettua kirjainta vastaava luku v�lilt� 0 - pituus.
     * @see Pelilauta#pituus
     */
    public int muutaYKoordinaattiNumeroksi(String ykoordinaatti) {
        int y = ykoordinaatti.charAt(0) - 64;
        return y - 1;
    }
    
    /**
     * Metodi muuttaa annetun Gomoku-pelilaudan numeron ohjelman k�ytt�m�n taulukon x-koordinaatiksi.
     * @param xkoordinaatti Gomoku-pelilaudan numero.
     * @return annettua numeroa vastaava luku v�lilt� 0 - pituus.
     * @see Pelilauta#pituus
     */
    public int muutaXKoordinaattiNumeroksi(String xkoordinaatti) {
        int x = Integer.valueOf(xkoordinaatti);
        return pituus - x;
    }
    
    /**
     * Sijoittaa vuorossa olevan pelaajan merkin laudalle annettuihin koordinaatteihin ja vaihtaa vuoroa.
     * @param x koordinaatti.
     * @param y koordinaatti.
     * @return true, jos siirto onnistui ja false, jos koordinaateissa on jo pelimerkki.
     */
    public boolean sijoita(int x, int y) {
        if (lauta[x][y] == '+') {
            if (vuorossa == 1) lauta[x][y] = 'X';
            if (vuorossa == 0) lauta[x][y] = 'O';
            vuoroja++;
            return true;
        } 
        return false;
    }
    
    /**
     * Tarkistaa, onko annettu siirto aiheuttanut pelilaudalle voiton.
     * Lauta tarkistaa jokaisen siirron j�lkeen, p��ttyik� peli ja ilmoittaa tuloksen, jos peli p��ttyi.
     * @param x koordinaatti.
     * @param y koordinaatti.
     * @return merkkijono, joka kertoo jatketaanko peli�, onko tilanne tasapeli vai kumpi pelaaja voitti.
     */
    public String tarkistaVoitto(int x, int y) {
        String tulos = "Jatketaan.";
        boolean onVoitto = false;
        char merkki = 'X';
        if (vuorossa == 0) merkki = 'O';
        for(int i = 0; i < 4; i++){
            if(laskePisinSuora(x, y, suunnat[i], merkki) >= 5) onVoitto = true;
        }
        if (onVoitto) {
            if (vuorossa == 1) tulos = "Musta voitti pelin!";
            else tulos = "Valkoinen voitti pelin!";
            return tulos;
        } else if (vuoroja == pituus * pituus) {
            tulos = "Tasapeli!";
            return tulos;
        } else {
            if (vuorossa == 1) vuorossa--;
            else vuorossa++;
        }
        return tulos;
    }
    
    /**
     * Metodi laskee, mik� on pisin annetun suuntainen suora, jossa koordinaateissa oleva pelimerkki on mukana.
     * @param x koordinaatti.
     * @param y koordinaatti.
     * @param suunta tutkittava suunta (vaakasuora, pystysuora tai vasen- tai oikea vinosuora).
     * @param merkki pelimerkki, X tai O. Kertoo kumman pelaajan suoraa lasketaan.
     * @return pisimm�n annetun suuntaisen suoran pituus.
     */
    private int laskePisinSuora(int x, int y, int[] suunta, char merkki){
        int summa = 0;
        int alkux = x;
        int alkuy = y;
        while(alkux >= 0 && alkux < pituus && alkuy >= 0 && alkuy < pituus && lauta[alkux][alkuy] == merkki){
            summa++;
            alkux += suunta[0];
            alkuy += suunta[1];
        }
        alkux = x + suunta[2];
        alkuy = y + suunta[3];
        while(alkux >= 0 && alkux < pituus && alkuy >= 0 && alkuy < pituus && lauta[alkux][alkuy] == merkki){
            summa++;
            alkux += suunta[2];
            alkuy += suunta[3];
        }
        return summa;
    }
    
    /**
     * Alustaa pelilaudan tyhj�ll� merkill�.
     */
    private void alustaLauta() {
        for (int i = 0; i < pituus; i++) {
            for (int j = 0; j < pituus; j++) {
                lauta[i][j] = '+';
            }
        }
    }
    
    /**
     * Tulostaa pelilaudan.
     */
    public void tulostaLauta() {
        System.out.println("");
        System.out.println("ohjeet(o) pelis��nn�t(p) lopeta(x)");
        int vaakarivi = pituus;
        for (int i = 0; i < pituus; i++) {
            if (vaakarivi < 10) System.out.print(" ");
            System.out.print(vaakarivi + " ");
            for (int j = 0; j < pituus; j++) {
                if (j == pituus - 1) System.out.print(lauta[i][j]);
                else System.out.print(lauta[i][j] + " ");
            }
            System.out.println("");
            vaakarivi--;
        }
        System.out.print("   ");
        char asti = 'S';
        if (pituus == 15) asti = 'O';
        for (char i = 'A'; i <= asti; i++) {
            if (i == asti) System.out.print(i);
            else System.out.print(i + " ");
        }
        System.out.println("");
    }
    
    /**
     * Tulostaa kumman pelaajan vuoro on, mustan vai valkoisen.
     */
    public void tulostaVuorot() {
        if (vuorossa == 1) System.out.println("Vuorossa: MUSTA");
        else System.out.println("Vuorossa: VALKOINEN");
    }
    

    static String muutaMerkkijonoksi(char[][] lauta) {
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < lauta.length; i++){
            for(int j = 0; j < lauta.length; j++){
                s.append(lauta[i][j]);
            }
        }
        return s.toString();
    }
    
    /**
     * Alustaa listaan koordinaatit nelj�lle eri suunnalle, vaaka, pysty, vinovasen ja vino-oikea.
     * N�it� suuntia k�ytet��n yksinkertaistamaan laudan k�sittely�.
     * @param suunnat
     */
    public static void alustaSuunnat(int[][] suunnat){
        suunnat[0][0] = 0;
        suunnat[0][1] = 1;
        suunnat[0][2] = 0;
        suunnat[0][3] = -1;
        
        suunnat[1][0] = 1;
        suunnat[1][1] = 0;
        suunnat[1][2] = -1;
        suunnat[1][3] = 0;
        
        suunnat[2][0] = -1;
        suunnat[2][1] = -1;
        suunnat[2][2] = 1;
        suunnat[2][3] = 1;
        
        suunnat[3][0] = -1;
        suunnat[3][1] = 1;
        suunnat[3][2] = 1;
        suunnat[3][3] = -1;
    }
    
    //TESTI-METODIT
    
    /**
     * Testimetodi. Metodia k�ytet��n apuna JUnit-testauksessa.
     * @param x 
     */
    public void setVariTesti(int x) {
        this.vuorossa = x;
    }
    
    /**
     * Testimetodi. Metodia k�ytet��n apuna JUnit-testauksessa.
     */
    public void alustaLautaTesti() {
        for (int i = 0; i < pituus; i++) {
            for (int j = 0; j < pituus; j++) {
                lauta[i][j] = '+';
            }
        }
    }
}