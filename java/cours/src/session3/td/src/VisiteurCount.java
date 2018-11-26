public class VisiteurCount implements Visiteur<Integer>{
    private char caractereRechercher;
    private int occurence;

    public VisiteurCount(char caractereRechercher) {
        this.caractereRechercher = caractereRechercher;
        this.occurence = 0;
    }

    @Override
    public Integer casVide() {
        return occurence;
    }

    //TODO
    @Override
    public Integer casCons(char l, Integer r) {
        if (l == caractereRechercher)
            return occurence++;
        return occurence;
    }
}
