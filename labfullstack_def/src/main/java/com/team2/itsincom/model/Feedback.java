package com.team2.itsincom.model;

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
	
	private int voto;
	
	private String datafeedback;
		
	@ManyToOne
	@JoinColumn(name = "idutente", nullable = false)
	private Utenti utenti;
	
	@ManyToOne
	@JoinColumn(name = "iddomanda", nullable = false)
	private Domande domande;
	
	private int datediff;

	public Feedback() {}

	public Feedback(Integer idfeedback, int voto, String datafeedback, Utenti utenti, Domande domande,
			int datediff) {
		super();
		this.idfeedback = idfeedback;
		this.voto = voto;
		this.datafeedback = datafeedback;
		this.utenti = utenti;
		this.domande = domande;
		this.datediff = datediff;
	}

	public Integer getIdfeedback() {
		return idfeedback;
	}

	public void setIdfeedback(Integer idfeedback) {
		this.idfeedback = idfeedback;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public String getDatafeedback() {
		return datafeedback;
	}

	public void setDatafeedback(String datafeedback) {
		this.datafeedback = datafeedback;
	}

	public int getDatediff() {
		return datediff;
	}

	public void setDatediff(int datediff) {
		this.datediff = datediff;
	}

	
	
	
	
	


}
