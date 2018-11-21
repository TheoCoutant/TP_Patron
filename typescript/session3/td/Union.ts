import { Mot } from "./Mot"

export class Union extends Mot {
    motGauche : Mot;
    motDroit : Mot;

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

    public toString() : String {
        return this.motGauche.toString() + this.motDroit.toString();
    }
}
