package session3.demo.ensembles;

/*
 * Ensemble ::= Vide | Cons(Element, Ensemble) | Union(Ensemble, Ensemble)
 */
// Usage intensif de méthodes par défaut
interface Ensemble1 {
	// Sélecteurs
	default boolean casVide(){
		return false;
	}
	default boolean casCons(){
		return false;
	}
	default boolean casUnion(){
		return false;
	}
	// Destructeurs
	default int element(){
		throw new UnsupportedOperationException();
	}
	default Ensemble1 reste() {
		throw new UnsupportedOperationException();
	}
	default Ensemble1 gauche(){
		throw new UnsupportedOperationException();
	}
	default Ensemble1 droit(){
		throw new UnsupportedOperationException();
	}
	// Constructeurs
	default Ensemble1 vide(){
		return Vide1.SINGLETON; 
	}
	default Ensemble1 cons(int n){
		return new Cons1(n, this);
	}
	default Ensemble1 union(Ensemble1 ens){
		return new Union1(this, ens);
	}
	// Autres accesseurs
	int taille();
	default boolean estVide(){
		return this.taille() == 0;
	}

}

enum Vide1 implements Ensemble1 {
	SINGLETON;

	@Override
	public boolean casVide() {
		return true;
	}

	@Override
	public int taille() {
		return 0;
	}

}

class Cons1 implements Ensemble1 {
	private int taille;
	private int element;
	private Ensemble1 reste;

	public Cons1(int i, Ensemble1 ens) {
		this.element = i;
		this.reste = ens;
		this.taille = 1 + ens.taille();
	}
	
	@Override
	public boolean casCons() {
		return true;
	}


	@Override
	public int taille() {
		return this.taille;
	}

	@Override
	public int element() {
		return this.element;
	}

	@Override
	public Ensemble1 reste() {
		return this.reste;
	}


	
}

class Union1 implements Ensemble1 {
	private int taille;
	private Ensemble1 gauche;
	private Ensemble1 droit;

	public Union1(Ensemble1 g, Ensemble1 d) {
		this.gauche = g;
		this.droit = d;
		this.taille = g.taille() + d.taille();
	}

	public boolean casUnion(){
		return true;
	}
	
	@Override
	public int taille() {
		return this.taille;
	}

	@Override
	public int element() {
		if(!this.gauche.estVide())
			return this.gauche.element();
		if(!this.droit.estVide())
			return this.droit.element();
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble1 reste() {
		if(!this.gauche.estVide())
			return new Union1(this.gauche.reste(), this.droit);
		if(!this.droit.estVide())
			return this.droit.reste();
		throw new UnsupportedOperationException();
	}

	
}
public class EnsemblesNonIterables {

	public static void main(String[] args) {
		Ensemble1 a = Vide1.SINGLETON.cons(1);
		Ensemble1 b = Vide1.SINGLETON;
		for(int i = 0; i < 1000; i++){
			b = b.union(a);
		}
		while(!b.estVide()){
			System.out.println(b.element());
			b = b.reste(); // La pile explose !
		}
	}

}
