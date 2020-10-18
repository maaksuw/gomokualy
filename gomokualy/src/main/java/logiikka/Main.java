
package logiikka;

import java.util.Scanner;
import ui.UI;
import ui.UI2;

public class Main {

    public static void main(String[] args) {
        //TO DO:
        //Muuta
            //*Github-dokumentaatio kuntoon
            //*Checkstyle kuntoon ja tarksta jacoco
        
        Scanner s = new Scanner(System.in);
        UI2 ui = new UI2(s);
        ui.kaynnista();

    }
    
}
