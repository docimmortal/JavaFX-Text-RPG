package application.rpg.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Location implements Serializable{

	private static final long serialVersionUID = 6665362498440259025L;
	
	private int thisLocationId;
	private String name;
	private String description;
	private int[] locationIds;
	
	// item(s) at this location;
	private Map<String, Item> itemMap;
	private String imageFilename;
	
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
		itemMap = new HashMap<>();
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

	public String getImageFilename() {
		return imageFilename;
	}

	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
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
	
	public void addItem(Item item) {
		itemMap.put(item.getName(), item);
	}
	
	public Item getItem(String name) {
		return itemMap.get(name);
	}
	
	public void removeItem(String name) {
		itemMap.remove(name);
	}
	
	public boolean containsItem(String name) {
		return itemMap.containsKey(name);
	}
	
	public String getInfo() {
		String out = getName()+"\n";
		if (getDescription().length()>0) {
			out+=getDescription()+"\n";
		}
		if (itemMap.size() > 0) {
			Collection<Item> items = itemMap.values();
			String itemNames="";
			for (Item item: items) {
				if (item.getInsideItem() == null) {
					itemNames+=item.getArticleAndName()+"_";
				}
			}
			if (itemNames.length()>0) {
				itemNames=itemNames.trim().substring(0, itemNames.length()-1).replaceAll("_", ", ");
				out+="You see: "+itemNames+"\n";
			}
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
