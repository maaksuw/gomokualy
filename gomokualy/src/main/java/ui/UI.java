
package ui;

import java.util.Scanner;
import logiikka.Aly;
import logiikka.Pelilauta;

/**
 * Pelin tekstikäyttöliittymä.
 */

public class UI {
    
    private final Scanner lukija;
    private Aly botti;
    
    /**
     * Kertoo pelaako pelaaja peliä bottia vai ihmistä vastaan.
     */
    private boolean bottiPelaa;
    
    /**
     * Kertoo onko vuorossa botti vai ihmispelaaja.
     */
    private String vuorossa;
    
    /**
     * Käytetään tarkistaessa ovatko syötetyt koordinaatit oikeaa muotoa.
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
                        } else System.out.println("Näissä koordinaateissa on jo toinen nappula.");

                    } else System.out.println("Koordinaatit eivät kelpaa.");
                    
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
        System.out.println("Kiitos pelistä!");
    }
    
    /**
     * Ennen peliä valmistelee pelin sellaiseksi kuin käyttäjä haluaa.
     * Kysyy pelilaudan koon, haluaako käyttäjä pelata bottia vai ihmistä vastaan ja kuka aloittaa.
     * @param p Pelilauta.
     */
    private void aloitusKonfiguraatio(Pelilauta p) {
        System.out.println("Tervetuloa Gomoku-peliin.");
        System.out.println("Minkä kokoisella laudalla haluat pelata?");
        System.out.println("1) 19 x 19      2) 15 x 15");
        System.out.print("Syötä vaihtoehdon numero: ");
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
            } else System.out.println("Syötä jonkin yllä näkyvän vaihtoehdon numero:");
        }
        System.out.println("");
        System.out.println("Ketä vastaan haluat pelata?");
        System.out.println("1) Bottia     2) Ihmistä");
        System.out.print("Syötä vaihtoehdon numero: ");
        while (true) {
            String vastaus = lukija.nextLine();
            if (vastaus.equals("1")) {
                bottiPelaa = true;
                botti = new Aly();
                botti.setPituus(pituus);
                System.out.println("");
                System.out.println("Kumpi aloittaa?");
                System.out.println("1) Minä    2) Botti");
                System.out.print("Syötä vaihtoehdon numero: ");
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
                    } else System.out.println("Syötä jonkin yllä näkyvän vaihtoehdon numero:");
                }
                break;
            } else if (vastaus.equals("2")) {
                bottiPelaa = false;
                break;
            } else System.out.println("Syötä jonkin yllä näkyvän vaihtoehdon numero:");
        }
    }
    
    /**
     * Yhden pelin loputtua kysyy käyttäjältä haluaako hän pelata uudelleen vai lopettaa pelaamisen.
     * @return true, jos ohjelma suljetaan, ja false muuten.
     */
    private boolean loppuuko() {
        System.out.println("");
        System.out.println("Haluatko pelata uudelleen?"
                + "\n1) Kyllä   2) Ei");
        while (true) {
            System.out.print("Syötä vaihtoehdon numero: ");
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
     * Tulostaa käyttöliittymän ohjeet.
     */
    private void ohjeet() {
        System.out.println("Koordinaattien syöttäminen:");
        System.out.println("Syötä vaakarivin numero, välimerkki ja sitten pystyrivin kirjain.");
        System.out.println("Pelin lopettaminen:");
        System.out.println("Kun ohjelma kysyy koordinaatteja, paina x.");
        
    }
    
    /**
     * Tulostaa lyhyesti Gomoku-pelin säännöt.
     */
    private void pelisaannot() {
        System.out.println("Gomoku:");
        System.out.println("Pelin tarkoituksena on saada viisi omaa nappulaa peräkkäin joko vaaka-, pystyriviin tai vinoittain."
                + "\nPeliä pelaa kaksi pelaajaa vuorotellen, toinen mustilla (X) ja toinen valkoisilla (O) nappuloilla."
                + "\nEnsimmäinen pelaaja, joka saa viisi omaa nappulaansa peräkkäin, voittaa pelin."
                + "\nMusta aloittaa.");
    }
    
}
