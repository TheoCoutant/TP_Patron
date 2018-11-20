package session3.demo.patrons.observateur;

public class Test {

	public static void main(String[] args) {
		Client a = new Client(ModeCommunication.PULL, "A");
		Client b = new Client(ModeCommunication.PUSH, "B");
		Client c = new Client(ModeCommunication.PULL, "C");
		Serveur s = new Serveur();
		s.ajouterObservateur(a);
		s.ajouterObservateur(b);
		s.ajouterObservateur(c);
		s.mettreAJour();
		s.mettreAJour();
		s.informerObservateur(a);
		s.informerObservateur(b);
		s.informerObservateur(c);
	}

}
