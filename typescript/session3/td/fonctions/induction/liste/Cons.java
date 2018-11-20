package session3.demo.fonctions.induction.liste;

public class Cons<E> implements Liste<E> {

	private E tete;
	private Liste<E> reste;

	public Cons(E tete, Liste<E> reste) {
		this.tete = tete;
		this.reste = reste;
	}
	@Override
	public boolean estCons(){
		return true;
	}
	@Override
	public E tete() {
		return this.tete;
	}

	@Override
	public Liste<E> reste() {
		return this.reste;
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
		return 1 + this.reste().taille();
	}

}
