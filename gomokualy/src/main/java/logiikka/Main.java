
package logiikka;

import java.util.Scanner;
import ui.UI;

public class Main {

    public static void main(String[] args) {
        //TO DO:
        //Tekoäly
            //*Tunnista pakottavat siirrot ja tutki vain ne
            //*Laita pohjaheuristiikka tunnistamaan hajonneen avoimen kolmen
        //Testit
            //*Tee tehokkuustestit botille
        //Muuta
            //*Github-dokumentaatio kuntoon
        
        Scanner s = new Scanner(System.in);
        UI ui = new UI(s);
        ui.kaynnista();

    }
    
}
