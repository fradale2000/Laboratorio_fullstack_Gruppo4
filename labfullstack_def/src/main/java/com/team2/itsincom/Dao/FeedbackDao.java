package com.team2.itsincom.Dao;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.team2.itsincom.model.Feedback;

public interface FeedbackDao extends CrudRepository <Feedback, Long> {
	List<Feedback> findByVoto(String voto);
	List<Feedback> findByDatafeedback(String datafeedback);
	
	
	Feedback findByIdfeedback(Long idfeedback);
	
	
	

}
