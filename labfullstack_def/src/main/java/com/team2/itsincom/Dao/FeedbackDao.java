package com.team2.itsincom.Dao;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.team2.itsincom.model.Feedback;

public interface FeedbackDao extends CrudRepository <Feedback, Long> {
	List<Feedback> findByVoto(String voto);
	List<Feedback> findByDatafeedback(String datafeedback);
	
	
	Feedback findByIdfeedback(Long idfeedback);
	
	//aggiunta feedback
	@Modifying
	@Transactional
	@Query(value="INSERT INTO `feedbacks` (`idfeedback`, `voto`, `datafeedback`, `idutente`, `iddomanda`) "
			+ "VALUES (NULL, :voto, current_timestamp(), :idutente, :iddomanda);",nativeQuery = true)
	public void aggiuntaFeedback(int voto,int idutente, int iddomanda);
	

}
