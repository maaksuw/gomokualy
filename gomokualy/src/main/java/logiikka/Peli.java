
package logiikka;

public class Peli {
    
    private int pituus;
    private char[][] lauta;
    private int vari;
    private int vuoroja;
    
    public Peli(){
        pituus = 19;
        lauta = new char[pituus][pituus];
        alustaLauta();
        vari = 1;
        vuoroja = 0;
    }
    
    public void alustaPeli() {
        pituus = 19;
        alustaLauta();
        vari = 1;
        vuoroja = 0;
    }

    public void setPituus(int pituus) {
        this.pituus = pituus;
    }
    
    public int muutaYKoordinaattiNumeroksi(String ykoordinaatti){
        int y = ykoordinaatti.charAt(0) - 64;
        return y - 1;
    }
    
    public int muutaXKoordinaattiNumeroksi(String xkoordinaatti){
        int x = Integer.valueOf(xkoordinaatti);
        return pituus - x;
    }
    
    public boolean sijoita(int x, int y){
        if(lauta[x][y] == '+'){
            if(vari == 1) lauta[x][y] = 'X';
            if(vari == 0) lauta[x][y] = 'O';
            return true;
        } 
        return false;
    }
    
    public String pelaaVuoro(int x, int y){
        String tulos = "";
        vuoroja++;
        if(onkoVoittoa(x, y)){
            if(vari == 1) tulos = "Musta voitti pelin!";
            else tulos = "Valkoinen voitti pelin!";
            return tulos;
        } else if (vuoroja == pituus*pituus) {
            tulos = "Tasapeli!";
            return tulos;
        } else {
            if(vari == 1) vari--;
            else vari++;
        }
        return tulos;
    }
    
    private boolean onkoVoittoa(int x, int y) {
        char omaMerkki = 'X';
        if(vari == 0) omaMerkki = 'O';
        return vaakasuoraTarkistus(x, y, omaMerkki) || pystysuoraTarkistus(x, y, omaMerkki) || vinoVasenTarkistus(x, y, omaMerkki) || vinoOikeaTarkistus(x, y, omaMerkki);
    }
    
    private boolean vinoOikeaTarkistus(int x, int y, char omaMerkki) {
        int alkux = x - 4;
        int alkuy = y + 4;
        for(int i = 0; i < 5; i++){
            if(alkux < 0 || alkux + 4 >= pituus || alkuy >= pituus || alkuy - 4 < 0) continue;
            int omaa = 0;
            int kopiox = alkux;
            int kopioy = alkuy;
            for(int j = 0; j < 5; j++){
                if(lauta[kopiox][kopioy] == omaMerkki) omaa++;
                kopiox++;
                kopioy--;
            }
            if(omaa == 5) return true;
            alkux++;
            alkuy--;
        }
        return false;
    }
    
    private boolean vinoVasenTarkistus(int x, int y, char omaMerkki) {
        int alkux = x - 4;
        int alkuy = y - 4;
        for(int i = 0; i < 5; i++){
            if(alkux < 0 || alkux + 4 >= pituus || alkuy < 0 || alkuy + 4 >= pituus) continue;
            int omaa = 0;
            int kopiox = alkux;
            int kopioy = alkuy;
            for(int j = 0; j < 5; j++){
                if(lauta[kopiox][kopioy] == omaMerkki) omaa++;
                kopiox++;
                kopioy++;
            }
            if(omaa == 5) return true;
            alkux++;
            alkuy++;
        }
        return false;
    }
    
    private boolean pystysuoraTarkistus(int x, int y, char omaMerkki) {
        for(int i = x - 4; i <= x; i++){
            if(i < 0 || i + 4 >= pituus) continue;
            int omaa = 0;
            for(int j = i; j < i + 5; j++){
                if(lauta[j][y] == omaMerkki) omaa++;
            }
            if(omaa == 5) return true;
        }
        return false;
    }
    
    private boolean vaakasuoraTarkistus(int x, int y, char omaMerkki) {
        for(int i = y - 4; i <= y; i++){
            if(i < 0 || i + 4 >= pituus) continue;
            int omaa = 0;
            for(int j = i; j < i + 5; j++){
                if(lauta[x][j] == omaMerkki) omaa++;
            }
            if(omaa == 5) return true;
        }
        return false;
    }
    
    private void alustaLauta(){
        for(int i = 0; i < pituus; i++){
            for(int j = 0; j < pituus; j++){
                lauta[i][j] = '+';
            }
        }
    }
    
    public void tulostaVuorot(){
        if(vari == 1) System.out.println("Vuorossa: MUSTA");
        else System.out.println("Vuorossa: VALKOINEN");
    }
    
    public void tulostaLauta(){
        System.out.println("");
        System.out.println("ohjeet(o) pelisäännöt(p) lopeta(x)");
        int vaakarivi = pituus;
        for(int i = 0; i < pituus; i++){
            if(vaakarivi < 10) System.out.print(" ");
            System.out.print(vaakarivi + " ");
            for(int j = 0; j < pituus; j++){
                if(j == pituus - 1) System.out.print(lauta[i][j]);
                else System.out.print(lauta[i][j] + " ");
            }
            System.out.println("");
            vaakarivi--;
        }
        System.out.print("   ");
        char asti = 'S';
        if(pituus == 15) asti = 'O';
        for(char i = 'A'; i <= asti; i++){
            if(i == asti) System.out.print(i);
            else System.out.print(i + " ");
        }
        System.out.println("");
    }
}
