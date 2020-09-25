
package logiikka;

import apu.Kolmio;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Pelin teko‰ly.
 * @author pinjaw
 */

public class Aly {
    
    private HashMap<String, Integer> varasto;
    private ArrayList<int[]> suunnat;
    private char merkki;
    private int pituus;
    private boolean ekaSiirto;
    private final int sade;
    private final int syvyys;
    private final long aareton;
    private int[] siirto;
    
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
        siirto = new int[2];
    }
    
    /**
     * Alustaa listaan koordinaatit nelj‰lle eri suunnalle, vaaka, pysty, vinovasen ja vino-oikea.
     * N‰it‰ suuntia k‰ytet‰‰n yksinkertaistamaan suorien laskemista.
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
     * Metodilla asetetaan kumman v‰risill‰ nappuloilla botti pelaa, mustilla vai valkoisilla.
     * @param vari
     */
    public void setMerkki(int vari) {
        if (vari == 1) merkki = 'X';
        else merkki = 'O';
    }

    /**
     * Metodilla kerrotaan botille mink‰ kokoisella laudalla pelataan.
     * T‰m‰ pit‰‰ aina olla sama kuin pelilaudan koko, ett‰ botti antaa oikeat koordinaatit.
     * @param pituus 
     */
    public void setPituus(int pituus) {
        this.pituus = pituus;
    }
    
    /**
     * Metodissa on kovakoodattuna botin ensimm‰inen siirto sek‰ mustilla ett‰ valkoisilla nappuloilla pelatessa.
     * @param lauta, pelilauta char[][]-taulukkona
     * @return koordinaatit joihin botti asettaa nappulansa, int[]-taulukko
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
     * Metodi saa parametrin‰ pelilaudan, joka kertoo teko‰lylle pelin tilanteen ja
     * palauttaa int[]-taulukon, joka kertoo koordinaatit joihin botti halutaa tehd‰ seuraavan siirtonsa.
     * @param lauta pelilauta char[][]-taulukkona.
     * @return koordinaatit joihin botti haluaa asettaa nappulansa, int[]-taulukko.
     */
    public int[] teeSiirto(char[][] lauta) {
        if(ekaSiirto) return ekaSiirto(lauta);
        
        long alpha = -aareton;
        long beetta = aareton;
        arvioi(1, 1, lauta, alpha, beetta);
//        System.out.println("siirto[0] " + siirto[0]);
//        System.out.println("siirto[1] " + siirto[1]);
        return siirto;
    }
    
    /**
     * Metodi saa parametrina pelilaudan, joka kuvaa pelitilanteen ja palauttaa tilanteelle arvion numerona.
     * Metodi tallentaa botin oliomuuttujaan siirto parhaan siirron koordinaatit.
     * @param minimax 1 tarkoittaa maximitasoa ja 0 tarkoittaa minimitasoa.
     * @param taso kertoo mill‰ pelipuun tasolla ollaan menossa. Muuttuja pit‰‰ huolen siit‰ ett‰ rekursio p‰‰ttyy.
     * @param lauta kertoo pelitilanteen.
     * @param alpha alaraja arviolle.
     * @param beetta yl‰raja arviolle.
     * @return numeroarvio pelitilanteelle.
     */
    private long arvioi(int minimax, int taso, char[][] lauta, long alpha, long beetta) {
        char sijoitettavaMerkki;
        if(merkki == 'X') sijoitettavaMerkki = (minimax == 1) ? 'X' : 'O';
        else sijoitettavaMerkki = (minimax == 1) ? 'O': 'X';
        
        if (taso == syvyys) {
            return pohjaHeuristiikka(lauta, sijoitettavaMerkki);
        }
        
        ArrayList<Kolmio> siirrot = new ArrayList<>();
        long tulos = (minimax == 1) ? -aareton - 1: aareton + 1;
        for (int i = 0; i < pituus; i++) {
            for (int j = 0; j < pituus; j++) {
                if (potentiaalinenSiirto(i, j, lauta)) {
                    lauta[i][j] = sijoitettavaMerkki;
                    long arvio = pohjaHeuristiikka(lauta, sijoitettavaMerkki);
                    siirrot.add(new Kolmio(arvio, i, j));
                    lauta[i][j] = '+';
                }
            }
        }
        
        Collections.sort(siirrot);
        if(minimax == 1) Collections.reverse(siirrot);
//        if(taso == 1){
//            for(Kolmio k: siirrot){
//                System.out.println(k);
//            }
//        }
        
        for(Kolmio k: siirrot){
            int i = k.getX();
            int j = k.getY();
            //if(taso == 1) System.out.println("i " + i + " j " + j);
            lauta[i][j] = sijoitettavaMerkki;
            if (minimax == 1) {
                if(onkoVoittoa(i, j, sijoitettavaMerkki, lauta)) {
                    if(taso == 1){
                        siirto[0] = i;
                        siirto[1] = j;
                    }
                    lauta[i][j] = '+';
                    return aareton;
                }
                if(taso == 1){
                    long arvio = arvioi(0, taso + 1, lauta, alpha, beetta);
                    if(arvio > tulos){
                        tulos = arvio;
                        siirto[0] = i;
                        siirto[1] = j;
                    }
                } else tulos = Math.max(tulos, arvioi(0, taso + 1, lauta, alpha, beetta));
                if(tulos >= beetta) {
                    lauta[i][j] = '+';
                    return tulos;
                }
                if(tulos > alpha) alpha = tulos;
            } else {
                if(onkoVoittoa(i, j, sijoitettavaMerkki, lauta)) {
                    lauta[i][j] = '+';
                    return -aareton;
                }
                tulos = Math.min(tulos, arvioi(1, taso + 1, lauta, alpha, beetta));
                if(tulos <= alpha) {
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
     * Metodi saa parametrina pelilaudan, koordinaatit ja pelimerkin ja kertoo onko koordinaateissa annettu siirto aiheuttanut voiton.
     * Huomasin nyt ett‰ parametri vuoro on turha, poistan sen myˆhemmin.
     * @param x koordinaatti.
     * @param y koordinaatti.
     * @param vuoro tutkittavan siirron pelimerkki X tai O.
     * @param lauta pelilauta char[][]-taulukkona.
     * @return 
     */
    public boolean onkoVoittoa(int x, int y, char vuoro, char[][] lauta) {
        int[] ans = new int[5];
        for(int i = 0; i < 4; i++){
            laskePisinSuora(x, y, suunnat.get(i), lauta, vuoro, ans);
            if(ans[0] >= 5) return true;
        }
        return false;
    }
    
    /**
     * Metodi saa parametrina pelilaudan ja seuraavaksi vuorossa olevan pelaajan pelimerkin ja palauttaa numeroarvion annetulle tilanteelle.
     * Metodi antaa arvion pelitilanteen hyvyydest‰ botin kannalta.
     * Arvio on positiivinen jos tilanne on parempi botille ja negatiivinen jos se on parempi vastustajalle. T‰t‰ metodia k‰ytet‰‰n
     * arvioi-metodin viimeisell‰ tasolla, jolloin alkoille ei en‰‰ lasketa numeroarvoa sen lapsien avulla.
     * @param lauta pelilauta char[][]-taulukkona.
     * @param vuoro pelimerkki, X tai O.
     * @return numeroarvio pelitilanteelle.
     */
    public long pohjaHeuristiikka(char[][] lauta, char vuoro) {
        int tulos = 0;
        int[] tilasto = new int[10]; //kakkoset 0, avoimet kakkoset 1, kolmoset 2, avoimet kolmoset 3, neloset 4, ja vastustajan vastaavat (5 - 9)
        char vastustajanMerkki = merkki == 'X' ? 'O' : 'X';
        int[] ans = new int[5];
        boolean avoinNelja = false;
        boolean avoinNeljaVastustajalla = false;
        boolean voitto = false;
        boolean vastustajaVoittaa = false;
        for (int i = 0; i < pituus; i++) {
            for (int j = 0; j < pituus; j++) {
                if (lauta[i][j] == merkki) {
                    
                    for(int k = 0; k < 4; k++){
                        laskePisinSuora(i, j, suunnat.get(k), lauta, merkki, ans);
                        long arvio = uhkaarvio(ans, lauta, true, tilasto, i, j);
                        if(arvio == aareton) voitto = true;
                        if(arvio == aareton - 1) avoinNelja = true;
                        tulos += uhkaarvio(ans, lauta, true, tilasto, i, j);
                    }
                    
                } else if (lauta[i][j] == vastustajanMerkki) {
                    
                    for(int k = 0; k < 4; k++){
                        laskePisinSuora(i, j, suunnat.get(k), lauta, vastustajanMerkki, ans);
                        long arvio = uhkaarvio(ans, lauta, false, tilasto, i, j);
                        if(arvio == -aareton) vastustajaVoittaa = true;
                        if(arvio == -aareton + 1) avoinNeljaVastustajalla = true;
                        tulos += uhkaarvio(ans, lauta, false, tilasto, i, j);
                    }
                    
                }
            }
        }
        if(vastustajaVoittaa) return -aareton;
        if(voitto) return aareton;
        
        if(avoinNeljaVastustajalla && !avoinNelja) return -aareton + 1;
        if(avoinNelja && !avoinNeljaVastustajalla) return aareton - 1;
        if(avoinNelja && avoinNeljaVastustajalla){
            if(vuoro == merkki) return aareton - 1;
            else return -aareton + 1;
        }
        
        for(int i = 0; i < tilasto.length; i++){
            if(i == 0 || i == 1 || i == 5 || i == 6) tilasto[i] /= 2;
            if(i == 2 || i == 3 || i == 7 || i == 8) tilasto[i] /= 3;
            if(i == 4 || i == 9) tilasto[i] /= 4;
        }
        tulos = tilasto[0] * 10 - tilasto[5] * 10 + tilasto[1] * 50 - tilasto[6] * 50 + tilasto[2] * 100 - tilasto[7] * 100 
                + tilasto[3] * 1000 - tilasto[8] * 1000 + tilasto[4] * 1000 - tilasto[9] * 1000;
        return tulos;
    }
    
    /**
     * Metodi laskee mik‰ on pisin suora johon annetuissa koordinaateissa oleva pelimerkki sis‰ltyy.
     * Metodi kertoo mik‰ on pisin tiettyyn suuntaan oleva suora (vaakasuora, pystysuora tai vinosuora) jossa koordinaateissa oleva pelimerkki on osana.
     * Suoran p‰‰tepisteiden koordinaatit ja suoran pituus kirjoitetaan parametrina annettuun taulukkoon.
     * @param x koordinaati.
     * @param y koordinaatti.
     * @param suunnat mink‰ suuntainen suora tarkistetaan.
     * @param lauta pelilauta char[][]-taulukkona.
     * @param merkki pelimerkki, jolle suoraa lasketaan.
     * @param ans taulukko, johon tulokset kirjoitetaan.
     */
    private void laskePisinSuora(int x, int y, int[] suunnat, char[][] lauta, char merkki, int[] ans){
        int summa = 0;
        int alkux = x;
        int alkuy = y;
        while(alkux >= 0 && alkux < pituus && alkuy >= 0 && alkuy < pituus && lauta[alkux][alkuy] == merkki){
            summa++;
            alkux += suunnat[0];
            alkuy += suunnat[1];
        }
        ans[1] = alkux;
        ans[2] = alkuy;
        alkux = x + suunnat[2];
        alkuy = y + suunnat[3];
        while(alkux >= 0 && alkux < pituus && alkuy >= 0 && alkuy < pituus && lauta[alkux][alkuy] == merkki){
            summa++;
            alkux += suunnat[2];
            alkuy += suunnat[3];
        }
        ans[3] = alkux;
        ans[4] = alkuy;
        ans[0] = summa;
    }
    
    /**
     * Metodi saa parametrina laskePisinSuora-metodin tulostaulukon ja kertoo sen sis‰llˆn perusteella onko mink‰ tyyppinen uhka parametrin ans-taulukossa
     * kuvailtu suora on.
     * Vastaus kirjoitetaan parametrina saatavaan tilasto-taulukkoon. Mik‰li suora on pelin kannalta eritt‰in oleellinen, metodi palauttaa suoraan
     * numeroarvion. T‰t‰ metodia voisi selkeytt‰‰, niin ett‰ se ei palauttaisi vastausta kahdella tavalla.
     * @param ans suoran tiedot kertova taulukko.
     * @param lauta pelilauta char[][]-taulukkona.
     * @param botti true, jos kyseess‰ on botin suora ja false muuten.
     * @param tilasto taulukko, johon tilastoidaan mink‰laisia suoria on ja kuinka monta.
     * @param i 
     * @param j
     * @return 
     */
    private long uhkaarvio(int[] ans, char[][] lauta, boolean botti, int[] tilasto, int i, int j){
        if (ans[0] >= 5) {
            if (botti) return aareton;
            return -aareton;
        }
        if(ans[0] == 1) {
            if (botti) return Math.max(Math.min(i,(pituus -  1 - i)), Math.min(j, (pituus - 1 - j)));
            return -Math.max(Math.min(i,(pituus -  1 - i)),Math.min(j, (pituus - 1 - j)));
        }
        if ((laudalla(ans[1]) && laudalla(ans[2]) && lauta[ans[1]][ans[2]] == '+') && (laudalla(ans[3]) && laudalla(ans[4]) && lauta[ans[3]][ans[4]] == '+')){
            if(ans[0] == 2) {
                if(botti) tilasto[1]++;
                else tilasto[6]++;
            }
            if(ans[0] == 3) {
                if(botti) tilasto[3]++;
                else tilasto[8]++;
            }
            if(ans[0] == 4) {
                if(botti) return aareton - 1;
                return -aareton + 1;
            }
        } else if ((laudalla(ans[1]) && laudalla(ans[2]) && lauta[ans[1]][ans[2]] == '+') || (laudalla(ans[3]) && laudalla(ans[4]) && lauta[ans[3]][ans[4]] == '+')){
            if(ans[0] == 2) {
                if(botti) tilasto[0]++;
                else tilasto[5]++;
            }
            if(ans[0] == 3) {
                if(botti) tilasto[2]++;
                else tilasto[7]++;
            }
            if(ans[0] == 4) {
                if(botti) tilasto[4]++;
                else tilasto[9]++;
            }
        }
        return 0;
    }
    
    /**
     * Metodi kertoo onko annettu koordinaatti pelilaudalla.
     * Huomasin nyt ett‰ t‰h‰n kannattaisi antaa kaksi parametri‰ kerralla. Korjataan. Mit‰kˆh‰n olen aikaisemmin ajatellut.
     * @param a
     * @return true, jos luku on pelilaudan sis‰ll‰, false muuten.
     */
    private boolean laudalla(int a){
        if(a >= 0 && a < pituus) return true;
        return false;
    }
    
    /**
     * Metodi kertoo kannattaako parametreina annettuihin koordinaatteihin mahdollisesti sijoittaa pelimerkki.
     * Pelimerkki‰ ei kannata sijoittaa liian kauas muista pelimerkeist‰. Botin oliomuuttuja sade m‰‰ritt‰‰ mik‰ on pisin et‰isyys,
     * kuinka kauas toisesta pelimerkist‰ uusi pelimerkki kannattaa korkeintaan sijoittaa.
     * @param x koordinaatti
     * @param y koordinaatti
     * @param lauta pelilauta char[][]-taulukkona.
     * @return true, jos s‰teen sis‰ll‰ annetuista koordinaateista on toinen nappula, false muuten.
     */
    private boolean potentiaalinenSiirto(int x, int y, char[][] lauta) {
        if(lauta[x][y] != '+') return false;
        int mistax = Math.max(0, x - sade);
        int mihinx = Math.min(x + sade, pituus - 1);
        int mistay = Math.max(0, y - sade);
        int mihiny = Math.min(y + sade, pituus - 1);
        for (int i = mistax; i <= mihinx; i++) {
            for (int j = mistay; j <= mihiny; j++) {
                if (lauta[i][j] != '+') return true;
            }
        }
        return false;
    }
}