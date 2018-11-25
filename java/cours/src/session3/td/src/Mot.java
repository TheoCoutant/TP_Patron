import java.util.Iterator;

/**
 * Mot ::= Viide | suivi(Character, Mot) | concatenation(Mot, Mot)
 */

public interface Mot extends Iterable<Character> {
    // Sélecteurs
    default boolean casVide() { return false; }
    default boolean casCons() { return false; }
    default boolean casUnion() { return false; }
    // Fabriques
    default Mot vide() { return Vide.SINGLETON;   }
    default Mot consIter(char c) { return new ConsIter(c, this);}
    default Mot unionIter(Mot motAAjouter) {return new UnionIter(this, motAAjouter);}
    default Mot consRec(char c) { return new ConsRec(c, this);}
    default Mot unionRec(Mot motAAjouter) {return new UnionRec(this, motAAjouter);}
    // Projecteurs
    default char lettre() {	throw new UnsupportedOperationException(); }
    default Mot reste() { throw new UnsupportedOperationException(); }
    default Mot gauche() { throw new UnsupportedOperationException(); }
    default Mot droit() { throw new UnsupportedOperationException(); }

    String toString();
    int taille();
    default boolean estVide() {
        return this.taille() == 0;
    }

    default Iterateur iterateur() { return new Iterateur(this);}
    default Iterator<Character> iterator() { return this.iterateur();}

    default <T> T acceptRecursif(Visiteur<T> v) {
        if (this.estVide())
            return v.casVide();
        return v.casCons(this.lettre(), this.reste().acceptRecursif(v));
    }

    default <T> T accept(Visiteur<T> v) {
        T r = v.casVide();
        for(Character c : this) {
            r = v.casCons(c, r);
        }
        return r;
    }
}

