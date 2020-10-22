package testit;

import logiikka.Lauta;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestLauta {
    
    private Lauta lauta;
    
    public TestLauta() {
        lauta = new Lauta();
    }
    
    //VAAKASUORATARKISTUS

    @Test
    public void vaakaToimiiRistiVoittaa() {
        for (int i = 0; i <= 3; i++) {
            assertFalse(lauta.sijoita(0, i));
            assertFalse(lauta.sijoita(1, i));
        }
        assertTrue(lauta.sijoita(0, 4));
    }
    
    @Test
    public void vaakaToimiiNollaVoittaa() {
        assertFalse(lauta.sijoita(14, 14));
        for (int i = 0; i <= 3; i++) {
            assertFalse(lauta.sijoita(0, i));
            assertFalse(lauta.sijoita(1, i));
        }
        assertTrue(lauta.sijoita(0, 4));
    }
    
    @Test
    public void vaakaToimiiRistiVoittaaSatunnainenSijoitusjarjestys() {
        assertFalse(lauta.sijoita(7, 7));
        assertFalse(lauta.sijoita(8, 7));
        assertFalse(lauta.sijoita(7, 8));
        assertFalse(lauta.sijoita(8, 8));
        assertFalse(lauta.sijoita(7, 6));
        assertFalse(lauta.sijoita(8, 9));
        assertFalse(lauta.sijoita(7, 4));
        assertFalse(lauta.sijoita(8, 6));
        assertTrue(lauta.sijoita(7, 5));
    }
    
    @Test
    public void vaakaToimiiNollaVoittaaSatunnainenSijoitusjarjestys() {
        assertFalse(lauta.sijoita(1, 1));
        assertFalse(lauta.sijoita(7, 7));
        assertFalse(lauta.sijoita(8, 7));
        assertFalse(lauta.sijoita(7, 8));
        assertFalse(lauta.sijoita(8, 8));
        assertFalse(lauta.sijoita(7, 6));
        assertFalse(lauta.sijoita(8, 9));
        assertFalse(lauta.sijoita(7, 4));
        assertFalse(lauta.sijoita(8, 6));
        assertTrue(lauta.sijoita(7, 5));
    }
    
    //PYSTYSUORATARKISTUS
    
    @Test
    public void pystyToimiiRistiVoittaa() {
        for (int i = 0; i <= 3; i++) {
            assertFalse(lauta.sijoita(i, 0));
            assertFalse(lauta.sijoita(i, 1));
        }
        assertTrue(lauta.sijoita(4, 0));
    }
    
    @Test
    public void pystyToimiiNollaVoittaa() {
        assertFalse(lauta.sijoita(14, 14));
        for (int i = 0; i <= 3; i++) {
            assertFalse(lauta.sijoita(i, 0));
            assertFalse(lauta.sijoita(i, 1));
        }
        assertTrue(lauta.sijoita(4, 0));
    }
    
    @Test
    public void pystyToimiiRistiVoittaaSatunnainenSijoitusjarjestys() {
        assertFalse(lauta.sijoita(7, 7));
        assertFalse(lauta.sijoita(7, 8));
        assertFalse(lauta.sijoita(8, 7));
        assertFalse(lauta.sijoita(8, 8));
        assertFalse(lauta.sijoita(6, 7));
        assertFalse(lauta.sijoita(9, 8));
        assertFalse(lauta.sijoita(4, 7));
        assertFalse(lauta.sijoita(6, 8));
        assertTrue(lauta.sijoita(5, 7));
    }
    
    @Test
    public void pystyToimiiNollaVoittaaSatunnainenSijoitusjarjestys() {
        assertFalse(lauta.sijoita(1, 1));
        assertFalse(lauta.sijoita(7, 7));
        assertFalse(lauta.sijoita(7, 8));
        assertFalse(lauta.sijoita(8, 7));
        assertFalse(lauta.sijoita(8, 8));
        assertFalse(lauta.sijoita(6, 7));
        assertFalse(lauta.sijoita(9, 8));
        assertFalse(lauta.sijoita(4, 7));
        assertFalse(lauta.sijoita(6, 8));
        assertTrue(lauta.sijoita(5, 7));
    }
    
    //VINOVASENTARKISTUS
    
    @Test
    public void vinovasenToimiiRistiVoittaa() {
        int j = 0;
        for (int i = 0; i <= 3; i++) {
            assertFalse(lauta.sijoita(i, j));
            assertFalse(lauta.sijoita(i, j + 1));
            j++;
        }
        assertTrue(lauta.sijoita(4, 4));
    }
    
    @Test
    public void vinovasenToimiiNollaVoittaa() {
        assertFalse(lauta.sijoita(14, 14));
        int j = 0;
        for (int i = 0; i <= 3; i++) {
            assertFalse(lauta.sijoita(i, j));
            assertFalse(lauta.sijoita(i, j + 1));
            j++;
        }
        assertTrue(lauta.sijoita(4, 4));
    }
    
    @Test
    public void vinovasenToimiiRistiVoittaaSatunnainenSijoitusjarjestys() {
        assertFalse(lauta.sijoita(4, 4));
        assertFalse(lauta.sijoita(4, 5));
        assertFalse(lauta.sijoita(7, 7));
        assertFalse(lauta.sijoita(7, 8));
        assertFalse(lauta.sijoita(5, 5));
        assertFalse(lauta.sijoita(5, 6));
        assertFalse(lauta.sijoita(6, 6));
        assertFalse(lauta.sijoita(6, 7));
        assertTrue(lauta.sijoita(8, 8));
    }
    
    @Test
    public void vinovasenToimiiNollaVoittaaSatunnainenSijoitusjarjestys() {
        assertFalse(lauta.sijoita(0, 0));
        assertFalse(lauta.sijoita(4, 4));
        assertFalse(lauta.sijoita(4, 5));
        assertFalse(lauta.sijoita(7, 7));
        assertFalse(lauta.sijoita(7, 8));
        assertFalse(lauta.sijoita(5, 5));
        assertFalse(lauta.sijoita(5, 6));
        assertFalse(lauta.sijoita(6, 6));
        assertFalse(lauta.sijoita(6, 7));
        assertTrue(lauta.sijoita(8, 8));
    }
    
    //VINOOIKEATARKISTUS
    
    @Test
    public void vinooikeaToimiiRistiVoittaa() {
        int j = 14;
        for (int i = 0; i <= 3; i++) {
            assertFalse(lauta.sijoita(i, j));
            assertFalse(lauta.sijoita(i, j - 1));
            j--;
        }
        assertTrue(lauta.sijoita(4, 10));
    }
    
    @Test
    public void vinooikeaToimiiNollaVoittaa() {
        assertFalse(lauta.sijoita(0, 0));
        int j = 14;
        for (int i = 0; i <= 3; i++) {
            assertFalse(lauta.sijoita(i, j));
            assertFalse(lauta.sijoita(i, j - 1));
            j--;
        }
        assertTrue(lauta.sijoita(4, 10));
    }
    
    @Test
    public void vinooikeaToimiiRistiVoittaaSatunnainenSijoitusjarjestys() {
        assertFalse(lauta.sijoita(8, 7));
        assertFalse(lauta.sijoita(8, 6));
        assertFalse(lauta.sijoita(9, 6));
        assertFalse(lauta.sijoita(9, 5));
        assertFalse(lauta.sijoita(7, 8));
        assertFalse(lauta.sijoita(7, 7));
        assertFalse(lauta.sijoita(10, 5));
        assertFalse(lauta.sijoita(10, 4));
        assertTrue(lauta.sijoita(11, 4));
    }
    
    @Test
    public void vinooikeaToimiiNollaVoittaaSatunnainenSijoitusjarjestys() {
        assertFalse(lauta.sijoita(0, 0));
        assertFalse(lauta.sijoita(8, 7));
        assertFalse(lauta.sijoita(8, 6));
        assertFalse(lauta.sijoita(9, 6));
        assertFalse(lauta.sijoita(9, 5));
        assertFalse(lauta.sijoita(7, 8));
        assertFalse(lauta.sijoita(7, 7));
        assertFalse(lauta.sijoita(10, 5));
        assertFalse(lauta.sijoita(10, 4));
        assertTrue(lauta.sijoita(11, 4));
    }
    
    //TASAPELI
    
    @Test
    public void tasapeliToimii() {
        int pituus = 15;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < pituus; j++) {
                assertFalse(lauta.sijoita(i, j));
            }
        }
        for (int i = 11; i < pituus; i++) {
            for (int j = 0; j < pituus; j++) {
                assertFalse(lauta.sijoita(i, j));
            }
        }
        for (int j = 1; j < pituus; j++) assertFalse(lauta.sijoita(4, j));
        for (int j = 0; j < pituus; j++) assertFalse(lauta.sijoita(5, j));
        assertFalse(lauta.sijoita(4, 0));
        for (int j = 1; j < pituus; j++) assertFalse(lauta.sijoita(6, j));
        for (int j = 0; j < pituus; j++) assertFalse(lauta.sijoita(7, j));
        assertFalse(lauta.sijoita(6, 0));
        for (int j = 0; j < pituus; j++) assertFalse(lauta.sijoita(8, j));
        for (int j = 0; j < pituus; j++) assertFalse(lauta.sijoita(9, j));
        for (int j = 0; j < pituus - 1; j++) assertFalse(lauta.sijoita(10, j));
        assertTrue(lauta.sijoita(10, 14));
    }
}