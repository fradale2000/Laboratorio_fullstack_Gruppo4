package com.team2.itsincom.Dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import com.team2.itsincom.model.Utenti;

public interface UtentiDao extends CrudRepository <Utenti, Integer> {
	
	List<Utenti> findByNome(String nome);
	List<Utenti> findByCognome(String cognome);
	List<Utenti> findByEmail(String email);
	List<Utenti> findByPwd(String pwd);
	
	
	Utenti findByIdutente(int idutente);
	
	
    //Query eventuali
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE utenti AS u SET u.pwd=:pwd WHERE u.email=:email", nativeQuery = true)
	public int registrazione(@Param("email") String email,@Param("pwd") String pwd);


	
	@Query(value="SELECT * FROM utenti",nativeQuery=true)
 	public Collection <Utenti> visualizzaUtenti();

} 


