package com.lab.fullstsck_gruppo4.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lab.fullstsck_gruppo4.model.Utenti;

public interface UtentiDao extends CrudRepository <Utenti, Integer> {
	List<Utenti> findByNome(String nome);
	List<Utenti> findByCognome(String cognome);
	List<Utenti> findByEmail(String email);
	List<Utenti> findByPassworddocente(String password);
	List<Utenti> findBySesso (char sesso);
	
	Utenti findByIdutente(Long idutente);
	Utenti findByIdutente5(Long idutente);
	
    //Query eventuali

} 


