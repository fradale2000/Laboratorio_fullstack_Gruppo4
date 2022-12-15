package com.team2.itsincom.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table (name = "domande")
public class Domande {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer iddomanda;
	
	private String testodomanda;
	
	@OneToMany(mappedBy = "domande")
	private List <Domande> domande;
	
	
	public Domande() {}


	public Domande(Integer iddomanda, String testodomanda) {
		this.iddomanda = iddomanda;
		this.testodomanda = testodomanda;
	}


	public Integer getIddomanda() {
		return iddomanda;
	}


	public void setIddomanda(Integer iddomanda) {
		this.iddomanda = iddomanda;
	}


	public String getTestodomanda() {
		return testodomanda;
	}


	public void setTestodomanda(String testodomanda) {
		this.testodomanda = testodomanda;
	}


	
	
	
	
	

}
