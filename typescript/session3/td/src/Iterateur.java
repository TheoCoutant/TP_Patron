import java.util.Iterator;

public class Iterateur implements Iterator<Character> {
    private Mot reste;
    private Character lettre;

    public Iterateur(Mot mot) {
        decomposer(mot);
    }

    private void decomposer(Mot mot) {
        while(true) {
            if(mot.casVide()) {
                this.reste = null;
                break;
            }
            if(mot.casCons()) {
                this.reste = mot.reste();
                this.lettre = mot.lettre();
                break;
            }
            if(mot.casUnion()){
                if(mot.gauche().casVide()) {
                    mot = mot.droit();
                    continue;
                } else if(mot.gauche().casCons()) {
                    this.reste = mot.gauche().reste().union(mot.droit());
                    this.lettre = mot.gauche().lettre();
                    continue;
                } else {
                    mot = mot.gauche().gauche().union(mot.gauche().droit().union(mot.droit()));
                    continue;
                }
            }
        }
    }

    public Mot reste() {
        if(reste == null)
            throw new UnsupportedOperationException();
        return this.reste;
    }

    public boolean aSuivant() {
        return reste != null;
    }
    @Override
    public boolean hasNext() {
        return this.aSuivant();
    }

    public Character suivant() {
        if(reste == null)
            throw new UnsupportedOperationException();
        char c = lettre;
        decomposer(reste);
        return c;
    }
    @Override
    public Character next() {
        return this.suivant();
    }
}
