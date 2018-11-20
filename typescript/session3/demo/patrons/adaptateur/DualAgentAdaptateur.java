package session3.demo.patrons.adaptateur;

public class DualAgentAdaptateur implements NouvelAgent, Injectable<Adaptation> {

	private Adaptation strategie;

	@Override
	public void envoyer(String enTete, String msg) {
		// Adaptation du message
		this.strategie.appliquer(enTete, msg);
	}

	@Override
	public void injecter(Adaptation x) {
		strategie = x;
	}

	
	
}
