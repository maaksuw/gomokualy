
package logiikka;

import java.util.Scanner;
import ui.UI;

public class Main {

    public static void main(String[] args) {
        //Windows-k�ytt�ohjeet Gitiin
        //Teko�ly eka versio valmiiksi
            //*ottaa avoimet uhat huomioon
        //Lis�� "Uusi peli" -toiminto valikkoon
        Scanner s = new Scanner(System.in);
        UI ui = new UI(s);
        ui.kaynnista();
    }
    
}
