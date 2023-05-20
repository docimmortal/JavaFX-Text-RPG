package application.rpg.entities;

import java.util.HashMap;
import java.util.Map;

import uber.rpg.entities.UberLocation;
import uber.rpg.entities.UberPlayer;

public class GameContent {

	private UberPlayer player;
	private int currentLocation;
	private Map<Integer, UberLocation> locationMap;

	public GameContent() {
		currentLocation=1;
		player=new UberPlayer();
		locationMap = new HashMap<>();
	}
	
	public Location getLocation(int locationId) {
		return locationMap.get(locationId);
	}
	
	public void setLocationId(int locationId) {
		this.currentLocation=locationId;
	}
	
	public UberLocation getCurrentLocation() {
		return locationMap.get(currentLocation);
	}
	public void setCurrentLocation(UberLocation uc) {
		locationMap.put(currentLocation, uc);
	}
	
	public void updateLocation(int newLocationId) {
		currentLocation = newLocationId;
	}

	public void setLocationMap(Map<Integer, UberLocation> locationMap) {
		this.locationMap = locationMap;
	}

	public UberPlayer getPlayer() {
		return player;
	}

	public void setPlayer(UberPlayer player) {
		this.player = player;
	}
	
}
