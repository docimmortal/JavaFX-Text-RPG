package application.rpg.entities;

import java.util.HashMap;
import java.util.Map;

public class GameContent {

	private Player player;
	private int currentLocation;
	private Map<Integer, Location> locationMap;

	public GameContent() {
		currentLocation=1;
		player=new Player();
		locationMap = new HashMap<>();
	}
	
	public Location getLocation(int locationId) {
		return locationMap.get(locationId);
	}
	
	public void setLocationId(int locationId) {
		this.currentLocation=locationId;
	}
	
	public Location getCurrentLocation() {
		return locationMap.get(currentLocation);
	}
	
	public void updateLocation(int newLocationId) {
		currentLocation = newLocationId;
	}

	public void setLocationMap(Map<Integer, Location> locationMap) {
		this.locationMap = locationMap;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
