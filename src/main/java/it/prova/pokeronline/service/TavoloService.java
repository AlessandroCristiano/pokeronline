package it.prova.pokeronline.service;

import java.util.List;

import it.prova.pokeronline.model.Tavolo;

public interface TavoloService {
	List<Tavolo> listAllElements();
	
	List<Tavolo> listAllElementsEager();
	
	List<Tavolo>findByUsername();

	Tavolo caricaSingoloElemento(Long id);
	
	Tavolo caricaSingoloElementoConUtenza(Long id);

	Tavolo aggiorna(Tavolo tavoloInstance);

	Tavolo inserisciNuovo(Tavolo tavoloInstance);

	void rimuovi(Long idToRemove);

}
