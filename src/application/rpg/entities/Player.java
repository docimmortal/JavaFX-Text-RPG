package application.rpg.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int xp;

	private Map<String, Item> inventory;

	public Player() {
		inventory = new HashMap<>();
	}
	
	public Item getItem(String item) {
		return inventory.get(item);
	}
	
	public void addItem(Item item) {
		inventory.put(item.getName(), item);
	}
	
	public boolean hasItem(String item) {
		return inventory.containsKey(item);
	}
	
	public List<String> getInventory() {
		return new ArrayList<>(inventory.keySet());
	}
	public int getXp() {
		return xp;
	}

	public void addXp(int xp) {
		this.xp += xp;
	}

}
