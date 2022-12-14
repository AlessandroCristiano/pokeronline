package it.prova.pokeronline.service;

import java.util.List;

import it.prova.pokeronline.model.Tavolo;

public interface TavoloService {
	List<Tavolo> listAllTavoli();
	
	List<Tavolo> listAllElementsEager();
	
	List<Tavolo>findByUsername();

	Tavolo caricaSingoloTavolo(Long id);
	
	Tavolo caricaSingoloTavoloConUtenza(Long id);

	Tavolo aggiorna(Tavolo tavoloInstance);

	Tavolo inserisciNuovo(Tavolo tavoloInstance);

	void rimuovi(Long idToRemove);
	
	List<Tavolo> findByEsperienzaMinimaLessThan();
	
	void gioca(Long id, String username);
	
	public List<Tavolo> findByExample(Tavolo example);

}
