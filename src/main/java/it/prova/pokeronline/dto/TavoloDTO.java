package it.prova.pokeronline.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TavoloDTO {
	
	private Long id;
	
	@NotBlank(message = "{esperienzaMin.notblank}")
	private Integer esperienzaMin;
	
	@NotBlank(message = "{cifraMinima.notblank}")
	private Integer cifraMinima;
	
	@NotBlank(message = "{denominazione.notblank}")
	private String denominazione;
	
	private LocalDate dataCreazione;
	
	@JsonIgnoreProperties(value = { "tavolo" })
	private Set<UtenteDTO> utenti = new HashSet<UtenteDTO>(0);
	
	@JsonIgnoreProperties(value = { "tavolo" })
	@NotNull(message = "utenteCreazione.notnull")
	private UtenteDTO utenteCreazione;

	public TavoloDTO() {
		super();
	}

	public TavoloDTO(Long id, Integer esperienzaMin, Integer cifraMinima, String denominazione, LocalDate dataCreazione,
			Set<UtenteDTO> utenti, UtenteDTO utenteCreazione) {
		super();
		this.id = id;
		this.esperienzaMin = esperienzaMin;
		this.cifraMinima = cifraMinima;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
		this.utenti = utenti;
		this.utenteCreazione = utenteCreazione;
	}
	
	public TavoloDTO(Long id, Integer esperienzaMin, Integer cifraMinima, String denominazione, 
			LocalDate dataCreazione, UtenteDTO utenteCreazione) {
		super();
		this.id = id;
		this.esperienzaMin = esperienzaMin;
		this.cifraMinima = cifraMinima;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
		this.utenteCreazione = utenteCreazione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getEsperienzaMin() {
		return esperienzaMin;
	}

	public void setEsperienzaMin(Integer esperienzaMin) {
		this.esperienzaMin = esperienzaMin;
	}

	public Integer getCifraMinima() {
		return cifraMinima;
	}

	public void setCifraMinima(Integer cifraMinima) {
		this.cifraMinima = cifraMinima;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public LocalDate getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDate dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Set<UtenteDTO> getUtenti() {
		return utenti;
	}

	public void setUtenti(Set<UtenteDTO> utenti) {
		this.utenti = utenti;
	}

	public UtenteDTO getUtenteCreazione() {
		return utenteCreazione;
	}

	public void setUtenteCreazione(UtenteDTO utenteCreazione) {
		this.utenteCreazione = utenteCreazione;
	}	
	
	public Tavolo buildFromModel() {
		Tavolo result = new Tavolo(this.id, this.esperienzaMin, this.cifraMinima, this.denominazione,
				this.dataCreazione, this.utenteCreazione.buildUtenteModel(false));
		if (utenti.size() != 0) {
			for (UtenteDTO giocatoreItem : utenti) {
				result.getUtenti().add(giocatoreItem.buildUtenteModel(false));
			}
		}
		return result;
	}
	
	public static TavoloDTO buildTavoloDTOFromModel(Tavolo tavoloModel, boolean includeGiocatori) {
		TavoloDTO result = new TavoloDTO(tavoloModel.getId(), tavoloModel.getEsperienzaMin(),
				tavoloModel.getCifraMinima(), tavoloModel.getDenominazione(), tavoloModel.getDataCreazione(),
				UtenteDTO.buildUtenteDTOFromModel(tavoloModel.getUtenteCreazione()));

		if (includeGiocatori) {
			for (Utente giocatoriItem : tavoloModel.getUtenti()) {
				result.getUtenti().add(UtenteDTO.buildUtenteDTOFromModel(giocatoriItem));
			}
		}
		return result;
	}
	
	public static List<TavoloDTO> createTavoloDTOListFromModelList(List<Tavolo> modelList, boolean includeGiocatori) {
		return modelList.stream().map(tavoloEntity -> {
			return TavoloDTO.buildTavoloDTOFromModel(tavoloEntity, includeGiocatori);
		}).collect(Collectors.toList());
	}

}
