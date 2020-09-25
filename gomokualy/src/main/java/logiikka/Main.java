
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
        
//        Aly botti = new Aly();
//        botti.setMerkki(1);
//        botti.setPituus(15);
//        char[][] lauta = new char[15][15];
//        for(int i = 0; i < 15; i++){
//            for(int j = 0; j < 15; j++){
//                lauta[i][j] = '+';
//            }
//        }
//        lauta[3][6] = 'X';
//        lauta[3][9] = 'O';
//        lauta[4][9] = 'X';
//        lauta[4][10] = 'O';
//        lauta[5][7] = 'X';
//        lauta[5][8] = 'X';
//        lauta[5][9] = 'X';
//        lauta[6][8] = 'X';
//        lauta[6][9] = 'X';
//        lauta[6][11] = 'O';
//        lauta[7][6] = 'O';
//        lauta[7][7] = 'X';
//        lauta[7][8] = 'X';
//        lauta[7][9] = 'X';
//        lauta[7][10] = 'X';
//        lauta[7][11] = 'O';
//        lauta[8][6] = 'X';
//        lauta[8][7] = 'O';
//        lauta[8][8] = 'O';
//        lauta[8][9] = 'O';
//        lauta[8][10] = 'X';
//        lauta[8][11] = 'O';
//        lauta[9][5] = 'O';
//        lauta[9][6] = 'O';
//        lauta[9][11] = 'O';
//        lauta[10][11] = 'O';
//        System.out.println(botti.pohjaHeuristiikka(lauta));
    }
    
}
