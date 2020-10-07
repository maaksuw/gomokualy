
package apu;

public class Lauta {
    
    private char[][] lauta;
    
    public Lauta(char[][] lauta) {
        this.lauta = lauta;
    }

    public char[][] getLauta() {
        return lauta;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(!(o instanceof Lauta)) return false;
        Lauta l = (Lauta) o;
        if(l.getLauta().length != this.lauta.length) return false;
        int n = l.getLauta().length;
        char[][] llauta = l.getLauta();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(llauta[i][j] != this.lauta[i][j]) return false;
            }
        }
        return true;
    }
}
