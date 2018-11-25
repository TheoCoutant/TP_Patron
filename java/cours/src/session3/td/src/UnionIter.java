import java.util.Iterator;

public class UnionIter implements Mot{
    private Mot gauche;
    private Mot droit;
    private int taille;

    UnionIter(Mot gauche, Mot droit) {
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

    public String toString() {
        return gauche.toString() + droit.toString();
    }

    @Override
    public int taille() {
        return taille;
    }

    @Override
    public char lettre() {
        Iterateur i = this.iterateur();
        return i.next();
    }

    @Override
    public Mot reste() {
        Iterateur i = this.iterateur();
        return i.reste();
    }
}
