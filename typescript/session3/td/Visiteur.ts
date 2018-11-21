export interface Visiteur<T> {
    casVide() : T;
    casCons(l : string, r : T) : T;
}

