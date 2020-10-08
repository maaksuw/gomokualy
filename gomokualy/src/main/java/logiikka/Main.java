
package logiikka;

import java.util.Scanner;
import ui.UI;

public class Main {

    public static void main(String[] args) {
        //TO DO:
        //Teko‰ly
            //*Tunnista pakottavat siirrot ja tutki vain ne
            //*Laita pohjaheuristiikka tunnistamaan hajonneen avoimen kolmen
            //*Koita syvent‰‰ hakua viel‰, principal variation search?
        //Muuta
            //*Github-dokumentaatio kuntoon
            //*Checkstyle kuntoon ja tarksta jacoco
        
        Scanner s = new Scanner(System.in);
        UI ui = new UI(s);
        ui.kaynnista();
    }
    
}
