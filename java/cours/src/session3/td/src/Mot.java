import java.util.Iterator;

/**
 * Mot ::= Viide | suivi(Character, Mot) | concatenation(Mot, Mot)
 */

public interface Mot extends Iterable<Character> {
    // Sélecteurs
    default boolean casVide() { return false; }
    default boolean casPrecedeParCaractere() { return false; }
    default boolean casConcatenationMot() { return false; }
    // Fabriques
    default Mot creerMotVideIteratif() { return MotVideIteratif.SINGLETON; }
    default Mot creerMotPrecedeCararactereIteratif(char c) { return new MotCaractereIteratif(c, this);}
    default Mot creerMotConcatenationIteratif(Mot motAAjouter) {return new MotConcatIteratif(this, motAAjouter);}
    default Mot creerMotVideRecursif() { return MotVideRecursif.SINGLETON; }
    default Mot creerMotPrecedeCararactereRecursif(char c) { return new MotCaractereRecursif(c, this);}
    default Mot creerMotConcatenationRecursif(Mot motAAjouter) {return new MotConcatRecursif(this, motAAjouter);}
    // Projecteurs
    default char caractere() {	throw new UnsupportedOperationException(); }
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

    default <T> T acceptRecursif(VisiteurMot<T> v) {
        if (this.estVide())
            return v.estVide();
        return v.estPrecedeParCaractere(this.caractere(), this.reste().acceptRecursif(v));
    }
}

