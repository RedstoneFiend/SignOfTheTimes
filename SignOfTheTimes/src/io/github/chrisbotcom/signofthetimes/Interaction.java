package io.github.chrisbotcom.signofthetimes;

public class Interaction {

	String command;
	String name;
	String action;
	
	/**
	 * 
	 * @param command
	 * @param name
	 * @param action
	 */
	public Interaction(String command, String name, String action) {
		this.command = command;
		this.name = name;
		this.action = action;
	}
	
	public String getCommand() {
		return command;
	}
	
	public void setCommand(String command) {
		this.command = command;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
}
