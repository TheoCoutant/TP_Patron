package session3.demo.patrons.decorateur;

import session3.demo.patrons.agregationDelegation.Agent;

public class AgentDecoupantMessages implements Agent {
	// Agent décoré
	private Agent agent;
	@Override
	public void envoyer(String msg) {
		int TAILLE = 5;
		StringBuilder m = new StringBuilder(msg);
		int q = msg.length() / TAILLE;
		int r = msg.length() % TAILLE;
		for(int j = 0; j < q; j++){
			this.agent.envoyer(m.substring(j * TAILLE, (j+1) * TAILLE));
		}
		this.agent.envoyer(m.substring(q * TAILLE, q * TAILLE + r));
	}
}
