import { Vide } from "./Vide"
import { Cons } from "./Cons"
import { Union } from "./Union"
import { Iterateur } from "./Iterateur"

/**
 * Mot ::= Vide | suivi(Character, Mot) | concatenation(Mot, Mot)
 */

export abstract class Mot {

    public static FILTRAGERA : T;

    public casVide() : boolean { 
        return false; 
    }

    public casCons() : boolean {
        return false; 
    }
    
    public casUnion() : boolean { 
        return false; 
    }

    // Destructeurs
	public lettre() : string {
		throw new Error();
    }
    
	public reste() : Mot {
		throw new Error();
    }
    
	public gauche() : Mot{
		throw new Error();
    }
    
	public droit() : Mot{
		throw new Error();
    }

    // Constructeurs
	public vide() : Mot {
		return Vide.SINGLETON; 
    }
    
	public cons(n : string) : Mot {
		return new Cons(n, this);
    }
    
	public union(ens : Mot){
		return new Union(this, ens);
	}
    
    // Autres accesseurs
    public abstract taille() : number;
    
	public estVide() : boolean {
		return this.taille() == 0;
    }
    
	public iterateur() : Iterateur {
		return new Iterateur(this);
	}
	 	
	public iterator() : Iterator<string> {
		return this.iterateur();
	}

	// Visiteur itératif (programmé récursivement puis itérativement)
	public accueilRecursif(v : Visiteur<T>) : <T> T {
		if (this.estVide()) {
			return v.casVide();
		}
		return v.casCons(this.lettre(), 
				this.reste().accueilRecursif(v));
	}

	public accueil(v : Visiteur<T>) : <T> T {
		T r = v.casVide();
		for (int x : this) {
			r = v.casCons(x, r);
		}
		return r;
	}

	// Visiteur itératif avec des lambda-expressions
	public accueilRecursif(casVide : Supplier<T>, casCons : BiFunction<Integer, T, T>) : <T> T {
		if (this.estVide()) {
			return casVide.get();
		}
		return casCons.apply(this.element(), this.reste().accueilRecursif(casVide, casCons));
	}

	public accueil(casVide : Supplier<T>, casCons : BiFunction<Integer, T, T>) :  <T> T {
		T r = casVide.get();
		for (int x : this) {
			r = casCons.apply(x, r);
		}
		return r;
	}

	// Visiteur récursif primitif analogue du filtrage par cas (pattern matching),
	// programmé récursivement
	public filtrageRécursif(casVide : Supplier<T>, casCons : BiFunction<Integer, Ensemble4, T>) : <T> T {
		if (this.estVide()) {
			return casVide.get();
		}
		return casCons.apply(this.lettre(), this.reste());
	}

	// Visiteur récursif primitif analogue du filtrage par cas (pattern matching),
	// programmé itérativement
	public filtrage(casVide : Supplier<T>, casCons : BiFunction<Integer, Ensemble4, Function<T, T>>) :  <T> T {
		T r = casVide.get();
		Ensemble4 arg = this.vide();
		Ensemble4 courant = this;
		while (!courant.estVide()) {
			int e = courant.lettre();
			r = casCons.apply(e, arg).apply(r);
			arg = arg.cons(e);
			courant = courant.reste();
		}
		return r;
	}

}

