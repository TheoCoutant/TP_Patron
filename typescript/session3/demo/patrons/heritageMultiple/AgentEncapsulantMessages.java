package session3.demo.patrons.heritageMultiple;

import session3.demo.patrons.agregationDelegation.AgentCommuniquant;

public interface AgentEncapsulantMessages extends AgentCommuniquant {
	@Override
	default
	public void envoyer(String msg) {
		this.emettre(msg);
	}
}
