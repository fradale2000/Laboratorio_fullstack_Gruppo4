package com.team2.itsincom.model;

public class DateDiffFeedback {

	private Feedback feedback;
	private int datediff;
	
	public DateDiffFeedback() {}

	public DateDiffFeedback(Feedback feedback, int datediff) {
		super();
		this.feedback = feedback;
		this.datediff = datediff;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public int getDatediff() {
		return datediff;
	}

	public void setDatediff(int datediff) {
		this.datediff = datediff;
	}
	
	
}
