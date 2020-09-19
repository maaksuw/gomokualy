
package logiikka;

public class Kolmio implements Comparable<Kolmio> {
    
    private int arvo;
    private int x;
    private int y;
    
    public Kolmio(int arvo, int x, int y) {
        this.arvo = arvo;
        this.x = x;
        this.y = y;
    }

    public int getArvo() {
        return arvo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setArvo(int arvo) {
        this.arvo = arvo;
    }
    
    public int compareTo(Kolmio k) {
       return this.arvo - k.arvo; 
    }
    
}
