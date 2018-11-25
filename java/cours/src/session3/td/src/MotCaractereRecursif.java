public class MotCaractereRecursif implements Mot {
    private char caractere;
    private Mot reste;

    MotCaractereRecursif(char caractere, Mot reste) {
        this.caractere = caractere;
        this.reste = reste;
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
        return 1 + reste.taille();
    }

    @Override
    public boolean casPrecedeParCaractere() {
        return true;
    }
}
