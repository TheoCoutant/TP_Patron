public class Union implements Mot {
    Mot motGauche, motDroit;

    public Union (Mot motGauche, Mot motDroit) {
        this.motGauche = motGauche;
        this.motDroit = motDroit;
    }

    public boolean casUnion() {
        return true;
    }

    @Override
    public Mot gauche() {
        return motGauche;
    }

    @Override
    public Mot droit() {
        return motDroit;
    }

    @Override
    public int taille() {
        return motGauche.taille() + motDroit.taille();
    }

    public String toString() {
        return motGauche.toString() + motDroit.toString();
    }
}
