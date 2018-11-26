public class ConsIter implements Mot{
    private char lettre;
    private Mot reste;
    private int taille;

    ConsIter(char lettre, Mot reste) {
        this.lettre = lettre;
        this.reste = reste;
        this.taille =  reste.taille() + 1;
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
        return taille;
    }

    @Override
    public boolean casCons() {
        return true;
    }

    public String toString() {
        return "" + lettre + reste.toString();
    }
}
