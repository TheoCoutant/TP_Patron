public class VisiteurCount implements Visiteur<Integer>{
    private char charACompter;

    public VisiteurCount(char charACompter) {
        this.charACompter = charACompter;
    }

    @Override
    public Integer casVide() {
        return 0;
    }

    //TODO
    @Override
    public Integer casCons(char l, Integer r) {
        if (l == charACompter)
            return 1;
        return 0;
    }
}
