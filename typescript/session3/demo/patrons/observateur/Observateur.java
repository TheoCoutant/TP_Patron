package session3.demo.patrons.observateur;

import java.util.Collection;

/*
 * - C : observateur client
 * - E : évènement
 * - S : serveur observable 
 */
public interface Observateur<C extends Observateur<C, E, S>, E, S extends Observable<S, E, C>> {
	default boolean modeTraction(){
		return !modePulsion();
	}
	default boolean modePulsion(){
		return !modeTraction();
	}
	// Mode push
	void recevoir(S emetteur, E evenement);
	// Mode pull
	// 1. Réception d'une notification
	void recevoir(S emetteur);
	// 2. Réception des évènements à la demande
	void recevoir(S emetteur, Collection<E> evenement);
	
}
