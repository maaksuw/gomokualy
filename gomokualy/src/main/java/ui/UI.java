
package ui;

import java.util.Scanner;
import logiikka.Aly;
import logiikka.Pelilauta;

/**
 * Pelin tekstik�ytt�liittym�.
 */

public class UI {
    
    private final Scanner lukija;
    private Aly botti;
    
    /**
     * Kertoo pelaako pelaaja peli� bottia vai ihmist� vastaan.
     */
    private boolean bottiPelaa;
    
    /**
     * Kertoo onko vuorossa botti vai ihmispelaaja.
     */
    private String vuorossa;
    
    /**
     * K�ytet��n tarkistaessa ovatko sy�tetyt koordinaatit oikeaa muotoa.
     */
    private String regex;
    
    public UI(Scanner s) {
        lukija = s;
        regex = "([1-9]|(1[0-9])) ([A-S])";
        bottiPelaa = true;
        vuorossa = "pelaaja";
    }
    
    /**
     * Pelisilmukka.
     */
    public void kaynnista() {
        
        Pelilauta p = new Pelilauta();
        aloitusKonfiguraatio(p);
        boolean lopetetaan = false;
        boolean tyhjaLauta = true;
        
        while (true) {
            
            if(tyhjaLauta && vuorossa.equals("botti")){
            } else {
                p.tulostaLauta();
                p.tulostaVuorot();
                tyhjaLauta = false;
            }
            
            while (true) {
                
                if (vuorossa.equals("pelaaja")) {
                    System.out.println("");
                    System.out.print("Sijoitus: ");
                    String vastaus = lukija.nextLine().trim();

                    if (vastaus.equals("o")) {
                        System.out.println("");
                        ohjeet();
                        continue;
                    } else if (vastaus.equals("p")) {
                        System.out.println("");
                        pelisaannot();
                        continue;
                    } else if (vastaus.equals("x")) {
                        lopetetaan = true;
                        break;
                    }

                    if (vastaus.matches(regex)) {
                        int x = p.muutaXKoordinaattiNumeroksi(vastaus.substring(0 , 2).trim());
                        int y = p.muutaYKoordinaattiNumeroksi(vastaus.substring(vastaus.length() - 1));
                        if (p.sijoita(x, y)) {
                            String tulos = p.tarkistaVoitto(x, y);
                            if (!tulos.equals("Jatketaan.")) {
                                p.tulostaLauta();
                                System.out.println("");
                                System.out.println(tulos);
                                if (loppuuko()) {
                                    lopetetaan = true;
                                    break;
                                } else {
                                    p.alustaPeli();
                                    aloitusKonfiguraatio(p);
                                    tyhjaLauta = true;
                                }
                            } else {
                                if (bottiPelaa) vuorossa = "botti";
                            }
                            break;
                        } else System.out.println("N�iss� koordinaateissa on jo toinen nappula.");

                    } else System.out.println("Koordinaatit eiv�t kelpaa.");
                    
                } else {
                    int[] koordinaatit = botti.teeSiirto(p.getLauta());
                    p.sijoita(koordinaatit[0], koordinaatit[1]);
                    String tulos = p.tarkistaVoitto(koordinaatit[0], koordinaatit[1]);
                    if (!tulos.equals("Jatketaan.")) {
                        p.tulostaLauta();
                        System.out.println("");
                        System.out.println(tulos);
                        if (loppuuko()) {
                            lopetetaan = true;
                            break;
                        } else {
                            p.alustaPeli();
                            aloitusKonfiguraatio(p);
                            tyhjaLauta = true;
                        }
                    } else vuorossa = "pelaaja";
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
     * @param p Pelilauta.
     */
    private void aloitusKonfiguraatio(Pelilauta p) {
        System.out.println("Tervetuloa Gomoku-peliin.");
        System.out.println("Mink� kokoisella laudalla haluat pelata?");
        System.out.println("1) 19 x 19      2) 15 x 15");
        System.out.print("Sy�t� vaihtoehdon numero: ");
        int pituus = 0;
        while (true) {
            String vastaus = lukija.nextLine();
            if (vastaus.equals("2")) {
                p.setPituus(15);
                pituus = 15;
                regex = "([1-9]|(1[0-5])) ([A-O])";
                break;
            } else if (vastaus.equals("1")) {
                p.setPituus(19);
                pituus = 19;
                regex = "([1-9]|(1[0-9])) ([A-S])";
                break;
            } else System.out.println("Sy�t� jonkin yll� n�kyv�n vaihtoehdon numero:");
        }
        System.out.println("");
        System.out.println("Ket� vastaan haluat pelata?");
        System.out.println("1) Bottia     2) Ihmist�");
        System.out.print("Sy�t� vaihtoehdon numero: ");
        while (true) {
            String vastaus = lukija.nextLine();
            if (vastaus.equals("1")) {
                bottiPelaa = true;
                botti = new Aly();
                botti.setPituus(pituus);
                System.out.println("");
                System.out.println("Kumpi aloittaa?");
                System.out.println("1) Min�    2) Botti");
                System.out.print("Sy�t� vaihtoehdon numero: ");
                while (true) {
                    String vastaus2 = lukija.nextLine();
                    if (vastaus2.equals("1")) {
                        vuorossa = "pelaaja";
                        botti.setMerkki(0);
                        break;
                    } else if (vastaus2.equals("2")) {
                        vuorossa = "botti";
                        botti.setMerkki(1);
                        break;
                    } else System.out.println("Sy�t� jonkin yll� n�kyv�n vaihtoehdon numero:");
                }
                break;
            } else if (vastaus.equals("2")) {
                bottiPelaa = false;
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
        System.out.println("Koordinaattien sy�tt�minen:");
        System.out.println("Sy�t� vaakarivin numero, v�limerkki ja sitten pystyrivin kirjain.");
        System.out.println("Pelin lopettaminen:");
        System.out.println("Kun ohjelma kysyy koordinaatteja, paina x.");
        
    }
    
    /**
     * Tulostaa lyhyesti Gomoku-pelin s��nn�t.
     */
    private void pelisaannot() {
        System.out.println("Gomoku:");
        System.out.println("Pelin tarkoituksena on saada viisi omaa nappulaa per�kk�in joko vaaka-, pystyriviin tai vinoittain."
                + "\nPeli� pelaa kaksi pelaajaa vuorotellen, toinen mustilla (X) ja toinen valkoisilla (O) nappuloilla."
                + "\nEnsimm�inen pelaaja, joka saa viisi omaa nappulaansa per�kk�in, voittaa pelin."
                + "\nMusta aloittaa.");
    }
    
}
