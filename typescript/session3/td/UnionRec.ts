import { UnionIter } from './UnionIter';
import { ConsIter } from './ConsIter';
import { Mot } from "./Mot"

export class UnionRec extends Mot {
    public motGauche : Mot;
    public motDroit : Mot;

    public constructor(motGauche : Mot, motDroit : Mot) {
        super();
        this.motGauche = motGauche;
        this.motDroit = motDroit;
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
        return this.motGauche.taille() + this.motDroit.taille();
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
        if(!this.motGauche.estVide())
			return new UnionIter(this.motGauche.reste(), this.motDroit);
		if(!this.motDroit.estVide())
			return this.motDroit.reste();
        throw Error("Unsupported operation");
    }
}
