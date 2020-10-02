
package apu;

/**
 * Tarjoaa laskemiseen liittyviä apumetodeja.
 */

public class Matikka {
    
    public static long max(long a, long b){
        if(a >= b) return a;
        else return b;
    }
    
    public static long min(long a, long b){
        if(a <= b) return a;
        else return b;
    }
    
    public static int maxi(int a, int b){
        if(a >= b) return a;
        else return b;
    }
    
    public static int mini(int a, int b){
        if(a <= b) return a;
        else return b;
    }
    
    public static long potenssi(int kantaluku, int potenssi) {
        long vastaus = 1;
        for(int i = 0; i < potenssi; i++) vastaus *= kantaluku;
        return vastaus;
    }
}
