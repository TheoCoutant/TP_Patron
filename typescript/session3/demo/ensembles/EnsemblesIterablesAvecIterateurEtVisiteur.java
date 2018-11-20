package session3.demo.ensembles;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import session3.demo.fonctions.induction.java8.FonctionRecursive;

/*
 * Ensemble ::= Vide | Cons(Element, Ensemble) | Union(Ensemble, Ensemble)
 */

interface Ensemble4 extends Iterable<Integer> {
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
	default Ensemble4 reste() {
		throw new UnsupportedOperationException();
	}
	default Ensemble4 gauche(){
		throw new UnsupportedOperationException();
	}
	default Ensemble4 droit(){
		throw new UnsupportedOperationException();
	}
	// Constructeurs
	default Ensemble4 vide(){
		return Vide4.SINGLETON; 
	}
	default Ensemble4 cons(int n){
		return new Cons4(n, this);
	}
	default Ensemble4 union(Ensemble4 ens){
		return new Union4(this, ens);
	}
	// Autres accesseurs
	int taille();
	default boolean estVide(){
		return this.taille() == 0;
	}
	default Iterateur4 iterateur(){
		return new Iterateur4(this);
	}
	default 	
	public Iterator<Integer> iterator() {
		return this.iterateur();
	}

	// Visiteur itératif (programmé récursivement puis itérativement)
	default <T> T accueilRecursif(Visiteur4<T> v) {
		if (this.estVide()) {
			return v.casVide();
		}
		return v.casCons(this.element(), 
				this.reste().accueilRecursif(v));
	}

	default <T> T accueil(Visiteur4<T> v) {
		T r = v.casVide();
		for (int x : this) {
			r = v.casCons(x, r);
		}
		return r;
	}

	// Visiteur itératif avec des lambda-expressions
	default <T> T accueilRecursif(Supplier<T> casVide, BiFunction<Integer, T, T> casCons) {
		if (this.estVide()) {
			return casVide.get();
		}
		return casCons.apply(this.element(), this.reste().accueilRecursif(casVide, casCons));
	}

	default <T> T accueil(Supplier<T> casVide, BiFunction<Integer, T, T> casCons) {
		T r = casVide.get();
		for (int x : this) {
			r = casCons.apply(x, r);
		}
		return r;
	}

	// Visiteur récursif primitif analogue du filtrage par cas (pattern matching),
	// programmé récursivement
	default <T> T filtrageRécursif(Supplier<T> casVide, BiFunction<Integer, Ensemble4, T> casCons) {
		if (this.estVide()) {
			return casVide.get();
		}
		return casCons.apply(this.element(), this.reste());
	}

	// Visiteur récursif primitif analogue du filtrage par cas (pattern matching),
	// programmé itérativement
	default <T> T filtrage(Supplier<T> casVide, BiFunction<Integer, Ensemble4, Function<T, T>> casCons) {
		T r = casVide.get();
		Ensemble4 arg = this.vide();
		Ensemble4 courant = this;
		while (!courant.estVide()) {
			int e = courant.element();
			r = casCons.apply(e, arg).apply(r);
			arg = arg.cons(e);
			courant = courant.reste();
		}
		return r;
	}
}

/*
 * Visiteur générique (le paramètre T désigne le type de ce qui est calculé) Le
 * visiteur définit une structure d'algèbre associée à l'itérateur. Cf. fold de
 * Haskell.
 */
interface Visiteur4<T> {
	T casVide();

	T casCons(int n, T r);
}

class CalculCardinal implements Visiteur4<Integer> {

	@Override
	public Integer casVide() {
		return 0;
	}

	@Override
	public Integer casCons(int n, Integer r) {
		return r + 1;
	}

}

class Representation implements Visiteur4<String> {

	@Override
	public String casVide() {
		return "";
	}

	@Override
	public String casCons(int n, String r) {
		return r + Integer.toString(n) + " ;"; // Inversion dans le cas de la récursion
	}

}

/*
 * Les itérateurs sont supposés mutables, conforméménet à l'interface Java.
 */
class Iterateur4 implements java.util.Iterator<Integer> {
	private Ensemble4 reste;
	private int element;

	public Iterateur4(Ensemble4 ens) {
		decomposer(ens);
	}

	private void decomposer(Ensemble4 ens) {
		while (true) {
			if (ens.estVide()) {
				this.reste = null;
				break;
			}
			if (ens.casCons()) {
				this.reste = ens.reste();
				this.element = ens.element();
				break;
			}
			if (ens.casUnion()) {
				if (ens.gauche().estVide()) {
					ens = ens.droit();
					continue;
				} else if (ens.gauche().casCons()) {
					this.reste = ens.gauche().reste().union(ens.droit());
					this.element = ens.gauche().element();
					break;
				} else {
					ens = ens.gauche().gauche().union(ens.gauche().droit().union(ens.droit()));
					continue;
				}
			}
		}
	}

	public boolean aSuivant() {
		return reste != null;
	}

	public int suivant() {
		if (reste == null)
			throw new UnsupportedOperationException();
		int r = element;
		decomposer(reste);
		return r;
	}

	public Ensemble4 reste() {
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

enum Vide4 implements Ensemble4 {
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

class Cons4 implements Ensemble4 {

	private int element;
	private Ensemble4 reste;
	private int taille;

	public Cons4(int i, Ensemble4 ens) {
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
	public Ensemble4 reste() {
		return this.reste;
	}

}

class Union4 implements Ensemble4 {

	private Ensemble4 gauche;
	private Ensemble4 droit;
	private int taille;

	public Union4(Ensemble4 g, Ensemble4 d) {
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

	@Override
	public int element() {
		Iterateur4 i = this.iterateur();
		return i.suivant();
	}

	@Override
	public Ensemble4 reste() {
		Iterateur4 i = this.iterateur();
		return i.reste();
	}

	@Override
	public Ensemble4 gauche() {
		return this.gauche;
	}

	@Override
	public Ensemble4 droit() {
		return this.droit;
	}

}

public class EnsemblesIterablesAvecIterateurEtVisiteur {

	private static int tailleRec(Ensemble4 ens) {
		return ens.filtrageRécursif(() -> 0, (elt, reste) -> 1 + tailleRec(reste));
	}

	private static int taille(Ensemble4 ens) {
		return ens.filtrage(() -> 0, (elt, reste) -> (tailleReste -> 1 + tailleReste));
	}

	public static void main(String[] args) {

		Ensemble4 a = Vide4.SINGLETON.cons(1);
		Ensemble4 b = Vide4.SINGLETON;
		for (int i = 0; i < 10000000; i++) {
			b = b.union(a);
		}
		int s = 0;
		while (!b.estVide()) {
			s = s + b.element();
			b = b.reste();
		}
		System.out.println(s);
		b = Vide4.SINGLETON;
		for (int i = 0; i < 100; i++) {
			b = b.union(Vide4.SINGLETON.cons(i));
		}
		Representation r = new Representation();
		System.out.println("Ens 0, ..., 99 : (" + b.accueil(r) + ")");
		System.out.println("Ens 0, ..., 99 : (" + b.accueilRecursif(r) + ")");

		CalculCardinal c = new CalculCardinal();
		System.out.println("Taille ens (100) : " + b.accueil(c));
		System.out.println("Taille ens (100) : " + b.accueilRecursif(c));

		System.out.println("Taille ens (100) : " + b.accueil(() -> 0, (elt, card) -> card + 1));
		System.out.println("Taille ens (100) : " + b.accueilRecursif(() -> 0, (elt, card) -> card + 1));
		System.out.println("Taille ens (100) : " + tailleRec(b));
		System.out.println("Taille ens (100) : " + taille(b));
		
		Function<Ensemble4, Integer> defTaille = new FonctionRecursive<>(
				fun -> (ens -> ens.filtrageRécursif(() -> 0, (elt, reste) -> 1 + fun.apply(reste))));

		System.out.println("Taille ens (100) : " + defTaille.apply(b));

	}

}
