
package logiikka;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Pitää selvittää miten ääkköset saisi toimimaan Netbeansin outputlootassa/command promptissa
        Scanner s = new Scanner(System.in);
        Peli p = new Peli(s);
        p.kaynnista();
    }
    
}
