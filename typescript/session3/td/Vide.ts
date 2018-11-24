import {Mot} from './Mot'

export class Vide extends Mot {

    public static SINGLETON = new Vide();

    private constructor() {
        super();
    }

    public casVide() : boolean {
        return true;
    }

    public taille() : number {
        return 0;
    }
}