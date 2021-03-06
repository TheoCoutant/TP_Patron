export abstract class Mot implements Iterable<string> {

	// Selecteurs
    public casVide() : boolean { 
        return false;
    }

    public casCons() : boolean {
        return false; 
    }
    
    public casUnion() : boolean { 
        return false; 
    }

    // Projecteurs
	public lettre() : string {
		throw Error("Unsupported operation");
    }
    
	public reste() : Mot {
		throw Error("Unsupported operation");
    }
    
	public gauche() : Mot{
		throw Error("Unsupported operation");
    }
    
	public droit() : Mot{
		throw Error("Unsupported operation");
    }

    // Fabriques
	public vide() : Mot {
		return Vide.SINGLETON; 
    }
    
	public consRec(n : string) : Mot {
		return new ConsRec(n, this);
	}
	
	public consIter(n : string) : Mot {
		return new ConsIter(n, this);
    }
    
	public unionRec(ens : Mot){
		return new UnionRec(this, ens);
	}

	public unionIter(ens : Mot){
		return new UnionIter(this, ens);
	}
    
    // Autres accesseurs
    public abstract taille() : number;
    
	public estVide() : boolean {
		return this.taille() == 0;
    }
    
	public iterateur() : Iterateur {
		return new Iterateur(this);
	}

	[Symbol.iterator](): Iterator<string> {
		return this.iterateur();
	}

	public accept<T>(v : Visiteur<T>) : T {
		var r : T = v.casVide();
		for(var x of this) {
			r = v.casCons(x, r);
		}
		return r;
	}

	public acceptRecursif<T>(v: Visiteur<T>): T {
        if(this.casVide()){
            return v.casVide();
		}
        return v.casCons(this.lettre(), this.reste().acceptRecursif(v));
	}

	public filtrage<T>(casVide: () => T, casCons : (string, Mot) => ((T) => T)): T {
        var r: T = casVide();
        var arg: Mot = this.vide();
        var courant: Mot = this;
        while (!courant.estVide()) {
            var e: string = courant.lettre();
            r = casCons(e, arg)(r);
            arg = arg.consIter(e);
            courant = courant.reste();
        }
        return r;
	}

    public filtrageRecursif<T> (casVide: () => T, casCons : (string, Mot) => T): T {
        if(this.estVide()){
            return casVide();
        }
        return casCons(this.lettre(), this.reste());
	}

	public representation(): string {
        return this.filtrageRecursif(
            () => "",
            (lettre, reste) => lettre + this.reste().representation()
        );
	}
}

import { Vide } from "./Vide"
import { ConsRec } from "./ConsRec"
import { ConsIter } from "./ConsIter"
import { UnionRec } from "./UnionRec"
import { UnionIter } from "./UnionIter"
import { Iterateur } from "./Iterateur"
import { Visiteur } from "./Visiteur"

