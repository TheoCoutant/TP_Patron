package session3.demo.patrons.agregationFonctionnelle;

import session3.demo.patrons.agregationDelegation.AgentCommuniquant;
import session3.demo.patrons.agregationDelegation.Canal;

public class Decoupage implements Delegation {

	@Override
	public AgentCommuniquant deleguer(Canal c) {
		return new AgentCommuniquant(){

			@Override
			public void envoyer(String msg) {
				int TAILLE = 5;
				StringBuilder m = new StringBuilder(msg);
				int q = msg.length() / TAILLE;
				int r = msg.length() % TAILLE;
				for (int j = 0; j < q; j++) {
					this.emettre(m.substring(j * TAILLE, (j + 1) * TAILLE));
				}
				this.emettre(m.substring(q * TAILLE, q * TAILLE + r));
			}

			@Override
			public void emettre(String msg) {
				c.emettre(msg);

			}
			
		};
	}

}
