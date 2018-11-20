public class Test {
    public static void main(String[] args) {
        Mot mot1 = Vide.SINGLETON.cons('z');
        Mot mot2 = Vide.SINGLETON;

        for(int i = 0; i < 1000; i++) {
            mot2 = mot2.union(mot1);
        }

        System.out.println(mot2);
    }
}
