public enum Vide implements Mot {
    SINGLETON;

    public boolean casVide() {
        return true;
    }

    public String toString() {
        return "";
    }

    public int taille() {
        return 0;
    }
}
