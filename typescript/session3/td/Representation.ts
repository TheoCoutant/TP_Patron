import { Visiteur } from "./Visiteur";

export class Representation implements Visiteur<string> {

    public casVide() : string {
        return "";
    }

    public casCons(n : string, r : string) : string {
        return n + r; // Inversion dans le cas de la récursion
    }
}