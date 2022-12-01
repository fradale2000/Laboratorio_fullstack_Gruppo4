package com.team2.itsincom.model;

import java.util.List;

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
@Table(name="utenti")
public class Utenti {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idutente;
			
    @NotNull(message = "La e-mail deve essere inserita.")
	private String email;
    
    
	


	private String nome;
    
    
	private String cognome;
	    
	private String pwd;
		
	@OneToMany(mappedBy = "utenti")
	private List <Utenti> utenti;
	
	
	public Utenti() {}


	public Utenti(Integer idutente,String email, String pwd) {
		
		this.idutente = idutente;		
		this.email = email;
		this.pwd = pwd;					
	}
	
	
	public Utenti(String email) {						
		this.email = email;						
	}
	

	public Integer getIdutente() {
		return idutente;
	}

	public void setIdutente(Integer idutente) {
		this.idutente = idutente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	

	
	
	
	
}