package session3.demo.patrons.heritageAscendant;

import session3.demo.patrons.agregationDelegation.Agent;
import session3.demo.patrons.agregationDelegation.AgentCommuniquant;

public class Test {
	public static void main(String[] args) {
		AgentCommuniquant a = new AgentDecoupantMessagesPourProtocole1();
		a.envoyer("nul n'est censé ignorer la loi.");
		
		Agent b = new AgentDecoupantMessagesPourProtocole2();
		b.envoyer("nul n'est censé ignorer la loi.");
		
		a = new AgentEncapsulantMessagesPourProtocole1();
		a.envoyer("nul n'est censé ignorer la loi.");
		
		b = new AgentEncapsulantMessagesPourProtocole2();
		b.envoyer("nul n'est censé ignorer la loi.");
	}
}
