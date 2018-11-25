public class VisiteurCharAt implements Visiteur<Character>{
    private int index;
    private int cpt;
    private char charTrouve;

    public VisiteurCharAt(int index) {
        this.index = index;
        cpt = 0;
    }

    @Override
    public Character casVide() {
        return 0;
    }

    @Override
    public Character casCons(char l, Character r) {
        if (this.cpt == this.index) {
            this.cpt++;
            this.charTrouve = l;
            return l;
        } else {
           this.cpt++;
           return r;
        }
    }
}
