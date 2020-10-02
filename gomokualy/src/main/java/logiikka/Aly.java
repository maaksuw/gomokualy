
package logiikka;

import apu.Matikka;
import apu.Siirto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Pelin tekoäly.
 */

public class Aly {
    
    private HashMap<String, Long> varasto;
    private ArrayList<int[]> suunnat;
    private char botinMerkki;
    private int pituus;
    private boolean ekaSiirto;
    private final int sade;
    private final int syvyys;
    private final long aareton;
    private int[] koordinaatit;
    
    public Aly() {
        varasto = new HashMap<>();
        suunnat = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            suunnat.add(new int[4]);
        }
        alustaSuunnat();
        sade = 2;
        syvyys = 6;
        aareton = 1000000000;
        ekaSiirto = true;
        koordinaatit = new int[2];
    }
    
    /**
     * Alustaa listaan koordinaatit neljälle eri suunnalle, vaaka, pysty, vinovasen ja vino-oikea.
     * Näitä suuntia käytetään yksinkertaistamaan suorien laskemista.
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

    /**
     * Metodilla asetetaan kumman värisillä nappuloilla botti pelaa, mustilla vai valkoisilla.
     * @param vari 1 on musta ja 0 on valkoinen.
     */
    public void setMerkki(int vari) {
        if (vari == 1) botinMerkki = 'X';
        else botinMerkki = 'O';
    }

    /**
     * Metodilla kerrotaan botille minkä kokoisella laudalla pelataan.
     * Tämä pitää aina olla sama kuin pelilaudan koko, että botti antaa oikeat koordinaatit.
     * @param pituus 
     */
    public void setPituus(int pituus) {
        this.pituus = pituus;
    }
    
    /**
     * Metodissa on kovakoodattuna botin ensimmäinen siirto sekä mustilla että valkoisilla nappuloilla pelatessa.
     * @param lauta, pelilauta char[][]-taulukkona
     * @return koordinaatit, joihin botti haluaa asettaa nappulansa.
     */
    private int[] ekaSiirto(char[][] lauta) {
        ekaSiirto = false;
        int[] koordinaatit = new int[2];
        koordinaatit[0] = pituus / 2;
        koordinaatit[1] = pituus / 2;
        if(lauta[pituus / 2][pituus / 2] != '+') koordinaatit[1] = pituus / 2 + 1;
        return koordinaatit;
    }
    
    /**
     * Metodi saa parametrinä pelilaudan, joka kertoo tekoälylle pelin tilanteen, ja
     * palauttaa koordinaatit, joihin botti halutaa tehdä seuraavan siirtonsa.
     * @param lauta pelilauta char[][]-taulukkona.
     * @return koordinaatit, joihin botti haluaa asettaa nappulansa.
     */
    public int[] teeSiirto(char[][] lauta) {
        if(ekaSiirto) return ekaSiirto(lauta);
        varasto.clear();
        long alpha = -aareton;
        long beetta = aareton;
        arvioi(1, 1, lauta, alpha, beetta);
        System.out.println(koordinaatit[0]);
        System.out.println(koordinaatit[1]);
        return koordinaatit;
    }
    
    /**
     * Metodi saa parametrina pelilaudan, joka kuvaa pelitilanteen, ja palauttaa tilanteelle numeroarvion.
     * Metodi tallentaa botin oliomuuttujaan koordinaatit parhaan siirron koordinaatit.
     * @param minimax 1 tarkoittaa maximitasoa ja 0 tarkoittaa minimitasoa.
     * @param taso kertoo millä pelipuun tasolla ollaan menossa. Muuttuja pitää huolen siitä että rekursio päättyy. Juurisolmu on tasolla 1.
     * @param lauta pelilauta char[][]-taulukkona.
     * @param alfa alaraja arviolle.
     * @param beetta yläraja arviolle.
     * @return numeroarvio pelitilanteelle.
     * @see Aly#koordinaatit
     */
    private long arvioi(int minimax, int taso, char[][] lauta, long alfa, long beetta) {
        char sijoitettavaMerkki;
        if(botinMerkki == 'X') sijoitettavaMerkki = (minimax == 1) ? 'X' : 'O';
        else sijoitettavaMerkki = (minimax == 1) ? 'O': 'X';
        
        if (taso == syvyys) {
            String tilanne = Lauta.muutaMerkkijonoksi(lauta);
            if(varasto.containsKey(tilanne)) return varasto.get(tilanne);
            long arvio = pohjaHeuristiikka(lauta, sijoitettavaMerkki);
            varasto.put(tilanne, arvio);
            return arvio;
        }
        
        ArrayList<Siirto> siirrot = new ArrayList<>();
        for (int i = 0; i < pituus; i++) {
            for (int j = 0; j < pituus; j++) {
                if (potentiaalinenSiirto(i, j, lauta)) {
                    lauta[i][j] = sijoitettavaMerkki;
                    long arvio = pohjaHeuristiikka(lauta, sijoitettavaMerkki);
                    siirrot.add(new Siirto(arvio, i, j));
                    lauta[i][j] = '+';
                }
            }
        }
        
        Collections.sort(siirrot);
        if(minimax == 1) Collections.reverse(siirrot);
        
//        if(taso == 1){
//            for(Siirto k: siirrot){
//                System.out.println(k);
//            }
//        }

        long tulos = (minimax == 1) ? -aareton - 1: aareton + 1;
        for(Siirto s: siirrot){
            int i = s.getX();
            int j = s.getY();
            lauta[i][j] = sijoitettavaMerkki;
            String tilanne = Lauta.muutaMerkkijonoksi(lauta);
            if (minimax == 1) {
                if(onkoVoittoa(i, j, lauta)) {
                    if(taso == 1){
                        koordinaatit[0] = i;
                        koordinaatit[1] = j;
                    }
                    lauta[i][j] = '+';
                    return aareton;
                }
                long arvio = 0;
                if(varasto.containsKey(tilanne)) arvio = varasto.get(tilanne);
                else {
                    arvio = arvioi(0, taso + 1, lauta, alfa, beetta);
                    varasto.put(tilanne, arvio);
                }
                if(arvio > tulos){
                    tulos = arvio;
                    if(taso == 1){
                        koordinaatit[0] = i;
                        koordinaatit[1] = j;
                    }
                }
                if(tulos >= beetta) {
                    lauta[i][j] = '+';
                    return tulos;
                }
                if(tulos > alfa) alfa = tulos;
            } else {
                if(onkoVoittoa(i, j, lauta)) {
                    lauta[i][j] = '+';
                    return -aareton;
                }
                long arvio = 0;
                if(varasto.containsKey(tilanne)) arvio = varasto.get(tilanne);
                else {
                    arvio = arvioi(1, taso + 1, lauta, alfa, beetta);
                    varasto.put(tilanne, arvio);
                }
                tulos = Matikka.min(tulos, arvio);
                if(tulos <= alfa) {
                    lauta[i][j] = '+';
                    return tulos;
                }
                if(tulos < beetta) beetta = tulos;
            }
            lauta[i][j] = '+';
        }
        return tulos;
    }
    
    /**
     * Metodi saa parametrina pelilaudan ja koordinaatit ja kertoo, onko koordinaateissa annettu siirto aiheuttanut voiton.
     * @param x koordinaatti.
     * @param y koordinaatti.
     * @param lauta pelilauta char[][]-taulukkona.
     * @return 
     */
    public boolean onkoVoittoa(int x, int y, char[][] lauta) {
        int[] vastaus = new int[5];
        for(int i = 0; i < 4; i++){
            laskePisinSuora(x, y, suunnat.get(i), lauta, vastaus);
            if(vastaus[0] >= 5) return true;
        }
        return false;
    }
    
    /**
     * Metodi saa parametrina pelilaudan ja seuraavaksi vuorossa olevan pelaajan pelimerkin ja palauttaa numeroarvion annetulle tilanteelle.
     * Metodi antaa arvion pelitilanteen hyvyydestä botin kannalta.
     * Arvio on positiivinen, jos tilanne on parempi botille, ja negatiivinen, jos se on parempi vastustajalle. Tätä metodia käytetään
     * arvioi-metodin viimeisellä tasolla, jolloin alkoille ei enää lasketa numeroarvoa sen lapsien avulla.
     * @param lauta pelilauta char[][]-taulukkona.
     * @param vuoro pelimerkki, X tai O. Kertoo, kenen vuoro on seuraavaksi tehdä siirto.
     * @return numeroarvio pelitilanteelle.
     * @see Aly#arvioi(int, int, char[][], long, long) 
     */
    public long pohjaHeuristiikka(char[][] lauta, char vuoro) {
        int tulos = 0;
        int[] tilasto = new int[16]; 
        //ykköset 0, kakkoset 1, avoimet kakkoset 2, kolmoset 3, avoimet kolmoset 4, neloset 5, avoimet neloset 6, vitoset 7 
        //vastustajan vastaavat (8 - 15)
        char vastustajanMerkki = botinMerkki == 'X' ? 'O' : 'X';
        int[] vastaus = new int[5];
        boolean avoinNelja = false;
        boolean avoinNeljaVastustajalla = false;
        boolean voitto = false;
        boolean vastustajaVoittaa = false;
        for (int i = 0; i < pituus; i++) {
            for (int j = 0; j < pituus; j++) {
                if (lauta[i][j] == botinMerkki) {
                    
                    for(int k = 0; k < 4; k++){
                        laskePisinSuora(i, j, suunnat.get(k), lauta, vastaus);
                        uhkaarvio(vastaus, lauta, true, tilasto, i, j);
                        tulos += tilasto[0];
                        tilasto[0] = 0;
                    }
                    
                } else if (lauta[i][j] == vastustajanMerkki) {
                    
                    for(int k = 0; k < 4; k++){
                        laskePisinSuora(i, j, suunnat.get(k), lauta, vastaus);
                        uhkaarvio(vastaus, lauta, false, tilasto, i, j);
                        tulos += tilasto[8];
                        tilasto[8] = 0;
                    }
                    
                }
            }
        }
        if(tilasto[7] > 0) voitto = true;
        if(tilasto[6] > 0) avoinNelja = true;
        if(tilasto[15] > 0) vastustajaVoittaa = true;
        if(tilasto[14] > 0) avoinNeljaVastustajalla = true;
        
        if(vastustajaVoittaa) return -aareton;
        if(voitto) return aareton;
        
        if(avoinNeljaVastustajalla && !avoinNelja) return -aareton + 1;
        if(avoinNelja && !avoinNeljaVastustajalla) return aareton - 1;
        if(avoinNelja && avoinNeljaVastustajalla){
            if(vuoro == botinMerkki) return aareton - 1;
            else return -aareton + 1;
        }
        
        for(int i = 0; i < tilasto.length; i++){
            if(i == 1 || i == 2 || i == 9 || i == 10) tilasto[i] /= 2;
            if(i == 3 || i == 4 || i == 11 || i == 12) tilasto[i] /= 3;
            if(i == 5 || i == 13) tilasto[i] /= 4;
        }
        tulos = tilasto[1] * 10 - tilasto[9] * 10 + tilasto[2] * 50 - tilasto[10] * 50 + tilasto[3] * 100 - tilasto[11] * 100 
                + tilasto[4] * 1000 - tilasto[12] * 1000 + tilasto[5] * 1000 - tilasto[13] * 1000;
        return tulos;
    }
    
    /**
     * Metodi laskee, mikä on pisin annetun suuntainen suora, johon annetuissa koordinaateissa oleva pelimerkki sisältyy.
     * Metodi kertoo, mikä on pisin annettuun suuntaan jatkuva suora, jossa koordinaateissa oleva pelimerkki on osana.
     * Suunta, jota tutkitaan, annetaan parametrina (vaakasuora, pystysuora tai vasen- tai oikea vinosuora).
     * Suoran päätepisteiden koordinaatit ja suoran pituus kirjoitetaan parametrina annettuun vastaus-taulukkoon.
     * @param x koordinaati.
     * @param y koordinaatti.
     * @param suunta minkä suuntainen suora tarkistetaan.
     * @param lauta pelilauta char[][]-taulukkona.
     * @param vastaus taulukko, johon saadut tulokset kirjoitetaan.
     */
    private void laskePisinSuora(int x, int y, int[] suunta, char[][] lauta, int[] vastaus){
        char merkki = lauta[x][y];
        int summa = 0;
        int alkux = x;
        int alkuy = y;
        while(alkux >= 0 && alkux < pituus && alkuy >= 0 && alkuy < pituus && lauta[alkux][alkuy] == merkki){
            summa++;
            alkux += suunta[0];
            alkuy += suunta[1];
        }
        vastaus[1] = alkux;
        vastaus[2] = alkuy;
        alkux = x + suunta[2];
        alkuy = y + suunta[3];
        while(alkux >= 0 && alkux < pituus && alkuy >= 0 && alkuy < pituus && lauta[alkux][alkuy] == merkki){
            summa++;
            alkux += suunta[2];
            alkuy += suunta[3];
        }
        vastaus[3] = alkux;
        vastaus[4] = alkuy;
        vastaus[0] = summa;
    }
    
    /**
     * Metodi saa parametrina laskePisinSuora-metodin tulostaulukon ja kertoo sen sisällön perusteella, minkälainen uhka taulukossa kuvailtu suora on.
     * Vastaus kirjoitetaan parametrina saatavaan tilasto-taulukkoon, jossa eri uhat on luokiteltuna.
     * @param suora arvioitavan suoran tiedot.
     * @param lauta pelilauta char[][]-taulukkona.
     * @param botti true, jos kyseessä on botin oma uhka ja false muuten.
     * @param tilasto taulukko, johon arvioitu uhka tilastoidaan.
     * @param i lisäparametri yksinkertaistamaan metodia.
     * @param j lisäparametri yksinkertaistamaan metodia.
     * @see Aly#laskePisinSuora(int, int, int[], char[][], int[])
     */
    private void uhkaarvio(int[] suora, char[][] lauta, boolean botti, int[] tilasto, int i, int j){
        if (suora[0] >= 5) {
            if (botti) tilasto[7]++;
            else tilasto[15]++;
        }
        if(suora[0] == 1) {
            if (botti) tilasto[0] = Matikka.maxi(Matikka.mini(i,(pituus -  1 - i)), Matikka.mini(j, (pituus - 1 - j)));
            else tilasto[8] = -Matikka.maxi(Matikka.mini(i,(pituus -  1 - i)), Matikka.mini(j, (pituus - 1 - j)));
        }
        if ((laudalla(suora[1], suora[2]) && lauta[suora[1]][suora[2]] == '+') && (laudalla(suora[3], suora[4]) && lauta[suora[3]][suora[4]] == '+')){
            if(suora[0] == 2) {
                if(botti) tilasto[2]++;
                else tilasto[10]++;
            }
            if(suora[0] == 3) {
                if(botti) tilasto[4]++;
                else tilasto[12]++;
            }
            if(suora[0] == 4) {
                if(botti) tilasto[6]++;
                else tilasto[14]++;
            }
        } else if ((laudalla(suora[1], suora[2]) && lauta[suora[1]][suora[2]] == '+') || (laudalla(suora[3], suora[4]) && lauta[suora[3]][suora[4]] == '+')){
            if(suora[0] == 2) {
                if(botti) tilasto[1]++;
                else tilasto[9]++;
            }
            if(suora[0] == 3) {
                if(botti) tilasto[3]++;
                else tilasto[11]++;
            }
            if(suora[0] == 4) {
                if(botti) tilasto[5]++;
                else tilasto[13]++;
            }
        }
    }
    
    /**
     * Metodi kertoo, ovatko annetut koordinaatit pelilaudalla.
     * @param x koordinaatti
     * @param y koordinaatti
     * @return true, jos luku on pelilaudan sisällä, false muuten.
     */
    private boolean laudalla(int x, int y){
        if(x >= 0 && x < pituus && y >= 0 && y < pituus) return true;
        return false;
    }
    
    /**
     * Metodi kertoo, kannattaako parametreina annettuihin koordinaatteihin mahdollisesti sijoittaa pelimerkki.
     * Pelimerkkiä ei kannata sijoittaa liian kauas muista pelimerkeistä. Botin oliomuuttuja sade määrittää, mikä on pisin etäisyys,
     * kuinka kauas toisesta pelimerkistä uusi pelimerkki kannattaa korkeintaan sijoittaa.
     * @param x koordinaatti
     * @param y koordinaatti
     * @param lauta pelilauta char[][]-taulukkona.
     * @return true, jos säteen sisällä annetuista koordinaateista on toinen nappula, false muuten.
     * @see Aly#sade
     */
    private boolean potentiaalinenSiirto(int x, int y, char[][] lauta) {
        if(lauta[x][y] != '+') return false;
        int mistax = Matikka.maxi(0, x - sade);
        int mihinx = Matikka.mini(x + sade, pituus - 1);
        int mistay = Matikka.maxi(0, y - sade);
        int mihiny = Matikka.mini(y + sade, pituus - 1);
        for (int i = mistax; i <= mihinx; i++) {
            for (int j = mistay; j <= mihiny; j++) {
                if (lauta[i][j] != '+') return true;
            }
        }
        return false;
    }
}