package testit;

import logiikka.Aly;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class TestAly {
    
    private Aly botti;
    private char[][] lauta;
    private int pituus = 15;
    
    public TestAly() {
        botti = new Aly();
        lauta = new char[pituus][pituus];
    }
    
    @Before
    public void setUp() {
        for (int i = 0; i < pituus; i++) {
            for (int j = 0; j < pituus; j++) {
                lauta[i][j] = '+';
            }
        }
    }
    
    //Testaa reagoigo botti suljettuun neljän uhkaan.
    //Suljettu neljän uhka on pakottava siirto.
    @Test
    public void bottiEstaaSuljetunNeljanUhanRisti() {
        botti.setMerkki('X');
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        lauta[7][8] = 'O';
        lauta[7][9] = 'O';
        lauta[7][10] = 'O';
        lauta[7][11] = 'O';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        assertTrue(lauta[7][12] == 'X');
    }

    @Test
    public void bottiEstaaSuljetunNeljanUhanNolla() {
        lauta[7][7] = 'X';
        botti.setMerkki('O');
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        lauta[7][6] = 'X';
        lauta[7][5] = 'X';
        lauta[7][4] = 'X';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        assertTrue(lauta[7][3] == 'O');
    }
    
    //Testaa reagoigo botti avoimeen kolmen uhkaan.
    //Avoin kolmen uhka on pakottava siirto.
    @Test
    public void bottiEstaaAvoimenKolmenUhanRisti() {
        botti.setMerkki('X');
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        lauta[8][10] = 'O';
        lauta[8][11] = 'O';
        lauta[8][12] = 'O';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        assertTrue((lauta[8][13] == 'X') || (lauta[8][9] == 'X'));
    }
    
    @Test
    public void bottiEstaaAvoimenKolmenUhanNolla() {
        lauta[7][7] = 'X';
        botti.setMerkki('O');
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        lauta[9][10] = 'X';
        lauta[9][11] = 'X';
        lauta[9][12] = 'X';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        assertTrue((lauta[9][13] == 'O') || (lauta[9][9] == 'O'));
    }
    
    //Testaa tekeeko botti pelin päättävän voittavan siirron kun sen on pakko (tai se uhkaa hävitä).
    //Tämä on oleellinen ominaisuus ja määrittelee yllä olevien testien kanssa sen toimiiko botin kaikista perustavimmanlaatuiset toiminnot vai ei.
    @Test
    public void bottiPaattaaPelinKunOnPakkoRisti() {
        botti.setMerkki('X');
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        lauta[7][8] = 'X';
        lauta[7][9] = 'X';
        lauta[7][10] = 'X';
        lauta[4][4] = 'O';
        lauta[4][5] = 'O';
        lauta[4][6] = 'O';
        lauta[4][7] = 'O';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        assertTrue(lauta[7][11] == 'X' || lauta[7][6] == 'X');
    }
    
    @Test
    public void bottiPaattaaPelinKunOnPakkoNolla() {
        lauta[7][7] = 'X';
        botti.setMerkki('O');
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        lauta[6][8] = 'O';
        lauta[5][8] = 'O';
        lauta[4][8] = 'O';
        lauta[3][8] = 'O';
        lauta[7][6] = 'X';
        lauta[7][5] = 'X';
        lauta[7][4] = 'X';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        assertTrue((lauta[2][8] == 'O') || (lauta[8][8] == 'O'));
    }
    
    //Testaa tekeekö botti pelin päättävän voittavan siirron kun se on mahdollista (vaikka vastustaja ei pakota siirtoa).
    //Tämä ei sinänsä ole pakollinen ominaisuus, mutta haluttu kuitenkin ja saa botin pelin näyttämään ihmismäisemmältä.
    @Test
    public void bottiPaattaaPelinRasti() {
        botti.setMerkki('X');
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        lauta[7][8] = 'X';
        lauta[7][9] = 'X';
        lauta[7][10] = 'X';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        assertTrue((lauta[7][11] == 'X') || (lauta[7][6] == 'X'));
    }
    
    @Test
    public void bottiPaataaPelinRasti2() {
        botti.setMerkki('X');
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        lauta[7][8] = 'X';
        lauta[7][9] = 'X';
        lauta[7][10] = 'X';
        lauta[4][9] = 'O';
        lauta[4][10] = 'O';
        lauta[4][11] = 'O';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        assertTrue((lauta[7][11] == 'X') || (lauta[7][6] == 'X'));
    }
    
    @Test
    public void bottiPaataaPelinNolla() {
        lauta[1][1] = 'X';
        botti.setMerkki('O');
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        lauta[7][7] = 'O';
        lauta[8][7] = 'O';
        lauta[9][7] = 'O';
        lauta[10][7] = 'O';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        assertTrue((lauta[6][7] == 'O') || (lauta[11][7] == 'O'));
    }
    
    @Test
    public void bottiPaataaPelinNolla2() {
        lauta[1][1] = 'X';
        botti.setMerkki('O');
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        lauta[7][7] = 'O';
        lauta[8][7] = 'O';
        lauta[9][7] = 'O';
        lauta[10][7] = 'O';
        lauta[10][1] = 'X';
        lauta[10][2] = 'X';
        lauta[10][3] = 'X';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        assertTrue((lauta[11][7] == 'O') || (lauta[6][7] == 'O'));
    }
    
    @Test
    public void ensimmainenSiirto1() {
        botti.setMerkki('O');
        lauta[(pituus / 2) - 1][(pituus / 2) - 1] = 'X';
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        assertTrue(lauta[pituus / 2][pituus / 2] == 'O');
    }

    @Test
    public void ensimmainenSiirto2() {
        botti.setMerkki('O');
        lauta[(pituus / 2) - 1][(pituus / 2) - 1] = 'X';
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        assertTrue(lauta[pituus / 2][pituus / 2] == 'O');
    }
    
    @Test
    public void ensimmainenSiirto3() {
        botti.setMerkki('O');
        lauta[(pituus / 2) - 1][(pituus / 2) + 1] = 'X';
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        assertTrue(lauta[pituus / 2][pituus / 2] == 'O');
    }
    
    @Test
    public void ensimmainenSiirto4() {
        botti.setMerkki('O');
        lauta[pituus / 2][(pituus / 2) - 1] = 'X';
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        assertTrue(lauta[pituus / 2][pituus / 2] == 'O');
    }
    
    @Test
    public void ensimmainenSiirto5() {
        botti.setMerkki('O');
        lauta[pituus / 2][pituus / 2] = 'X';
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        assertTrue(lauta[pituus / 2][(pituus / 2) + 1] == 'O');
    }
    
    @Test
    public void ensimmainenSiirto6() {
        botti.setMerkki('O');
        lauta[pituus / 2][(pituus / 2) + 1] = 'X';
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        assertTrue(lauta[pituus / 2][pituus / 2] == 'O');
    }
    
    @Test
    public void ensimmainenSiirto7() {
        botti.setMerkki('O');
        lauta[(pituus / 2) + 1][(pituus / 2) - 1] = 'X';
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        assertTrue(lauta[pituus / 2][pituus / 2] == 'O');
    }
    
    @Test
    public void ensimmainenSiirto8() {
        botti.setMerkki('O');
        lauta[(pituus / 2) + 1][pituus / 2] = 'X';
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        assertTrue(lauta[pituus / 2][pituus / 2] == 'O');
    }
    
    @Test
    public void ensimmainenSiirto9() {
        botti.setMerkki('O');
        lauta[(pituus / 2) + 1][(pituus / 2) + 1] = 'X';
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        assertTrue(lauta[pituus / 2][pituus / 2] == 'O');
    }
    
    @Test
    public void bottiEstaaNeljanVastenSeinaa() {
        botti.setMerkki('X');
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        lauta[9][10] = 'O';
        lauta[9][11] = 'O';
        lauta[9][12] = 'O';
        lauta[9][13] = 'O';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        assertTrue(lauta[9][9] == 'X');
    }
}
