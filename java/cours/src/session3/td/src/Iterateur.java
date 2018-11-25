import java.util.Iterator;

public class Iterateur implements Iterator<Character> {
    private Mot reste;
    private char caractere;

    public Iterateur(Mot mot) {
        decomposer(mot);
    }

    private void decomposer(Mot mot) {
        while(true) {
            if(mot.casVide()) {
                this.reste = null;
                break;
            }
            if(mot.casPrecedeParCaractere()) {
                this.reste = mot.reste();
                this.caractere = mot.caractere();
                break;
            }
            if(mot.casConcatenationMot()){
                if(mot.gauche().casVide()) {
                    mot = mot.droit();
                    continue;
                } else if(mot.gauche().casPrecedeParCaractere()) {
                    this.reste = mot.gauche().reste().creerMotConcatenationIteratif(mot.droit());
                    this.caractere = mot.gauche().caractere();
                    continue;
                } else {
                    mot = mot.gauche().gauche().creerMotConcatenationIteratif(mot.gauche().droit().creerMotConcatenationIteratif(mot.droit()));
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
            throw new UnsupportedOperationException();
        char c = caractere;
        decomposer(reste);
        return c;
    }
    @Override
    public Character next() {
        return this.suivant();
    }
}
