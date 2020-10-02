
package apu;

public class Hakemisto {
    
    private Lista[] hakemisto;
    private int koko;
    private int kerroin;
    
    public Hakemisto(){
        koko = 100019;
        kerroin = 31;
        hakemisto = new Lista[koko];
    }
    
    public void lisaa(String avain, long arvo) {
        int hajautusarvo = hajautusFunktio(avain);
        Tilanne t = new Tilanne(avain, arvo);
        if(hakemisto[hajautusarvo] == null){
            hakemisto[hajautusarvo] = new Lista();
            hakemisto[hajautusarvo].lisaa(t);
        } else {
            hakemisto[hajautusarvo].lisaa(t);
        }
    }
    
    public long hae(String avain) {
        int hajautusarvo = hajautusFunktio(avain);
        if(hakemisto[hajautusarvo] == null) return 1010101010;
        for(int i = 0; i < hakemisto[hajautusarvo].pituus(); i++){
            Tilanne t = (Tilanne) hakemisto[hajautusarvo].hae(i);
            if(t.getLauta().equals(avain)) return t.getArvo();
        }
        return 1010101010;
    }
    
    public int hajautusFunktio(String c) {
        int h = 0;
        for(int i = 0; i < c.length(); i++){
            h = (h*kerroin + c.charAt(i))%koko;
        }
        return h;
    }
}
