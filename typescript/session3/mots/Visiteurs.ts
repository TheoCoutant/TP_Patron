import {VisiteurMot} from './Mot'

export class CharAt implements VisiteurMot<string> {

    private index: number;
    private cpt: number = 0;

    constructor(index: number){
        this.index = index;
    }

    estMotVide(): string {
        return "";
    }

    estPrecedeParCaractere(str: string, r: string): string {
        this.cpt++;
        if(this.cpt == this.index)
            return str;
        return r;
    }

}

export class Count implements VisiteurMot<number> {

    private caractereRecherche: string;
    private occurence: number = 0;

    constructor(caractereRecherche: string){
        this.caractereRecherche = caractereRecherche;
    }

    estMotVide(): number {
        return this.occurence;
    }

    estPrecedeParCaractere(str: string, r: number): number {
        if(str == this.caractereRecherche)
            this.occurence++;
        return this.occurence;
    }

}

export class Length implements VisiteurMot<number> {

    estMotVide(): number {
        return 0;
    }

    estPrecedeParCaractere(str: string, r: number): number {
        return r+1;
    }
}