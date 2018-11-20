package session3.demo.fonctions.induction.java8;

import java.util.function.BiFunction;
import java.util.function.Function;

/*
 * Classe permettant de définir un point fixe à partir d'une définition récursive. 
 * Usage : REC2((fun, a) -> exp) où exp est une expression pouvant utiliser l'argument  
 * a et des appels récursifs fun.apply(...) après une importation statique de REC2.
 * 
 *  import static session2.demo.fonctions.induction.java8.FonctionRecursive2.REC2;
 */
public class FonctionRecursive2<A, B, R> implements BiFunction<A, B, R> {
	
	private Function<FonctionRecursive2<A, B, R>, BiFunction<A, B, R>> def;
	
	
	public FonctionRecursive2(Function<FonctionRecursive2<A, B, R>, BiFunction<A, B, R>> def) {
		super();
		this.def = def;
	}

	@Override
	public R apply(A a, B b) {
		return def.apply(this).apply(a, b);
	}

	static public <A, B, R> FonctionRecursive2<A, B, R> REC2(Function<FonctionRecursive2<A, B, R>, BiFunction<A, B, R>> def) {
		return new FonctionRecursive2<>(def);
	}
}