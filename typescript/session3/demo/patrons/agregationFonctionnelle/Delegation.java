package session3.demo.patrons.agregationFonctionnelle;

import session3.demo.patrons.agregationDelegation.AgentCommuniquant;
import session3.demo.patrons.agregationDelegation.Canal;

@FunctionalInterface
public interface Delegation {

	public AgentCommuniquant deleguer(Canal c);

}
