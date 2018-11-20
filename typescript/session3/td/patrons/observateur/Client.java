package session3.demo.patrons.observateur;

import java.util.Collection;

public class Client implements Observateur<Client, String, Serveur>{

	private ModeCommunication mode;
	private String nom;
	
	public Client(ModeCommunication mode, String nom) {
		super();
		this.mode = mode;
		this.nom = nom;
	}

	@Override
	public boolean modeTraction() {
		switch(mode){
		case PUSH : return false; 
		case PULL : return true;
		}
		return false;
	}
	
	@Override
	public void recevoir(Serveur emetteur, String evenement) {
		System.out.println("mode " + mode + " - réception de : " + evenement );
	}

	@Override
	public void recevoir(Serveur emetteur, Collection<String> evenements) {
		System.out.println("mode " + mode + " - réception de : " + evenements );
	}

	@Override
	public void recevoir(Serveur emetteur) {
		System.out.println("mode " + mode + " - notification");
	}

	@Override
	public int hashCode() {
		return nom.hashCode();
	}
}
