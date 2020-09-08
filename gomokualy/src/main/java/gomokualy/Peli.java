
package gomokualy;

import java.util.Scanner;

public class Peli {
    
    int pituus;
    private char[][] lauta;
    private Scanner lukija;
    private String regex;
    
    public Peli(Scanner s){
        pituus = 19;
        lauta = new char[pituus][pituus];
        lukija = s;
        regex = "([1-9]|1[0-9]) ([A-S])";
    }
    
    public void kaynnista(){
        int vari = 1;
        boolean lopetetaan = false;
        alustaLauta();
        aloitusKonfiguraatio();
        while(true){
            tulostaLauta(vari);
            while(true){
                System.out.println("");
                System.out.println("Sijoitus:"); //T�m� pit�isi saadaa toimimaan pelk�ll� printill�.
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
                        if(vari == 1) vari--;
                        else vari++;
                        break;
                    } else System.out.println("N�iss� koordinaateissa on jo toinen nappula.");
                } 
                else System.out.println("Koordinaatit eiv�t kelpaa.");
                System.out.println("");
            }
            if(lopetetaan) break;
        }
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
        if(lauta[x][y] == '#'){
            if(vari == 1) lauta[x][y] = 'X';
            if(vari == 0) lauta[x][y] = 'O';
            return true;
        } 
        return false;
    }
    
    private void alustaLauta(){
        for(int i = 0; i < pituus; i++){
            for(int j = 0; j < pituus; j++){
                lauta[i][j] = '#';
            }
        }
    }
    
    private void tulostaLauta(int vari){
        System.out.println("");
        System.out.println("ohjeet(o) pelis��nn�t(p) lopeta(x)");
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
    
    private void pelisaannot(){
        System.out.println("Gomoku:");
        System.out.println("Pelin tarkoituksena on saada viisi omaa nappulaa per�kk�in joko vaaka-, pystyriviin tai vinoittain."
                + "\nPeli� pelaa kaksi pelaajaa vuorotellen, toinen mustilla ja toinen valkoisilla nappuloilla."
                + "\nEnsimm�inen pelaaja, joka saa viisi omaa nappulaansa per�kk�in, voittaa pelin."
                + "\nMusta aloittaa.");
    }
    
    private void ohjeet(){
        System.out.println("Koordinaattien sy�tt�minen:");
        System.out.println("Sy�t� ensin vaakarivin numero, paina enter ja sy�t� sitten pystyrivin kirjain.");
        System.out.println("Peliohjeiden tarkistaminen:");
        System.out.println("Kun ohjelma kysyy koordinaatteja, paina p.");
        System.out.println("Pelin lopettaminen:");
        System.out.println("Kun ohjelma kysyy koordinaatteja, paina x.");
        
    }
    
    private void aloitusKonfiguraatio(){
        System.out.println("Tervetuloa Gomoku-peliin.");
        System.out.println("Mink� kokoisella laudalla haluat pelata?");
        System.out.println("1) 19 x 19      2) 15 x 15");
        System.out.println("Sy�t� vaihtoehdon numero:");
        while(true){
            String vastaus = lukija.nextLine();
            if(vastaus.matches("[12]")){
                int valinta = Integer.valueOf(vastaus);
                if (valinta == 2) {
                    pituus = 15;
                    regex = "([1-5]|1[0-5]) ([A-O])";
                }
                break;
            } else System.out.println("Sy�t� jonkin yll� n�kyv�n vaihtoehdon numero:");
        }
    }
}
