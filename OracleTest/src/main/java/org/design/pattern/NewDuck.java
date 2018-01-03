package org.design.pattern;

public class NewDuck extends Duck {

	private String emailAddress;
	private String team;
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public String getTeam() {
		return team;
	}

	public void setEmailAddress(String emailAddress) {
		System.out.println("NewDuck: inside setter method - setEmailAddress");
		this.emailAddress = emailAddress;
	}

	public void setTeam(String team) {
		System.out.println("NewDuck: inside setter method - setTeam");
		this.team = team;
	}

	@Override
	public void display() {
		System.out.println("I am a new duck.");
	}

}
