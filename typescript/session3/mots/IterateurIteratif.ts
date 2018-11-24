import { Mot } from "./Mot";

export class IterateurIteratif implements IterableIterator<string> {

    private _reste: Mot | null;
    private _caractere: string;

    constructor(mot: Mot){
        this.decomposer(mot);
    }

    private decomposer(mot: Mot) {
        while(true){
			if(mot.estVide()){
				this._reste = null;
				break;
			}
			if(mot.estPrecedeParCaractere()){
				this._reste = mot.reste();
				this._caractere = mot.caractere();
				break;
			}
			if(mot.estConcatenationMot()){
				if(mot.gauche().estVide()){
					mot = mot.droit();
					continue;
				}else if(mot.gauche().estPrecedeParCaractere()){
					this._reste = mot.gauche().reste().creerMotConcatenationIteratif(mot.droit());
					this._caractere = mot.gauche().caractere();
					break;
				}else{
					mot = mot.gauche().gauche().creerMotConcatenationIteratif(mot.gauche().droit().creerMotConcatenationIteratif(mot.droit()));
					continue;
				}
			}
		}
    }


    reste() : Mot {
        if(this._reste == null)
            throw Error("Unsupported operation");
        return this._reste;
    }

    next(): IteratorResult<string> {
        if(this._reste == null)
            return {
                done: true,
                value: "" 
            }
        var r : string = this._caractere;
        this.decomposer(this._reste);
        return {
            done: false,
            value: r
        }
    }

    [Symbol.iterator](): IterableIterator<string> {
        return this;
    }

}