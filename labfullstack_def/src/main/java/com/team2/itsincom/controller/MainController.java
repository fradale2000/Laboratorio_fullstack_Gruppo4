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
import com.team2.itsincom.Dao.DomandeDao;
import com.team2.itsincom.Dao.UtentiDao;
import com.team2.itsincom.model.Utenti;
import com.team2.itsincom.model.Domande;
import com.team2.itsincom.model.ReCaptchaResponse;


@Controller
@RequestMapping("")
public class MainController {
	
	//Serve per il logger in console
	public static final Logger LOGGER=LoggerFactory.getLogger(MainController.class);
	
	
	@Autowired
	UtentiDao utenteRepository; 
	
	@Autowired
	DomandeDao domandaRepository; 
	
	
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
	public String post_login(@Valid Utenti utenti, @RequestParam("email") String email, @RequestParam("pwd") String pwd, BindingResult fields,HttpSession session) {
		if(fields.hasErrors()) {
			return "login";
		}			
		if(utenteRepository.findByEmail(email).size()>0) {
			// Lo studente è presente nel db			
			Utenti utenteAttuale=utenteRepository.findByEmail(email).get(0);			
			if(utenteAttuale.getPwd().equals(pwd)) {
				session.setAttribute("utenteAttuale", utenteAttuale);
				//Se la password è uguale a quella del db, entra
				LOGGER.info("Studente loggato");
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
	public String modulo(Model model,HttpSession session) {
		LOGGER.info("Utente in modulo");
		Utenti utenteAttuale = (Utenti) session.getAttribute("utenteAttuale");
		//System.out.println(utenteAttuale.getIdutente());
		//scelta domanda 1
		Domande domanda1 = domandaRepository.domanda1Random();
		model.addAttribute("domanda1",domanda1);
		//scelta domanda 2
		Domande domanda2 = domandaRepository.domanda2Random(domanda1.getIddomanda());
		model.addAttribute("domanda2",domanda2);
		//scelta domanda 3
		Domande domanda3 = domandaRepository.domanda3Random(domanda1.getIddomanda(),domanda2.getIddomanda());
		model.addAttribute("domanda3",domanda3);
		//scelta domanda 4
		Domande domanda4 = domandaRepository.domanda4Random(domanda1.getIddomanda(),domanda2.getIddomanda(),domanda3.getIddomanda());
		model.addAttribute("domanda4",domanda4);
		
		return "/modulo";				
	}
	
	// ACCESSO ALLA PAGINA menu_admin.html
    @RequestMapping(value = "menu_admin", method = RequestMethod.GET)
    public String get_menu_admin() {
    	LOGGER.info("Admin loggato");
        return "menu_admin";
    }
    
        
    // STUDENTE
    
    
    // INSERIMENTO STUDENTE
    
    // ACCESSO ALLA PAGINA inserisci_studente.html
    @GetMapping("inserisci_studente")
    public ModelAndView get_inserimento_studente(Utenti utenti) {
        ModelAndView listaUtenti=new ModelAndView();
        listaUtenti.setViewName("inserisci_studente");
        listaUtenti.addObject("utenti", utenteRepository.visualizzaUtenti()); 
        LOGGER.info("Admin in inserimento");
        return listaUtenti;
    }
    
    // INSERIMENTO DI UN NUOVO STUDENTE NEL DB
    @RequestMapping(value = "inserisci_studente", method = RequestMethod.POST)
    public String post_inserisci_studente(@Valid Utenti utenti, @RequestParam("nome") String nome, @RequestParam("cognome") String cognome, @RequestParam("email") String email, BindingResult fields) {        
        if(utenteRepository.findByEmail(email).size()==0) {
            utenti = new Utenti (nome, cognome, email, null);
            utenteRepository.save(utenti);
            LOGGER.info("Inserimento di un studente avvenuto correttamente");
            return "menu_admin";
        }   
        LOGGER.info("Inserimento non avvenuto");
        return "inserisci_studente";            
    }

    
    // MODIFICA STUDENTE
    
    // ACCESSO ALLA PAGINA modifica_studente.html
    @GetMapping("modifica_studente")
    public ModelAndView get_modifica_studente(Utenti utenti) {
        ModelAndView listaUtente=new ModelAndView();
        listaUtente.setViewName("modifica_studente");
        listaUtente.addObject("utenti", utenteRepository.visualizzaUtenti()); 
        LOGGER.info("Admin in modifica");
        return listaUtente;
    }
    
    // MODIFICA DI UNO STUDENTE SUL DB
    @RequestMapping(value = "modifica_studente", method = RequestMethod.POST)
    public String post_modifica_studente(@Valid Utenti utenti, @RequestParam("idutente") Integer idutente, @RequestParam("nome") String nome, @RequestParam("cognome") String cognome, @RequestParam("email") String email, @RequestParam("pwd") String pwd,  BindingResult fields) {
    	if(fields.hasErrors()) {
 			return "redirect:/modifica_studente";
 		}
    	if(pwd.equals("")) {
    		pwd=null;
    	}
 		Utenti utenteModificato=utenteRepository.findById(idutente).get();
 		utenteModificato.setNome(nome);
 		utenteModificato.setCognome(cognome);
 		utenteModificato.setEmail(email);
 		utenteModificato.setPwd(pwd);
 		utenteRepository.save(utenteModificato);
 		LOGGER.info("Utente modificato correttamente");
        return "menu_admin";           
	}
    
    //Git fa schifo
    // RIMOZIONE STUDENTE
    
    // ACCESSO ALLA PAGINA rimuovi_studente.html
    @GetMapping("rimuovi_studente")
    public ModelAndView get_rimuovi_studente(Utenti utenti) {
        ModelAndView listaUtenti=new ModelAndView();
        listaUtenti.setViewName("rimuovi_studente");
        listaUtenti.addObject("utenti",utenteRepository.visualizzaUtenti()); 
        LOGGER.info("Admin in rimuovi");
        return listaUtenti;
    }
    
    // RIMOZIONE DI UNO STUDENTE DAL DB
    @RequestMapping(value = "rimuovi_studente", method = RequestMethod.POST)
    public String post_rimuovi_studente(Utenti utenti, @RequestParam("idutente") Integer idutente, BindingResult fields) {
    	utenteRepository.deleteById(idutente);
    	LOGGER.info("Admin ha rimosso");
        return "menu_admin";
	}
    

	@RequestMapping(value = "/invio_modulo", method = RequestMethod.POST)
	public String invio_modulo(@RequestParam() BindingResult fields,HttpSession session) {
		if(fields.hasErrors()) {
			return "modulo";
		}			
		if(utenteRepository.findByEmail(email).size()>0) {
			// Lo studente è presente nel db			
			Utenti utenteAttuale=utenteRepository.findByEmail(email).get(0);			
			if(utenteAttuale.getPwd().equals(pwd)) {
				session.setAttribute("utenteAttuale", utenteAttuale);
				//Se la password è uguale a quella del db, entra
				return "redirect:home/"+utenteAttuale.getIdutente();
			}
		}
		return "login";
	}
}