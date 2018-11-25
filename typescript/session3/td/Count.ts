import { Visiteur } from "./Visiteur";

export class Count implements Visiteur<number> {

    private caractereRecherche: string;
    private occurence: number = 0;

    constructor(caractereRecherche: string){
        this.caractereRecherche = caractereRecherche;
    }

    public casVide(): number {
        return this.occurence;
    }

    public casCons(str: string, r: number): number {
        if(str == this.caractereRecherche)
            this.occurence++;
        return this.occurence;
    }

}