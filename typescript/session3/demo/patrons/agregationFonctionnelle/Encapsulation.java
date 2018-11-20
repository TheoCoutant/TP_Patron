package session3.demo.patrons.agregationFonctionnelle;

import session3.demo.patrons.agregationDelegation.AgentCommuniquant;
import session3.demo.patrons.agregationDelegation.Canal;

public class Encapsulation implements Delegation {

	@Override
	public AgentCommuniquant deleguer(Canal c) {
		return new AgentCommuniquant(){

			@Override
			public void envoyer(String msg) {
				this.emettre(msg);
			}

			@Override
			public void emettre(String msg) {
				c.emettre(msg);

			}
			
		};
	}

}
