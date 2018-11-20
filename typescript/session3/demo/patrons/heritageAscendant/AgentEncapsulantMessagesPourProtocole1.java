package session3.demo.patrons.heritageAscendant;

import session3.demo.patrons.agregationDelegation.AgentCommuniquant;
import session3.demo.patrons.agregationDelegation.CanalOutProtocole1;

public class AgentEncapsulantMessagesPourProtocole1 extends CanalOutProtocole1 implements AgentCommuniquant {

	@Override
	public void envoyer(String msg) {
		this.emettre(msg);
	}
	

}
