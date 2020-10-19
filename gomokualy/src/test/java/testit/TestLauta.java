package testit;


import logiikka.VanhaLauta;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestLauta {
    
    private VanhaLauta p;
    
    public TestLauta() {
        p = new VanhaLauta();
    }
    
    @Before
    public void setUp() {
        
    }
    
    //VAAKASUORATARKISTUS, MUSTA: isot testit

    @Test
    public void vaakaToimiiIsollaLaudallaMustaVoittaa() {
        int pituus = 19;
        for(int i = 0; i < pituus; i++) {
            for(int j = 0; j <= pituus - 5; j++) {
                p.alustaLautaTesti();
                for(int k = j; k <= j + 4; k++){
                    p.sijoita(i, k);
                }
                assertEquals("Musta voitti pelin!", p.tarkistaVoitto(i, j + 4));
            }
        }
    }
    
    @Test
    public void vaakaToimiiPienellaLaudallaMustaVoittaa() {
        int pituus = 15;
        p.setPituus(pituus);
        for(int i = 0; i < pituus; i++) {
            for(int j = 0; j <= pituus - 5; j++) {
                p.alustaLautaTesti();
                for(int k = j; k <= j + 4; k++){
                    p.sijoita(i, k);
                }
                assertEquals("Musta voitti pelin!", p.tarkistaVoitto(i, j + 4));
            }
        }
    }
    
    @Test
    public void vaakaToimiiIsollaLaudallaMustaVoittaaValkoistaReunoillaIsostaPieneen() {
        int pituus = 19;
        for(int i = 0; i < pituus; i++) {
            for(int j = pituus - 2; j >= 5; j--) {
                p.alustaLautaTesti();
                p.setVariTesti(0);
                p.sijoita(i, j + 1);
                p.sijoita(i, j - 5);
                p.setVariTesti(1);
                for(int k = j; k >= j - 4; k--){
                    p.sijoita(i, k);
                }
                assertEquals("Musta voitti pelin!", p.tarkistaVoitto(i, j - 4));
            }
        }
    }
    
    @Test
    public void vaakaToimiiPienellaLaudallaMustaVoittaaValkoistaReunoillaIsostaPieneen() {
        int pituus = 15;
        p.setPituus(pituus);
        for(int i = 0; i < pituus; i++) {
            for(int j = pituus - 2; j >= 5; j--) {
                p.alustaLautaTesti();
                p.setVariTesti(0);
                p.sijoita(i, j + 1);
                p.sijoita(i, j - 5);
                p.setVariTesti(1);
                for(int k = j; k >= j - 4; k--){
                    p.sijoita(i, k);
                }
                assertEquals("Musta voitti pelin!", p.tarkistaVoitto(i, j - 4));
            }
        }
    }
    
    //VAAKASUORATARKISTUS, MUSTA: pienet testit
    
    @Test
    public void vaakaToimiiIsollaLaudallaMustaVoittaaSatunnainenSijoitusjarjestys() {
        p.sijoita(10, 11);
        p.sijoita(10, 12);
        p.sijoita(10, 14);
        p.sijoita(10, 15);
        p.sijoita(10, 13);
        assertEquals("Musta voitti pelin!", p.tarkistaVoitto(10, 13));
    }
    
    @Test
    public void vaakaToimiiPienellaLaudallaMustaVoittaaSatunnainenSijoitusjarjestys() {
        p.setPituus(15);
        p.sijoita(10, 1);
        p.sijoita(10, 2);
        p.sijoita(10, 4);
        p.sijoita(10, 5);
        p.sijoita(10, 3);
        assertEquals("Musta voitti pelin!", p.tarkistaVoitto(10, 3));
    }
    
    //VAAKASUORATARKISTUS, VALKOINEN: isot testit
    
    @Test
    public void vaakaToimiiIsollaLaudallaValkoinenVoittaa() {
        int pituus = 19;
        p.setVariTesti(0);
        for(int i = 0; i < pituus; i++) {
            for(int j = 0; j <= pituus - 5; j++) {
                p.alustaLautaTesti();
                for(int k = j; k <= j + 4; k++){
                    p.sijoita(i, k);
                }
                assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(i, j + 4));
            }
        }
    }
    
    @Test
    public void vaakaToimiiPienellaLaudallaValkoinenVoittaa() {
        int pituus = 15;
        p.setPituus(pituus);
        p.setVariTesti(0);
        for(int i = 0; i < pituus; i++) {
            for(int j = 0; j <= pituus - 5; j++) {
                p.alustaLautaTesti();
                for(int k = j; k <= j + 4; k++){
                    p.sijoita(i, k);
                }
                assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(i, j + 4));
            }
        }
    }
    
    @Test
    public void vaakaToimiiIsollaLaudallaValkoinenVoittaaMustaaReunoillaIsostaPieneen() {
        int pituus = 19;
        p.setVariTesti(0);
        for(int i = 0; i < pituus; i++) {
            for(int j = pituus - 2; j >= 5; j--) {
                p.alustaLautaTesti();
                p.setVariTesti(1);
                p.sijoita(i, j + 1);
                p.sijoita(i, j - 5);
                p.setVariTesti(0);
                for(int k = j; k >= j - 4; k--){
                    p.sijoita(i, k);
                }
                assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(i, j - 4));
            }
        }
    }    
    
    @Test
    public void vaakaToimiiPienellaLaudallaValkoinenVoittaaMustaaReunoillaIsostaPieneen() {
        int pituus = 15;
        p.setPituus(pituus);
        p.setVariTesti(0);
        for(int i = 0; i < pituus; i++) {
            for(int j = pituus - 2; j >= 5; j--) {
                p.alustaLautaTesti();
                p.setVariTesti(1);
                p.sijoita(i, j + 1);
                p.sijoita(i, j - 5);
                p.setVariTesti(0);
                for(int k = j; k >= j - 4; k--){
                    p.sijoita(i, k);
                }
                assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(i, j - 4));
            }
        }
    }
    
    //VAAKASUORATARKISTUS, VALKOINEN: pienet testit

    @Test
    public void vaakaToimiiIsollaLaudallaValkoinenVoittaaSatunnainenSijoitusjarjestys() {
        p.setVariTesti(0);
        p.sijoita(10, 11);
        p.sijoita(10, 12);
        p.sijoita(10, 14);
        p.sijoita(10, 15);
        p.sijoita(10, 13);
        assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(10, 13));
    }
    
    @Test
    public void vaakaToimiiPienellaLaudallaValkoinenVoittaaSatunnainenSijoitusjarjestys() {
        p.setPituus(15);
        p.setVariTesti(0);
        p.sijoita(10, 1);
        p.sijoita(10, 2);
        p.sijoita(10, 4);
        p.sijoita(10, 5);
        p.sijoita(10, 3);
        assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(10, 3));
    }
    
    //PYSTYSUORATARKISTUS, MUSTA: isot testit
    
    @Test
    public void pystyToimiiIsollaLaudallaMustaVoittaa() {
        int pituus = 19;
        for(int i = 0; i < pituus; i++) {
            for(int j = 0; j <= pituus - 5; j++) {
                p.alustaLautaTesti();
                for(int k = j; k <= j + 4; k++){
                    p.sijoita(k, i);
                }
                assertEquals("Musta voitti pelin!", p.tarkistaVoitto(j + 4, i));
            }
        }
    }
    
    @Test
    public void pystyToimiiPienellaLaudallaMustaVoittaa() {
        int pituus = 15;
        p.setPituus(pituus);
        for(int i = 0; i < pituus; i++) {
            for(int j = 0; j <= pituus - 5; j++) {
                p.alustaLautaTesti();
                for(int k = j; k <= j + 4; k++){
                    p.sijoita(k, i);
                }
                assertEquals("Musta voitti pelin!", p.tarkistaVoitto(j + 4, i));
            }
        }
    }
    
    @Test
    public void pystyToimiiIsollaLaudallaMustaVoittaaValkoistaReunoillaIsostaPieneen() {
        int pituus = 19;
        for(int i = 0; i < pituus; i++) {
            for(int j = pituus - 2; j >= 5; j--) {
                p.alustaLautaTesti();
                p.setVariTesti(0);
                p.sijoita(j + 1, i);
                p.sijoita(j - 5, i);
                p.setVariTesti(1);
                for(int k = j; k >= j - 4; k--){
                    p.sijoita(k, i);
                }
                assertEquals("Musta voitti pelin!", p.tarkistaVoitto(j - 4, i));
            }
        }
    }
    
    @Test
    public void pystyToimiiPienellaLaudallaMustaVoittaaValkoistaReunoillaIsostaPieneen() {
        int pituus = 15;
        p.setPituus(15);
        for(int i = 0; i < pituus; i++) {
            for(int j = pituus - 2; j >= 5; j--) {
                p.alustaLautaTesti();
                p.setVariTesti(0);
                p.sijoita(j + 1, i);
                p.sijoita(j - 5, i);
                p.setVariTesti(1);
                for(int k = j; k >= j - 4; k--){
                    p.sijoita(k, i);
                }
                assertEquals("Musta voitti pelin!", p.tarkistaVoitto(j - 4, i));
            }
        }
    }
    
    //PYSTYSUORATARKISTUS, MUSTA: pienet testit
    
    @Test
    public void pystyToimiiIsollaLaudallaMustaVoittaaSatunnainenSijoitusjarjestys() {
        p.sijoita(11, 10);
        p.sijoita(12, 10);
        p.sijoita(14, 10);
        p.sijoita(15, 10);
        p.sijoita(13, 10);
        assertEquals("Musta voitti pelin!", p.tarkistaVoitto(13, 10));
    }
    
    @Test
    public void pystyToimiiPienellaLaudallaMustaVoittaaSatunnainenSijoitusjarjestys() {
        p.setPituus(15);
        p.sijoita(1, 10);
        p.sijoita(2, 10);
        p.sijoita(4, 10);
        p.sijoita(5, 10);
        p.sijoita(3, 10);
        assertEquals("Musta voitti pelin!", p.tarkistaVoitto(3, 10));
    }
    
    //PYSTYSUORATARKISTUS, VALKOINEN: isot testit
    
    @Test
    public void pystyToimiiIsollaLaudallaValkoinenVoittaa() {
        int pituus = 19;
        p.setVariTesti(0);
        for(int i = 0; i < pituus; i++) {
            for(int j = 0; j <= pituus - 5; j++) {
                p.alustaLautaTesti();
                for(int k = j; k <= j + 4; k++){
                    p.sijoita(k, i);
                }
                assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(j + 4, i));
            }
        }
    }
    
    @Test
    public void pystyToimiiPienellaLaudallaValkoinenVoittaa() {
        int pituus = 15;
        p.setPituus(pituus);
        p.setVariTesti(0);
        for(int i = 0; i < pituus; i++) {
            for(int j = 0; j <= pituus - 5; j++) {
                p.alustaLautaTesti();
                for(int k = j; k <= j + 4; k++){
                    p.sijoita(k, i);
                }
                assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(j + 4, i));
            }
        }
    }
    
    @Test
    public void pystyToimiiIsollaLaudallaValkoinenVoittaaMustaaReunoillaIsostaPieneen() {
        int pituus = 19;
        p.setVariTesti(0);
        for(int i = 0; i < pituus; i++) {
            for(int j = pituus - 2; j >= 5; j--) {
                p.alustaLautaTesti();
                p.setVariTesti(1);
                p.sijoita(j + 1, i);
                p.sijoita(j - 5, i);
                p.setVariTesti(0);
                for(int k = j; k >= j - 4; k--){
                    p.sijoita(k, i);
                }
                assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(j - 4, i));
            }
        }
    }
    
    @Test
    public void pystyToimiiPienellaLaudallaValkoinenVoittaaMustaaReunoillaIsostaPieneen() {
        int pituus = 15;
        p.setPituus(15);
        p.setVariTesti(0);
        for(int i = 0; i < pituus; i++) {
            for(int j = pituus - 2; j >= 5; j--) {
                p.alustaLautaTesti();
                p.setVariTesti(1);
                p.sijoita(j + 1, i);
                p.sijoita(j - 5, i);
                p.setVariTesti(0);
                for(int k = j; k >= j - 4; k--){
                    p.sijoita(k, i);
                }
                assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(j - 4, i));
            }
        }
    }
    
    //PYSTYSUORATARKISTUS, VALKOINEN: pienet testit
    
    @Test
    public void pystyToimiiIsollaLaudallaValkoinenVoittaaSatunnainenSijoitusjarjestys() {
        p.setVariTesti(0);
        p.sijoita(11, 10);
        p.sijoita(12, 10);
        p.sijoita(14, 10);
        p.sijoita(15, 10);
        p.sijoita(13, 10);
        assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(13, 10));
    }
    
    @Test
    public void pystyToimiiPienellaLaudallaValkoinenVoittaaSatunnainenSijoitusjarjestys() {
        p.setPituus(15);
        p.setVariTesti(0);
        p.sijoita(1, 10);
        p.sijoita(2, 10);
        p.sijoita(4, 10);
        p.sijoita(5, 10);
        p.sijoita(3, 10);
        assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(3, 10));
    }
    
    //VINOVASENTARKISTUS, MUSTA: isot testit
    
    @Test
    public void vinovasenToimiiIsollaLaudallaMustaVoittaa() {
        int pituus = 19;
        for(int i = 0; i <= pituus - 5; i++) {
            for(int j = 0; j <= pituus - 5; j++) {
                p.alustaLautaTesti();
                int k = i;
                int l = j;
                for(int m = 0; m < 5; m++){
                    p.sijoita(k, l);
                    k++; l++;
                }
                assertEquals("Musta voitti pelin!", p.tarkistaVoitto(k - 1, l - 1));
            }
        }
    }
    
    @Test
    public void vinovasenToimiiPienellaLaudallaMustaVoittaa() {
        int pituus = 15;
        p.setPituus(pituus);
        for(int i = 0; i <= pituus - 5; i++) {
            for(int j = 0; j <= pituus - 5; j++) {
                p.alustaLautaTesti();
                int k = i;
                int l = j;
                for(int m = 0; m < 5; m++){
                    p.sijoita(k, l);
                    k++; l++;
                }
                assertEquals("Musta voitti pelin!", p.tarkistaVoitto(k - 1, l - 1));
            }
        }
    }
    
    @Test
    public void vinovasenToimiiIsollaLaudallaMustaVoittaaValkoistaReunoillaIsostaPieneen() {
        int pituus = 19;
        for(int i = 5; i <= pituus - 2; i++) {
            for(int j = 5; j <= pituus - 2; j++) {
                p.alustaLautaTesti();
                p.setVariTesti(0);
                p.sijoita(i + 1, j + 1);
                p.sijoita(i - 5, j - 5);
                p.setVariTesti(1);
                int k = i;
                int l = j;
                for(int m = 0; m < 5; m++){
                    p.sijoita(k, l);
                    k--; l--;
                }
                assertEquals("Musta voitti pelin!", p.tarkistaVoitto(k + 1, l + 1));
            }
        }
    }
    
    @Test
    public void vinovasenToimiiPienellaLaudallaMustaVoittaaValkoistaReunoillaIsostaPieneen() {
        int pituus = 15;
        p.setPituus(pituus);
        for(int i = 5; i <= pituus - 2; i++) {
            for(int j = 5; j <= pituus - 2; j++) {
                p.alustaLautaTesti();
                p.setVariTesti(0);
                p.sijoita(i + 1, j + 1);
                p.sijoita(i - 5, j - 5);
                p.setVariTesti(1);
                int k = i;
                int l = j;
                for(int m = 0; m < 5; m++){
                    p.sijoita(k, l);
                    k--; l--;
                }
                assertEquals("Musta voitti pelin!", p.tarkistaVoitto(k + 1, l + 1));
            }
        }
    }
    
    //VINOVASENTARKISTUS, MUSTA: pienet testit
    
    @Test
    public void vinovasenToimiiIsollaLaudallaMustaVoittaaSatunnainenSijoitusjarjestys() {
        p.sijoita(3, 3);
        p.sijoita(4, 4);
        p.sijoita(6, 6);
        p.sijoita(7, 7);
        p.sijoita(5, 5);
        assertEquals("Musta voitti pelin!", p.tarkistaVoitto(5, 5));
    }
    
    @Test
    public void vinovasenToimiiPienellaLaudallaMustaVoittaaSatunnainenSijoitusjarjestys() {
        p.setPituus(15);
        p.sijoita(3, 3);
        p.sijoita(4, 4);
        p.sijoita(6, 6);
        p.sijoita(7, 7);
        p.sijoita(5, 5);
        assertEquals("Musta voitti pelin!", p.tarkistaVoitto(5, 5));
    }
    
    //VINOVASENTARKISTUS, VALKOINEN: isot testit
    
    @Test
    public void vinovasenToimiiIsollaLaudallaValkoinenVoittaa() {
        int pituus = 19;
        p.setVariTesti(0);
        for(int i = 0; i <= pituus - 5; i++) {
            for(int j = 0; j <= pituus - 5; j++) {
                p.alustaLautaTesti();
                int k = i;
                int l = j;
                for(int m = 0; m < 5; m++){
                    p.sijoita(k, l);
                    k++; l++;
                }
                assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(k - 1, l - 1));
            }
        }
    }
    
    @Test
    public void vinovasenToimiiPienellaLaudallaValkoinenVoittaa() {
        int pituus = 15;
        p.setPituus(pituus);
        p.setVariTesti(0);
        for(int i = 0; i <= pituus - 5; i++) {
            for(int j = 0; j <= pituus - 5; j++) {
                p.alustaLautaTesti();
                int k = i;
                int l = j;
                for(int m = 0; m < 5; m++){
                    p.sijoita(k, l);
                    k++; l++;
                }
                assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(k - 1, l - 1));
            }
        }
    }
    
    @Test
    public void vinovasenToimiiIsollaLaudallaValkoinenVoittaaMustaaReunoillaIsostaPieneen() {
        int pituus = 19;
        p.setVariTesti(0);
        for(int i = 5; i <= pituus - 2; i++) {
            for(int j = 5; j <= pituus - 2; j++) {
                p.alustaLautaTesti();
                p.setVariTesti(1);
                p.sijoita(i + 1, j + 1);
                p.sijoita(i - 5, j - 5);
                p.setVariTesti(0);
                int k = i;
                int l = j;
                for(int m = 0; m < 5; m++){
                    p.sijoita(k, l);
                    k--; l--;
                }
                assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(k + 1, l + 1));
            }
        }
    }
    
    @Test
    public void vinovasenToimiiPienellaLaudallaValkoinenVoittaaMustaaReunoillaIsostaPieneen() {
        int pituus = 15;
        p.setPituus(pituus);
        p.setVariTesti(0);
        for(int i = 5; i <= pituus - 2; i++) {
            for(int j = 5; j <= pituus - 2; j++) {
                p.alustaLautaTesti();
                p.setVariTesti(1);
                p.sijoita(i + 1, j + 1);
                p.sijoita(i - 5, j - 5);
                p.setVariTesti(0);
                int k = i;
                int l = j;
                for(int m = 0; m < 5; m++){
                    p.sijoita(k, l);
                    k--; l--;
                }
                assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(k + 1, l + 1));
            }
        }
    }
    
    //VINOVASENTARKISTUS, VALKOINEN: pienet testit
    
    @Test
    public void vinovasenToimiiIsollaLaudallaValkoinenVoittaaSatunnainenSijoitusjarjestys() {
        p.setVariTesti(0);
        p.sijoita(3, 3);
        p.sijoita(4, 4);
        p.sijoita(6, 6);
        p.sijoita(7, 7);
        p.sijoita(5, 5);
        assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(5, 5));
    }
    
    @Test
    public void vinovasenToimiiPienellaLaudallaValkoinenVoittaaSatunnainenSijoitusjarjestys() {
        p.setPituus(15);
        p.setVariTesti(0);
        p.sijoita(3, 3);
        p.sijoita(4, 4);
        p.sijoita(6, 6);
        p.sijoita(7, 7);
        p.sijoita(5, 5);
        assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(5, 5));
    }
    
    //VINOOIKEATARKISTUS, MUSTA: isot testit
    
    @Test
    public void vinooikeaToimiiIsollaLaudallaMustaVoittaa() {
        int pituus = 19;
        for(int i = 0; i <= pituus - 5; i++) {
            for(int j = pituus - 1; j >= 4; j--) {
                p.alustaLautaTesti();
                int k = i;
                int l = j;
                for(int m = 0; m < 5; m++){
                    p.sijoita(k, l);
                    k++; l--;
                }
                assertEquals("Musta voitti pelin!", p.tarkistaVoitto(k - 1, l + 1));
            }
        }
    }
    
    @Test
    public void vinooikeaToimiiPienellaLaudallaMustaVoittaa() {
        int pituus = 15;
        p.setPituus(pituus);
        for(int i = 0; i <= pituus - 5; i++) {
            for(int j = pituus - 1; j >= 4; j--) {
                p.alustaLautaTesti();
                int k = i;
                int l = j;
                for(int m = 0; m < 5; m++){
                    p.sijoita(k, l);
                    k++; l--;
                }
                assertEquals("Musta voitti pelin!", p.tarkistaVoitto(k - 1, l + 1));
            }
        }
    }
    
    @Test
    public void vinooikeaToimiiIsollaLaudallaMustaVoittaaValkoistaReunoillaIsostaPieneen() {
        int pituus = 19;
        for(int i = 5; i <= pituus - 2; i++) {
            for(int j = 1; j < pituus - 5; j++) {
                p.alustaLautaTesti();
                p.setVariTesti(0);
                p.sijoita(i + 1, j - 1);
                p.sijoita(i - 5, j + 5);
                p.setVariTesti(1);
                int k = i;
                int l = j;
                for(int m = 0; m < 5; m++){
                    p.sijoita(k, l);
                    k--; l++;
                }
                assertEquals("Musta voitti pelin!", p.tarkistaVoitto(k + 1, l - 1));
            }
        }
    }
    
    @Test
    public void vinooikeaToimiiPienellaLaudallaMustaVoittaaValkoistaReunoillaIsostaPieneen() {
        int pituus = 15;
        p.setPituus(pituus);
        for(int i = 5; i <= pituus - 2; i++) {
            for(int j = 1; j < pituus - 5; j++) {
                p.alustaLautaTesti();
                p.setVariTesti(0);
                p.sijoita(i + 1, j - 1);
                p.sijoita(i - 5, j + 5);
                p.setVariTesti(1);
                int k = i;
                int l = j;
                for(int m = 0; m < 5; m++){
                    p.sijoita(k, l);
                    k--; l++;
                }
                assertEquals("Musta voitti pelin!", p.tarkistaVoitto(k + 1, l - 1));
            }
        }
    }
    
    //VINOOIKEATARKISTUS, MUSTA: pienet testit
    
    @Test
    public void vinooikeaToimiiIsollaLaudallaMustaVoittaaSatunnainenSijoitusjarjestys() {
        p.sijoita(3, 10);
        p.sijoita(4, 9);
        p.sijoita(6, 7);
        p.sijoita(7, 6);
        p.sijoita(5, 8);
        assertEquals("Musta voitti pelin!", p.tarkistaVoitto(5, 8));
    }
    
    @Test
    public void vinooikeaToimiiPienellaLaudallaMustaVoittaaSatunnainenSijoitusjarjestys() {
        p.setPituus(15);
        p.sijoita(3, 10);
        p.sijoita(4, 9);
        p.sijoita(6, 7);
        p.sijoita(7, 6);
        p.sijoita(5, 8);
        assertEquals("Musta voitti pelin!", p.tarkistaVoitto(5, 8));
    }
    
    //VINOOIKEATARKISTUS, VALKOINEN: isot testit
    
    @Test
    public void vinooikeaToimiiIsollaLaudallaValkoinenVoittaa() {
        int pituus = 19;
        p.setVariTesti(0);
        for(int i = 0; i <= pituus - 5; i++) {
            for(int j = pituus - 1; j >= 4; j--) {
                p.alustaLautaTesti();
                int k = i;
                int l = j;
                for(int m = 0; m < 5; m++){
                    p.sijoita(k, l);
                    k++; l--;
                }
                assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(k - 1, l + 1));
            }
        }
    }
    
    @Test
    public void vinooikeaToimiiPienellaLaudallaValkoinenVoittaa() {
        int pituus = 15;
        p.setPituus(pituus);
        p.setVariTesti(0);
        for(int i = 0; i <= pituus - 5; i++) {
            for(int j = pituus - 1; j >= 4; j--) {
                p.alustaLautaTesti();
                int k = i;
                int l = j;
                for(int m = 0; m < 5; m++){
                    p.sijoita(k, l);
                    k++; l--;
                }
                assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(k - 1, l + 1));
            }
        }
    }
    
    @Test
    public void vinooikeaToimiiIsollaLaudallaValkoinenVoittaaMustaaReunoillaIsostaPieneen() {
        int pituus = 19;
        p.setVariTesti(0);
        for(int i = 5; i <= pituus - 2; i++) {
            for(int j = 1; j < pituus - 5; j++) {
                p.alustaLautaTesti();
                p.setVariTesti(1);
                p.sijoita(i + 1, j - 1);
                p.sijoita(i - 5, j + 5);
                p.setVariTesti(0);
                int k = i;
                int l = j;
                for(int m = 0; m < 5; m++){
                    p.sijoita(k, l);
                    k--; l++;
                }
                assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(k + 1, l - 1));
            }
        }
    }
    
    @Test
    public void vinooikeaToimiiPienellaLaudallaValkoinenVoittaaMustaaReunoillaIsostaPieneen() {
        int pituus = 15;
        p.setPituus(pituus);
        p.setVariTesti(0);
        for(int i = 5; i <= pituus - 2; i++) {
            for(int j = 1; j < pituus - 5; j++) {
                p.alustaLautaTesti();
                p.setVariTesti(1);
                p.sijoita(i + 1, j - 1);
                p.sijoita(i - 5, j + 5);
                p.setVariTesti(0);
                int k = i;
                int l = j;
                for(int m = 0; m < 5; m++){
                    p.sijoita(k, l);
                    k--; l++;
                }
                assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(k + 1, l - 1));
            }
        }
    }
    
    //VINOOIKEATARKISTUS, VALKOINEN: pienet testit
    
    @Test
    public void vinooikeaToimiiIsollaLaudallaValkoinenVoittaaSatunnainenSijoitusjarjestys() {
        p.setVariTesti(0);
        p.sijoita(3, 10);
        p.sijoita(4, 9);
        p.sijoita(6, 7);
        p.sijoita(7, 6);
        p.sijoita(5, 8);
        assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(5, 8));
    }
    
    @Test
    public void vinooikeaToimiiPienellaLaudallaValkoinenVoittaaSatunnainenSijoitusjarjestys() {
        p.setVariTesti(0);
        p.setPituus(15);
        p.sijoita(3, 10);
        p.sijoita(4, 9);
        p.sijoita(6, 7);
        p.sijoita(7, 6);
        p.sijoita(5, 8);
        assertEquals("Valkoinen voitti pelin!", p.tarkistaVoitto(5, 8));
    }
    
    //MUITA TESTEJÄ
    
    @Test
    public void tasapeliToimii() {
        int pituus = 19;
        String tulos = "";
        for(int i = 0; i < pituus; i++){
            for(int j = 0; j < pituus; j++){
                p.sijoita(i, j);
                tulos = p.tarkistaVoitto(i, j);
            }
        }
        assertEquals("Tasapeli!", tulos);
    }
}