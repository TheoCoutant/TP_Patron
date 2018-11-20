package session3.demo.patrons.agregationDelegation;

public class AgentEncapsulantMessages extends AgentDeleguant implements AgentCommuniquant {

	public AgentEncapsulantMessages(Canal canal) {
		super(canal);
	}

	@Override
	public void envoyer(String msg) {
		this.emettre(msg);
	}

}
