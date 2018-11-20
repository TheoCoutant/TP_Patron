package session3.demo.patrons.adaptateur;

import session3.demo.patrons.agregationDelegation.Agent;

public class AgentAdaptateur implements NouvelAgent{

	private Agent agent;

	@Override
	public void envoyer(String enTete, String msg) {
		// Adaptation du message
		this.agent.envoyer(enTete + "--" + msg);		
	}

}
