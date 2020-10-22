
package apu;

/**
 * Tarjoaa laskemiseen liittyviä apumetodeja.
 */

public class Matikka {
    
    /**
     * Palauttaa kahdesta kokonaisluvusta suuremman.
     * @param a
     * @param b
     * @return a, jos a on suurempi ja b muuten.
     */
    public static int max(int a, int b) {
        if (a > b) return a;
        else return b;
    }
    
    /**
     * Palauttaa kahdesta kokonaisluvusta pienemmän.
     * @param a
     * @param b
     * @return a, jos a on pienempi ja b muuten.
     */
    public static int min(int a, int b) {
        if (a < b) return a;
        else return b;
    }
    
}
