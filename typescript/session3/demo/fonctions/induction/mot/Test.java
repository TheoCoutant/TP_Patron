package session3.demo.fonctions.induction.mot;


public class Test {

	public static void main(String[] args) {
		Mot fabrique = new Base("aaaaa");
		Mot b1 = fabrique.creerBase("base1");
		Mot b2 = fabrique.creerBase("base2");
		Mot b3 = fabrique.creerBase("base3");
		Mot c1 = fabrique.creerConcatenation(fabrique.creerConcatenation(b1, b2), b3);
		Mot c2 = fabrique.creerConcatenation(b1, fabrique.creerConcatenation(b2, b3));
		System.out.println("true ? " + c1.estEgal(c2));
		System.out.println("true ? " + c1.equals(c2));
		System.out.println("base1base2base3 ? " + c1.conclusion());
		System.out.println("((base1.base2).base3) ? " + c1);
		System.out.println("(base1.(base2.base3)) ? " + c1.representant());
		System.out.println("(base1.(base2.base3)) ? " + c2);
		System.out.println("(base1.(base2.base3)) ? " + c2.representant());
		System.out.println("base1base2base3 ? " + c2.conclusion());
		System.out.println("15 ? " + taille(c1));
		System.out.println("15 ? " + taille(c2));
		System.out.println("base1 ? " + miroir(b1));
		System.out.println("((base3.base2).base1) ? " + miroir(c1));
		System.out.println("(base3.(base2.base1)) ? " + miroir(c1).representant());
		System.out.println("base3base2base1 ? " + miroir(c1).conclusion());
		System.out.println("(base1.(base2.(base3.(base1.(base2.base3))))) ? " + fabrique.creerConcatenation(c1, c2).representant());
	}

	// Exemples de fonctions récursives
	// 1. Taille d'un mot
	private static int taille(Mot m){
		m = m.representant(); // Garantie du passage au quotient
		// Récursion : cas de base
		if(m.estBasique()) 
			return m.base().length();
		// Récursion : cas construit 
		return taille(m.gauche()) + taille(m.droite());
	}
	// 2. Miroir (unité : mots de cinq caractères)
	private static Mot miroir(Mot m){
		m = m.representant(); // Garantie du passage au quotient
		// Récursion : cas de base
		if(m.estBasique()){
			return m;
		}
		// Récursion : cas construit 
		return m.creerConcatenation(miroir(m.droite()), m.gauche());
	}
	
}
