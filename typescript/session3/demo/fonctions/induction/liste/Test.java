package session3.demo.fonctions.induction.liste;

public class Test {

	public static void main(String[] args) {
		Liste<String> fab = new Vide<>(); 
		Liste<String> vide = fab.creerVide();
		Liste<String> l1 = fab.creerCons("l1", vide);
		Liste<String> l2 = fab.creerCons("l2", l1);
		System.out.println("2 ? " + l2.taille());
		System.out.println("2 ? " + taille(l2));
	}
	// Fonction récursive
	private static <E> int taille(Liste<E> l){
		if(l.estVide())
			return 0;
		return 1 + taille(l.reste());
	}
}
