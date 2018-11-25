public class UnionRec implements Mot{
    private Mot gauche;
    private Mot droit;
    private int taille;

    UnionRec(Mot gauche, Mot droit) {
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
    public boolean casUnion() {
        return true;
    }

    @Override
    public int taille() {
        return taille;
    }

    public String toString() {
        return gauche.toString() + droit.toString();
    }

    @Override
    public char lettre() {
        if(!gauche.estVide())
            return this.gauche.lettre();
        if(!droit.estVide())
            return this.droit.lettre();
        throw new UnsupportedOperationException();
    }

    @Override
    public Mot reste() {
        if(!gauche.estVide())
            return new UnionRec(this.gauche.reste(), this.droit);
        if(!droit.estVide())
            return this.droit.reste();
        throw new UnsupportedOperationException();
    }
}
