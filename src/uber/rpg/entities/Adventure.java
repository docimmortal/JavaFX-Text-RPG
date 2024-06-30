  package uber.rpg.entities;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

import application.rpg.entities.Location;
import application.rpg.utilities.FileUtilities;

public class Adventure implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String description;
	private String locationFileName;
	
	public Adventure(String name, String description, String locationFileName) {
		this.name = name;
		this.description = description;
		this.locationFileName = locationFileName;
	}
	
	public void displayInfo() {
		System.out.println(name+"\n"+description);
	}
	
	@SuppressWarnings("unchecked")
	public Map<Integer, Location> loadGame() {
		return (Map<Integer, Location>) FileUtilities.read(locationFileName);
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, locationFileName);
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
		return Objects.equals(name, other.name) && Objects.equals(locationFileName, other.locationFileName);
	}

	@Override
	public String toString() {
		return name+"\n"+description;
	}
}
