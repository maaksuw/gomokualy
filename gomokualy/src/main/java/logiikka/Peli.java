
package logiikka;

import java.util.Scanner;

public class Peli {
    
    private int pituus;
    private char[][] lauta;
    private Scanner lukija;
    private String regex;
    
    public Peli(Scanner s){
        pituus = 19;
        lauta = new char[pituus][pituus];
        lukija = s;
        regex = "([1-9]|(1[0-9])) ([A-S])";
    }
    
    public void kaynnista(){
        int vari = 1;
        int vuoroja = 0;
        alustaLauta();
        aloitusKonfiguraatio();
        boolean lopetetaan = false;
        while(true){
            tulostaLauta(vari);
            while(true){
                System.out.println("");
                System.out.print("Sijoitus:");
                String vastaus = lukija.nextLine().trim();
                if(vastaus.equals("o")){
                    System.out.println("");
                    ohjeet();
                    continue;
                } else if (vastaus.equals("p")){
                    System.out.println("");
                    pelisaannot();
                    continue;
                } else if (vastaus.equals("x")){
                    lopetetaan = true;
                    break;
                }
                if(vastaus.matches(regex)){
                    int x = muutaXKoordinaattiNumeroksi(vastaus.substring(0,2).trim());
                    int y = muutaYKoordinaattiNumeroksi(vastaus.substring(vastaus.length() - 1));
                    if(sijoita(x, y, vari)){
                        vuoroja++;
                        if(onkoVoittoa(x, y, vari)){
                            tulostaLautaIlmanVuoroja();
                            System.out.println("");
                            if(vari == 1) System.out.println("Musta voitti pelin!");
                            else System.out.println("Valkoinen voitti pelin!");
                            if(loppuuko()){
                                lopetetaan = true;
                                break;
                            } else {
                                vari = 1;
                                vuoroja = 0;
                                alustaLauta();
                                aloitusKonfiguraatio();
                                break;
                            }
                        } else {
                            if(vari == 1) vari--;
                            else vari++;
                        }
                        if(vuoroja == pituus*pituus){
                            tulostaLautaIlmanVuoroja();
                            System.out.println("Tasapeli!");
                            if(loppuuko()){
                                lopetetaan = true;
                                break;
                            } else {
                                vari = 1;
                                vuoroja = 0;
                                alustaLauta();
                                aloitusKonfiguraatio();
                                break;
                            }
                        }
                        break;
                    } else System.out.print("Näissä koordinaateissa on jo toinen nappula.");
                } else {
                    System.out.print("Koordinaatit eivät kelpaa.");
                }
                System.out.println("");
            }
            if(lopetetaan) break;
        }
    }
    
    private boolean loppuuko(){
        System.out.println("");
        System.out.println("Haluatko pelata uudelleen?"
                + "\n1) Kyllä   2) Ei");
        while(true){
            System.out.println("Syötä vaihtoehdon numero:");
            String uudelleenko = lukija.nextLine().trim();
            if(uudelleenko.equals("1")){
                System.out.println("");
                return false;
            } else if (uudelleenko.equals("2")){
                System.out.println("");
                return true;
            }
        }
    }
    
    private boolean onkoVoittoa(int x, int y, int vari) {
        char omaMerkki = 'X';
        if(vari == 0) omaMerkki = 'O';
        System.out.println(vaakasuoraTarkistus(x, y, omaMerkki));
        System.out.println(pystysuoraTarkistus(x, y, omaMerkki));
        System.out.println(vinoVasenTarkistus(x, y, omaMerkki));
        System.out.println(vinoOikeaTarkistus(x, y, omaMerkki));
        return vaakasuoraTarkistus(x, y, omaMerkki) || pystysuoraTarkistus(x, y, omaMerkki) || vinoVasenTarkistus(x, y, omaMerkki) || vinoOikeaTarkistus(x, y, omaMerkki);
    }
    
    private boolean vinoOikeaTarkistus(int x, int y, char omaMerkki) {
        for(int i = 0; i < 5; i++){
            int alkux = x - 4;
            int alkuy = y + 4;
            if(alkux < 0 || alkux + 4 >= pituus || alkuy >= pituus || alkuy - 4 < 0) continue;
            int omaa = 0;
            int kopiox = alkux;
            int kopioy = alkuy;
            for(int j = 0; j < 5; j++){
                if(lauta[kopiox][kopioy] == omaMerkki) omaa++;
                kopiox++;
                kopioy--;
            }
            if(omaa == 5) return true;
            alkux++;
            alkuy--;
        }
        return false;
    }
    
    private boolean vinoVasenTarkistus(int x, int y, char omaMerkki) {
        for(int i = 0; i < 5; i++){
            int alkux = x - 4;
            int alkuy = y - 4;
            if(alkux < 0 || alkux + 4 >= pituus || alkuy < 0 || alkuy + 4 >= pituus) continue;
            int omaa = 0;
            int kopiox = alkux;
            int kopioy = alkuy;
            for(int j = 0; j < 5; j++){
                if(lauta[kopiox][kopioy] == omaMerkki) omaa++;
                kopiox++;
                kopioy++;
            }
            if(omaa == 5) return true;
            alkux++;
            alkuy++;
        }
        return false;
    }
    
    private boolean pystysuoraTarkistus(int x, int y, char omaMerkki) {
        for(int i = x - 4; i <= x; i++){
            if(i < 0 || i + 4 >= pituus) continue;
            int omaa = 0;
            for(int j = i; j < i + 5; j++){
                if(lauta[j][y] == omaMerkki) omaa++;
            }
            if(omaa == 5) return true;
        }
        return false;
    }
    
    private boolean vaakasuoraTarkistus(int x, int y, char omaMerkki) {
        for(int i = y - 4; i <= y; i++){
            if(i < 0 || i + 4 >= pituus) continue;
            int omaa = 0;
            for(int j = i; j < i + 5; j++){
                if(lauta[x][j] == omaMerkki) omaa++;
            }
            if(omaa == 5) return true;
        }
        return false;
    }
    
    private int muutaYKoordinaattiNumeroksi(String ykoordinaatti){
        int y = ykoordinaatti.charAt(0) - 64;
        return y - 1;
    }
    
    private int muutaXKoordinaattiNumeroksi(String xkoordinaatti){
        int x = Integer.valueOf(xkoordinaatti);
        return pituus - x;
    }
    
    private boolean sijoita(int x, int y, int vari){
        if(lauta[x][y] == '+'){
            if(vari == 1) lauta[x][y] = 'X';
            if(vari == 0) lauta[x][y] = 'O';
            return true;
        } 
        return false;
    }
    
    private void alustaLauta(){
        for(int i = 0; i < pituus; i++){
            for(int j = 0; j < pituus; j++){
                lauta[i][j] = '+';
            }
        }
    }
    
    private void tulostaLauta(int vari){
        System.out.println("");
        System.out.println("ohjeet(o) pelisäännöt(p) lopeta(x)");
        int vaakarivi = pituus;
        for(int i = 0; i < pituus; i++){
            if(vaakarivi < 10) System.out.print(" ");
            System.out.print(vaakarivi + " ");
            for(int j = 0; j < pituus; j++){
                if(j == pituus - 1) System.out.print(lauta[i][j]);
                else System.out.print(lauta[i][j] + " ");
            }
            System.out.println("");
            vaakarivi--;
        }
        System.out.print("   ");
        char asti = 'S';
        if(pituus == 15) asti = 'O';
        for(char i = 'A'; i <= asti; i++){
            if(i == asti) System.out.print(i);
            else System.out.print(i + " ");
        }
        System.out.println("");
        
        if(vari == 1) System.out.println("Vuorossa: MUSTA");
        else System.out.println("Vuorossa: VALKOINEN");
    }
    
    private void tulostaLautaIlmanVuoroja(){
        System.out.println("");
        System.out.println("ohjeet(o) pelisäännöt(p) lopeta(x)");
        int vaakarivi = pituus;
        for(int i = 0; i < pituus; i++){
            if(vaakarivi < 10) System.out.print(" ");
            System.out.print(vaakarivi + " ");
            for(int j = 0; j < pituus; j++){
                if(j == pituus - 1) System.out.print(lauta[i][j]);
                else System.out.print(lauta[i][j] + " ");
            }
            System.out.println("");
            vaakarivi--;
        }
        System.out.print("   ");
        char asti = 'S';
        if(pituus == 15) asti = 'O';
        for(char i = 'A'; i <= asti; i++){
            if(i == asti) System.out.print(i);
            else System.out.print(i + " ");
        }
        System.out.println("");
    }
    
    private void pelisaannot(){
        System.out.println("Gomoku:");
        System.out.println("Pelin tarkoituksena on saada viisi omaa nappulaa peräkkäin joko vaaka-, pystyriviin tai vinoittain."
                + "\nPeliä pelaa kaksi pelaajaa vuorotellen, toinen mustilla ja toinen valkoisilla nappuloilla."
                + "\nEnsimmäinen pelaaja, joka saa viisi omaa nappulaansa peräkkäin, voittaa pelin."
                + "\nMusta aloittaa.");
    }
    
    private void ohjeet(){
        System.out.println("Koordinaattien syöttäminen:");
        System.out.println("Syötä vaakarivin numero, välimerkki ja sitten pystyrivin kirjain.");
        System.out.println("Peliohjeiden tarkistaminen:");
        System.out.println("Kun ohjelma kysyy koordinaatteja, paina p.");
        System.out.println("Pelin lopettaminen:");
        System.out.println("Kun ohjelma kysyy koordinaatteja, paina x.");
        
    }
    
    private void aloitusKonfiguraatio(){
        System.out.println("Tervetuloa Gomoku-peliin.");
        System.out.println("Minkä kokoisella laudalla haluat pelata?");
        System.out.println("1) 19 x 19      2) 15 x 15");
        System.out.println("Syötä vaihtoehdon numero:");
        while(true){
            String vastaus = lukija.nextLine();
            if(vastaus.equals("2")){
                pituus = 15;
                regex = "([1-9]|(1[0-5])) ([A-O])";
                break;
            } else if (vastaus.equals("1")) {
                pituus = 19;
                regex = "([1-9]|(1[0-9])) ([A-S])";
                break;
            }
            else System.out.println("Syötä jonkin yllä näkyvän vaihtoehdon numero:");
        }
    }
}
