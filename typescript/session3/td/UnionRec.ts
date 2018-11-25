import { UnionIter } from './UnionIter';
import { ConsIter } from './ConsIter';
import { Mot } from "./Mot"
import { Iterateur } from './Iterateur';

export class UnionRec extends Mot {
    public motGauche : Mot;
    public motDroit : Mot;
    public var_taille : number;

    public constructor(motGauche : Mot, motDroit : Mot) {
        super();
        this.motGauche = motGauche;
        this.motDroit = motDroit;
        this.var_taille = motGauche.taille() + motDroit.taille();
    }

    public casUnion() : boolean {
        return true;
    }

    public gauche() : Mot {
        return this.motGauche;
    }

    public droit() : Mot {
        return this.motDroit;
    }

    public taille() : number {
        return this.var_taille;
    }

    public lettre() : string {
        if(!this.motGauche.estVide()) {
			return this.motGauche.lettre();
        } else if(!this.motDroit.estVide()) {
            return this.motDroit.lettre();
        } else {
            throw Error("Unsupported operation");
        }	
    }

    public reste(): Mot {
        if(!this.motGauche.estVide()) {
			return new UnionIter(this.motGauche.reste(), this.motDroit);
        } else if(!this.motDroit.estVide()) {
			return this.motDroit.reste();
        } else {
            throw Error("Unsupported operation");
        }
    }
}
