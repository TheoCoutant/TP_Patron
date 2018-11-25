import { Mot } from "./Mot"

export class UnionIter extends Mot {
    private motGauche : Mot;
    private motDroit : Mot;
    private var_taille : number;

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

    public lettre(): string {
        var i = this.iterateur();
        return i.next().value;
    }

    public reste(): Mot {
        var i = this.iterateur();
        return i.reste();
    }
}
