
package apu;

/**
 *
 */
public class Tilanne {
    
    private String lauta;
    private long arvo;
    
    public Tilanne(String s, long a) {
        lauta = s;
        arvo = a;
    }

    public String getLauta() {
        return lauta;
    }
    
    public long getArvo() {
        return arvo;
    }

}
