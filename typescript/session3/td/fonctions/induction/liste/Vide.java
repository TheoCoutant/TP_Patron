package session3.demo.fonctions.induction.liste;

public class Vide<E> implements Liste<E> {
	
	@Override
	public boolean estVide() {
		return true;
	}

	@Override
	public E tete() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Liste<E> reste() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Liste<E> creerVide() {
		return new Vide<>();
	}

	@Override
	public Liste<E> creerCons(E t, Liste<E> r) {
		return new Cons<>(t, r);
	}

	@Override
	public int taille() {
		return 0;
	}

}
