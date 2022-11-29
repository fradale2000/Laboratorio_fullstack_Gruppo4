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
	
	private String domandatesto;
	
	@OneToMany(mappedBy = "domande")
	private List <Domande> domande;
	
	
	public Domande() {}


	public Domande(Integer iddomanda, String domandatesto) {
		this.iddomanda = iddomanda;
		this.domandatesto = domandatesto;
	}


	public Integer getIddomanda() {
		return iddomanda;
	}


	public void setIddomanda(Integer iddomanda) {
		this.iddomanda = iddomanda;
	}


	public String getDomandatesto() {
		return domandatesto;
	}


	public void setDomandatesto(String domandatesto) {
		this.domandatesto = domandatesto;
	}
	
	
	
	

}
