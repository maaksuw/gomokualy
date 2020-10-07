package testit;


import apu.Hakemisto;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestHakemisto {
    
    private Hakemisto hakemisto;
    
    public TestHakemisto() {
        hakemisto = new Hakemisto();
    }
    
//    private char[][] sana(String sana) {
//        int n = sana.length();
//        char[][] taulu = new char[n][n];
//        for(int i = 0; i < n; i++){
//            for(int j = 0; j < n; j++){
//                taulu[i][j] = sana.charAt(j);
//            }
//        }
//        return taulu;
//    }
    
    @Test
    public void lisaaToimii() {
        hakemisto.lisaa("pupu", 3000);
        hakemisto.lisaa("misse", 3000);
        hakemisto.lisaa("makkis", 3000);
        hakemisto.lisaa("misse", 2);
        hakemisto.lisaa("misse", 13);
        assertEquals(3, hakemisto.alkioita());
    }
    
    @Test
    public void lisaaJaHaeToimii() {
        hakemisto.lisaa("pupu", 3000);
        hakemisto.lisaa("misse", 3000);
        assertTrue(hakemisto.hae("misse") == 3000);
        hakemisto.lisaa("misse", 2);
        assertTrue(hakemisto.hae("misse") == 2);
    }
    
    @Test
    public void haeToimiiJosArvoaEiOle() {
        hakemisto.lisaa("pupu", 3000);
        hakemisto.lisaa("misse", 3000);
        assertNull(hakemisto.hae("makkis"));
    }
    
    @Test
    public void tyhjennaToimii() {
        hakemisto.lisaa("pupu", 3000);
        hakemisto.lisaa("misse", 3000);
        hakemisto.lisaa("makkis", 3000);
        hakemisto.lisaa("kroko", 3000);
        hakemisto.lisaa("puppe", 3000);
        hakemisto.tyhjenna();
        assertEquals(0, hakemisto.alkioita());
        assertNull(hakemisto.hae("pupu"));
        assertNull(hakemisto.hae("misse"));
        assertNull(hakemisto.hae("makkis"));
        assertNull(hakemisto.hae("kroko"));
        assertNull(hakemisto.hae("puppe"));
    }
    
}
