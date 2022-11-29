package com.lab.fullstsck_gruppo4.model;

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
