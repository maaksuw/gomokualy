
package logiikka;

import java.util.Scanner;
import ui.UI;

public class Main {

    public static void main(String[] args) {
        //TO DO:
        //REFAKTOROI COPYPASTE KOODI POIS SIT� ON KAIKKIALLA
        //Teko�ly
            //*koita syvent�� hakua
            //*kovakoodaa botille pari ekaa parasta siirtoa
            //*paranna pohjaheuristiikkaa: ottaa huomioon uhat muotoa xx xx tai oo oo ja sen kumman vuoro on avoimessa nelj�n uhassa
        //Lauta
            //*Kevenn� voittotarkistus
        //Tee uudet testit Lauta-luokalle ja testit Aly-luokalle
        //Lis�� "Uusi peli" -toiminto valikkoon niin ett� uuden pelin voi aloittaa keskenkaiken
        Scanner s = new Scanner(System.in);
        UI ui = new UI(s);
        ui.kaynnista();
    }
    
}
