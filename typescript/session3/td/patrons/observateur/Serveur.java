package session3.demo.patrons.observateur;

// Classe concrète produisant des observations
// - agrégeant le serveur générique gestionnaire des observations
public class Serveur implements Observable<Serveur, String, Client> {
	private ServeurObservations<Serveur, String, Client> gestionnaire;
	private int compteur;

	public Serveur() {
		super();
		this.gestionnaire = new ServeurObservations<Serveur, String, Client>(this);
		compteur = 0;
	}
	@Override
	public void ajouterObservateur(Client c){
		gestionnaire.ajouterObservateur(c);
	}
	@Override
	public void notifierObservateurs(String e){
		gestionnaire.notifierObservateurs(e);
	}

	@Override
	public void informerObservateur(Client client) {
		gestionnaire.informerObservateur(client);
	}

	public void mettreAJour(){
		compteur++;
		this.notifierObservateurs("mise à jour " + compteur);
	}
}
