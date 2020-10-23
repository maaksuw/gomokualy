package testit;

import apu.Lista;
import java.util.Random;
import logiikka.Siirto;
import logiikka.Tilanne;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestLista {
    
    private Lista<Siirto> lsiirto;
    private Lista<Tilanne> ltilanne;
    private Random r;
    
    public TestLista() {
        r = new Random();
        lsiirto = new Lista();
        ltilanne = new Lista();
    }
    
    //SIIRTO-TESTIT
    
    @Test
    public void lisaaPieniTestiSiirto() {
        lsiirto.lisaa(new Siirto(1, 2, 3));
        lsiirto.lisaa(new Siirto(2, 4, 4));
        lsiirto.lisaa(new Siirto(3, 13, 0));
        assertEquals(3, lsiirto.pituus());
    }
    
    @Test
    public void lisaaSuuriTestiSiirto() {
        int n = 100000;
        for (int i = 1; i <= n; i++) {
            lsiirto.lisaa(new Siirto(r.nextInt(n + 1), i, i));
        }
        assertEquals(n, lsiirto.pituus());
    }
    
    @Test
    public void lisatytAlkiotLoytyvatListaltaSiirto() {
        Siirto s1 = new Siirto(1, 2, 3);
        lsiirto.lisaa(s1);
        Siirto s2 = new Siirto(2, 4, 4);
        lsiirto.lisaa(s2);
        Siirto s3 = new Siirto(3, 13, 0);
        lsiirto.lisaa(s3);
        assertTrue(lsiirto.hae(0).equals(s1));
        assertTrue(lsiirto.hae(1).equals(s2));
        assertTrue(lsiirto.hae(2).equals(s3));
    }
    
    @Test
    public void jarjestaPieniToimiiSiirto() {
        int n = 15;
        for (int i = 1; i <= n; i++) {
            int arvo = r.nextInt(n + 1);
            int etumerkki = r.nextInt(2);
            if (etumerkki == 0) arvo = -arvo;
            lsiirto.lisaa(new Siirto(arvo, i, i));
        }
        lsiirto.jarjesta();
        boolean jarjestyksessa = true;
        for (int i = 1; i < n; i++) {
            Siirto nykyinen = lsiirto.hae(i);
            Siirto edellinen = lsiirto.hae(i - 1);
            if (nykyinen.getArvo() < edellinen.getArvo()) {
                jarjestyksessa = false;
                break;
            }
        }
        assertTrue(jarjestyksessa);
    }
    
    @Test
    public void jarjestaIsoToimiiSiirto() {
        int n = 100000;
        for (int i = 1; i <= n; i++) {
            int arvo = r.nextInt(n + 1);
            int etumerkki = r.nextInt(2);
            if (etumerkki == 0) arvo = -arvo;
            lsiirto.lisaa(new Siirto(arvo, i, i));
        }
        lsiirto.jarjesta();
        boolean jarjestyksessa = true;
        for (int i = 1; i < n; i++) {
            Siirto nykyinen = lsiirto.hae(i);
            Siirto edellinen = lsiirto.hae(i - 1);
            if (nykyinen.getArvo() < edellinen.getArvo()) {
                jarjestyksessa = false;
                break;
            }
        }
        assertTrue(jarjestyksessa);
    }
    
    @Test
    public void kaannaToimiiSiirto() {
        int n = 100;
        for (int i = 1; i <= n; i++) {
            int arvo = r.nextInt(n + 1);
            int etumerkki = r.nextInt(2);
            if (etumerkki == 0) arvo = -arvo;
            lsiirto.lisaa(new Siirto(arvo, i, i));
        }
        lsiirto.jarjesta();
        lsiirto.kaanna();
        boolean laskeva = true;
        for (int i = 1; i < n; i++) {
            Siirto nykyinen = lsiirto.hae(i);
            Siirto edellinen = lsiirto.hae(i - 1);
            if (nykyinen.getArvo() > edellinen.getArvo()) {
                laskeva = false;
                break;
            }
        }
        assertTrue(laskeva);
    }
    
    //TILANNE-TESTIT
    
    @Test
    public void lisaaPieniTestiTilanne() {
        ltilanne.lisaa(new Tilanne("pupu", 30));
        ltilanne.lisaa(new Tilanne("misse", 31));
        ltilanne.lisaa(new Tilanne("makkis", 13));
        assertEquals(3, ltilanne.pituus());
    }
    
    @Test
    public void lisaaSuuriTestiTilanne() {
        int n = 100000;
        for (int i = 1; i <= n; i++) {
            ltilanne.lisaa(new Tilanne("pupu", r.nextInt(n + 1)));
        }
        assertEquals(n, ltilanne.pituus());
    }

    @Test
    public void lisatytAlkiotLoytyvatListaltaTilanne() {
        Tilanne t1 = new Tilanne("pupu", 3000);
        ltilanne.lisaa(t1);
        Tilanne t2 = new Tilanne("misse", 12);
        ltilanne.lisaa(t2);
        Tilanne t3 = new Tilanne("makkis", 600);
        ltilanne.lisaa(t3);
        assertTrue(ltilanne.hae(0).equals(t1));
        assertTrue(ltilanne.hae(1).equals(t2));
        assertTrue(ltilanne.hae(2).equals(t3));
    }
    
    @Test
    public void jarjestaPieniToimiiTilanne() {
        int n = 15;
        for (int i = 1; i <= n; i++) {
            int arvo = r.nextInt(n + 1);
            int etumerkki = r.nextInt(2);
            if (etumerkki == 0) arvo = -arvo;
            ltilanne.lisaa(new Tilanne("pupu", arvo));
        }
        ltilanne.jarjesta();
        boolean jarjestyksessa = true;
        for (int i = 1; i < n; i++) {
            Tilanne nykyinen = ltilanne.hae(i);
            Tilanne edellinen = ltilanne.hae(i - 1);
            if (nykyinen.getArvo() < edellinen.getArvo()) {
                jarjestyksessa = false;
                break;
            }
        }
        assertTrue(jarjestyksessa);
    }
    
    @Test
    public void jarjestaIsoToimiiTilanne() {
        int n = 100000;
        for (int i = 1; i <= n; i++) {
            int arvo = r.nextInt(n + 1);
            int etumerkki = r.nextInt(2);
            if (etumerkki == 0) arvo = -arvo;
            ltilanne.lisaa(new Tilanne("pupu", arvo));
        }
        ltilanne.jarjesta();
        boolean jarjestyksessa = true;
        for (int i = 1; i < n; i++) {
            Tilanne nykyinen = ltilanne.hae(i);
            Tilanne edellinen = ltilanne.hae(i - 1);
            if (nykyinen.getArvo() < edellinen.getArvo()) {
                jarjestyksessa = false;
                break;
            }
        }
        assertTrue(jarjestyksessa);
    }
    
    @Test
    public void kaannaToimiiTilanne() {
        int n = 100;
        for (int i = 1; i <= n; i++) {
            int arvo = r.nextInt(n + 1);
            int etumerkki = r.nextInt(2);
            if (etumerkki == 0) arvo = -arvo;
            ltilanne.lisaa(new Tilanne("pupu", arvo));
        }
        ltilanne.jarjesta();
        ltilanne.kaanna();
        boolean laskeva = true;
        for (int i = 1; i < n; i++) {
            Tilanne nykyinen = ltilanne.hae(i);
            Tilanne edellinen = ltilanne.hae(i - 1);
            if (nykyinen.getArvo() > edellinen.getArvo()) {
                laskeva = false;
                break;
            }
        }
        assertTrue(laskeva);
    }
    
    //MUUT TESTIT
    
    @Test
    public void haePalauttaaNull() {
        assertTrue(lsiirto.hae(-1) == null);
        assertTrue(lsiirto.hae(0) == null);
        lsiirto.lisaa(new Siirto(1, 11, 111));
        lsiirto.lisaa(new Siirto(2, 22, 222));
        assertTrue(lsiirto.hae(2) == null);
    }
}
