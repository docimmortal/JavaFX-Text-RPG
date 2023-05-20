package uber.rpg.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.rpg.entities.Player;

public class UberPlayer extends Player {

	private static final long serialVersionUID = 1L;
	private Map<String, UberItem> inventory;

	public UberPlayer() {
		inventory = new HashMap<>();
	}
	
	public UberItem getItem(String item) {
		return inventory.get(item);
	}
	
	public void addItem(UberItem item) {
		inventory.put(item.getName(), item);
	}
	
	public boolean hasItem(String item) {
		return inventory.containsKey(item);
	}
	
	public List<String> getInventory() {
		return new ArrayList<>(inventory.keySet());
	}
}
