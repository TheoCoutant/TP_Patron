

export interface VisiteurMot<T> {
    estMotVide() : T;

    estPrecedeParCaractere(str : string, r : T) : T;
}

export abstract class Mot implements Iterable<string> {

    estMotVide(): boolean {
        return false;
    }

    abstract taille(): number;

    estPrecedeParCaractere(): boolean {
        return false;
    }

    estConcatenationMot(): boolean {
        return false;
    }

    estVide(): boolean {
        return this.taille() == 0;
    }

    creerMotVideIteratif(): Mot {
        return MotVideIteratif.SINGLETON;
    }

    creerMotPrecedeCaractereIteratif(c: string): Mot {
        return new MotCaractereIteratif(c, this);
    }

    creerMotConcatenationIteratif(droit: Mot){
        return new MotConcatIteratif(this, droit);
    }

    creerMotVideRecursif(): Mot {
        return MotVideRecursif.SINGLETON;
    }

    creerMotPrecedeCaractereRecursif(c: string): Mot {
        return new MotCaractereRecursif(c, this);
    }

    creerMotConcatenationRecursif(droit: Mot){
        return new MotConcatRecursif(this, droit);
    }

    caractere(): string {
        throw Error("Unsupported operation");
    }

    reste(): Mot {
        throw Error("Unsupported operation");
    }

    gauche(): Mot {
        throw Error("Unsupported operation");
    }

    droit(): Mot {
        throw Error("Unsupported operation");
    }

    iterateur(): IterateurIteratif {
        return new IterateurIteratif(this);
    }

    [Symbol.iterator](): Iterator<string> {
        return this.iterateur();
    }

    accept<T>(v: VisiteurMot<T>): T {
        var r : T = v.estMotVide();
        for(var x of this){
            r = v.estPrecedeParCaractere(x, r);
        }
        return r;
    }

    acceptRecursif<T>(v: VisiteurMot<T>): T {
        if(this.estVide()){
            return v.estMotVide();
        }
        return v.estPrecedeParCaractere(this.caractere(), this.reste().acceptRecursif(v));
    }

}

import { IterateurIteratif } from './IterateurIteratif';
import { MotCaractereRecursif, MotVideRecursif, MotConcatRecursif } from './MotRecursif';
import { MotVideIteratif, MotCaractereIteratif, MotConcatIteratif } from './MotIteratif';