package session3.demo.ensembles;

/*
 * Ensemble ::= Vide | Cons(Element, Ensemble) | Union(Ensemble, Ensemble)
 */
interface Ensemble2 {
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
	default Ensemble2 reste() {
		throw new UnsupportedOperationException();
	}
	default Ensemble2 gauche(){
		throw new UnsupportedOperationException();
	}
	default Ensemble2 droit(){
		throw new UnsupportedOperationException();
	}
	// Constructeurs
	default Ensemble2 vide(){
		return Vide2.SINGLETON; 
	}
	default Ensemble2 cons(int n){
		return new Cons2(n, this);
	}
	default Ensemble2 union(Ensemble2 ens){
		return new Union2(this, ens);
	}
	// Autres accesseurs
	int taille();
	default boolean estVide(){
		return this.taille() == 0;
	}

}

enum Vide2 implements Ensemble2 {
	SINGLETON;

	@Override
	public int taille() {
		return 0;
	}

	@Override
	public boolean casVide() {
		return true;
	}

}

class Cons2 implements Ensemble2 {

	private int element;
	private Ensemble2 reste;
	private int taille;
	
	public Cons2(int i, Ensemble2 ens) {
		this.element = i;
		this.reste = ens;
		this.taille = 1 + ens.taille();
	}

	@Override
	public int taille() {
		return this.taille;
	}

	@Override
	public boolean casCons() {
		return true;
	}

	@Override
	public int element() {
		return this.element;
	}

	@Override
	public Ensemble2 reste() {
		return this.reste;
	}

}

class Union2 implements Ensemble2 {

	private Ensemble2 gauche;
	private Ensemble2 droit;
	private int taille;
	
	// Mémorisation
	private int element;
	private Ensemble2 reste;

	public Union2(Ensemble2 g, Ensemble2 d) {
		this.gauche = g;
		this.droit = d;
		this.taille = g.taille() + d.taille();
	}

	@Override
	public int taille() {
		return this.taille;
	}

	@Override
	public boolean casUnion() {
		return true;
	}
	
	private void decomposer(){
		// Precondition : !this.estVide() donc !this.casVide()
		Ensemble2 courant = this; // Invariant : !courant.estVide()
		while(true){
			if(courant.casCons()){ // Cons
				this.reste = courant.reste();
				this.element = courant.element();
				return;
			}else{ // Union
				if(courant.gauche().estVide()){
					courant = courant.droit();
				}else if(courant.gauche().casCons()){
					this.reste = courant.gauche().reste().union(courant.droit());
					this.element = courant.gauche().element();
					return;
				}else{
					courant = courant.gauche().gauche().union(courant.gauche().droit().union(courant.droit()));
				}
			}
		}
	}
	
	@Override
	public int element() {
		if(this.estVide()){
			throw new UnsupportedOperationException();
		}
		if(this.reste != null){
			return this.element;
		}
		decomposer();
		return this.element;
	}
	
	@Override
	public Ensemble2 reste() {
		if(this.estVide()){
			throw new UnsupportedOperationException();
		}
		if(this.reste != null){
			return this.reste;
		}
		decomposer();
		return this.reste;
	}

	@Override
	public Ensemble2 gauche() {
		return this.gauche;
	}

	@Override
	public Ensemble2 droit() {
		return this.droit;
	}

}


public class EnsemblesIterables {

	public static void main(String[] args) {
		Ensemble2 a = Vide2.SINGLETON.cons(1);
		Ensemble2 b = Vide2.SINGLETON;
		for(int i = 0; i < 10000000; i++){
			b = b.union(a);
		}
		int s = 0;
		while(!b.estVide()){
			s = s + b.element();
			b = b.reste(); 
		}
		System.out.println(s);
	}

}
