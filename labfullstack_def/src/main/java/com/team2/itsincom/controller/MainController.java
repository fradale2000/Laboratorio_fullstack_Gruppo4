package com.team2.itsincom.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.team2.itsincom.Dao.DomandeDao;
import com.team2.itsincom.Dao.FeedbackDao;
import com.team2.itsincom.Dao.UtentiDao;
import com.team2.itsincom.model.Utenti;
import com.team2.itsincom.model.DateDiffFeedback;
import com.team2.itsincom.model.Domande;
import com.team2.itsincom.model.Feedback;
import com.team2.itsincom.model.PercFeedback;
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
	
	@Autowired
	FeedbackDao feedbackRepository; 
	
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
		public String post_registrazione(@Valid Utenti utenti, 
										@RequestParam("email") String email, 
										@RequestParam("pwd") String pwd, 
										@RequestParam("g-recaptcha-response") String captchaResponse, 
										BindingResult fields) {	
			
			String url = "https://www.google.com/recaptcha/api/siteverify";
			String params = "?secret=6LcmWycjAAAAAL_CPGuBMw7G9MzzVYRjOYGV0joE&response="+captchaResponse;		
			ReCaptchaResponse reCaptchaResponse = restTemplate.exchange(url+params, HttpMethod.POST,null,ReCaptchaResponse.class).getBody();
			
			String encryptedpassword;	
			
			try   
	        {  			
				
				/* MessageDigest instance for MD5. */  
				MessageDigest m = MessageDigest.getInstance("MD5");  
	        	/* Add plain-text password bytes to digest using MD5 update() method. */  
				m.update(pwd.getBytes());   
				/* Convert the hash value into bytes */   
				byte[] bytes = m.digest();  	              
	            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
	            StringBuilder s = new StringBuilder();  
	            for(int i=0; i< bytes.length ;i++) {  
	            	s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
	            } 
	            
	            encryptedpassword = s.toString();
	            
	            
	            if(utenteRepository.findByEmail(email).size()>0) {
				// Lo studente è presente nel database

				Utenti utenteAttuale = utenteRepository.findByEmail(email).get(0);
				if(utenteAttuale.getPwd()== null) {				
					// La password dello studente non è impostata				
					utenteRepository.registrazione(email, encryptedpassword);
					LOGGER.info("Registrazione confermata");
					return "login";
				} else {
					LOGGER.info("Registrazione fallita");
		        	return "redirect:/registrazione?error";  
				} } }
	          
	        catch (NoSuchAlgorithmException e)  
		
	        {  
	            e.printStackTrace();  
	        }
		LOGGER.info("Registrazione fallita");
		return "redirect:/login";  	          
	     } 

		
		@GetMapping("/login") 
		public String login() {
			LOGGER.info("Utente in login");
			return "login";
			
		}
		
		
		@RequestMapping(value = "login", method = RequestMethod.POST)
		public String post_login(@Valid Utenti utenti, @RequestParam("email") String email, @RequestParam("pwd") String pwd, BindingResult fields, Model model, HttpSession session) {
			

			  String encryptedpassword;
			try   
		        {  			
					
					/* MessageDigest instance for MD5. */  
					MessageDigest m = MessageDigest.getInstance("MD5");  
		        	/* Add plain-text password bytes to digest using MD5 update() method. */  
					m.update(pwd.getBytes());   
					/* Convert the hash value into bytes */   
					byte[] bytes = m.digest();  	              
		            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
		            StringBuilder s = new StringBuilder();  
		            for(int i=0; i< bytes.length ;i++) {  
		            	s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
		            }  	              
		            /* Complete hashed password in hexadecimal format */  
		            encryptedpassword = s.toString();  		         
			        /*save user in db */
		            
					Utenti utenteAttuale = utenteRepository.login(email, encryptedpassword);				
					if(utenteAttuale != null) {
					session.setAttribute("utenteAttuale", utenteAttuale);				
					LOGGER.info("Utente loggato"); 				
					return "redirect:/home/";
					}
					if(utenteRepository.findByEmail(email).size()>0) {
						// Lo studente è presente nel db			
						Utenti adminLogin =utenteRepository.findByEmail(email).get(0);			
						if(adminLogin.getPwd().equals("admin")) {				
							session.setAttribute("adminLogin", adminLogin);
							//Se la password è uguale a quella del db, entra
							LOGGER.info("Admin loggato");
							return "redirect:menu_admin";
						}  
						
						}
					
		        } 
			
			
			
			catch (NoSuchAlgorithmException e) {  
				e.printStackTrace();  
				LOGGER.info("Login fallito");
			return "redirect:/registrazione?error";
			}
			
			return"registrazione";
				
				
					
				}
	
	// ACCESSO HOME UTENTE
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(Model model,HttpSession session){
		Utenti utenteAttuale = (Utenti) session.getAttribute("utenteAttuale");
		List<Feedback> checkfeedback = feedbackRepository.checkFeedback(utenteAttuale.getIdutente());
		if (utenteAttuale != null){
			model.addAttribute("utente",utenteAttuale);
				}
		 //controllo che l'utente abbia già fatto un form
        //se il risulato è 0 vuole dire che non ha mai fatto un form
        if (checkfeedback.size() > 0) {
            //se invece l'ha già fatto controllo quanto tempo fa
            if (checkfeedback.get(0).getDatediff()>= 7) {
                return "/home" ;
            } else {
            	return "redirect:/aspetta";
            }
        } else {
        	return "/home";
        }
			} 
	
	@GetMapping("/aspetta") 
	public String aspetta(Model model,HttpSession session) {
		LOGGER.info("Utente in attesa");
		Utenti utenteAttuale = (Utenti) session.getAttribute("utenteAttuale");
		List<Feedback> checkfeedback = feedbackRepository.checkFeedback(utenteAttuale.getIdutente());
		checkfeedback.get(0).setDatediff(7 - checkfeedback.get(0).getDatediff());
		Feedback giornimancanti = new Feedback(checkfeedback.get(0).getIdfeedback(), checkfeedback.get(0).getVoto(), checkfeedback.get(0).getDatafeedback(), utenteAttuale, null,checkfeedback.get(0).getDatediff());
		model.addAttribute("utente",utenteAttuale);
		model.addAttribute("giornimancanti",giornimancanti);
		return "/aspetta";				
	}
	
	// MODULO UTENTE
	@GetMapping("/modulo") 
	public String modulo(Model model,HttpSession session) {
		LOGGER.info("Utente in modulo");
		Utenti utenteAttuale = (Utenti) session.getAttribute("utenteAttuale");
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

	
	// CODICE PER LE FUNZIONI DELL'ADMIN
    
    
	
		// ACCESSO ALLA PAGINA menu_admin.html
	//INVIO MODULO
	
	@RequestMapping(value="invio_modulo", method=RequestMethod.POST)
	@ResponseBody
	public void AggiuntaStudente(@RequestParam("iddomanda1") int iddomanda1,@RequestParam("iddomanda2") int iddomanda2,
									@RequestParam("iddomanda3") int iddomanda3,@RequestParam("iddomanda4") int iddomanda4,
									@RequestParam("risposta1") int risposta1,@RequestParam("risposta2") int risposta2,
									@RequestParam("risposta3") int risposta3,@RequestParam("risposta4") int risposta4,
									HttpSession session) {
		Utenti utenteAttuale = (Utenti) session.getAttribute("utenteAttuale");
		feedbackRepository.aggiuntaFeedback(risposta1, utenteAttuale.getIdutente(), iddomanda1);
		feedbackRepository.aggiuntaFeedback(risposta2, utenteAttuale.getIdutente(), iddomanda2);
		feedbackRepository.aggiuntaFeedback(risposta3, utenteAttuale.getIdutente(), iddomanda3);
		feedbackRepository.aggiuntaFeedback(risposta4, utenteAttuale.getIdutente(), iddomanda4);
		LOGGER.info("Feedback salvato");
	}
	
	
	
	
	
	
	//REPORT FEEDBACKS
	
	@GetMapping("/reportfeedbacks") 
	public String reportfeedbacks(Model model,HttpSession session) {
		Utenti adminLogin = (Utenti) session.getAttribute("adminLogin");
		LOGGER.info("Admin in reportfeedbacks");
		//prendo tutte le domande e le stampo nell'html
		List <Domande> listadomande = domandaRepository.listadomande();
		//lista da far visualizzare nell'HTML
		List <PercFeedback> listapercentuali = new ArrayList<>();
		for (int i = 0; i < listadomande.size(); i++) {
			Domande domandacorrente = listadomande.get(i);
			PercFeedback percentuali = new PercFeedback();
			percentuali.setTestodomanda(domandacorrente.getTestodomanda());
			//prendo tuttle le risposte alla determinata domanda
			int nrisposte = feedbackRepository.numeroRisposteadomanda(domandacorrente.getIddomanda());
			percentuali.setNrisposte(nrisposte);
			//prendo il determinato voto della determinata domanda
			int voto_1 = feedbackRepository.numeroVotiperdomanda(domandacorrente.getIddomanda(), 1);
			int voto_2 = feedbackRepository.numeroVotiperdomanda(domandacorrente.getIddomanda(), 2);
			int voto_3 = feedbackRepository.numeroVotiperdomanda(domandacorrente.getIddomanda(), 3);
			int voto_4 = feedbackRepository.numeroVotiperdomanda(domandacorrente.getIddomanda(), 4);
			//calcolo la percentuale dei voti per domanda
			if (voto_1 == 0) {
				int percvoto1 = 0;
				model.addAttribute("perc_v_1",percvoto1);
				percentuali.setPerc_voto_1(percvoto1);
			} else {
				int percvoto1 = (100 * voto_1)/nrisposte;
				percentuali.setPerc_voto_1(percvoto1);
			}
			
			if (voto_2 == 0) {
				int percvoto2 = 0;
				percentuali.setPerc_voto_2(percvoto2);
			} else {
				int percvoto2 = (100 * voto_2)/nrisposte;
				percentuali.setPerc_voto_2(percvoto2);
			}
			
			if (voto_3 == 0) {
				int percvoto3 = 0;
				percentuali.setPerc_voto_3(percvoto3);
			} else {
				int percvoto3 = (100 * voto_3)/nrisposte;
				percentuali.setPerc_voto_3(percvoto3);
			}
			
			if (voto_4 == 0) {
				int percvoto4 = 0;
				percentuali.setPerc_voto_4(percvoto4);
			} else {
				int percvoto4 = (100 * voto_4)/nrisposte;
				percentuali.setPerc_voto_4(percvoto4);
			}	
			//calcolo media
			int media = ((1 *voto_1) + (2 *voto_2) + (3 *voto_3) + (4 *voto_4) )/nrisposte;
			switch (media) {
			case 1:
				percentuali.setMedia("NON SODDISFATTI");
				break;
			case 2:
				percentuali.setMedia("POCO SODDISFATTI");
				break;
			case 3:
				percentuali.setMedia("ABBASTANZA SODDISFATTI");
				break;
			case 4:
				percentuali.setMedia("MOLTO SODDISFATTI");
				break;
			default:
				percentuali.setMedia("Non calcolabile");
			}
			listapercentuali.add(percentuali);
		}
		model.addAttribute("listapercentuali",listapercentuali);
		
		return "reportfeedbacks";				
	}
	
	
	
    
        
 
    
    
    
    // INSERIMENTO STUDENTE
    
	@RequestMapping(value = "menu_admin", method = RequestMethod.GET)
    public ModelAndView get_menu_admin(HttpSession session) {
		Utenti adminLogin = (Utenti) session.getAttribute("adminLogin");
		Utenti utente = utenteRepository.findByIdutente(adminLogin.getIdutente());
		ModelAndView menuAdmin = new ModelAndView();
		if (utente != null) {
			menuAdmin.setViewName("menu_admin");
			menuAdmin.addObject("utente",utente);
			return menuAdmin;
				} else {
					return null;				
		}  
		
		
    }
    @RequestMapping(value = "visualizza_studente", method = RequestMethod.GET)
    public ModelAndView get_visualizza_studente(HttpSession session) {
    	Utenti adminLogin = (Utenti) session.getAttribute("adminLogin");
    	Utenti utente = utenteRepository.findByIdutente(adminLogin.getIdutente());
        ModelAndView listaUtenti=new ModelAndView();
        listaUtenti.setViewName("visualizza_studente");
        listaUtenti.addObject("utente",utente);
        listaUtenti.addObject("utenti", utenteRepository.visualizzaUtenti());
        LOGGER.info("Admin in visualizza");
        return listaUtenti;
    }
    
    
    // ACCESSO ALLA PAGINA inserisci_studente.html
    @RequestMapping(value = "inserisci_studente", method = RequestMethod.GET)
    public ModelAndView get_inserimento_studente(HttpSession session) {
    	Utenti adminLogin = (Utenti) session.getAttribute("adminLogin");
    	Utenti utente = utenteRepository.findByIdutente(adminLogin.getIdutente());
        ModelAndView listaUtenti=new ModelAndView();
        listaUtenti.setViewName("inserisci_studente");
        listaUtenti.addObject("utenti", utenteRepository.visualizzaUtenti()); 
        listaUtenti.addObject("utente",utente);
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
    @RequestMapping(value ="modifica_studente/{id}" , method = RequestMethod.GET)
    public ModelAndView get_modifica_studente(@PathVariable("id") int idutente, HttpSession session) {
    	Utenti adminLogin = (Utenti) session.getAttribute("adminLogin");
    	Utenti admin = utenteRepository.findByIdutente(adminLogin.getIdutente());
    	Utenti utente = utenteRepository.findByIdutente(idutente);
    	ModelAndView modificaStudente=new ModelAndView();
        modificaStudente.setViewName("modifica_studente");
       //non stampa il maledetto nome
        modificaStudente.addObject("admin",admin);
        modificaStudente.addObject("utenti", utente);  
        modificaStudente.addObject("idutente", utente.getIdutente());
        modificaStudente.addObject("nomestudente", utente.getNome());
        modificaStudente.addObject("cognomestudente", utente.getCognome()); 
        modificaStudente.addObject("emailstudente", utente.getEmail()); 
        modificaStudente.addObject("pwdstudente", utente.getPwd()); 
        modificaStudente.addObject("utenti", utenteRepository.visualizzaUtenti()); 
        LOGGER.info("Admin in modifica");
        return modificaStudente;
    }
          
       
 // MODIFICA DI UNO STUDENTE SUL DB
    @RequestMapping(value = "modifica_studente", method = RequestMethod.POST)
    public String post_modifica_studente(@Valid Utenti utenti, 
								    		@RequestParam(value = "idutente", required = false) Integer idutente,
								    		@RequestParam("nome") String nome, 
								    		@RequestParam("cognome") String cognome,
								            @RequestParam("email") String email, 
    									    @RequestParam(value = "pwd", required = false) String pwd, 
    									    BindingResult fields)
    {
//    	if(fields.hasErrors()) {
// 			return "redirect:/menu_admin";
// 		}
    	
      	String encryptedpassword;
		try   
	        {  						
				/* MessageDigest instance for MD5. */  
				MessageDigest m = MessageDigest.getInstance("MD5");  
				m.update(pwd.getBytes()); 
	        	/* Add plain-text password bytes to digest using MD5 update() method. */ 
				System.out.println(pwd);				
				
				
				/* Convert the hash value into bytes */   
				byte[] bytes = m.digest();  	              
	            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
	            StringBuilder s = new StringBuilder();  
	            for(int i=0; i< bytes.length ;i++) {  
	            	s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
	            }  	              
	            /* Complete hashed password in hexadecimal format */  
	            encryptedpassword = s.toString(); 		         
		        /*save user in db */
	            
											    	
	            Utenti nuovoutente = new Utenti();
	            nuovoutente.setIdutente(idutente);
	            nuovoutente.setNome(nome);
	            nuovoutente.setCognome(cognome);
	            nuovoutente.setEmail(email); 
	            
		    	nuovoutente.setPwd(encryptedpassword);
		    	utenteRepository.save(nuovoutente);
		    	  
//		    	if (nuovoutente != null) {
//		 		utenteRepository.save(nuovoutente);
		 		LOGGER.info("Utente modificato correttamente");		 		 
		 			return "redirect:/visualizza_studente";  
	         	}
		catch (NoSuchAlgorithmException e) {  
	        e.printStackTrace();  
	        LOGGER.info("Utente non modificato");
			return "redirect:/visualizza_studente";
		} 
		} 
    
 
    // RIMOZIONE STUDENTE
    
    // Metodo per rimuovere un utente senza andare in un altra pagina
    
    // RIMOZIONE DI UNO STUDENTE DAL DB
    @RequestMapping(value = "rimuovi_studente/{id}", method = RequestMethod.GET)
    public String post_rimuovi_studente(@PathVariable("id") Integer id) {
    	utenteRepository.deleteById(id);    	
    	LOGGER.info("Admin ha rimosso uno studente");
    	
        return "redirect:/visualizza_studente";
	}
    


 
}