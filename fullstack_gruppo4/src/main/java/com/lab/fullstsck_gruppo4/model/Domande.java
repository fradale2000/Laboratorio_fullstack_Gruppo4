package com.lab.fullstsck_gruppo4.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "domande")
public class Domande {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer iddomanda;
	
	private String testo_domanda;
	
	@OneToMany(mappedBy = "domande")
	private List <Domande> domande;
	
	
	public Domande() {}


	public Domande(Integer iddomanda, String testo_domanda) {
		this.iddomanda = iddomanda;
		this.testo_domanda = testo_domanda;
	}


	public Integer getIddomanda() {
		return iddomanda;
	}


	public void setIddomanda(Integer iddomanda) {
		this.iddomanda = iddomanda;
	}


	public String gettesto_domanda() {
		return testo_domanda;
	}


	public void settesto_domanda(String testo_domanda) {
		this.testo_domanda = testo_domanda;
	}
	
	
	
	

}
