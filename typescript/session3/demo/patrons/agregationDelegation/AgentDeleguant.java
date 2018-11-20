package session3.demo.patrons.agregationDelegation;

public abstract class AgentDeleguant implements AgentCommuniquant {

	private Canal canal;

	public AgentDeleguant(Canal canal) {
		super();
		this.canal = canal;
	}

	@Override
	abstract public void envoyer(String msg);

	@Override
	public void emettre(String msg) {
		this.canal.emettre(msg);

	}

}
