package uber.rpg.entities;

import java.io.Serializable;

public class UberItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String description;
	
	public UberItem(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "UberItem [name=" + name + ", description=" + description + "]";
	}
	
	
}
