
package logiikka;

import java.util.Scanner;
import ui.UI;

public class Main {

    public static void main(String[] args) {
        //TO DO:
        //Teko�ly
            //*Tunnista pakottavat siirrot ja tutki vain ne
            //*Laita pohjaheuristiikka tunnistamaan hajonneen avoimen kolmen
        //Tietorakenteet
            //*Ota k�ytt��n Lista ja Hakemisto
            //*Hommaudu eroon Lauta-luokan StringBuilderist�
        //Testit
            //*Selvit� miten tehokkuustesti-luokan saa toimimaan testipakkauksessa
        //Muuta
            //*Github-dokumentaatio kuntoon
        
        Scanner s = new Scanner(System.in);
        UI ui = new UI(s);
        ui.kaynnista();

    }
    
}
