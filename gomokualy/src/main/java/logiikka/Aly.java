
package logiikka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Aly {
    
    private HashMap<String, Integer> varasto;
    private ArrayList<int[]> suunnat;
    private char merkki;
    private int pituus;
    private boolean ekaSiirto;
    private int sade;
    private int syvyys;
    private final long aareton;
    
    public Aly() {
        varasto = new HashMap<>();
        suunnat = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            suunnat.add(new int[4]);
        }
        alustaSuunnat();
        sade = 2;
        syvyys = 4;
        aareton = 1000000000;
        ekaSiirto = true;
    }
    
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

    public void setMerkki(int vari) {
        if (vari == 1) merkki = 'X';
        else merkki = 'O';
    }

    public void setPituus(int pituus) {
        this.pituus = pituus;
    }
    
    private int[] ekaSiirto(char[][] lauta) {
        ekaSiirto = false;
        int[] koordinaatit = new int[2];
        koordinaatit[0] = pituus / 2;
        koordinaatit[1] = pituus / 2;
        if(lauta[pituus / 2][pituus / 2] != '+') koordinaatit[1] = pituus / 2 + 1;
        return koordinaatit;
    }
    
    public int[] teeSiirto(char[][] lauta) {
        int[] koordinaatit = new int[2];
        
        if(ekaSiirto) return ekaSiirto(lauta);
        
        long tulos = 0;
        long paras = -aareton - 1;
        long alpha = -aareton;
        long beetta = aareton;
        for (int i = 0; i < pituus; i++) {  
            for (int j = 0; j < pituus; j++) {
                if (potentiaalinenSiirto(i, j, lauta)) {
                    lauta[i][j] = merkki;
                    
                    //testitulostuksia
//                    System.out.println("siirto? " + i + " " + j);
//                    for (int x = 0; x < pituus; x++) {
//                        for (int y = 0; y < pituus; y++) {
//                            System.out.print(lauta[x][y] + " ");
//                        }
//                        System.out.println("");
//                    }
                    
                    if(onkoVoittoa(i, j, merkki, lauta)) {
                        koordinaatit[0] = i;
                        koordinaatit[1] = j;
                        lauta[i][j] = '+';
                        return koordinaatit;
                    } else tulos = arvioi(0, 1, lauta, alpha, beetta);
                    if(tulos > alpha) alpha = tulos;
                    
                    //testitulostuksia
//                    System.out.println("tulos " + tulos);
//                    System.out.println("paras " + paras);
                    
                    
                    if (tulos > paras) {
                        koordinaatit[0] = i;
                        koordinaatit[1] = j;
                        paras = tulos;
                    }
                    lauta[i][j] = '+';
                }
            }
        }
//        System.out.println("tulos " + tulos);
        return koordinaatit;
    }
    
    private long arvioi(int minimax, int taso, char[][] lauta, long alpha, long beetta) {
        char sijoitettavaMerkki;
        if(merkki == 'X'){
            sijoitettavaMerkki = (minimax == 1) ? 'X' : 'O';
        } else {
            sijoitettavaMerkki = (minimax == 1) ? 'O': 'X';
        }
        
        if (taso == syvyys) {
            return pohjaHeuristiikka(lauta, sijoitettavaMerkki);
        }
        ArrayList<Kolmio> siirrot = new ArrayList<>();
        long tulos = (minimax == 1) ? -aareton : aareton;
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
        for(Kolmio k: siirrot){
            int i = k.getX();
            int j = k.getY();
            lauta[i][j] = sijoitettavaMerkki;
            if (minimax == 1) {
                if(onkoVoittoa(i, j, sijoitettavaMerkki, lauta)) {
                    lauta[i][j] = '+';
                    return aareton;
                }
                tulos = Math.max(tulos, arvioi(0, taso + 1, lauta, alpha, beetta));
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
    
    public boolean onkoVoittoa(int x, int y, char vuoro, char[][] lauta) {
        int[] ans = new int[5];
        for(int i = 0; i < 4; i++){
            laskePisinSuora(x, y, suunnat.get(i), lauta, vuoro, ans);
            if(ans[0] >= 5) return true;
        }
        return false;
    }
    
    public long pohjaHeuristiikka(char[][] lauta, char vuoro) {
        int tulos = 0;
        int[] tilasto = new int[10]; //kakkoset 0, avoimet kakkoset 1, kolmoset 2, avoimet kolmoset 3, neloset 4, ja vastustajan vastaavat (5 - 9)
        char vastustajanMerkki = merkki == 'X' ? 'O' : 'X';
        int[] ans = new int[5];
        boolean voitto = false;
        boolean vastustajaVoittaa = false;
        for (int i = 0; i < pituus; i++) {
            for (int j = 0; j < pituus; j++) {
                if (lauta[i][j] == merkki) {
                    
                    laskePisinSuora(i, j, suunnat.get(0), lauta, merkki, ans);
                    if(uhkaarvio(ans, lauta, true, tilasto, i, j) == aareton) voitto = true;
                    tulos += uhkaarvio(ans, lauta, true, tilasto, i, j);
                    
                    laskePisinSuora(i, j, suunnat.get(1), lauta, merkki, ans);
                    if(uhkaarvio(ans, lauta, true, tilasto, i, j) == aareton) voitto = true;
                    tulos += uhkaarvio(ans, lauta, true, tilasto, i, j);
                    
                    laskePisinSuora(i, j, suunnat.get(2), lauta, merkki, ans);
                    if(uhkaarvio(ans, lauta, true, tilasto, i, j) == aareton) voitto = true;
                    tulos += uhkaarvio(ans, lauta, true, tilasto, i, j);
                    
                    laskePisinSuora(i, j, suunnat.get(3), lauta, merkki, ans);
                    if(uhkaarvio(ans, lauta, true, tilasto, i, j) == aareton) voitto = true;
                    tulos += uhkaarvio(ans, lauta, true, tilasto, i, j);
                    
                } else if (lauta[i][j] == vastustajanMerkki) {
                    
                    laskePisinSuora(i, j, suunnat.get(0), lauta, vastustajanMerkki, ans);
                    if(uhkaarvio(ans, lauta, false, tilasto, i, j) == -aareton) vastustajaVoittaa = true;
                    tulos += uhkaarvio(ans, lauta, false, tilasto, i, j);
                    
                    laskePisinSuora(i, j, suunnat.get(1), lauta, vastustajanMerkki, ans);
                    if(uhkaarvio(ans, lauta, false, tilasto, i, j) == -aareton) vastustajaVoittaa = true;
                    tulos += uhkaarvio(ans, lauta, false, tilasto, i, j);
                    
                    laskePisinSuora(i, j, suunnat.get(2), lauta, vastustajanMerkki, ans);
                    if(uhkaarvio(ans, lauta, false, tilasto, i, j) == -aareton) vastustajaVoittaa = true;
                    tulos += uhkaarvio(ans, lauta, false, tilasto, i, j);
                    
                    laskePisinSuora(i, j, suunnat.get(3), lauta, vastustajanMerkki, ans);
                    if(uhkaarvio(ans, lauta, false, tilasto, i, j) == -aareton) vastustajaVoittaa = true;
                    tulos += uhkaarvio(ans, lauta, false, tilasto, i, j);
                    
                }
            }
        }
        //jos vastustajalla avoin neljä ja mulla ei, häviö riippumatta kenen vuoro
        //jos mulla avoin neljä ja vastustajalla ei, voitti riippumatta kenen vuoro
        //jos molemmilla on avoin neljä, se voittaa kumman vuoro on
        if(vastustajaVoittaa && !voitto) return -aareton;
        if(voitto && !vastustajaVoittaa) return aareton;
        if(voitto && vastustajaVoittaa){
            if(vuoro == merkki) return aareton;
            else return -aareton;
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
                if(botti) return aareton;
                return -aareton;
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
    
    private boolean laudalla(int a){
        if(a >= 0 && a < pituus) return true;
        return false;
    }
        
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