package com.lab.fullstsck_gruppo4.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lab.fullstsck_gruppo4.model.Utenti;

public interface UtentiDao extends CrudRepository <Utenti, Integer> {
	List<Utenti> findByNome(String nome);
	List<Utenti> findByCognome(String cognome);
	List<Utenti> findByEmail(String email);
	List<Utenti> findByPwd(String pwd);
	List<Utenti> findBySesso (char sesso);
	
	Utenti findByIdutente(Long idutente);
	
	
    //Query eventuali

} 


