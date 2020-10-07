package tehokkuustestit;

import apu.Hakemisto;
import apu.Lista;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import logiikka.Aly;
import logiikka.Siirto;
import org.junit.Test;

public class Tehokkuustestit {
    
    private void tulostaLauta(int pituus, char[][] lauta) {
        for(int i = 0; i < pituus; i++){
            for(int j = 0; j < pituus; j++){
                System.out.print(lauta[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    @Test
    public void testiraportti() {
        System.out.println("TEHOKKUUSTESTIT"
                + "Suoritetaan tekoälyn ja tietorakenteiden tehokkuustestit.");
        System.out.println("");
        bottiTestit();
        listaTestit();
        hakemistoHajautustesti();
    }
    
    //BOTIN TEHOKKUUSTESTIT
    
    private void bottiTestit() {
        System.out.println("BOTIN TESTIT");
        System.out.println("");
        System.out.println("Odota hetki, botti pelaa yhden esimerkkipelin...");
        System.out.println("Keskimaarainen siirtoaika:");
        double[] tulokset = esimerkkiPeli();
        System.out.println(tulokset[0]);
        System.out.println("Pisin odotusaika:");
        System.out.println(tulokset[1]);
        System.out.println("");
    }
    
    private void teeSiirto(Aly botti, char[][] lauta, int[] koordinaatit, ArrayList<Double> tulokset) {
        long alku = System.nanoTime();
        koordinaatit = botti.teeSiirto(lauta);
        long loppu = System.nanoTime();
        tulokset.add((loppu - alku)/1e9);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
    }
    
    private double[] esimerkkiPeli() {
        ArrayList<Double> tulokset = new ArrayList<>();
        int[] koordinaatit = new int[2];
        Aly botti = new Aly();
        int pituus = 15;
        botti.setPituus(pituus);
        botti.setMerkki(1);
        char[][] lauta = new char[15][15];
        for(int i = 0; i < pituus; i++){
            for(int j = 0; j < pituus; j++){
                lauta[i][j] = '+';
            }
        }
        teeSiirto(botti, lauta, koordinaatit, tulokset);
        lauta[7][8] = 'O';
        teeSiirto(botti, lauta, koordinaatit, tulokset);
        lauta[8][8] = 'O';
        teeSiirto(botti, lauta, koordinaatit, tulokset);
        lauta[8][7] = 'O';
        teeSiirto(botti, lauta, koordinaatit, tulokset);
        lauta[9][5] = 'O';
        teeSiirto(botti, lauta, koordinaatit, tulokset);
        lauta[6][9] = 'O';
        teeSiirto(botti, lauta, koordinaatit, tulokset);
        lauta[7][6] = 'O';
        teeSiirto(botti, lauta, koordinaatit, tulokset);
        lauta[6][4] = 'O';
        teeSiirto(botti, lauta, koordinaatit, tulokset);
        lauta[4][7] = 'O';
        teeSiirto(botti, lauta, koordinaatit, tulokset);
        lauta[4][8] = 'O';
        teeSiirto(botti, lauta, koordinaatit, tulokset);
        lauta[4][5] = 'O';
        teeSiirto(botti, lauta, koordinaatit, tulokset);
        lauta[5][8] = 'O';
        teeSiirto(botti, lauta, koordinaatit, tulokset);
        lauta[5][6] = 'O';
        teeSiirto(botti, lauta, koordinaatit, tulokset);
        double tulos = 0;
        double max = 0;
        for(Double d: tulokset){
            max = Math.max(max, d);
            tulos += d;
        }
        tulos /= 12;
        double[] palautus = new double[2];
        palautus[0] = tulos;
        palautus[1] = max;
        return palautus;
    }
    
    //LISTAN TEHOKKUUSTESTIT
    
    private void listaTestit() {
        System.out.println("LISTAN TESTIT");
        System.out.println("");
        System.out.println("LISTAAN LISAAMINEN:");
        listaLisaaTesti(10000);
        listaLisaaTesti(100000);
        listaLisaaTesti(1000000);
        System.out.println("");
        System.out.println("LISTAN JARJESTAMINEN");
        listaJarjestaTesti(10000);
        listaJarjestaTesti(100000);
        listaJarjestaTesti(1000000);
    }
    
    private void listaLisaaTesti(int n) {
        double listaka = 0;
        double javalistaka = 0;
        for(int i = 0; i < 100; i++){
            double[] tulokset = listaLisaaVertailu(n);
            listaka += tulokset[0];
            javalistaka += tulokset[1];
        }
        System.out.println("n: " + n);
        System.out.println("Listaan lisaaminen (keskiarvo 100:sta testikerrasta):");
        System.out.println("ArrayList: " + (javalistaka/100));
        System.out.println("Lista (oma ketale): " + (listaka/100));
        System.out.println("");
    }
    
    private void listaJarjestaTesti(int n) {
        double listaka = 0;
        double javalistaka = 0;
        for(int i = 0; i < 10; i++){
            double[] tulokset = listaJarjestaVertailu(n);
            listaka += tulokset[0];
            javalistaka += tulokset[1];
        }
        System.out.println("n: " + n);
        System.out.println("Listan jarjestaminen (keskiarvo 10:sta testikerrasta):");
        System.out.println("ArrayList: " + (javalistaka/10));
        System.out.println("Lista (oma ketale): " + (listaka/10));
        System.out.println("");
    }
    
    private double[] listaLisaaVertailu(int n) {
        Lista<Siirto> lista = new Lista();
        long alku = System.nanoTime();
        for(int i = 0; i < n; i++){
            lista.lisaa(new Siirto(i,i,i));
        }
        long loppu = System.nanoTime();
        double aikaLista = ((loppu - alku)/1e9);
        
        ArrayList<Siirto> javalista = new ArrayList<>();
        alku = System.nanoTime();
        for(int i = 0; i < n; i++){
            javalista.add(new Siirto(i,i,i));
        }
        loppu = System.nanoTime();
        double aikaJavalista = ((loppu - alku)/1e9);
        
        double[] tulokset = new double[2];
        tulokset[0] = aikaLista;
        tulokset[1] = aikaJavalista;
        return tulokset;
    }
    
    private double[] listaJarjestaVertailu(int n) {
        Random r = new Random();
        r.setSeed(1337);
        
        Lista<Siirto> lista = new Lista();
        for(int i = 0; i < n; i++){
            int arvo = r.nextInt(n + 1);
            int etumerkki = r.nextInt(2);
            if(etumerkki == 0) arvo = -arvo;
            lista.lisaa(new Siirto(arvo,i,i));
        }
        
        long alku = System.nanoTime();
        lista.jarjesta();
        long loppu = System.nanoTime();
        double aikaLista = ((loppu - alku)/1e9);
        
        ArrayList<Siirto> javalista = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int arvo = r.nextInt(n + 1);
            int etumerkki = r.nextInt(2);
            if(etumerkki == 0) arvo = -arvo;
            javalista.add(new Siirto(arvo,i,i));
        }
        
        alku = System.nanoTime();
        Collections.sort(javalista);
        loppu = System.nanoTime();
        double aikaJavalista = ((loppu - alku)/1e9);
        
        double[] tulokset = new double[2];
        tulokset[0] = aikaLista;
        tulokset[1] = aikaJavalista;
        return tulokset;
    }
    
    //HAKEMISTON TEHOKKUUSTESTIT
    
    private void hakemistoHajautustesti() {
        System.out.println("HAKEMISTON TESTIT");
        System.out.println("");
        System.out.println("Hajautuksen tehokkuus:");
        System.out.println("Pisimman listan pituus / hakemistossa olevat alkiot.");
        System.out.println(hajautusArvio(1000) + " / 1000");
        System.out.println(hajautusArvio(10000) + " / 10000");
        System.out.println(hajautusArvio(100000) + " / 100000");
        System.out.println(hajautusArvio(300000) + " / 300000");
        System.out.println(hajautusArvio(500000) + " / 500000");
    }
    
    private int hajautusArvio(int n) {
        Random r = new Random();
        Hakemisto h = new Hakemisto();
        for(int i = 0; i < n; i++){
            StringBuilder s = new StringBuilder();
            for(int j = 0; j < 361; j++){
                char c = '+';
                int arvo = r.nextInt(4);
                if(arvo == 2) c = 'O';
                if(arvo == 3) c = 'X';
                s.append(c);
            }
            h.lisaa(s.toString(), i);
        }
        return h.pahinTormays();
    }
}
