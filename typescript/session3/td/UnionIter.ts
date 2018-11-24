import { Mot } from "./Mot"

export class UnionIter extends Mot {
    private motGauche : Mot;
    private motDroit : Mot;

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
        var var_taille : number = 0;
        var motGaucheTmp = this.motGauche;
        var motDroitTmp = this.motGauche;
        while (motGaucheTmp.casVide()) {
            var_taille += 1;
            motGaucheTmp = motGaucheTmp.reste();
        }

        while (motDroitTmp.casVide()) {
            var_taille += 1;
            motDroitTmp = motDroitTmp.reste();
        }
        return var_taille;
    }

    public lettre(): string {
        var i = this.iterateur();
        return i.suivant();
    }

    public reste(): Mot {
        var i = this.iterateur();
        return i.reste();
    }
}
