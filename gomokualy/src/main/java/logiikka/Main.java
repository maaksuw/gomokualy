
package logiikka;

import java.util.Scanner;
import javafx.application.Application;
import ui.GUI;
import ui.UI;

public class Main {

    public static void main(String[] args) {
        //TO DO:
        //Muuta
            //*Github-dokumentaatio kuntoon
            //*Tarkista jacoco
            //*Botin tehokkuusraportti
            //Muuta v�rit rastiksi ja nollaksi
            //Laita oma siirto n�kyviin ennen botin siirtoa
            //Laita teksti ilmoittamaan, kun botti miettii
        
        Scanner s = new Scanner(System.in);
        UI ui = new UI(s);
        ui.kaynnista();

        //Application.launch(GUI.class, args);

    }
    
}
