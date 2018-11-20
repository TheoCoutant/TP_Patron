package session3.demo.fonctions.induction.liste;
/*
 * liste ::= Vide | Cons(e, liste)
 */
public interface Liste<E> {
	// Sélecteurs
	default boolean estVide(){
		return !this.estCons();
	}
	default boolean estCons(){
		return !this.estVide();
	}
	// Destructeurs
	E tete();
	Liste<E> reste();
	// Fabriques (constructeurs)
	Liste<E> creerVide();
	Liste<E> creerCons(E t, Liste<E> r);
	// Patron interpréteur (interprétation triviale)
	int taille();
}
