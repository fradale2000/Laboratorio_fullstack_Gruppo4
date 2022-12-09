package com.team2.itsincom.model;

public class PercFeedback {
	private String testodomanda;
	
	private int nrisposte;
	private int perc_voto_1;
	private int perc_voto_2;
	private int perc_voto_3;
	private int perc_voto_4;
	
	private String media ;

	public PercFeedback() {}

	public PercFeedback(String testodomanda, int nrisposte, int perc_voto_1, int perc_voto_2, int perc_voto_3,
			int perc_voto_4, String media) {
		super();
		this.testodomanda = testodomanda;
		this.nrisposte = nrisposte;
		this.perc_voto_1 = perc_voto_1;
		this.perc_voto_2 = perc_voto_2;
		this.perc_voto_3 = perc_voto_3;
		this.perc_voto_4 = perc_voto_4;
		this.media = media;
	}

	public String getTestodomanda() {
		return testodomanda;
	}

	public void setTestodomanda(String testodomanda) {
		this.testodomanda = testodomanda;
	}

	public int getNrisposte() {
		return nrisposte;
	}

	public void setNrisposte(int nrisposte) {
		this.nrisposte = nrisposte;
	}

	public int getPerc_voto_1() {
		return perc_voto_1;
	}

	public void setPerc_voto_1(int perc_voto_1) {
		this.perc_voto_1 = perc_voto_1;
	}

	public int getPerc_voto_2() {
		return perc_voto_2;
	}

	public void setPerc_voto_2(int perc_voto_2) {
		this.perc_voto_2 = perc_voto_2;
	}

	public int getPerc_voto_3() {
		return perc_voto_3;
	}

	public void setPerc_voto_3(int perc_voto_3) {
		this.perc_voto_3 = perc_voto_3;
	}

	public int getPerc_voto_4() {
		return perc_voto_4;
	}

	public void setPerc_voto_4(int perc_voto_4) {
		this.perc_voto_4 = perc_voto_4;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	};
	
}
