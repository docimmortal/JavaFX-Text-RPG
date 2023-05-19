package application.rpg.entities;

import java.io.Serializable;
import java.util.Arrays;

public class Location implements Serializable{

	private static final long serialVersionUID = 6665362498440259025L;
	
	private int thisLocationId;
	private String name;
	private String description;
	private int[] locationIds;
	
	public Location(String name, int thisLocationId, int[] locationIds) {
		this.name = name;
		this.thisLocationId=thisLocationId;
		description="";
		//0=north, 1=east, 2=south, 3=west
		if (locationIds != null && locationIds.length==4) {
			this.locationIds=locationIds;
		} else {
			locationIds = new int[4];
		}
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getThisLocationId() {
		return thisLocationId;
	}
	
	public int getNorthLocationId() {
		return locationIds[0];
	}
	
	public int getEastLocationId() {
		return locationIds[1];
	}
	
	public int getSouthLocationId() {
		return locationIds[2];
	}
	
	public int getWestLocationId() {
		return locationIds[3];
	}
	
	public String getExits() {
		StringBuilder sb = new StringBuilder("Exits are: ");
		StringBuilder exs = new StringBuilder();
		if (locationIds[0]!=0) {
			exs.append("North ");
		}
		if (locationIds[2]!=0) {
			exs.append("South ");
		}
		if (locationIds[1]!=0) {
			exs.append("East ");
		}
		if (locationIds[3]!=0) {
			exs.append("West");
		}
		sb.append(exs.toString().trim().replaceAll(" ", ", "));
		return sb.toString();
	}
	
	public String getInfo() {
		String out = name+"\n";
		if (description.length()>0) {
			out+=description+"\n";
		}
		out+=getExits()+"\n";
		return out;
	}

	@Override
	public String toString() {
		return "Location [thisLocationId=" + thisLocationId + ", name=" + name + ", description=" + description
				+ ", locationIds=" + Arrays.toString(locationIds) + "]";
	}

}
