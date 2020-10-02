
package logiikka;

import java.util.ArrayList;

/**
 * Pelilauta.
 */

public class Lauta {
    
    private int pituus;
    private char[][] lauta;
    private ArrayList<int[]> suunnat;
    private int vuorossa;
    private int vuoroja;
    
    public Lauta() {
        pituus = 19;
        lauta = new char[pituus][pituus];
        suunnat = new ArrayList<>();
        for(int i = 0; i < 4; i++) suunnat.add(new int[4]);
        alustaLauta();
        vuorossa = 1;
        vuoroja = 0;
        alustaSuunnat();
    }
    
    /**
     * Metodi, joka alustaa pelilaudan aloitustilanteeseen, kun pelaaja haluaa aloittaa uuden pelin ilman että ohjelma suljetaan välissä.
     */
    public void alustaPeli() {
        pituus = 19;
        alustaLauta();
        vuorossa = 1;
        vuoroja = 0;
    }
    
    /**
     * Alustaa listaan koordinaatit neljälle eri suunnalle, vaaka, pysty, vinovasen ja vino-oikea.
     * Näitä suuntia käytetään yksinkertaistamaan voittotarkistusta.
     */
    private void alustaSuunnat(){
        suunnat.get(0)[0] = 0;
        suunnat.get(0)[1] = 1;
        suunnat.get(0)[2] = 0;
        suunnat.get(0)[3] = -1;
        
        suunnat.get(1)[0] = 1;
        suunnat.get(1)[1] = 0;
        suunnat.get(1)[2] = -1;
        suunnat.get(1)[3] = 0;
        
        suunnat.get(2)[0] = -1;
        suunnat.get(2)[1] = -1;
        suunnat.get(2)[2] = 1;
        suunnat.get(2)[3] = 1;
        
        suunnat.get(3)[0] = -1;
        suunnat.get(3)[1] = 1;
        suunnat.get(3)[2] = 1;
        suunnat.get(3)[3] = -1;
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
     * Metodi muuttaa annetun Gomoku-pelilaudan kirjaimen ohjelman käyttämän taulukon y-koordinaatiksi.
     * @param ykoordinaatti Gomoku-pelilaudan kirjain.
     * @return annettua kirjainta vastaava luku väliltä 0 - pituus.
     * @see Lauta#pituus
     */
    public int muutaYKoordinaattiNumeroksi(String ykoordinaatti) {
        int y = ykoordinaatti.charAt(0) - 64;
        return y - 1;
    }
    
    /**
     * Metodi muuttaa annetun Gomoku-pelilaudan numeron ohjelman käyttämän taulukon x-koordinaatiksi.
     * @param xkoordinaatti Gomoku-pelilaudan numero.
     * @return annettua numeroa vastaava luku väliltä 0 - pituus.
     * @see Lauta#pituus
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
     * Lauta tarkistaa jokaisen siirron jälkeen, päättyikö peli ja ilmoittaa tuloksen, jos peli päättyi.
     * @param x koordinaatti.
     * @param y koordinaatti.
     * @return merkkijono, joka kertoo jatketaanko peliä, onko tilanne tasapeli vai kumpi pelaaja voitti.
     */
    public String tarkistaVoitto(int x, int y) {
        String tulos = "Jatketaan.";
        boolean onVoitto = false;
        char merkki = 'X';
        if (vuorossa == 0) merkki = 'O';
        for(int i = 0; i < 4; i++){
            if(laskePisinSuora(x, y, suunnat.get(i), merkki) >= 5) onVoitto = true;
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
     * Metodi laskee, mikä on pisin annetun suuntainen suora, jossa koordinaateissa oleva pelimerkki on mukana.
     * @param x koordinaatti.
     * @param y koordinaatti.
     * @param suunta tutkittava suunta (vaakasuora, pystysuora tai vasen- tai oikea vinosuora).
     * @param merkki pelimerkki, X tai O. Kertoo kumman pelaajan suoraa lasketaan.
     * @return pisimmän annetun suuntaisen suoran pituus.
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
     * Alustaa pelilaudan tyhjällä merkillä.
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
        System.out.println("ohjeet(o) pelisäännöt(p) lopeta(x)");
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
    
    /**
     * Muuttaa parametrina annetun pelilaudan merkkijonoksi.
     * @param lauta pelilauta char[][]-taulukkona.
     * @return pelilauta merkkijono-muodossa.
     */
    public static String muutaMerkkijonoksi(char[][] lauta) {
        StringBuilder merkkijono = new StringBuilder();
        for(int i = 0; i < lauta.length; i++){
            for(int j = 0; j < lauta.length; j++){
                merkkijono.append(lauta[i][j]);
            }
        }
        return merkkijono.toString();
    }
    
    //TESTI-METODIT
    
    /**
     * Testimetodi. Metodia käytetään apuna JUnit-testauksessa.
     * @param x 
     */
    public void setVariTesti(int x) {
        this.vuorossa = x;
    }
    
    /**
     * Testimetodi. Metodia käytetään apuna JUnit-testauksessa.
     */
    public void alustaLautaTesti() {
        for (int i = 0; i < pituus; i++) {
            for (int j = 0; j < pituus; j++) {
                lauta[i][j] = '+';
            }
        }
    }
}