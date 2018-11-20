package session3.demo.patrons.heritageAscendant;

import session3.demo.patrons.agregationDelegation.AgentCommuniquant;
import session3.demo.patrons.agregationDelegation.CanalOutProtocole2;

public class AgentEncapsulantMessagesPourProtocole2 extends CanalOutProtocole2 implements AgentCommuniquant {

	@Override
	public void envoyer(String msg) {
		this.emettre(msg);
	}
	

}
