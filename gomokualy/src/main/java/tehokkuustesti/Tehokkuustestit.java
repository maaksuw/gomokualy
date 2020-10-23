package tehokkuustesti;

import apu.Hakemisto;
import apu.Lista;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import logiikka.Aly;
import logiikka.Lauta;
import logiikka.Siirto;

public class Tehokkuustestit {
    
    public void testiraportti() {
        System.out.println("TEHOKKUUSTESTIT"
                + "\nSuoritetaan tietorakenteiden tehokkuustestit.");
        System.out.println("");
        listaTestit();
        hakemistoHajautustesti();
        bottiTestit();
    }
    
    //BOTIN TEHOKKUUSTESTIT
    
    private void bottiTestit() {
        System.out.println("");
        System.out.println("BOTIN TESTIT");
        System.out.println("");
        System.out.println("Odota hetki (noin 30 sekuntia), botti pelaa esimerkkipelin itseaan vastaan...");
        System.out.println("Keskimaarainen siirtoaika:");
        double[] tulokset = esimerkkiPeli();
        System.out.println(tulokset[0]);
        System.out.println("Pisin odotusaika:");
        System.out.println(tulokset[1]);
        System.out.println("");
    }
     
    private boolean teeSiirto(Aly botti, Lauta lauta, ArrayList<Double> tulokset) {
        long alku = System.nanoTime();
        int[] koordinaatit = botti.teeSiirto(lauta.getLauta());
        long loppu = System.nanoTime();
        tulokset.add((loppu - alku) / 1e9);
        return lauta.sijoita(koordinaatit[0], koordinaatit[1]);
    }
    
    private double[] esimerkkiPeli() {
        Aly botti1 = new Aly();
        botti1.setMerkki('X');
        ArrayList<Double> tulokset = new ArrayList<>();
        Aly botti2 = new Aly();
        botti2.setMerkki('O');
        Lauta lauta = new Lauta();
        int siirtoja = 0;
        while (true) {
            siirtoja++;
            if (teeSiirto(botti1, lauta, tulokset)) break;
            siirtoja++;
            if (teeSiirto(botti2, lauta, tulokset)) break;
        }
        double ka = 0;
        double max = 0;
        for (Double d: tulokset) {
            max = Math.max(max, d);
            ka += d;
        }
        ka /= siirtoja;
        double[] palautus = new double[2];
        palautus[0] = ka;
        palautus[1] = max;
        return palautus;
    }
    
    //LISTAN TEHOKKUUSTESTIT
    
    private void listaTestit() {
        System.out.println("LISTAN TESTIT");
        System.out.println("");
        System.out.println("LISTAAN LISAAMINEN:");
        listaLisaaTesti(10000, 100);
        listaLisaaTesti(100000, 100);
        listaLisaaTesti(1000000, 100);
        listaLisaaTesti(10000000, 1);
        System.out.println("");
        System.out.println("LISTAN JARJESTAMINEN");
        listaJarjestaTesti(10000, 10);
        listaJarjestaTesti(100000, 10);
        listaJarjestaTesti(1000000, 10);
        listaJarjestaTesti(10000000, 1);
        System.out.println("");
        System.out.println("LISTAN KAANTAMINEN");
        listaKaannaTesti(100000, 10);
        listaKaannaTesti(1000000, 10);
        listaKaannaTesti(10000000, 1);
    }
    
    private void listaLisaaTesti(int n, int kertoja) {
        double listaka = 0;
        double javalistaka = 0;
        for (int i = 0; i < kertoja; i++) {
            double[] tulokset = listaLisaaVertailu(n);
            listaka += tulokset[0];
            javalistaka += tulokset[1];
        }
        System.out.println("n: " + n);
        System.out.println("Listaan lisaaminen (keskiarvo " + kertoja + ":sta testikerrasta):");
        System.out.println("ArrayList: " + (javalistaka / kertoja));
        System.out.println("Lista: " + (listaka / kertoja));
        System.out.println("");
    }
    
    private void listaJarjestaTesti(int n, int kertoja) {
        double listaka = 0;
        double javalistaka = 0;
        for (int i = 0; i < kertoja; i++) {
            double[] tulokset = listaJarjestaVertailu(n);
            listaka += tulokset[0];
            javalistaka += tulokset[1];
        }
        System.out.println("n: " + n);
        System.out.println("Listan jarjestaminen (keskiarvo " + kertoja + ":sta testikerrasta):");
        System.out.println("ArrayList: " + (javalistaka / kertoja));
        System.out.println("Lista: " + (listaka / kertoja));
        System.out.println("");
    }
    
    private void listaKaannaTesti(int n, int kertoja) {
        double listaka = 0;
        double javalistaka = 0;
        for (int i = 0; i < kertoja; i++) {
            double[] tulokset = listaKaannaVertailu(n);
            listaka += tulokset[0];
            javalistaka += tulokset[1];
        }
        System.out.println("n: " + n);
        System.out.println("Listan kaantaminen (keskiarvo " + kertoja + ":sta testikerrasta):");
        System.out.println("ArrayList: " + (javalistaka / (kertoja)));
        System.out.println("Lista: " + (listaka / (kertoja)));
        System.out.println("");
    }
    
    private double[] listaLisaaVertailu(int n) {
        Lista<Siirto> lista = new Lista();
        long alku = System.nanoTime();
        for (int i = 0; i < n; i++) {
            lista.lisaa(new Siirto(i, i, i));
        }
        long loppu = System.nanoTime();
        double aikaLista = ((loppu - alku) / 1e9);
        
        ArrayList<Siirto> javalista = new ArrayList<>();
        alku = System.nanoTime();
        for (int i = 0; i < n; i++) {
            javalista.add(new Siirto(i, i, i));
        }
        loppu = System.nanoTime();
        double aikaJavalista = ((loppu - alku) / 1e9);
        
        double[] tulokset = new double[2];
        tulokset[0] = aikaLista;
        tulokset[1] = aikaJavalista;
        return tulokset;
    }
    
    private double[] listaJarjestaVertailu(int n) {
        Random r = new Random();
        r.setSeed(1337);
        
        Lista<Siirto> lista = new Lista();
        for (int i = 0; i < n; i++) {
            int arvo = r.nextInt(n + 1);
            int etumerkki = r.nextInt(2);
            if (etumerkki == 0) arvo = -arvo;
            lista.lisaa(new Siirto(arvo, i, i));
        }
        
        long alku = System.nanoTime();
        lista.jarjesta();
        long loppu = System.nanoTime();
        double aikaLista = ((loppu - alku) / 1e9);
        
        ArrayList<Siirto> javalista = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int arvo = r.nextInt(n + 1);
            int etumerkki = r.nextInt(2);
            if (etumerkki == 0) arvo = -arvo;
            javalista.add(new Siirto(arvo, i, i));
        }
        
        alku = System.nanoTime();
        Collections.sort(javalista);
        loppu = System.nanoTime();
        double aikaJavalista = ((loppu - alku) / 1e9);
        
        double[] tulokset = new double[2];
        tulokset[0] = aikaLista;
        tulokset[1] = aikaJavalista;
        return tulokset;
    }
    
    private double[] listaKaannaVertailu(int n) {
        Lista<Siirto> lista = new Lista();
        for (int i = 0; i < n; i++) {
            lista.lisaa(new Siirto(i, i, i));
        }
        long alku = System.nanoTime();
        lista.kaanna();
        long loppu = System.nanoTime();
        double aikaLista = ((loppu - alku) / 1e9);
        
        ArrayList<Siirto> javalista = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            javalista.add(new Siirto(i, i, i));
        }
        alku = System.nanoTime();
        Collections.reverse(javalista);
        loppu = System.nanoTime();
        double aikaJavalista = ((loppu - alku) / 1e9);
        
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
        for (int i = 0; i < n; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < 225; j++) {
                char c = '+';
                int arvo = r.nextInt(4);
                if (arvo == 2) c = 'O';
                if (arvo == 3) c = 'X';
                s.append(c);
            }
            h.lisaa(s.toString(), i);
        }
        return h.pahinTormays();
    }
}
