package uber.rpg.entities;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

import application.rpg.utilities.FileUtilities;

public class Adventure implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String description;
	private String uberLocationFileName;
	
	public Adventure(String name, String description, String uberLocationFileName) {
		this.name = name;
		this.description = description;
		this.uberLocationFileName = uberLocationFileName;
	}
	
	public void displayInfo() {
		System.out.println(name+"\n"+description);
	}
	
	@SuppressWarnings("unchecked")
	public Map<Integer, UberLocation> loadGame() {
		return (Map<Integer, UberLocation>) FileUtilities.read(uberLocationFileName);
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, uberLocationFileName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adventure other = (Adventure) obj;
		return Objects.equals(name, other.name) && Objects.equals(uberLocationFileName, other.uberLocationFileName);
	}

	@Override
	public String toString() {
		return name+"\n"+description;
	}
}
