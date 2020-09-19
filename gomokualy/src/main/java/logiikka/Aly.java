
package logiikka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Aly {
    
    private HashMap<String, Integer> varasto;
    private char merkki;
    private int pituus;
    private int vuoroja; //hommaudu eroon tästä, tämä on nyt vaan väliaikainen
    private int sade;
    private int syvyys;
    private final int aareton;
    
    public Aly() {
        varasto = new HashMap<>();
        vuoroja = 0;
        sade = 2;
        syvyys = 4;
        aareton = 1000000000;
    }

    public void setMerkki(int vari) {
        if (vari == 1) merkki = 'X';
        else merkki = 'O';
    }

    public void setPituus(int pituus) {
        this.pituus = pituus;
    }
    
    public int[] teeSiirto(char[][] lauta) {
        int[] koordinaatit = new int[2];
        
        //korvaa tämä myöhemmin
        vuoroja++;
        if (vuoroja == 1 && merkki == 'X') {
            koordinaatit[0] = pituus / 2;
            koordinaatit[1] = pituus / 2;
            return koordinaatit;
        }
        //
        
        int paras = -aareton - 1;
        int alpha = -aareton;
        int beetta = aareton;
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
//                    } //
                    
                    int tulos = arvioi(0, 1, lauta, alpha, beetta);
                    if(tulos > alpha) alpha = tulos;
                    
                    //testitulostuksia
//                    System.out.println("tulos " + tulos);
//                    System.out.println("paras " + paras);
//                    
                    
                    if (tulos > paras) {
                        koordinaatit[0] = i;
                        koordinaatit[1] = j;
                        paras = tulos;
                    }
                    lauta[i][j] = '+';
                }
            }
        }
        return koordinaatit;
    }
    
    private int arvioi(int minimax, int taso, char[][] lauta, int alpha, int beetta) {
        if (taso == syvyys) {
            return pohjaHeuristiikka(lauta);
        }
        ArrayList<Kolmio> siirrot = new ArrayList<>();
        int tulos = (minimax == 1) ? -aareton : aareton;
        char nykyinenMerkki = (minimax == 1) ? 'X' : 'O';
        for (int i = 0; i < pituus; i++) {
            for (int j = 0; j < pituus; j++) {
                if (potentiaalinenSiirto(i, j, lauta)) {
                    lauta[i][j] = nykyinenMerkki;
                    int arvio = pohjaHeuristiikka(lauta);
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
            lauta[i][j] = nykyinenMerkki;
            if (minimax == 1) {
                tulos = Math.max(tulos, arvioi(0, taso + 1, lauta, alpha, beetta));
                if(tulos >= beetta) {
                    lauta[i][j] = '+';
                    return tulos;
                }
                if(tulos > alpha) alpha = tulos;
            } else {
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
    
    public int yksinkertainenPohjaHeuristiikkaOngelmatilanteisiin(char[][] lauta) {
        
        char vastustajanMerkki = merkki == 'X' ? 'O' : 'X';
        for (int i = 0; i < pituus; i++) {
            for (int j = 0; j < pituus; j++) {
                if (lauta[i][j] == merkki) {
                    
                    int[] ans = new int[3];
                    pisinVaaka(i, j, lauta, merkki, ans);
                    if (ans[0] >= 5) return aareton;
                    
                    pisinPysty(i, j, lauta, merkki, ans);
                    if (ans[0] >= 5) return aareton;
                    
                    ans = new int[5];
                    pisinVinoVasen(i, j, lauta, merkki, ans);
                    
                    pisinVinoOikea(i, j, lauta, merkki, ans);
                    if (ans[0] >= 5) return aareton;
                    
                } else if (lauta[i][j] == vastustajanMerkki) {
                    int[] ans = new int[3];
                    pisinVaaka(i, j, lauta, vastustajanMerkki, ans);
                    if (ans[0] >= 5) return -aareton;
                    
                    pisinPysty(i, j, lauta, vastustajanMerkki, ans);
                    if (ans[0] >= 5) return -aareton;
                    
                    ans = new int[5];
                    pisinVinoVasen(i, j, lauta, vastustajanMerkki, ans);
                    if (ans[0] >= 5) return -aareton;
                    
                    pisinVinoOikea(i, j, lauta, vastustajanMerkki, ans);
                    if (ans[0] >= 5) return -aareton;
                }
            }
        }
        return 0;
    }
    
    private int pohjaHeuristiikka(char[][] lauta) { // refaktoroiroiroi??
        int tulos = 0;
        int kakkosia = 0;
        int avoimiaKakkosia = 0;
        int kolmosia = 0;
        int avoimiaKolmosia = 0;
        int nelosia = 0;
        int kakkosiav = 0;
        int avoimiaKakkosiav = 0;
        int kolmosiav = 0;
        int avoimiaKolmosiav = 0;
        int nelosiav = 0;
        char vastustajanMerkki = merkki == 'X' ? 'O' : 'X';
        for (int i = 0; i < pituus; i++) {
            for (int j = 0; j < pituus; j++) {
                if (lauta[i][j] == merkki) {
                    
                    int[] ans = new int[3];
                    pisinVaaka(i, j, lauta, merkki, ans);
                    if (ans[0] == 2) {
                        if ((ans[1] >= 0 && lauta[i][ans[1]] == '+') && (ans[2] < pituus && lauta[i][ans[2]] == '+')) avoimiaKakkosia++;
                        if ((ans[1] >= 0 && lauta[i][ans[1]] == '+') || (ans[2] < pituus && lauta[i][ans[2]] == '+')) kakkosia++;
                    }
                    if (ans[0] == 3) {
                        if ((ans[1] >= 0 && lauta[i][ans[1]] == '+') && (ans[2] < pituus && lauta[i][ans[2]] == '+')) avoimiaKolmosia++;
                        if ((ans[1] >= 0 && lauta[i][ans[1]] == '+') || (ans[2] < pituus && lauta[i][ans[2]] == '+')) kolmosia++;
                    }
                    if (ans[0] == 4) {
                        if ((ans[1] >= 0 && lauta[i][ans[1]] == '+') && (ans[2] < pituus && lauta[i][ans[2]] == '+')) return aareton;
                        if ((ans[1] >= 0 && lauta[i][ans[1]] == '+') || (ans[2] < pituus && lauta[i][ans[2]] == '+')) nelosia++;
                    } 
                    if (ans[0] >= 5) return aareton;
                    
                    pisinPysty(i, j, lauta, merkki, ans);
                    if (ans[0] == 2) {
                        if ((ans[1] >= 0 && lauta[ans[1]][j] == '+') && (ans[2] < pituus && lauta[ans[2]][j] == '+')) avoimiaKakkosia++;
                        if ((ans[1] >= 0 && lauta[ans[1]][j] == '+') || (ans[2] < pituus && lauta[ans[2]][j] == '+')) kakkosia++;
                    }
                    if (ans[0] == 3) {
                        if ((ans[1] >= 0 && lauta[ans[1]][j] == '+') && (ans[2] < pituus && lauta[ans[2]][j] == '+')) avoimiaKolmosia++;
                        if ((ans[1] >= 0 && lauta[ans[1]][j] == '+') || (ans[2] < pituus && lauta[ans[2]][j] == '+')) kolmosia++;
                    }
                    if (ans[0] == 4) {
                        if ((ans[1] >= 0 && lauta[ans[1]][j] == '+') && (ans[2] < pituus && lauta[ans[2]][j] == '+')) return aareton;
                        if ((ans[1] >= 0 && lauta[ans[1]][j] == '+') || (ans[2] < pituus && lauta[ans[2]][j] == '+')) nelosia++;
                    }
                    if (ans[0] >= 5) return aareton;
                    
                    ans = new int[5];
                    pisinVinoVasen(i, j, lauta, merkki, ans);
                    if (ans[0] == 2) {
                        if ((ans[1] >= 0 && ans[2] >= 0 && lauta[ans[1]][ans[2]] == '+') && (ans[3] < pituus && ans[4] < pituus && lauta[ans[3]][ans[4]] == '+')) avoimiaKakkosia++;
                        if ((ans[1] >= 0 && ans[2] >= 0 && lauta[ans[1]][ans[2]] == '+') || (ans[3] < pituus && ans[4] < pituus && lauta[ans[3]][ans[4]] == '+')) kakkosia++;
                    }
                    if (ans[0] == 3) {
                        if ((ans[1] >= 0 && ans[2] >= 0 && lauta[ans[1]][ans[2]] == '+') && (ans[3] < pituus && ans[4] < pituus && lauta[ans[3]][ans[4]] == '+')) avoimiaKolmosia++;
                        if ((ans[1] >= 0 && ans[2] >= 0 && lauta[ans[1]][ans[2]] == '+') || (ans[3] < pituus && ans[4] < pituus && lauta[ans[3]][ans[4]] == '+')) kolmosia++;
                    }
                    if (ans[0] == 4) {
                        if ((ans[1] >= 0 && ans[2] >= 0 && lauta[ans[1]][ans[2]] == '+') && (ans[3] < pituus && ans[4] < pituus && lauta[ans[3]][ans[4]] == '+')) return aareton;
                        if ((ans[1] >= 0 && ans[2] >= 0 && lauta[ans[1]][ans[2]] == '+') || (ans[3] < pituus && ans[4] < pituus && lauta[ans[3]][ans[4]] == '+')) nelosia++;
                    }
                    if (ans[0] >= 5) return aareton;
                    
                    pisinVinoOikea(i, j, lauta, merkki, ans);
                    if (ans[0] == 2) {
                        if ((ans[1] >= 0 && ans[2] < pituus && lauta[ans[1]][ans[2]] == '+') && (ans[3] < pituus && ans[4] >= 0 && lauta[ans[3]][ans[4]] == '+')) avoimiaKakkosia++;
                        if ((ans[1] >= 0 && ans[2] < pituus && lauta[ans[1]][ans[2]] == '+') || (ans[3] < pituus && ans[4] >= 0 && lauta[ans[3]][ans[4]] == '+')) kakkosia++;
                    }
                    if (ans[0] == 3) {
                        if ((ans[1] >= 0 && ans[2] < pituus && lauta[ans[1]][ans[2]] == '+') && (ans[3] < pituus && ans[4] >= 0 && lauta[ans[3]][ans[4]] == '+')) avoimiaKolmosia++;
                        if ((ans[1] >= 0 && ans[2] < pituus && lauta[ans[1]][ans[2]] == '+') || (ans[3] < pituus && ans[4] >= 0 && lauta[ans[3]][ans[4]] == '+')) kolmosia++;
                    }
                    if (ans[0] == 4) {
                        if ((ans[1] >= 0 && ans[2] < pituus && lauta[ans[1]][ans[2]] == '+') && (ans[3] < pituus && ans[4] >= 0 && lauta[ans[3]][ans[4]] == '+')) return aareton;
                        if ((ans[1] >= 0 && ans[2] < pituus && lauta[ans[1]][ans[2]] == '+') || (ans[3] < pituus && ans[4] >= 0 && lauta[ans[3]][ans[4]] == '+')) nelosia++;
                    }
                    if (ans[0] >= 5) return aareton;
                    
                } else if (lauta[i][j] == vastustajanMerkki) {
                    
                    int[] ans = new int[3];
                    pisinVaaka(i, j, lauta, vastustajanMerkki, ans);
                    if (ans[0] == 2) {
                        if ((ans[1] >= 0 && lauta[i][ans[1]] == '+') && (ans[2] < pituus && lauta[i][ans[2]] == '+')) avoimiaKakkosiav++;
                        if ((ans[1] >= 0 && lauta[i][ans[1]] == '+') || (ans[2] < pituus && lauta[i][ans[2]] == '+')) kakkosiav++;
                    }
                    if (ans[0] == 3) {
                        if ((ans[1] >= 0 && lauta[i][ans[1]] == '+') && (ans[2] < pituus && lauta[i][ans[2]] == '+')) avoimiaKolmosiav++;
                        if ((ans[1] >= 0 && lauta[i][ans[1]] == '+') || (ans[2] < pituus && lauta[i][ans[2]] == '+')) kolmosiav++;
                    }
                    if (ans[0] == 4) {
                        if ((ans[1] >= 0 && lauta[i][ans[1]] == '+') && (ans[2] < pituus && lauta[i][ans[2]] == '+')) return -aareton;
                        if ((ans[1] >= 0 && lauta[i][ans[1]] == '+') || (ans[2] < pituus && lauta[i][ans[2]] == '+')) nelosiav++;
                    } 
                    if (ans[0] >= 5) return -aareton;
                    
                    pisinPysty(i, j, lauta, vastustajanMerkki, ans);
                    if (ans[0] == 2) {
                        if ((ans[1] >= 0 && lauta[ans[1]][j] == '+') && (ans[2] < pituus && lauta[ans[2]][j] == '+')) avoimiaKakkosiav++;
                        if ((ans[1] >= 0 && lauta[ans[1]][j] == '+') || (ans[2] < pituus && lauta[ans[2]][j] == '+')) kakkosiav++;
                    }
                    if (ans[0] == 3) {
                        if ((ans[1] >= 0 && lauta[ans[1]][j] == '+') && (ans[2] < pituus && lauta[ans[2]][j] == '+')) avoimiaKolmosiav++;
                        if ((ans[1] >= 0 && lauta[ans[1]][j] == '+') || (ans[2] < pituus && lauta[ans[2]][j] == '+')) kolmosiav++;
                    }
                    if (ans[0] == 4) {
                        if ((ans[1] >= 0 && lauta[ans[1]][j] == '+') && (ans[2] < pituus && lauta[ans[2]][j] == '+')) return -aareton;
                        if ((ans[1] >= 0 && lauta[ans[1]][j] == '+') || (ans[2] < pituus && lauta[ans[2]][j] == '+')) nelosiav++;
                    }
                    if (ans[0] >= 5) return -aareton;
                    
                    ans = new int[5];
                    pisinVinoVasen(i, j, lauta, vastustajanMerkki, ans);
                    if (ans[0] == 2) {
                        if ((ans[1] >= 0 && ans[2] >= 0 && lauta[ans[1]][ans[2]] == '+') && (ans[3] < pituus && ans[4] < pituus && lauta[ans[3]][ans[4]] == '+')) avoimiaKakkosiav++;
                        if ((ans[1] >= 0 && ans[2] >= 0 && lauta[ans[1]][ans[2]] == '+') || (ans[3] < pituus && ans[4] < pituus && lauta[ans[3]][ans[4]] == '+')) kakkosiav++;
                    }
                    if (ans[0] == 3) {
                        if ((ans[1] >= 0 && ans[2] >= 0 && lauta[ans[1]][ans[2]] == '+') && (ans[3] < pituus && ans[4] < pituus && lauta[ans[3]][ans[4]] == '+')) avoimiaKolmosiav++;
                        if ((ans[1] >= 0 && ans[2] >= 0 && lauta[ans[1]][ans[2]] == '+') || (ans[3] < pituus && ans[4] < pituus && lauta[ans[3]][ans[4]] == '+')) kolmosiav++;
                    }
                    if (ans[0] == 4) {
                        if ((ans[1] >= 0 && ans[2] >= 0 && lauta[ans[1]][ans[2]] == '+') && (ans[3] < pituus && ans[4] < pituus && lauta[ans[3]][ans[4]] == '+')) return -aareton;
                        if ((ans[1] >= 0 && ans[2] >= 0 && lauta[ans[1]][ans[2]] == '+') || (ans[3] < pituus && ans[4] < pituus && lauta[ans[3]][ans[4]] == '+')) nelosiav++;
                    }
                    if (ans[0] >= 5) return -aareton;
                    
                    pisinVinoOikea(i, j, lauta, vastustajanMerkki, ans);
                    if (ans[0] == 2) {
                        if ((ans[1] >= 0 && ans[2] < pituus && lauta[ans[1]][ans[2]] == '+') && (ans[3] < pituus && ans[4] >= 0 && lauta[ans[3]][ans[4]] == '+')) avoimiaKakkosiav++;
                        if ((ans[1] >= 0 && ans[2] < pituus && lauta[ans[1]][ans[2]] == '+') || (ans[3] < pituus && ans[4] >= 0 && lauta[ans[3]][ans[4]] == '+')) kakkosiav++;
                    }
                    if (ans[0] == 3) {
                        if ((ans[1] >= 0 && ans[2] < pituus && lauta[ans[1]][ans[2]] == '+') && (ans[3] < pituus && ans[4] >= 0 && lauta[ans[3]][ans[4]] == '+')) avoimiaKolmosiav++;
                        if ((ans[1] >= 0 && ans[2] < pituus && lauta[ans[1]][ans[2]] == '+') || (ans[3] < pituus && ans[4] >= 0 && lauta[ans[3]][ans[4]] == '+')) kolmosiav++;
                    }
                    if (ans[0] == 4) {
                        if ((ans[1] >= 0 && ans[2] < pituus && lauta[ans[1]][ans[2]] == '+') && (ans[3] < pituus && ans[4] >= 0 && lauta[ans[3]][ans[4]] == '+')) return -aareton;
                        if ((ans[1] >= 0 && ans[2] < pituus && lauta[ans[1]][ans[2]] == '+') || (ans[3] < pituus && ans[4] >= 0 && lauta[ans[3]][ans[4]] == '+')) nelosiav++;
                    }
                    if (ans[0] >= 5) return -aareton;
                }
            }
        }
        kakkosia /= 2;
        avoimiaKakkosia /= 2;
        kolmosia /= 3;
        avoimiaKolmosia /= 3;
        nelosia /= 4;
        kakkosiav /= 2;
        avoimiaKakkosiav /= 2;
        kolmosiav /= 3;
        avoimiaKolmosiav /= 3;
        nelosiav /= 4;
        tulos = kakkosia * 1 - kakkosiav * 1 + avoimiaKakkosia * 50 - avoimiaKakkosiav * 50 + kolmosia * 100 - kolmosiav * 100 + avoimiaKolmosia * 1000 - avoimiaKolmosiav * 1000 + nelosia * 1000 - nelosiav * 1000;
        return tulos;
    }
    
    private void pisinVaaka(int x, int y, char[][] lauta, char merkki, int[] ans) {
        int summa = 0;
        int alkuy = y;
        while (alkuy < pituus && lauta[x][alkuy] == merkki) {
            summa++;
            alkuy++;
        }
        ans[2] = alkuy; 
        alkuy = --y;
        while (alkuy >= 0 && lauta[x][alkuy] == merkki) {
            summa++;
            alkuy--;
        }
        ans[1] = alkuy;
        ans[0] = summa;
    }
    
    private void pisinPysty(int x, int y, char[][] lauta, char merkki, int[] ans) {
        int summa = 0;
        int alkux = x;
        while (alkux < pituus && lauta[alkux][y] == merkki) {
            summa++;
            alkux++;
        }
        ans[2] = alkux;
        alkux = --x;
        while (alkux >= 0 && lauta[alkux][y] == merkki) {
            summa++;
            alkux--;
        }
        ans[1] = alkux;
        ans[0] = summa;
    }
    
    private void pisinVinoVasen(int x, int y, char[][] lauta, char merkki, int[] ans) {
        int summa = 0;
        int alkux = x;
        int alkuy = y;
        while (alkux >= 0 && alkuy >= 0 && lauta[alkux][alkuy] == merkki) {
            summa++;
            alkux--;
            alkuy--;
        }
        ans[1] = alkux;
        ans[2] = alkuy;
        alkux = ++x;
        alkuy = ++y;
        while (alkux < pituus && alkuy < pituus && lauta[alkux][alkuy] == merkki) {
            summa++;
            alkux++;
            alkuy++;
        }
        ans[3] = alkux;
        ans[4] = alkuy;
        ans[0] = summa;
    }
    
    private void pisinVinoOikea(int x, int y, char[][] lauta, char merkki, int[] ans) {
        int summa = 0;
        int alkux = x;
        int alkuy = y;
        while (alkux >= 0 && alkuy < pituus && lauta[alkux][alkuy] == merkki) {
            summa++;
            alkux--;
            alkuy++;
        }
        ans[1] = alkux;
        ans[2] = alkuy;
        alkux = ++x;
        alkuy = --y;
        while (alkux < pituus && alkuy >= 0 && lauta[alkux][alkuy] == merkki) {
            summa++;
            alkux++;
            alkuy--;
        }
        ans[3] = alkux;
        ans[4] = alkuy;
        ans[0] = summa;
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