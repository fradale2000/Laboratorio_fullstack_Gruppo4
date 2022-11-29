package com.lab.fullstsck_gruppo4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.lab.fullstsck_gruppo4.dao.UtentiDao;

public class MainController {
	
	@Autowired
	private UtentiDao utentiRepository;
	
	// REGISTRAZIONE
	@GetMapping("/registrazione") 
	public String registrazione() {
			return "registrazione";
	}
}
