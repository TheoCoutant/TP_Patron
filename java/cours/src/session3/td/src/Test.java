public class Test {
    public static void main(String[] args) {
        int nbIter = 10;

        Mot mot1 = Vide.SINGLETON.consRec('z');
        Mot mot2 = Vide.SINGLETON.consRec('B');

        testRecursif(nbIter, mot1);
        testIteratif(nbIter, mot1);

        System.out.println(createMotUnion(mot1, mot2));

        testCount(createMotUnion(mot1, mot2));
        testCharAt(createMotUnion(mot1, mot2));
        testLastOccurence(createMotUnion(mot1, mot2));


    }

    private static Mot createMotUnion(Mot mot1, Mot mot2) {
        Mot mot = mot1.unionRec(mot2);
        mot = mot.unionRec(mot1);
        mot = mot.unionRec(mot2);
        mot = mot.unionRec(mot2);
        return mot;
    }

    private static void testCount(Mot mot) {
        int i = mot.acceptRecursif(new VisiteurCount('B'));
        System.out.println("Occurence de B: " + i);
        i = mot.accept(new VisiteurCount('B'));
        System.out.println("Occurence de B: " + i);    }

    private static void testCharAt(Mot mot) {
        char c = mot.acceptRecursif(new VisiteurCharAt(2));
        System.out.println("CharAt(2): " + c);
        c = mot.accept(new VisiteurCharAt(2));
        System.out.println("CharAt(2): " + c);
    }

    private static void testLastOccurence(Mot mot) {
        int i = mot.acceptRecursif(new VisiteurLastOccurence('z'));
        System.out.println("lastOccurence(z): " + i);
        i = mot.accept(new VisiteurLastOccurence('z'));
        System.out.println("lastOccurence(z): " + i);
    }

    private static void testRecursif(int nbIteration, Mot mot) {
        Mot str = Vide.SINGLETON;

        StringBuilder strBuilder = new StringBuilder();

        for(int i = 0; i < nbIteration; i++) {
            str = str.unionRec(mot);
        }

        while(!str.estVide()) {
            try {
                strBuilder.append(str.lettre());
                str = str.reste();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        if (strBuilder.toString().length() == nbIteration)
            System.out.println("test validé");
        else
            System.out.println("test fail");
    }

    private static void testIteratif(int nbIteration, Mot mot) {
        Mot str = Vide.SINGLETON;

        StringBuilder strBuilder = new StringBuilder();

        for(int i = 0; i < nbIteration; i++) {
            str = str.unionIter(mot);
        }

        while(!str.estVide()) {
            try {
                strBuilder.append(str.lettre());
                str = str.reste();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        if (strBuilder.toString().length() == nbIteration)
            System.out.println("test validé");
        else
            System.out.println("test fail");
    }
}
