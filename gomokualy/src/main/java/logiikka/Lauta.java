
package logiikka;

import java.util.ArrayList;

/**
 * Pelilauta.
 * @author OMISTAJA
 */

public class Lauta {
    
    private int pituus;
    private char[][] lauta;
    private ArrayList<int[]> suunnat;
    private int vari;
    private int vuoroja;
    
    public Lauta() {
        pituus = 19;
        lauta = new char[pituus][pituus];
        suunnat = new ArrayList<>();
        for(int i = 0; i < 4; i++) suunnat.add(new int[4]);
        alustaLauta();
        vari = 1;
        vuoroja = 0;
        alustaSuunnat();
    }
    
    /**
     * Metodi joka alustaa pelilaudan aloitustilanteeseen, kun pelaaja haluaa aloittaa uuden pelin ilman että ohjelma suljetaan välissä.
     */
    public void alustaPeli() {
        pituus = 19;
        alustaLauta();
        vari = 1;
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
        return vari;
    }
    
    /**
     * Metodi muuttaa annetun Gomoku-peli muodossa olevan y-koordinaatin ohjelman käyttämän taulukon koordinaatiksi.
     * @param ykoordinaatti
     * @return 
     */
    public int muutaYKoordinaattiNumeroksi(String ykoordinaatti) {
        int y = ykoordinaatti.charAt(0) - 64;
        return y - 1;
    }
    
    /**
     * Metodi muuttaa annetun Gomoku-peli muodossa olevan x-koordinaatin ohjelman käyttämän taulukon koordinaatiksi.
     * @param xkoordinaatti
     * @return 
     */
    public int muutaXKoordinaattiNumeroksi(String xkoordinaatti) {
        int x = Integer.valueOf(xkoordinaatti);
        return pituus - x;
    }
    
    public boolean sijoita(int x, int y) {
        if (lauta[x][y] == '+') {
            if (vari == 1) lauta[x][y] = 'X';
            if (vari == 0) lauta[x][y] = 'O';
            vuoroja++;
            return true;
        } 
        return false;
    }
    
    public String tarkistaVoitto(int x, int y) {
        String tulos = "Jatketaan.";
        if (onkoVoittoa(x, y)) {
            if (vari == 1) tulos = "Musta voitti pelin!";
            else tulos = "Valkoinen voitti pelin!";
            return tulos;
        } else if (vuoroja == pituus * pituus) {
            tulos = "Tasapeli!";
            return tulos;
        } else {
            if (vari == 1) vari--;
            else vari++;
        }
        return tulos;
    }
    
    private boolean onkoVoittoa(int x, int y) {
        char merkki = 'X';
        if (vari == 0) merkki = 'O';
        for(int i = 0; i < 4; i++){
            if(laskePisinSuora(x, y, suunnat.get(i), merkki) >= 5) return true;
        }
        return false;
    }
    
    private int laskePisinSuora(int x, int y, int[] suunnat, char merkki){
        int summa = 0;
        int alkux = x;
        int alkuy = y;
        while(alkux >= 0 && alkux < pituus && alkuy >= 0 && alkuy < pituus && lauta[alkux][alkuy] == merkki){
            summa++;
            alkux += suunnat[0];
            alkuy += suunnat[1];
        }
        alkux = x + suunnat[2];
        alkuy = y + suunnat[3];
        while(alkux >= 0 && alkux < pituus && alkuy >= 0 && alkuy < pituus && lauta[alkux][alkuy] == merkki){
            summa++;
            alkux += suunnat[2];
            alkuy += suunnat[3];
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
    
    //TESTI-METODIT
    
    public void setVariTesti(int x) {
        this.vari = x;
    }
    
    public void alustaLautaTesti() {
        for (int i = 0; i < pituus; i++) {
            for (int j = 0; j < pituus; j++) {
                lauta[i][j] = '+';
            }
        }
    }
}