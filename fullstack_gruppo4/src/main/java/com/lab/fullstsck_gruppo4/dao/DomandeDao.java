package com.lab.fullstsck_gruppo4.dao;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.lab.fullstsck_gruppo4.model.Domande;





public interface DomandeDao extends CrudRepository <Domande, Integer> {
	List<Domande> findByTestodomanda(String testo_domanda);
	
	
	Domande findByIddomanda(Long iddomanda);
	
	
}


