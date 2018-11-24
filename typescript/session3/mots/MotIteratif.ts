import {Mot} from './Mot'
import { IterateurIteratif } from './IterateurIteratif';

export class MotCaractereIteratif extends Mot {

    private _caractere: string;
    private _reste: Mot;
    private _taille: number;

    constructor(caractere: string, reste: Mot){
        super();
        this._caractere = caractere;
        this._reste = reste;
        this._taille = 1 + reste.taille();
    }

    caractere(): string{
        return this._caractere;
    }

    reste(): Mot{
        return this._reste;
    }

    taille(): number {
        return this._taille;
    }

    estPrecedeParCaractere(): boolean{
        return true;
    }

}

export class MotVideIteratif extends Mot {

    public static SINGLETON = new MotVideIteratif();

    private constructor(){
        super();
    }

    taille(): number {
        return 0;
    }

    estMotVide(): boolean{
        return true;
    }
}

export class MotConcatIteratif extends Mot {

    private _gauche: Mot;
    private _droit: Mot;
    private _taille: number;

    private _caractere: string;
    private _reste: Mot | null;

    constructor(gauche: Mot, droit: Mot){
        super();
        this._gauche = gauche;
        this._droit = droit;
        this._taille = gauche.taille() + droit.taille();
    }

    gauche(): Mot {
        return this._gauche;
    }

    droit(): Mot {
        return this._droit;
    }

    estConcatenationMot(): boolean {
        return true;
    }

    taille(): number {
        return this._taille;
    }

    decomposer(mot: Mot) {
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

    caractere(): string {
        var i = this.iterateur();
        return i.next().value;
    }

    reste(): Mot {
        var i = this.iterateur();
        return i.reste();
    }
}