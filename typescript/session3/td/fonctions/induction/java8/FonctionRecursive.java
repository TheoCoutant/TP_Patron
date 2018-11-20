package session3.demo.fonctions.induction.java8;

import java.util.function.Function;

/*
 * Classe permettant de définir un point fixe à partir d'une définition récursive. 
 * Usage : REC((fun, a) -> exp) où exp est une expression pouvant utiliser l'argument  
 * a et des appels récursifs fun.apply(...) après une importation statique de REC.
 * 
 *  import static session2.demo.fonctions.induction.java8.FonctionRecursive.REC;
 */
public class FonctionRecursive<A, R> implements Function<A, R> {
	private Function<FonctionRecursive<A, R>, Function<A, R>> def;
	
	
	public FonctionRecursive(Function<FonctionRecursive<A, R>, Function<A, R>> def) {
		super();
		this.def = def;
	}

	@Override
	public R apply(A a) {
		return def.apply(this).apply(a);
	}
	
	static public <A, R> FonctionRecursive<A, R> REC(Function<FonctionRecursive<A, R>, Function<A, R>> def) {
		return new FonctionRecursive<>(def);
	}
	
}