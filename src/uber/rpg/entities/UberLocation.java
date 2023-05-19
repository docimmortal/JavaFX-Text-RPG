package uber.rpg.entities;

import java.util.HashMap;
import java.util.Map;

import application.rpg.entities.Location;

public class UberLocation extends Location {
	private static final long serialVersionUID = 1L;
	
	// item(s) at this location;
	private Map<String, UberItem> itemMap;

	public UberLocation(String name, int thisLocationId, int[] locationIds) {
		super(name, thisLocationId, locationIds);
		itemMap = new HashMap<>();
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

}
