import java.util.Iterator;

public class Iterateur implements Iterator<Character> {
    private Mot reste;
    private char caractere;

    public Iterateur(Mot mot) {
        decomposer(mot);
    }

    private void decomposer(Mot mot) {
        while(true) {
            if(mot.estVide()) {
                this.reste = null;
                break;
            }
            if(mot.casCons()) {
                this.reste = mot.reste();
                this.caractere = mot.lettre();
                break;
            }
            if(mot.casUnion()){
                if(mot.gauche().casVide()) {
                    mot = mot.droit();
                    continue;
                } else if(mot.gauche().casCons()) {
                    this.reste = mot.gauche().reste().unionIter(mot.droit());
                    this.caractere = mot.gauche().lettre();
                    break;
                } else {
                    mot = mot.gauche().gauche().unionIter(mot.gauche().droit().unionIter(mot.droit()));
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

    public char suivant() {
        if(reste == null)
            return 0;
        char c = caractere;
        decomposer(reste);
        return c;
    }
    @Override
    public Character next() {
        return this.suivant();
    }
}
