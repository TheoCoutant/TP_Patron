public class VisiteurLastOccurence implements Visiteur<Integer>{

    private int lastOccurence;
    private char charRechercher;
    private int cpt;

    public VisiteurLastOccurence(char charRechercher) {
        this.charRechercher = charRechercher;
    }

    @Override
    public Integer casVide() {
        return 0;
    }

    @Override
    public Integer casCons(char l, Integer r) {
        if(l == this.charRechercher)
            this.lastOccurence = this.cpt;
        cpt++;
        return lastOccurence;
    }
}
