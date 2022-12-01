package com.team2.itsincom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.security.MessageDigest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


import com.team2.itsincom.ItsincomApplication;
import com.team2.itsincom.Dao.UtentiDao;
import com.team2.itsincom.model.Utenti;
import com.team2.itsincom.model.ReCaptchaResponse;


@Controller
@RequestMapping("")
public class MainController {
	
	//Serve per il logger in console
	public static final Logger LOGGER=LoggerFactory.getLogger(MainController.class);
	
	
	@Autowired
	UtentiDao utenteRepository; 
	
	
	//Template per codice Captcha
	@Autowired
	RestTemplate restTemplate;
	
	// REGISTRAZIONE
	@GetMapping("/registrazione") 
	public String registrazione() {
		LOGGER.info("Utente in registrazione");
		return "registrazione";
		
	}
	
	//Codice per inserire la password	
	@RequestMapping(value = "registrazione", method = RequestMethod.POST)
	public String post_registrazione(@Valid Utenti utenti, @RequestParam("email") String email, @RequestParam("pwd") String pwd, @RequestParam("g-recaptcha-response") String captchaResponse, BindingResult fields) {
		
		String url = "https://www.google.com/recaptcha/api/siteverify";
		String params = "?secret=6LcmWycjAAAAAL_CPGuBMw7G9MzzVYRjOYGV0joE&response="+captchaResponse;		
		ReCaptchaResponse reCaptchaResponse = restTemplate.exchange(url+params, HttpMethod.POST,null,ReCaptchaResponse.class).getBody();
		System.out.println(email);
	
		if(fields.hasErrors()) {
			LOGGER.info("Registrazione fallita");
			return "registrazione";			
		}	
		
		if(utenteRepository.findByEmail(email).size()>0) {
			// Lo studente è presente nel database
			Utenti utenteAttuale = utenteRepository.findByEmail(email).get(0);
			if(utenteAttuale.getPwd()==null) {
				// La password dello studente non è impostata
				utenteRepository.registrazione(email, pwd);
				LOGGER.info("Registrazione confermata");
				return "login";
			}
		}		
		LOGGER.info("Registrazione fallita");
		return "registrazione";		
	}
	
	
	@GetMapping("/login") 
	public String login() {
		LOGGER.info("Utente in login");
		return "login";
		
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String post_login(@Valid Utenti utenti, @RequestParam("email") String email, @RequestParam("pwd") String pwd, BindingResult fields) {
		if(fields.hasErrors()) {
			return "login";
		}			
		if(utenteRepository.findByEmail(email).size()>0) {
			// Lo studente è presente nel db			
			Utenti utenteAttuale=utenteRepository.findByEmail(email).get(0);			
			if(utenteAttuale.getPwd().equals(pwd)) {				
				//Se la password è uguale a quella del db, entra
				return "redirect:home/"+utenteAttuale.getIdutente();
			}
		}
		return "login";
	}
	
	// ACCESSO HOME UTENTE
	@RequestMapping(value = "home/{id}", method = RequestMethod.GET)
	public ModelAndView get_home_id(@PathVariable("id") int idutente){
		Utenti utente = utenteRepository.findByIdutente(idutente);
		ModelAndView menuUtente = new ModelAndView();
		if (utente != null){
			menuUtente.setViewName("home");
			menuUtente.addObject("utente",utente);
			return menuUtente;
				} else {
					return null;
				}
			} 
	
	@GetMapping("/modulo") 
	public String modulo() {
		LOGGER.info("Utente in modulo");
		return "modulo";
		
	}
}