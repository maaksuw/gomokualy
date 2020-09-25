
import logiikka.Aly;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestAly {
    
    private Aly botti;
    private char[][] lauta;
    private int pituus;
    
    public TestAly() {
        botti = new Aly();
        pituus = 19;
        lauta = new char[pituus][pituus];
    }
    
    //Testaa reagoigo botti suljettuun neljän uhkaan
    //Suljettu neljän uhka on pakottava siirto
    @Test
    public void bottiEstääSuljetunNeljänUhanMusta() {
        botti.setMerkki(1);
        botti.setPituus(pituus);
        for(int i = 0; i < pituus; i++){
            for(int j = 0; j < pituus; j++){
                lauta[i][j] = '+';
            }
        }
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        lauta[9][10] = 'O';
        lauta[9][11] = 'O';
        lauta[9][12] = 'O';
        lauta[9][13] = 'O';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        assertTrue(lauta[9][14] == 'X');
    }

    @Test
    public void bottiEstääSuljetunNeljänUhanValkoinen() {
        botti.setMerkki(0);
        botti.setPituus(pituus);
        for(int i = 0; i < pituus; i++){
            for(int j = 0; j < pituus; j++){
                lauta[i][j] = '+';
            }
        }
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        lauta[9][10] = 'X';
        lauta[9][11] = 'X';
        lauta[9][12] = 'X';
        lauta[9][13] = 'X';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        assertTrue(lauta[9][14] == 'O');
    }
    
    //Testaa reagoigo botti avoimeen kolmen uhkaan
    //Avoin kolmen uhka on pakottava siirto
    @Test
    public void bottiEstääAvoimenKolmenUhanMusta() {
        botti.setMerkki(1);
        botti.setPituus(pituus);
        for(int i = 0; i < pituus; i++){
            for(int j = 0; j < pituus; j++){
                lauta[i][j] = '+';
            }
        }
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        lauta[8][11] = 'O';
        lauta[8][12] = 'O';
        lauta[8][13] = 'O';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        assertTrue((lauta[8][14] == 'X') || (lauta[8][10] == 'X'));
    }
    
    @Test
    public void bottiEstääAvoimenKolmenUhanValkoinen() {
        botti.setMerkki(0);
        botti.setPituus(pituus);
        for(int i = 0; i < pituus; i++){
            for(int j = 0; j < pituus; j++){
                lauta[i][j] = '+';
            }
        }
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        lauta[8][11] = 'X';
        lauta[8][12] = 'X';
        lauta[8][13] = 'X';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        assertTrue((lauta[8][14] == 'O') || (lauta[8][10] == 'O'));
    }
    
    //Testaa tekeeko botti pelin päättävän voittavan siirron kun sen on pakko (tai se uhkaa hävitä)
    //Tämä on oleellinen ominaisuus ja määrittelee yllä olevien testien kanssa sen toimiiko botin kaikista perustavimmanlaatuiset toiminnot vai ei
    @Test
    public void bottiPäättääPelinKunOnPakkoMusta() {
        botti.setMerkki(1);
        botti.setPituus(pituus);
        for(int i = 0; i < pituus; i++){
            for(int j = 0; j < pituus; j++){
                lauta[i][j] = '+';
            }
        }
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        lauta[9][10] = 'X';
        lauta[9][11] = 'X';
        lauta[9][12] = 'X';
        lauta[7][9] = 'O';
        lauta[7][10] = 'O';
        lauta[7][11] = 'O';
        lauta[7][12] = 'O';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        assertTrue((lauta[9][8] == 'X') || (lauta[9][13] == 'X'));
    }
    
    @Test
    public void bottiPäättääPelinKunOnPakkoValkoinen() {
        botti.setMerkki(0);
        botti.setPituus(pituus);
        for(int i = 0; i < pituus; i++){
            for(int j = 0; j < pituus; j++){
                lauta[i][j] = '+';
            }
        }
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        lauta[9][10] = 'O';
        lauta[9][11] = 'O';
        lauta[9][12] = 'O';
        lauta[7][9] = 'X';
        lauta[7][10] = 'X';
        lauta[7][11] = 'X';
        lauta[7][12] = 'X';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        assertTrue((lauta[9][8] == 'O') || (lauta[9][13] == 'O'));
    }
    
    //Testaa tekeekö botti pelin päättävän voittavan siirron kun se on mahdollista (vaikka vastustaja ei pakota siirtoa)
    //Tämä ei sinänsä ole pakollinen ominaisuus, mutta haluttu kuitenkin ja saa botin pelin näyttämään ihmismäisemmältä
    @Test
    public void bottiPäätääPelinMusta() {
        botti.setMerkki(1);
        botti.setPituus(pituus);
        for(int i = 0; i < pituus; i++){
            for(int j = 0; j < pituus; j++){
                lauta[i][j] = '+';
            }
        }
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        lauta[9][10] = 'X';
        lauta[9][11] = 'X';
        lauta[9][12] = 'X';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        assertTrue((lauta[9][8] == 'X') || (lauta[9][13] == 'X'));
    }
    
    @Test
    public void bottiPäätääPelinMusta2() {
        botti.setMerkki(1);
        botti.setPituus(pituus);
        for(int i = 0; i < pituus; i++){
            for(int j = 0; j < pituus; j++){
                lauta[i][j] = '+';
            }
        }
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        lauta[9][10] = 'X';
        lauta[9][11] = 'X';
        lauta[9][12] = 'X';
        lauta[7][9] = 'O';
        lauta[7][10] = 'O';
        lauta[7][11] = 'O';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'X';
        assertTrue((lauta[9][8] == 'X') || (lauta[9][13] == 'X'));
    }
    
    @Test
    public void bottiPäätääPelinValkoinen() {
        botti.setMerkki(0);
        botti.setPituus(pituus);
        for(int i = 0; i < pituus; i++){
            for(int j = 0; j < pituus; j++){
                lauta[i][j] = '+';
            }
        }
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        lauta[9][10] = 'O';
        lauta[9][11] = 'O';
        lauta[9][12] = 'O';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        assertTrue((lauta[9][8] == 'O') || (lauta[9][13] == 'O'));
    }
    
    @Test
    public void bottiPäätääPelinValkoinen2() {
        botti.setMerkki(0);
        botti.setPituus(pituus);
        for(int i = 0; i < pituus; i++){
            for(int j = 0; j < pituus; j++){
                lauta[i][j] = '+';
            }
        }
        int[] koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        lauta[9][10] = 'O';
        lauta[9][11] = 'O';
        lauta[9][12] = 'O';
        lauta[7][9] = 'X';
        lauta[7][10] = 'X';
        lauta[7][11] = 'X';
        koordinaatit = botti.teeSiirto(lauta);
        lauta[koordinaatit[0]][koordinaatit[1]] = 'O';
        assertTrue((lauta[9][8] == 'O') || (lauta[9][13] == 'O'));
    }
}
