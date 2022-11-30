package it.prova.pokeronline.repository.tavolo;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.pokeronline.model.Tavolo;

public interface TavoloRepository extends CrudRepository<Tavolo, Long>{
	
	@Query("from Tavolo t left join fetch t.utenti left join fetch t.utenteCreazione where t.id=?1")
	Tavolo findByIdEager(Long idTavolo);
	
	List<Tavolo> findByEsperienzaMinLessThan(Integer esperienza);
	
	Tavolo findByUtentiId(Long id);
	
	
	
	

}
