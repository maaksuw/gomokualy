
package logiikka;

import java.util.Scanner;
import ui.UI;

public class Main {

    public static void main(String[] args) {
        //TO DO:
        //REFAKTOROI COPYPASTE KOODI POIS SITÄ ON KAIKKIALLA
        //Tekoäly
            //*koita syventää hakua
            //*kovakoodaa botille pari ekaa parasta siirtoa
            //*paranna pohjaheuristiikkaa: ottaa huomioon uhat muotoa xx xx tai oo oo ja sen kumman vuoro on avoimessa neljän uhassa
        //Lauta
            //*Kevennä voittotarkistus
        //Tee uudet testit Lauta-luokalle ja testit Aly-luokalle
        //Lisää "Uusi peli" -toiminto valikkoon niin että uuden pelin voi aloittaa keskenkaiken
        Scanner s = new Scanner(System.in);
        UI ui = new UI(s);
        ui.kaynnista();
    }
    
}
