
package logiikka;

import java.util.HashMap;

public class Aly {
    
    private HashMap<String, Integer> varasto;
    private char merkki;
    private int pituus;
    private int vuoroja; //hommaudu eroon tästä, tämä on nyt vaan väliaikainen
    private int sade;
    private int syvyys;
    private final int aareton;
    
    public Aly(){
        varasto = new HashMap<>();
        vuoroja = 0;
        sade = 2;
        syvyys = 3;
        aareton = 1000000000;
    }

    public void setMerkki(int vari) {
        if(vari == 1) merkki = 'X';
        else merkki = 'O';
    }

    public void setPituus(int pituus) {
        this.pituus = pituus;
    }
    
    public int[] teeSiirto(char[][] lauta) {
        int[] koordinaatit = new int[2];
        vuoroja++;
        if(vuoroja == 1 && merkki == 'X'){
            koordinaatit[0] = pituus/2;
            koordinaatit[1] = pituus/2;
            return koordinaatit;
        }
        int tulos = -aareton - 1;
        for(int i = 0; i < pituus; i++){
            for(int j = 0; j < pituus; j++){
                if(potentiaalinenSiirto(i, j, lauta) && lauta[i][j] == '+'){
                    lauta[i][j] = merkki;
                    //System.out.println("siirto? " + i + " " + j);
//                    for(int x = 0; x < pituus; x++){
//                        for(int y = 0; y < pituus; y++){
//                            System.out.print(lauta[x][y] + " ");
//                        }
//                        System.out.println("");
//                    }
                    
                    int arvio = arvioi(0, 1, lauta);
//                    System.out.println("arvio " + arvio);
//                    System.out.println("tulos " + tulos);
                    if(arvio > tulos){
                        koordinaatit[0] = i;
                        koordinaatit[1] = j;
                        tulos = arvio;
                    }
                    lauta[i][j] = '+';
                }
            }
        }
        return koordinaatit;
    }
    
    private int arvioi(int minimax, int taso, char[][] lauta){
        if(taso == syvyys){
            return pohjaHeuristiikka(lauta);
        }
        int tulos = (minimax == 1)? -aareton : aareton;
        for(int i = 0; i < pituus; i++){
            for(int j = 0; j < pituus; j++){
                if(potentiaalinenSiirto(i, j, lauta) && lauta[i][j] == '+'){
                    if(minimax == 1){
                        lauta[i][j] = merkki;
                        tulos = Math.max(tulos, arvioi(0, taso + 1, lauta));
                    } else {
                        char vastustajanMerkki = (merkki == 'X') ? 'O' : 'X';
                        lauta[i][j] = vastustajanMerkki;
                        tulos = Math.min(tulos, arvioi(1, taso + 1, lauta));
                    }
                    lauta[i][j] = '+';
                }
            }
        }
        return tulos;
    }
    
    private int pohjaHeuristiikka(char[][] lauta){ //Huono!!!! Paranna tätä seuraavaksi
        int tulos = 0;
        char vastustajanMerkki = merkki == 'X' ? 'O' : 'X';
        int kahta = 0;
        int kolmea = 0;
        int neljaa = 0;
        boolean enemman = false;
        int kahtav = 0;
        int kolmeav = 0;
        int neljaav = 0;
        boolean enemmanv = false;
        for(int i = 0; i < pituus; i++){
            for(int j = 0; j < pituus; j++){
                if(lauta[i][j] == merkki){
                    int vaaka = pisinVaaka(i, j, lauta, merkki);
                    int pysty = pisinPysty(i, j, lauta, merkki);
                    int vinovasen = pisinVinoVasen(i, j, lauta, merkki);
                    int vinooikea = pisinVinoOikea(i, j, lauta, merkki);
                    if(vaaka == 2) kahta++;
                    if(vaaka == 3) kolmea++;
                    if(vaaka == 4) neljaa++;
                    if(vaaka > 4) enemman = true;
                    if(pysty == 2) kahta++;
                    if(pysty == 3) kolmea++;
                    if(pysty == 4) neljaa++;
                    if(pysty > 4) enemman = true;
                    if(vinovasen == 2) kahta++;
                    if(vinovasen == 3) kolmea++;
                    if(vinovasen == 4) neljaa++;
                    if(vinovasen > 4) enemman = true;
                    if(vinooikea == 2) kahta++;
                    if(vinooikea == 3) kolmea++;
                    if(vinooikea == 4) neljaa++;
                    if(vinooikea > 4) enemman = true;
                } else if (lauta[i][j] == vastustajanMerkki){
                    int vaaka = pisinVaaka(i, j, lauta, vastustajanMerkki);
                    int pysty = pisinPysty(i, j, lauta, vastustajanMerkki);
                    int vinovasen = pisinVinoVasen(i, j, lauta, vastustajanMerkki);
                    int vinooikea = pisinVinoOikea(i, j, lauta, vastustajanMerkki);
                    if(vaaka == 2) kahtav++;
                    if(vaaka == 3) kolmeav++;
                    if(vaaka == 4) neljaav++;
                    if(vaaka > 4) enemmanv = true;
                    if(pysty == 2) kahtav++;
                    if(pysty == 3) kolmeav++;
                    if(pysty == 4) neljaav++;
                    if(pysty > 4) enemmanv = true;
                    if(vinovasen == 2) kahtav++;
                    if(vinovasen == 3) kolmeav++;
                    if(vinovasen == 4) neljaav++;
                    if(vinovasen > 4) enemmanv = true;
                    if(vinooikea == 2) kahtav++;
                    if(vinooikea == 3) kolmeav++;
                    if(vinooikea == 4) neljaav++;
                    if(vinooikea > 4) enemmanv = true;
                }
                if(enemman) return aareton;
                if(enemmanv) return -aareton;
            }
        }
        kahta /= 2;
        kolmea /= 3;
        neljaa /= 4;
        kahtav /= 2;
        kolmeav /= 3;
        neljaav /= 4;
        tulos = kahta*10 + kolmea*100 + neljaa*500 - kahtav*10 - kolmeav*100 - neljaav*500;
        return tulos;
    }
    
    private int pisinVaaka(int x, int y, char[][] lauta, char merkki){
        int summa = 0;
        int alkuy = y;
        while(alkuy < pituus && lauta[x][alkuy] == merkki){
            summa++;
            alkuy++;
        }
        alkuy = --y;
        while(alkuy >= 0 && lauta[x][alkuy] == merkki){
            summa++;
            alkuy--;
        }
        return summa;
    }
    
    private int pisinPysty(int x, int y, char[][] lauta, char merkki){
        int summa = 0;
        int alkux = x;
        while(alkux < pituus && lauta[alkux][y] == merkki){
            summa++;
            alkux++;
        }
        alkux = --x;
        while(alkux >= 0 && lauta[alkux][y] == merkki){
            summa++;
            alkux--;
        }
        return summa;
    }
    
    private int pisinVinoVasen(int x, int y, char[][] lauta, char merkki){
        int summa = 0;
        int alkux = x;
        int alkuy = y;
        while(alkux >= 0 && alkuy >= 0 && lauta[alkux][alkuy] == merkki){
            summa++;
            alkux--;
            alkuy--;
        }
        alkux = ++x;
        alkuy = ++y;
        while(alkux < pituus && alkuy < pituus && lauta[alkux][alkuy] == merkki){
            summa++;
            alkux++;
            alkuy++;
        }
        return summa;
    }
    
    private int pisinVinoOikea(int x, int y, char[][] lauta, char merkki){
        int summa = 0;
        int alkux = x;
        int alkuy = y;
        while(alkux >= 0 && alkuy < pituus && lauta[alkux][alkuy] == merkki){
            summa++;
            alkux--;
            alkuy++;
        }
        alkux = ++x;
        alkuy = --y;
        while(alkux < pituus && alkuy >= 0 && lauta[alkux][alkuy] == merkki){
            summa++;
            alkux++;
            alkuy--;
        }
        return summa;
    }
        
    private boolean potentiaalinenSiirto(int x, int y, char[][] lauta){
        int mistax = Math.max(0, x - sade);
        int mihinx = Math.min(x + sade, pituus - 1);
        int mistay = Math.max(y - sade, 0);
        int mihiny = Math.min(y + sade, pituus - 1);
        for(int i = mistax; i <= mihinx; i++){
            for(int j = mistay; j <= mihiny; j++){
                if(lauta[i][j] != '+') return true;
            }
        }
        return false;
    }
}