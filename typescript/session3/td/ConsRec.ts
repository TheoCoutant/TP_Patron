import { Mot } from "./Mot"

export class ConsRec extends Mot {
    private var_lettre : string;
    private var_reste : Mot;
    private var_taille : number;

    public constructor(lettre : string, reste : Mot) {
        super();
        this.var_lettre = lettre;
        this.var_reste = reste;
        this.var_taille = 1 + reste.taille();
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
        return this.var_taille;
    }
}