import { Mot } from "./Mot"

export class ConsIter extends Mot {
    var_lettre : string;
    var_reste : Mot;
    var_taille : number;

    public constructor(lettre : string, reste : Mot) {
        super();
        this.var_lettre = lettre;
        this.var_reste = reste;
    }

    public casCons() : boolean {
        return true;
    }

    public lettre() : string {
        return this.var_lettre;
    }

    public reste() : Mot {
        return this.var_reste;
    }

    public taille() : number {
        while(this.casVide()) {
            this.var_taille++;
        }
        return this.var_taille;
    }
}