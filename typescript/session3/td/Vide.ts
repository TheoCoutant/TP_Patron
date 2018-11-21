import { Mot } from "./Mot"

export class Vide extends Mot {
    public static SINGLETON : Vide;

    public casVide() : boolean {
        return true;
    }

    public toString() : string {
        return "";
    }

    public taille() : number {
        return 0;
    }
}
