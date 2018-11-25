public class MotConcatIteratif implements Mot{
    private Mot gauche;
    private Mot droit;
    private int taille;

    private char caractere;
    private Mot reste;

    MotConcatIteratif(Mot gauche, Mot droit) {
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

    private void decomposer(Mot mot) {
        while(true) {
            if(mot.casVide()) {
                this.reste = null;
                break;
            }
            if(mot.casPrecedeParCaractere()) {
                this.reste = mot.reste();
                this.caractere = mot.caractere();
                break;
            }
            if(mot.casConcatenationMot()){
                if(mot.gauche().casVide()) {
                    mot = mot.droit();
                    continue;
                } else if(mot.gauche().casPrecedeParCaractere()) {
                    this.reste = mot.gauche().reste().creerMotConcatenationIteratif(mot.droit());
                    this.caractere = mot.gauche().caractere();
                    continue;
                } else {
                    mot = mot.gauche().gauche().creerMotConcatenationIteratif(mot.gauche().droit().creerMotConcatenationIteratif(mot.droit()));
                    continue;
                }
            }
        }
    }

    @Override
    public char caractere() {
        return caractere;
    }

    @Override
    public Mot reste() {
        return reste;
    }
}
