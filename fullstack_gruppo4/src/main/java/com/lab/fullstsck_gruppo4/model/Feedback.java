package com.lab.fullstsck_gruppo4.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
@Table (name = "feedbacks")
public class Feedback {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idfeedback;
	
	private String voto;
	
	private String data_feedback;
		
	@ManyToOne
	@JoinColumn(name = "idutente", nullable = false)
	private Utenti utenti;
	
	@ManyToOne
	@JoinColumn(name = "iddomanda", nullable = false)
	private Domande domande;

	public Feedback() {}

	public Feedback(Integer idfeedback, String voto, String data_feedback) {
		this.idfeedback = idfeedback;
		this.voto = voto;
		this.data_feedback = data_feedback;
	}

	public Integer getIdfeedback() {
		return idfeedback;
	}

	public void setIdfeedback(Integer idfeedback) {
		this.idfeedback = idfeedback;
	}

	public String getVoto() {
		return voto;
	}

	public void setVoto(String voto) {
		this.voto = voto;
	}

	public String getData_feedback() {
		return data_feedback;
	}

	public void setData_feedback(String data_feedback) {
		this.data_feedback = data_feedback;
	}
	
	
	
	


}
