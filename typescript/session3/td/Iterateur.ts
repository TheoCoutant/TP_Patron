import { Mot } from "./Mot"
import { Vide } from "./Vide"

export class Iterateur implements Iterator<String> {
    private var_reste : Mot;
    private var_lettre : string;

    public constructor(mot : Mot) {
        this.decomposer(mot);
    }

    private decomposer(mot : Mot) {
        while(true) {
            if(mot.casVide()) {
                this.var_reste = Vide.SINGLETON;
                break;
            }
            if(mot.casCons()) {
                this.var_reste = mot.reste();
                this.var_lettre = mot.lettre();
                break;
            }
            if(mot.casUnion()){
                if(mot.gauche().casVide()) {
                    mot = mot.droit();
                    continue;
                } else if(mot.gauche().casCons()) {
                    this.var_reste = mot.gauche().reste().union(mot.droit());
                    this.var_lettre = mot.gauche().lettre();
                    continue;
                } else {
                    mot = mot.gauche().gauche().union(mot.gauche().droit().union(mot.droit()));
                    continue;
                }
            }
        }
    }

    public reste() : Mot {
        if (this.var_reste.casVide()) {
            throw new Error();
        }
        return this.var_reste;
    }

    public aSuivant() : boolean {
        return this.var_reste.casVide();
    }
    
    public suivant() : string {
        if(this.var_reste.casVide())
            throw new Error();
        var c : string = this.var_lettre;
        this.decomposer(this.var_reste);
        return c;
    }

    public hasNext() : boolean {
        return this.aSuivant();
    }

    public next() : IteratorResult<string> {
        return {
            done : false,
            value : this.suivant()
        }
    }
}
