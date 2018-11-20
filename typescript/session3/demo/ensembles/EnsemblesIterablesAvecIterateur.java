package session3.demo.ensembles;

import java.util.Iterator;

/*
 * Ensemble ::= Vide | Cons(Element, Ensemble) | Union(Ensemble, Ensemble)
 */

interface Ensemble3 extends Iterable<Integer> {
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
	default Ensemble3 reste() {
		throw new UnsupportedOperationException();
	}
	default Ensemble3 gauche(){
		throw new UnsupportedOperationException();
	}
	default Ensemble3 droit(){
		throw new UnsupportedOperationException();
	}
	// Constructeurs
	default Ensemble3 vide(){
		return Vide3.SINGLETON; 
	}
	default Ensemble3 cons(int n){
		return new Cons3(n, this);
	}
	default Ensemble3 union(Ensemble3 ens){
		return new Union3(this, ens);
	}
	// Autres accesseurs
	int taille();
	default boolean estVide(){
		return this.taille() == 0;
	}
	default Iterateur3 iterateur(){
		return new Iterateur3(this);
	}
	default 	
	public Iterator<Integer> iterator() {
		return this.iterateur();
	}

}


/*
 * Les itérateurs sont supposés mutables, conformément à l'interface Java.
 */
class Iterateur3 implements java.util.Iterator<Integer>{
	private Ensemble3 reste;
	private int element;
	
	public Iterateur3(Ensemble3 ens){
		decomposer(ens);
	}
	private void decomposer(Ensemble3 ens){
		while(true){
			if(ens.estVide()){
				this.reste = null;
				break;
			}
			if(ens.casCons()){
				this.reste = ens.reste();
				this.element = ens.element();
				break;
			}
			if(ens.casUnion()){
				if(ens.gauche().estVide()){
					ens = ens.droit();
					continue;
				}else if(ens.gauche().casCons()){
					this.reste = ens.gauche().reste().union(ens.droit());
					this.element = ens.gauche().element();
					break;
				}else{
					ens = ens.gauche().gauche().union(ens.gauche().droit().union(ens.droit()));
					continue;
				}
			}
		}
	}

	public boolean aSuivant(){
		return reste != null;
	}
	public int suivant(){
		if (reste == null)
			throw new UnsupportedOperationException();
		int r = element;
		decomposer(reste);
		return r;
	}
	public Ensemble3 reste(){
		if (reste == null)
			throw new UnsupportedOperationException();
		return this.reste;
	}
	@Override
	public boolean hasNext() {
		return this.aSuivant();
	}
	@Override
	public Integer next() {
		return this.suivant();
	}
}


enum Vide3 implements Ensemble3 {
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

class Cons3 implements Ensemble3 {

	private int element;
	private Ensemble3 reste;
	private int taille;
	
	public Cons3(int i, Ensemble3 ens) {
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
	public int element() {
		return this.element;
	}

	@Override
	public Ensemble3 reste() {
		return this.reste;
	}
}

class Union3 implements Ensemble3 {

	private Ensemble3 gauche;
	private Ensemble3 droit;
	private int taille;
	
	public Union3(Ensemble3 g, Ensemble3 d) {
		this.gauche = g;
		this.droit = d;
		this.taille = g.taille() + d.taille();
	}

	@Override
	public boolean casUnion() {
		return true;
	}
	
	@Override
	public int taille() {
		return this.taille;
	}

	@Override
	public int element() {
		Iterateur3 i = this.iterateur();
		return i.suivant();
	}
	
	@Override
	public Ensemble3 reste() {
		Iterateur3 i = this.iterateur();
		return i.reste();
	}

	@Override
	public Ensemble3 gauche() {
		return this.gauche;
	}

	@Override
	public Ensemble3 droit() {
		return this.droit;
	}

}


public class EnsemblesIterablesAvecIterateur {

	public static void main(String[] args) {
		Ensemble3 a = Vide3.SINGLETON.cons(1);
		Ensemble3 b = Vide3.SINGLETON;
		for(int i = 0; i < 10000000; i++){
			b = b.union(a);
		}
		int s = 0;
		for(int x : b){
			s = s + x;
		}
		/*
   		Iterateur3 i = b.iterateur(); 
		while(i.aSuivant()){
			s = s + i.suivant();
		}
		*/
		System.out.println(s);
	}

}
