package com.team2.itsincom.Dao;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.team2.itsincom.model.Domande;




public interface DomandeDao extends CrudRepository <Domande, Integer> {
	List<Domande> findByTestodomanda(String testodomanda);
	
	
	Domande findByIddomanda(Long iddomanda);
	
	
}


