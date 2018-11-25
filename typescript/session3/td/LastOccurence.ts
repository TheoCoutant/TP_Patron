import { Visiteur } from "./Visiteur";

export class LastOccurence implements Visiteur<number> {

    private lastOccurence: number;
    private charRechercher : string;
    private cpt : number = 0;

    constructor(charRecherche: string) {
        this.charRechercher = charRecherche;
    }

    casVide(): number {
        return 0;
    }

    casCons(str: string, r: number): number {
        if(str == this.charRechercher) {
            this.lastOccurence = this.cpt;
        }
        this.cpt++;
        return this.lastOccurence;
    }
}