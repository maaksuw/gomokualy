
package logiikka;

import java.util.Scanner;
import ui.UI;

public class Main {

    public static void main(String[] args) {
        //TO DO:
        //Muuta
            //*Github-dokumentaatio kuntoon
            //*Checkstyle kuntoon
            //*Tarkista jacoco
            //*Botin tehokkuusraportti
            //*GUI
        
        Scanner s = new Scanner(System.in);
        UI ui = new UI(s);
        ui.kaynnista();

    }
    
}
