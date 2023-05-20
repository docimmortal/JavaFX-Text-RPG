package uber.rpg.entities;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import application.rpg.entities.Location;

public class UberLocation extends Location {
	private static final long serialVersionUID = 1L;
	
	// item(s) at this location;
	private Map<String, UberItem> itemMap;
	
	private String imageFilename;

	public UberLocation(String name, int thisLocationId, int[] locationIds) {
		super(name, thisLocationId, locationIds);
		itemMap = new HashMap<>();
	}
	
	public String getImageFilename() {
		return imageFilename;
	}



	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
	}



	public void addItem(UberItem item) {
		itemMap.put(item.getName(), item);
	}
	
	public boolean containsItem(String name) {
		return itemMap.containsKey(name);
	}
	
	public UberItem getItem(String name) {
		return itemMap.get(name);
	}
	
	public void removeItem(String name) {
		itemMap.remove(name);
	}
	
	public String getInfo() {
		String out = getName()+"\n";
		if (getDescription().length()>0) {
			out+=getDescription()+"\n";
		}
		if (itemMap.size() > 0) {
			Collection<UberItem> items = itemMap.values();
			String itemNames="";
			for (UberItem item: items) {
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

}
