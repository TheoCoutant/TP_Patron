interface Visiteur<T> {
    T casVide();
    T casCons(char l, T r);
}

