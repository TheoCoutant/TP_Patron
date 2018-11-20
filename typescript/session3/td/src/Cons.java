public class Cons implements Mot {
    char lettre;
    Mot reste;

    public Cons(Character c, Mot mot) {
        this.lettre = c;
        this.reste = mot;
    }

    public boolean casCons() {
        return true;
    }

    @Override
    public char lettre() {
        return lettre;
    }

    @Override
    public Mot reste() {
        return reste;
    }

    @Override
    public int taille() {
        return reste.taille() + 1;
    }

    public String toString() {
        return this.lettre + reste.toString();
    }
}