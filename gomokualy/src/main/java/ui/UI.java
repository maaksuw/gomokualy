
package ui;

import java.util.Scanner;
import logiikka.Aly;
import logiikka.VanhaLauta;

/**
 * Pelin tekstik�ytt�liittym�.
 */

public class UI {
    
    private final Scanner lukija;
    private Aly botti1;
    private Aly botti2;
    
    /**
     * Kertoo pelaako pelaaja bottia vai ihmist� vastaan, vai pelaako kaksi bottia vastakkain.
     */
    private boolean bottiIhminen;
    
    /**
     * Kertoo onko vuorossa botti vai ihmispelaaja.
     */
    private boolean botinVuoro;
    
    /**
     * Kahden botin peliss� kertoo kumman botin vuoro on.
     */
    private boolean botin1Vuoro;
    
    /**
     * K�ytet��n tarkistaessa ovatko sy�tetyt koordinaatit oikeaa muotoa.
     */
    private String regex;
    
    /**
     * Pelilauta.
     */
    private VanhaLauta lauta;
    
    public UI(Scanner s) {
        lukija = s;
        regex = "([1-9]|(1[0-9])) ([A-S])";
        bottiIhminen = false;
        botinVuoro = false;
        lauta = new VanhaLauta();
    }
    
    /**
     * Pelisilmukka.
     */
    public void kaynnista() {
        
        aloitusKonfiguraatio();
        boolean lopetetaan = false;
        boolean tyhjaLauta = true;
        
        while (true) {
            
            if (!(tyhjaLauta && botinVuoro == true)) {
                lauta.tulostaLauta();
                lauta.tulostaVuorot();
            }
            tyhjaLauta = false;
            
            while (true) {
                
                if (!botinVuoro) {
                    
                    System.out.println("");
                    System.out.print("Sijoitus: ");
                    String vastaus = lukija.nextLine().trim();

                    if (vastaus.equals("o")) {
                        ohjeet();
                        continue;
                    } else if (vastaus.equals("p")) {
                        pelisaannot();
                        continue;
                    } else if (vastaus.equals("x")) {
                        lopetetaan = true;
                        break;
                    }

                    if (vastaus.matches(regex)) {
                        
                        int x = lauta.muutaXKoordinaattiNumeroksi(vastaus.substring(0 , 2).trim());
                        int y = lauta.muutaYKoordinaattiNumeroksi(vastaus.substring(vastaus.length() - 1));
                        
                        if (lauta.sijoita(x, y)) {
                            
                            String tulos = lauta.tarkistaVoitto(x,y);
                            
                            if (!tulos.equals("Jatketaan.")) {
                                
                                lauta.tulostaLauta();
                                System.out.println("");
                                System.out.println(tulos);
                                
                                if (loppuuko()) {
                                    lopetetaan = true;
                                    break;
                                } else {
                                    aloitusKonfiguraatio();
                                    tyhjaLauta = true;
                                }
                                
                            } else {
                                if (bottiIhminen) botinVuoro = true;
                            }
                            
                            break;
                        } else System.out.println("N�iss� koordinaateissa on jo toinen nappula.");

                    } else System.out.println("Koordinaatit eiv�t kelpaa.");
                    
                } else {
                    
                    int[] koordinaatit;
                    if (botin1Vuoro || bottiIhminen) {
                        koordinaatit = botti1.teeSiirto(lauta.getLauta());
                        botin1Vuoro = false;
                    } else {
                        koordinaatit = botti2.teeSiirto(lauta.getLauta());
                        botin1Vuoro = true;
                    }
                    
                    lauta.sijoita(koordinaatit[0], koordinaatit[1]);
                    String tulos = lauta.tarkistaVoitto(koordinaatit[0], koordinaatit[1]);

                    if (!tulos.equals("Jatketaan.")) {

                        lauta.tulostaLauta();
                        System.out.println("");
                        System.out.println(tulos);

                        if (loppuuko()) {
                            lopetetaan = true;
                            break;
                        } else {
                            aloitusKonfiguraatio();
                            tyhjaLauta = true;
                        }

                    } else if (bottiIhminen) botinVuoro = false;
                        
                    break;
                }
            }
            if (lopetetaan) break;
        }
        
        System.out.println("");
        System.out.println("Kiitos pelist�!");
    }
    
    /**
     * Ennen peli� valmistelee pelin sellaiseksi kuin k�ytt�j� haluaa.
     * Kysyy pelilaudan koon, haluaako k�ytt�j� pelata bottia vai ihmist� vastaan ja kuka aloittaa.
     * @param lauta Pelilauta.
     */
    private void aloitusKonfiguraatio() {
        
        System.out.println("Tervetuloa Gomoku-peliin.");
        System.out.println("Mink� kokoisella laudalla haluat pelata?");
        System.out.println("1) 15 x 15      2) 19 x 19");
        System.out.print("Sy�t� vaihtoehdon numero: ");
        
        bottiIhminen = false;
        botinVuoro = false;
        lauta.alustaPeli();
        
        while (true) {
            String vastaus = lukija.nextLine();
            if (vastaus.equals("1")) {
                lauta.setPituus(15);
                regex = "([1-9]|(1[0-5])) ([A-O])";
                break;
            } else if (vastaus.equals("2")) {
                lauta.setPituus(19);
                regex = "([1-9]|(1[0-9])) ([A-S])";
                break;
            } else System.out.println("Sy�t� jonkin yll� n�kyv�n vaihtoehdon numero:");
        }
        
        System.out.println("");
        System.out.println("Ket� vastaan haluat pelata?");
        System.out.println("1) Bottia     2) Ihmist�    3) Anna botin pelata itse��n vastaan");
        System.out.print("Sy�t� vaihtoehdon numero: ");
        
        while (true) {
            
            String vastaus = lukija.nextLine();
            
            if (vastaus.equals("1")) {
                bottiIhminen = true;
                botti1 = new Aly();
                botti1.setPituus(lauta.getPituus());
                
                System.out.println("");
                System.out.println("Kumpi aloittaa?");
                System.out.println("1) Min�    2) Botti");
                System.out.print("Sy�t� vaihtoehdon numero: ");
                
                while (true) {
                    
                    String vastaus2 = lukija.nextLine();
                    
                    if (vastaus2.equals("1")) {
                        botinVuoro = false;
                        botti1.setMerkki('O');
                        break;
                    } else if (vastaus2.equals("2")) {
                        botinVuoro = true;
                        botti1.setMerkki('X');
                        break;
                    } else System.out.println("Sy�t� jonkin yll� n�kyv�n vaihtoehdon numero:");
                }
                
                break;
            } else if (vastaus.equals("2")) {
                break;
            } else if (vastaus.equals("3")) {
                botti1 = new Aly();
                botti2 = new Aly();
                botti1.setPituus(lauta.getPituus());
                botti2.setPituus(lauta.getPituus());
                botti1.setMerkki('X');
                botti2.setMerkki('O');
                botinVuoro = true;
                botin1Vuoro = true;
                break;
            } else System.out.println("Sy�t� jonkin yll� n�kyv�n vaihtoehdon numero:");
        }
    }
    
    /**
     * Yhden pelin loputtua kysyy k�ytt�j�lt� haluaako h�n pelata uudelleen vai lopettaa pelaamisen.
     * @return true, jos ohjelma suljetaan, ja false muuten.
     */
    private boolean loppuuko() {
        
        System.out.println("");
        System.out.println("Haluatko pelata uudelleen?"
                + "\n1) Kyll�   2) Ei");
        
        while (true) {
            System.out.print("Sy�t� vaihtoehdon numero: ");
            String uudelleenko = lukija.nextLine().trim();
            if (uudelleenko.equals("1")) {
                System.out.println("");
                return false;
            } else if (uudelleenko.equals("2")) {
                return true;
            }
        }
    }
    
    /**
     * Tulostaa k�ytt�liittym�n ohjeet.
     */
    private void ohjeet() {
        System.out.println("");
        System.out.println("Koordinaattien sy�tt�minen:");
        System.out.println("Sy�t� vaakarivin numero, v�limerkki ja sitten pystyrivin kirjain.");
        System.out.println("Pelin lopettaminen:");
        System.out.println("Kun ohjelma kysyy koordinaatteja, paina x.");
        
    }
    
    /**
     * Tulostaa lyhyesti Gomoku-pelin s��nn�t.
     */
    private void pelisaannot() {
        System.out.println("");
        System.out.println("Gomoku:");
        System.out.println("Pelin tarkoituksena on saada viisi omaa nappulaa per�kk�in joko vaaka-, pystyriviin tai vinoittain."
                + "\nPeli� pelaa kaksi pelaajaa vuorotellen, toinen mustilla (X) ja toinen valkoisilla (O) nappuloilla."
                + "\nEnsimm�inen pelaaja, joka saa viisi omaa nappulaansa per�kk�in, voittaa pelin."
                + "\nMusta aloittaa.");
    }
    
}
