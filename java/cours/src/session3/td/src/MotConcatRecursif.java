public class MotConcatRecursif implements Mot{
    private Mot gauche;
    private Mot droit;
    private int taille;

    MotConcatRecursif(Mot gauche, Mot droit) {
        this.gauche = gauche;
        this.droit = droit;
        this.taille = gauche.taille() + droit.taille();
    }

    @Override
    public Mot gauche() {
        return gauche;
    }

    @Override
    public Mot droit() {
        return droit;
    }

    @Override
    public boolean casConcatenationMot() {
        return true;
    }

    @Override
    public int taille() {
        return taille;
    }

    @Override
    public char caractere() {
        if(!gauche.estVide())
            return this.gauche.caractere();
        if(!droit.estVide())
            return this.droit.caractere();
        throw new UnsupportedOperationException();
    }

    @Override
    public Mot reste() {
        if(!gauche.estVide())
            return new MotConcatIteratif(this.gauche.reste(), this.droit);
        if(!droit.estVide())
            return this.droit.reste();
        throw new UnsupportedOperationException();
    }
}
