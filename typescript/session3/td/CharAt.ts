import { Visiteur } from "./Visiteur"

export class CharAt implements Visiteur<string> {

    private index : number;
    private cpt : number = 0;
    private charTrouve : string;

    public constructor(index : number) {
        this.index = index;
    }

    public casVide() : string {
        return "";
    }

    public casCons(n : string, r : string) : string {
        if (this.cpt == this.index) {
            this.cpt++;
            this.charTrouve = n;
            return n;
        } else {
            this.cpt++;
            return r;
        }
    }
}