
package logiikka;

import java.util.Scanner;
import ui.UI;

public class Main {

    public static void main(String[] args) {
        //Pitää selvittää miten ääkköset saisi toimimaan Netbeansin outputlootassa/command promptissa
        Scanner s = new Scanner(System.in);
        UI ui = new UI(s);
        ui.kaynnista();
    }
    
}
