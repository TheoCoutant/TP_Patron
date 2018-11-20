public class VisiteurTaille implements Visiteur<Integer> {

    @Override
    public Integer casVide() {
        return 0;
    }

    @Override
    public Integer casCons(char l, Integer r) {
        return 1 + r;
    }
}
