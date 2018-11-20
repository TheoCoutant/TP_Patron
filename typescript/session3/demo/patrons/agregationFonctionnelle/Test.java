package session3.demo.patrons.agregationFonctionnelle;

import session3.demo.patrons.agregationDelegation.Agent;
import session3.demo.patrons.agregationDelegation.AgentCommuniquant;
import session3.demo.patrons.agregationDelegation.CanalOutProtocole1;
import session3.demo.patrons.agregationDelegation.CanalOutProtocole2;

public class Test {
	public static void main(String[] args) {
		Delegation d1 = FabriqueDelegation.creer((agent, msg) -> {
			int TAILLE = 5;
			StringBuilder m = new StringBuilder(msg);
			int q = msg.length() / TAILLE;
			int r = msg.length() % TAILLE;
			for (int j = 0; j < q; j++) {
				agent.emettre(m.substring(j * TAILLE, (j + 1) * TAILLE));
			}
			agent.emettre(m.substring(q * TAILLE, q * TAILLE + r));
		});
		Delegation d2 = FabriqueDelegation.creer((agent, msg) -> {
			agent.emettre(msg);
		});
		
		AgentCommuniquant a = d1.deleguer(new CanalOutProtocole1());
		a.envoyer("nul n'est censé ignorer la loi.");
		
		Agent b = d2.deleguer(new CanalOutProtocole2());
		b.envoyer("nul n'est censé ignorer la loi.");
		
		a = d2.deleguer(new CanalOutProtocole1());
		a.envoyer("nul n'est censé ignorer la loi.");
		
		b = d1.deleguer(new CanalOutProtocole2());
		b.envoyer("nul n'est censé ignorer la loi.");
		
	}
}
