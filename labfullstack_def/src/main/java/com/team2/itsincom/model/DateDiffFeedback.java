package com.team2.itsincom.model;

public class DateDiffFeedback {

	private int idfeedback;
	private int voto;
	private String datafeedback;
	private int idutente;
	private int iddomanda;
	private int datediff;
	
	public DateDiffFeedback() {}

	public DateDiffFeedback(int idfeedback, int voto, String datafeedback, int idutente, int iddomanda, int datediff) {
		this.idfeedback = idfeedback;
		this.voto = voto;
		this.datafeedback = datafeedback;
		this.idutente = idutente;
		this.iddomanda = iddomanda;
		this.datediff = datediff;
	}

	public int getIdfeedback() {
		return idfeedback;
	}

	public void setIdfeedback(int idfeedback) {
		this.idfeedback = idfeedback;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public String getDatafeedback() {
		return datafeedback;
	}

	public void setDatafeedback(String datafeedback) {
		this.datafeedback = datafeedback;
	}

	public int getIdutente() {
		return idutente;
	}

	public void setIdutente(int idutente) {
		this.idutente = idutente;
	}

	public int getIddomanda() {
		return iddomanda;
	}

	public void setIddomanda(int iddomanda) {
		this.iddomanda = iddomanda;
	}

	public int getDatediff() {
		return datediff;
	}

	public void setDatediff(int datediff) {
		this.datediff = datediff;
	}	
	
}
