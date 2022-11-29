package com.lab.fullstsck_gruppo4.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="utenti")
public class Utenti {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idutente;
	
	@Size(min=1, max=45, message="Il nome deve avere al massimo 45 caratteri.")
    @NotNull(message = "Il nome deve essere inserito.")
	private String nome;

	
	@Size(min=1, max=45, message="Il nome deve avere al massimo 45 caratteri.")
    @NotNull(message = "Il nome deve essere inserito.")
	private String cognome;
	
	
	@Size(min=1, max=45, message="Il nome deve avere al massimo 45 caratteri.")
    @NotNull(message = "Il nome deve essere inserito.")
	private String email;
	
	private String pwd;
	
	private char sesso;
	
	@OneToMany(mappedBy = "utenti")
	private List <Utenti> utenti;
	
	
	public Utenti() {}


	public Utenti(Integer idutente, String nome, String cognome,String email, String pwd, char sesso) {
		
		this.idutente = idutente;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.pwd = pwd;
		this.sesso = sesso;				
	}

	public Integer getIdutente() {
		return idutente;
	}

	public void setIdutente(Integer idutente) {
		this.idutente = idutente;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public char getSesso() {
		return sesso;
	}

	public void setSesso(char sesso) {
		this.sesso = sesso;
	}


	
	
	
	
}