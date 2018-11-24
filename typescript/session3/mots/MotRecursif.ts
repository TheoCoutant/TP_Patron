import {Mot} from './Mot'
import { MotConcatIteratif } from './MotIteratif';

export class MotCaractereRecursif extends Mot {

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

export class MotVideRecursif extends Mot {

    public static SINGLETON = new MotVideRecursif();

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

export class MotConcatRecursif extends Mot {

    private _gauche: Mot;
    private _droit: Mot;
    private _taille: number;

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

    caractere(): string {
        if(!this._gauche.estVide())
			return this._gauche.caractere();
		if(!this._droit.estVide())
			return this._droit.caractere();
        throw Error("Unsupported operation");
    }

    reste(): Mot {
        if(!this._gauche.estVide())
			return new MotConcatIteratif(this._gauche.reste(), this._droit);
		if(!this._droit.estVide())
			return this._droit.reste();
        throw Error("Unsupported operation");
    }

}