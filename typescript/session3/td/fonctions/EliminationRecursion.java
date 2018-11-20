package session3.demo.fonctions;

import java.util.function.IntUnaryOperator;

/*
 * Interface pour décrire les continuations.
 * Type inductif : k ::= vide | cons(traitement, k)
 * On se restreint à des traitements sur des entiers : 
 * il s'agit de fonctions de int dans int, de type IntUnaryOperator.
 * 
 */
interface Continuation {
	boolean estVide();
	IntUnaryOperator tete();
	Continuation reste();
}
class Vide implements Continuation {
	@Override
	public boolean estVide() {
		return true;
	}
	@Override
	public IntUnaryOperator tete() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Continuation reste() {
		throw new UnsupportedOperationException();
	}
}

class Cons implements Continuation {
	private IntUnaryOperator tete;
    private Continuation reste;
	public Cons(IntUnaryOperator tete, Continuation reste) {
		this.tete = tete;
		this.reste = reste;
	}
	@Override
	public boolean estVide() {
		return false;
	}
	@Override
	public IntUnaryOperator tete() {
		return tete;
	}
	@Override
	public Continuation reste() {
		return reste;
	}
}


public class EliminationRecursion {
	// Factorielle récursive (+ remplaçant * pour éviter les dépassements)
	//@SuppressWarnings("unused")
	private static int fact(int n){
		return n == 0 ? 1 : n + fact(n - 1);
	}
	// Factorielle avec récursion terminale
	// Elimination de la récursion non terminale en utilisant une continuation
	//@SuppressWarnings("unused")
	private static int factSansRecursionNonTerminale(int n, Continuation k){
		return n == 0 ? appliquer(k, 1) : factSansRecursionNonTerminale(n - 1, new Cons(x -> n + x, k));	
	}
	private static int appliquer(Continuation k, int v) {
		if(k.estVide())
			return v;
		return appliquer(k.reste(), k.tete().applyAsInt(v));
	}
	// Factorielle sans récursion
	// Elimination de la récursion terminale en utilisant l'itération
	private static int factSansRecursion(int n, Continuation k){
		while(n > 0){
			final int p = n; // Nécessaire pour la fermeture
			k = new Cons(x -> x + p, k);
			n = n - 1;
		}
		return appliquerSansRecursion(k, 1);
	}
	private static int appliquerSansRecursion(Continuation k, int v) {
		while(!k.estVide()){
			v = k.tete().applyAsInt(v);
			k = k.reste();
		}
		return v;
	}
	// Factorielle sans récursion et sans continuation
	// Réduction de la continuation à un traitement (x -> x * a)
	private static int factSansRecursionSansContinuation(int n, IntUnaryOperator op){
		while(n > 0){
			int a = op.applyAsInt(n);
			op = x -> x + a;
			n = n - 1;
		}
		return op.applyAsInt(1);
	}
	// Factorielle itérative classique
	private static int factIteratif(int n){
		int a = 1;
		while(n > 0){
			a = n + a;
			n = n - 1;
		}
		return a;
	}
	public static void main(String[] args) {
		int n = 5_000; // A faire varier
		System.out.println(fact(n));
		System.out.println(factSansRecursionNonTerminale(n, new Vide()));
		System.out.println(factSansRecursion(n, new Vide()));
		System.out.println(factSansRecursionSansContinuation(n, IntUnaryOperator.identity()));
		System.out.println(factIteratif(n));
	}
}
