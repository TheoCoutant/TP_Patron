package session3.demo.fonctions.induction.mot;

import java.util.function.Predicate;

/*
 * univers : ensemble des chaînes de caractères
 * système d'inférence :
 * - si la chaîne c a pour longueur 5, alors  c est dans M.
 *   |c| = 5
 *   -------
 *   c : M
 *  - si c1 et c2 appartiennent à M, alors c1.c2 appartient à M.
 *  (c1 : M)     (c2 : M)
 *  ---------------------
 *      (c1.c2 : M)
 * (M = ensemble des chaînes ayant pour longueur un multiple non nul de 5)
 */
// Représentation des preuves du système d'inférence
public interface Mot {
	public static Predicate<String> predicatBase = (s -> s.length() == 5);

	// Sélecteurs
	boolean estBasique();
	boolean estConcatenation();

	// Destructeurs
	String base();
	Mot gauche();
	Mot droite();

	// Fabriques (appelés constructeurs dans un cadre fonctionnel)
	Mot creerBase(String s);
	Mot creerConcatenation(Mot gauche, Mot droit);
	
	// Conclusion de la preuve
	String conclusion();
	
	// Egalité définissant l'équivalence
	default boolean estEgal(Mot m){
		return this.conclusion().equals(m.conclusion());
	}
	// Représentant de la classe d'équivalence
	default Mot representant(){
		if(this.estBasique())
			return this;
		if(this.gauche().estBasique())
			return this.creerConcatenation(this.gauche(), this.droite().representant());
		Mot gg = this.gauche().gauche();
		Mot gd = this.gauche().droite();
		Mot d = this.droite();
		return this.creerConcatenation(gg, this.creerConcatenation(gd, d)).representant();
	}
}
