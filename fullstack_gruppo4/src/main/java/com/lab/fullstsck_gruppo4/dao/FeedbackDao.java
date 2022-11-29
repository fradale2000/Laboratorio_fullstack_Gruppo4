package com.lab.fullstsck_gruppo4.dao;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.lab.fullstsck_gruppo4.model.Feedback;


public interface FeedbackDao extends CrudRepository <Feedback, Long> {
	List<Feedback> findByVoto(String voto);
	List<Feedback> findByDatafeedback(String data_feedback);
	
	
	Feedback findByIdfeedback(Long idfeedback);
	
	
	

}
