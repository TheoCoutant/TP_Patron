public class ConsRec implements Mot {
    private char lettre;
    private Mot reste;

    ConsRec(char lettre, Mot reste) {
        this.lettre = lettre;
        this.reste = reste;
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
        return 1 + reste.taille();
    }

    @Override
    public boolean casCons() {
        return true;
    }

    public String toString() {
        return "" + lettre + reste.toString();
    }
}
