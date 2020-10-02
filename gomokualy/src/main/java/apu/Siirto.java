
package apu;

public class Siirto implements Comparable<Siirto> {
    
    private long arvo;
    private int x;
    private int y;
    
    public Siirto(long arvo, int x, int y) {
        this.arvo = arvo;
        this.x = x;
        this.y = y;
    }

    public long getArvo() {
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
    
    public int compareTo(Siirto k) {
        if(this.arvo -  k.arvo < 0) return -1;
        else if (this.arvo -  k.arvo > 0) return 1;
        return 0; 
    }

    @Override
    public String toString() {
        return x + " " + y + " " + arvo;
    }
    
}
