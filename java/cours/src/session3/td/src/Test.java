public class Test {
    public static void main(String[] args) {
        Mot mot1 = MotVideRecursif.SINGLETON.creerMotPrecedeCararactereRecursif('z');
        Mot mot2 = MotVideRecursif.SINGLETON.creerMotPrecedeCararactereRecursif('B');
        Mot vide = MotVideIteratif.SINGLETON;

        testRecursifLong(1000, mot1);

        System.out.println(mot2);
    }

    private static void testRecursifLong(int nbIteration, Mot mot) {
        Mot str = MotVideRecursif.SINGLETON;

        StringBuilder strBuilder = new StringBuilder();

        for(int i = 0; i < nbIteration; i++) {
            str = str.creerMotConcatenationRecursif(mot);
        }

        while(!str.estVide()) {
            try {
                strBuilder.append(str.caractere());
                str = str.reste();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
