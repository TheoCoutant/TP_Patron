public class MotCaractereIteratif implements Mot{
    private char caractere;
    private Mot reste;
    private int taille;

    MotCaractereIteratif(char caractere, Mot reste) {
        this.caractere = caractere;
        this.reste = reste;
        this.taille =  reste.taille() + 1;
    }

    @Override
    public char caractere() {
        return caractere;
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
    public boolean casPrecedeParCaractere() {
        return true;
    }
}
