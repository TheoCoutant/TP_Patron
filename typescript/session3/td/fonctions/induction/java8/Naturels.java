package session3.demo.fonctions.induction.java8;

import static session3.demo.fonctions.induction.java8.FonctionRecursive.REC;
import static session3.demo.fonctions.induction.java8.FonctionRecursive2.REC2;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

interface FiltreNat<T> {
	<R> R filtrage(Supplier<R> casZero, Function<T, R> casSucc);
	<R> R filtrageIter(Supplier<R> casZero, Function<T, Function<R, R>> casSucc);
	public static <T extends FiltreNat<T> & FabriqueNat<T>, R> R filtrageIter(T n, Supplier<R> casZero, Function<T, Function<R, R>> casSucc){
		R r = casZero.get();
		T p = n.zero();
		while(!n.estNul()){
			r = casSucc.apply(p).apply(r);
			p = p.succ();
			n = n.pred();
		}
		return r;
	}
	default public boolean estNul() {
		return filtrage(() -> true, pred -> false);
	}
	default public T pred() {
		return filtrage(() -> {throw new UnsupportedOperationException();}, pred -> pred);
	}
}
interface  FabriqueNat<T> {
	T zero();
	T succ();
}

interface ServiceNat<T> {
	T add(T x);
	T mult(T x);
	String rep();
}

interface ServiceNatRec<T extends FiltreNat<T> & ServiceNat<T> & FabriqueNat<T>> 
	extends FiltreNat<T>, ServiceNat<T> {
	@Override
	default T add(T x){
		return filtrage(
				() -> x, 
				pred -> pred.add(x).succ());
	}
	@Override
	default T mult(T x){
		return filtrage(
				() -> x.zero(), 
				pred -> pred.mult(x).add(x));
	}
	@Override
	default String rep() {
		return filtrage(
				() -> "0", 
				pred -> "S" + pred.rep());
				
	}
}

class NatInductif implements FiltreNat<NatInductif>, ServiceNatRec<NatInductif>, FabriqueNat<NatInductif>{
	private NatInductif(){}

	@Override
	public <R> R filtrage(Supplier<R> casZero, Function<NatInductif, R> casSucc) {
		throw new UnsupportedOperationException();
	}
	@Override
	public <R> R filtrageIter(Supplier<R> casZero, Function<NatInductif, Function<R, R>> casSucc) {
		return FiltreNat.filtrageIter(this, casZero, casSucc);
	}

	
	@Override
	public NatInductif zero() {
		return new NatInductif(){
			@Override
			public <R> R filtrage(Supplier<R> casZero, Function<NatInductif, R> casSucc) {
				return casZero.get();
			}
		};
	}
	@Override
	public NatInductif succ() {
		return new NatInductif(){
			@Override
			public <R> R filtrage(Supplier<R> casZero, Function<NatInductif, R> casSucc) {
				return casSucc.apply(NatInductif.this);
			}
		};
	}
	public static FabriqueNat<NatInductif> FAB = new NatInductif();
	
}

public class Naturels {
	
	public static NatInductif factorielle(NatInductif n){
		return n.filtrageIter(
				() -> n.zero().succ(),
				pred -> (factPred -> pred.succ().mult(factPred)));
	}
	
	public static String rep(NatInductif n){
		return n.filtrage(
				() -> "0", 
				pred -> "S" + rep(pred));
	}
	
	public static int val(NatInductif n){
		return n.filtrageIter(
				() -> 0,
				pred -> (valPred -> valPred + 1));
	}
	
	public static void main(String[] args) {
		NatInductif zero = NatInductif.FAB.zero();
		NatInductif un = zero.succ();
		BiFunction<NatInductif, NatInductif, NatInductif> add = 
				REC2(fun -> ((n, p) -> n.filtrage(
						() -> p, 
						pred -> fun.apply(pred, p).succ())));
		NatInductif deux = add.apply(un, un);
		System.out.println("2 : " + rep(deux));
		System.out.println("4 : " + rep(add.apply(deux, deux)));
		System.out.println("4 : " + rep(deux.add(deux)));
		System.out.println("4 : " + rep(deux.mult(deux)));
		System.out.println("6 : " + deux.mult(deux).add(deux).rep());
		
		Function<Integer, Integer> fact1 = 
				REC(fun -> ((Integer n) -> 
					(n == 0) ? 1 : fun.apply(n - 1) * n));
		System.out.println("120 : " + fact1.apply(5));							
		
		Function<NatInductif, NatInductif> fact2 = 
				REC(fun -> (n -> n.filtrage(
						() -> n.zero().succ(), 
						pred -> fun.apply(pred).mult(pred.succ()))));
		System.out.println("120 : " + val(fact2.apply(deux.add(deux).succ())));
		System.out.println("24 : " + val(factorielle(deux.add(deux))));
	}
}
