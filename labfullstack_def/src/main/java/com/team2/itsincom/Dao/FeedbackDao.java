package com.team2.itsincom.Dao;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.team2.itsincom.model.DateDiffFeedback;
import com.team2.itsincom.model.Domande;
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
	
	//query per prendere il numero di tutti i feedback
	@Query(value="SELECT COUNT(idfeedback) as nrispostetotali FROM `feedbacks`",nativeQuery = true)
	public int numeroFeedbacks();
		
	//query per prendere il numero di risposte alla determinata domanda
	@Query(value="SELECT COUNT(idfeedback) as nrisposte FROM `feedbacks` WHERE iddomanda = :iddomanda",nativeQuery = true)
	public int numeroRisposteadomanda(int iddomanda);
	
	//query per prendere il numero di risposte alla determinata domanda con determinato voto
	@Query(value="SELECT COUNT(idfeedback) as nvoti FROM `feedbacks` WHERE iddomanda = :iddomanda and voto = :voto",nativeQuery = true)
	public int numeroVotiperdomanda(int iddomanda, int voto );
	
	//query per prendere il numero di risposte alla determinata domanda con determinato voto
	@Query(value="SELECT idfeedback,voto,datafeedback,idutente,iddomanda FROM `feedbacks` WHERE (idutente = :idutente AND datediff(sysdate(),datafeedback) = 7) GROUP by idutente;",nativeQuery = true)
	public List <DateDiffFeedback> checkFeedback(int idutente);
	
	


}
