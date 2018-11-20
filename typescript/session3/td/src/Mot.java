/**
 * Mot ::= Vide | suivi(Character, Mot) | concatenation(Mot, Mot)
 */

public interface mot {
    default boolean casVide() { return false; }
    default boolean casSuivi() { return false; }
    default boolean casConcatenation() { return false; }
}
