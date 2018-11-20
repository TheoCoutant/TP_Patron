package session3.demo.patrons.agregationFonctionnelle;

import java.util.function.BiConsumer;

import session3.demo.patrons.agregationDelegation.AgentCommuniquant;
import session3.demo.patrons.agregationDelegation.Canal;

public class FabriqueDelegation {
	static Delegation creer(BiConsumer<AgentCommuniquant, String> envoi) {
		return new Delegation() {

			@Override
			public AgentCommuniquant deleguer(Canal c) {
				return new AgentCommuniquant() {

					@Override
					public void envoyer(String msg) {
						envoi.accept(this, msg);
					}

					@Override
					public void emettre(String msg) {
						c.emettre(msg);

					}

				};

			}

		};
	}
}
