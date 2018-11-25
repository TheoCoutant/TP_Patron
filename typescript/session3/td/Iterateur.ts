import { Mot } from "./Mot"

export class Iterateur implements IterableIterator<string> {
    private var_reste : Mot | null;
    private var_lettre : string;

    public constructor(mot : Mot) {
        this.decomposer(mot);
    }

    private decomposer(mot: Mot) {
        while(true){
			if(mot.estVide()){
				this.var_reste = null;
				break;
			}
			if(mot.casCons()){
				this.var_reste = mot.reste();
				this.var_lettre = mot.lettre();
				break;
			}
			if(mot.casUnion()){
				if(mot.gauche().estVide()){
					mot = mot.droit();
					continue;
				}else if(mot.gauche().casCons()){
					this.var_reste = mot.gauche().reste().unionIter(mot.droit());
					this.var_lettre = mot.gauche().lettre();
					break;
				}else{
					mot = mot.gauche().gauche().unionIter(mot.gauche().droit().unionIter(mot.droit()));
					continue;
				}
			}
		}
    }

    public reste() : Mot {
        if (this.var_reste == null) {
            throw Error("Unsupported operation");
        }
        return this.var_reste;
    }

    public next() : IteratorResult<string> {
		if (this.var_reste == null) {
			return {
                done: true,
                value: "" 
            }
        }
		var r = this.var_lettre;
		this.decomposer(this.var_reste);
        return {
            done: false,
            value: r
        }
    }

    [Symbol.iterator](): IterableIterator<string> {
        return this;
    }
}
