import { Mot } from "./Mot"
import { Vide } from "./Vide"

export class Test {
    public static main(args : String[]) {
        var mot1 : Mot = Vide.SINGLETON.cons('z');
        var mot2 : Mot = Vide.SINGLETON;

        for(var i = 0; i < 1000; i++) {
            mot2 = mot2.union(mot1);
        }

        console.log(mot2);
    }
}
