
package apu;

import logiikka.Tilanne;

/**
 * Yksinkertainen hakemisto merkkijono-kokonaisluku -pareille.
 * Hakemistoon voi lis‰t‰ alkion ja sielt‰ voi hakea arvon avaimen perusteella.
 * Hakemiston voi tyhjent‰‰.
 * @see Tilanne
 */

public class Hakemisto {
    
    private Lista<Tilanne>[] hakemisto;
    private int koko;
    private int kerroin;
    private int isoluku;
    
    public Hakemisto(){
        koko = 100019;
        kerroin = 31;
        isoluku = 1010101010;
        hakemisto = new Lista[koko];
    }
    
    public void lisaa(String avain, long arvo) {
        int hajautusarvo = hajautusFunktio(avain);
        Tilanne t = new Tilanne(avain, arvo);
        boolean loytyy = false;
        if(hakemisto[hajautusarvo] == null) hakemisto[hajautusarvo] = new Lista<Tilanne>();
        for(int i = 0; i < hakemisto[hajautusarvo].pituus(); i++){
            Tilanne tt = hakemisto[hajautusarvo].hae(i);
            if(tt.getLauta().equals(avain)) {
                tt.setArvo(arvo);
                loytyy = true;
            }
        }
        if(!loytyy) hakemisto[hajautusarvo].lisaa(t);
    }
    
    private int hajautusFunktio(String avain) {
        int x = 0;
        for(int i = 0; i < avain.length(); i++){
            x = (x*kerroin + avain.charAt(i))%koko;
        }
        return x;
    }
    
    public long hae(String avain) {
        int hajautusarvo = hajautusFunktio(avain);
        if(hakemisto[hajautusarvo] == null) return isoluku;
        for(int i = 0; i < hakemisto[hajautusarvo].pituus(); i++){
            Tilanne t = hakemisto[hajautusarvo].hae(i);
            if(t.getLauta().equals(avain)) return t.getArvo();
        }
        return isoluku;
    }
    
    public void tyhjenna() {
        for(int i = 0; i < koko; i++){
            hakemisto[i] = null;
        }
    }

    public String hajautusArvio() {
        String s = "";
        int kok = 0;
        for(int i = 0; i < koko; i++){
            if(hakemisto[i] != null) kok++;
        }
        return s + "listoja: " + kok;
    }
}
