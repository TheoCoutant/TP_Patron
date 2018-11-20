package session3.demo.patrons.observateur;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ServeurObservations<S extends Observable<S, E , C>, E, C extends Observateur<C, E, S>> 
	implements Observable<S, E, C>{
	private List<C> observateurs;
	private Map<C, List<E>> evenementsParObservateur; // Invariant : listes non vides (si vide, pas d'association)
	private S serveur;
	public ServeurObservations(S serveur) {
		this.serveur = serveur;
		this.observateurs = new LinkedList<>();
		this.evenementsParObservateur = new HashMap<>();
	}
	@Override
	public void ajouterObservateur(C c){
		observateurs.add(c);
	}
	@Override
	public void notifierObservateurs(E e) {
		for(C obs : observateurs){
			if(obs.modePulsion()){
				obs.recevoir(this.serveur(), e);
			}else{
				obs.recevoir(this.serveur());
				List<E> ev = evenementsParObservateur.get(obs);
				if(ev == null){
					ev = new LinkedList<>();
					evenementsParObservateur.put(obs,  ev);
				}	
				ev.add(e);
			}
		}
		
	}
	@Override 
	public void informerObservateur(C client){
		List<E> evenements = evenementsParObservateur.get(client);
		if(evenements == null)
			return;
		client.recevoir(this.serveur(), Collections.unmodifiableCollection(evenements));		
	}
	public S serveur() {
		return this.serveur;
	}
}
