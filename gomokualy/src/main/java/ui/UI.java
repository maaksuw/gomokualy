
package ui;

import java.util.Scanner;
import logiikka.Peli;

public class UI {
    
    private final Scanner lukija;
    private String regex;
    
    public UI(Scanner s) {
        lukija = s;
        regex = "([1-9]|(1[0-9])) ([A-S])";
    }
    
    public void kaynnista(){
        
        Peli p = new Peli();
        aloitusKonfiguraatio(p);
        boolean lopetetaan = false;
        
        while(true){
            
            p.tulostaLauta();
            p.tulostaVuorot();
            
            while(true){
                
                System.out.println("");
                System.out.print("Sijoitus: ");
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
                    int x = p.muutaXKoordinaattiNumeroksi(vastaus.substring(0,2).trim());
                    int y = p.muutaYKoordinaattiNumeroksi(vastaus.substring(vastaus.length() - 1));
                    if(p.sijoita(x, y)){
                        String tulos = p.pelaaVuoro(x, y);
                        if(!tulos.equals("")){
                            p.tulostaLauta();
                            System.out.println("");
                            System.out.println(tulos);
                            if(loppuuko()){
                                lopetetaan = true;
                                break;
                            } else {
                                p.alustaPeli();
                                aloitusKonfiguraatio(p);
                            }
                        }
                        break;
                    } else System.out.println("N�iss� koordinaateissa on jo toinen nappula.");
             
                } else System.out.println("Koordinaatit eiv�t kelpaa.");
                
            }
            if(lopetetaan) break;
        }
        
        System.out.println("");
        System.out.println("Kiitos pelist�!");
    }
    
     private void aloitusKonfiguraatio(Peli p){
        System.out.println("Tervetuloa Gomoku-peliin.");
        System.out.println("Mink� kokoisella laudalla haluat pelata?");
        System.out.println("1) 19 x 19      2) 15 x 15");
        System.out.print("Sy�t� vaihtoehdon numero: ");
        while(true){
            String vastaus = lukija.nextLine();
            if(vastaus.equals("2")){
                p.setPituus(15);
                regex = "([1-9]|(1[0-5])) ([A-O])";
                break;
            } else if (vastaus.equals("1")) {
                p.setPituus(19);
                regex = "([1-9]|(1[0-9])) ([A-S])";
                break;
            }
            else System.out.println("Sy�t� jonkin yll� n�kyv�n vaihtoehdon numero:");
        }
    }
     
    private boolean loppuuko(){
        System.out.println("");
        System.out.println("Haluatko pelata uudelleen?"
                + "\n1) Kyll�   2) Ei");
        while(true){
            System.out.print("Sy�t� vaihtoehdon numero: ");
            String uudelleenko = lukija.nextLine().trim();
            if(uudelleenko.equals("1")){
                System.out.println("");
                return false;
            } else if (uudelleenko.equals("2")){
                return true;
            }
        }
    }
    
    private void ohjeet(){
        System.out.println("Koordinaattien sy�tt�minen:");
        System.out.println("Sy�t� vaakarivin numero, v�limerkki ja sitten pystyrivin kirjain.");
        System.out.println("Peliohjeiden tarkistaminen:");
        System.out.println("Kun ohjelma kysyy koordinaatteja, paina p.");
        System.out.println("Pelin lopettaminen:");
        System.out.println("Kun ohjelma kysyy koordinaatteja, paina x.");
        
    }
    
    private void pelisaannot(){
        System.out.println("Gomoku:");
        System.out.println("Pelin tarkoituksena on saada viisi omaa nappulaa per�kk�in joko vaaka-, pystyriviin tai vinoittain."
                + "\nPeli� pelaa kaksi pelaajaa vuorotellen, toinen mustilla ja toinen valkoisilla nappuloilla."
                + "\nEnsimm�inen pelaaja, joka saa viisi omaa nappulaansa per�kk�in, voittaa pelin."
                + "\nMusta aloittaa.");
    }
}
