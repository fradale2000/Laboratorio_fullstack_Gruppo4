package com.lab.fullstsck_gruppo4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab.fullstsck_gruppo4.dao.UtentiDao;
@Controller
@RequestMapping
public class MainController {
	
	@Autowired
	private UtentiDao utentiRepository;
	
	// REGISTRAZIONE
	@GetMapping("/registrazione")  
	public String registrazione() {
			return "registrazione";
	}
	
	
}
