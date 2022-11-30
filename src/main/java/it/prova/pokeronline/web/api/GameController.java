package it.prova.pokeronline.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pokeronline.dto.TavoloDTO;
import it.prova.pokeronline.dto.UtenteDTO;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.service.TavoloService;
import it.prova.pokeronline.service.UtenteService;

@RestController
@RequestMapping("/api/game")
public class GameController {
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private TavoloService tavoloService;
	
	
	@GetMapping("/compraCredito/{soldi}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public UtenteDTO addCredito(@PathVariable(value = "soldi", required = true) Integer soldi) {
				
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteLoggato = utenteService.findByUsername(username);
		
		Integer nuovoCredito = utenteLoggato.getCreditoAccumulato() + soldi;
		
		utenteLoggato.setCreditoAccumulato(nuovoCredito);
		utenteService.aggiorna(utenteLoggato);
		
		return UtenteDTO.buildUtenteDTOFromModel(utenteLoggato);
		
	}
	
	@GetMapping("/ricerca")
	public List<TavoloDTO> tavoliConEsperienza(){
		return TavoloDTO.createTavoloDTOListFromModelList(tavoloService.findByEsperienzaMinimaLessThan(), true);
	}
	
	@GetMapping("/giocaA/{idTavolo}")
	@ResponseStatus(HttpStatus.OK)
	public void gioca(@PathVariable(value = "idTavolo", required = true) Long id) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteLoggato = utenteService.findByUsername(username);
		
		tavoloService.gioca(id, username);
	}
	
	
}
