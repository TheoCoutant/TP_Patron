package session3.demo.patrons.heritageDescendant;

import session3.demo.patrons.agregationDelegation.AgentCommuniquant;

public abstract class AgentEncapsulantMessages implements AgentCommuniquant{
	@Override
	public void envoyer(String msg) {
		this.emettre(msg);
	}
}
