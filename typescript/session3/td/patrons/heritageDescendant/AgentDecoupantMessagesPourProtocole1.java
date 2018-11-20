package session3.demo.patrons.heritageDescendant;

import session3.demo.patrons.agregationDelegation.AgentCommuniquant;

public class AgentDecoupantMessagesPourProtocole1 extends AgentDecoupantMessages implements AgentCommuniquant{

	@Override
	public void emettre(String msg) {
		System.out.println("protocole 1 : " + msg);
	}

}
