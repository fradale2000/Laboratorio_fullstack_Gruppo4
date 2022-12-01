package com.team2.itsincom.Dao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.team2.itsincom.model.Domande;




public interface DomandeDao extends CrudRepository <Domande, Integer> {
	List<Domande> findByTestodomanda(String testodomanda);
	
	
	Domande findByIddomanda(Long iddomanda);
	//scelta randomica della domanda 1
	@Query(value="SELECT * FROM domande ORDER BY RAND() LIMIT 1;",nativeQuery = true)
	public Domande domanda1Random();
	//scelta randomica della domanda 2
	@Query(value="SELECT * FROM domande where iddomanda != :iddomanda1 ORDER BY RAND() LIMIT 1 ;",nativeQuery = true)
	public Domande domanda2Random(int iddomanda1);
	//scelta randomica della domanda 3
	@Query(value="SELECT * FROM domande where iddomanda not in (:iddomanda1,:iddomanda2)  ORDER BY RAND() LIMIT 1 ;",nativeQuery = true)
	public Domande domanda3Random(int iddomanda1,int iddomanda2);
	//scelta randomica della domanda 4
	@Query(value="SELECT * FROM domande where iddomanda not in (:iddomanda1,:iddomanda2,:iddomanda3)  ORDER BY RAND() LIMIT 1 ;",nativeQuery = true)
	public Domande domanda4Random(int iddomanda1,int iddomanda2,int iddomanda3);
	
}


