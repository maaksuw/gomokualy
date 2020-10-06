
package apu;

import logiikka.Tilanne;

/**
 * Yksinkertainen hakemisto merkkijono-kokonaisluku -pareille.
 * Hakemistoon voi lisätä alkion ja sieltä voi hakea arvon avaimen perusteella.
 * Hakemiston voi tyhjentää.
 * @see Tilanne
 */

public class Hakemisto {
    
    private Lista<Tilanne>[] hakemisto;
    private int koko;
    private int kerroin;
    private int alkioita;
    
    public Hakemisto(){
        koko = 300007;
        kerroin = 31;
        alkioita = 0;
        hakemisto = new Lista[koko];
    }
    
    /**
     * Lisää merkkijono-kokonaisluku parin hakemistoon.
     * Jos tällä avaimella on löytyy jo arvo hakemistosta, vanha arvo korvataan uudella. 
     * @param avain
     * @param arvo 
     */
    public void lisaa(String avain, Integer arvo) {
        int hajautusarvo = hajautusFunktio(avain);
        Tilanne t = new Tilanne(avain, arvo);
        boolean uusiAvain = true;
        if(hakemisto[hajautusarvo] == null) hakemisto[hajautusarvo] = new Lista<>();
        Lista<Tilanne> l = hakemisto[hajautusarvo];
        for(int i = 0; i < l.pituus(); i++){
            Tilanne tt = l.hae(i);
            if(tt.getLauta().equals(avain)){
                uusiAvain = false;
                tt.setArvo(arvo);
                break;
            }
        }
        if(uusiAvain) {
            l.lisaa(t);
            alkioita++;
        }
    }
    
    private int hajautusFunktio(String avain) {
        int x = 0;
        int n = avain.length();
        for(int i = 0; i < n; i++){
            x = (x*kerroin + avain.charAt(i))%koko;
            
        }
        return x;
    }
    
    public Integer hae(String avain) {
        int hajautusarvo = hajautusFunktio(avain);
        Lista<Tilanne> l = hakemisto[hajautusarvo];
        if(l == null) return null;
        for(int i = 0; i < l.pituus(); i++){
            Tilanne t = l.hae(i);
            if(t.getLauta().equals(avain)) return t.getArvo();
        }
        return null;
    }
    
    public boolean onkoAvainta(String avain) {
        int hajautusarvo = hajautusFunktio(avain);
        Lista<Tilanne> l = hakemisto[hajautusarvo];
        if(l == null) return false;
        for(int i = 0; i < l.pituus(); i++){
            Tilanne t = l.hae(i);
            if(t.getLauta().equals(avain)) return true;
        }
        return false;
    }
    
    public int alkioita() {
        return alkioita;
    }
    
    public void tyhjenna() {
        for(int i = 0; i < koko; i++){
            hakemisto[i] = null;
        }
        alkioita = 0;
    }

    public int pahinTormays() {
        int max = 0;
        for(int i = 0; i < koko; i++){
            if(hakemisto[i] != null) max = Math.max(max, hakemisto[i].pituus());
        }
        return max;
    }
}
