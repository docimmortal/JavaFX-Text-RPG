package application.rpg.utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.rpg.entities.Location;
import uber.rpg.entities.UberLocation;

public class Convert {

	public static Map<Integer, Location> toMap(List<Location> locations) {
		Map<Integer, Location> map = new HashMap<>();
		for (int i=0; i< locations.size(); i++) {
			map.put(i+1, locations.get(i));
		}
		return map;
	}
	
	public static Map<Integer, UberLocation> toUberMap(List<UberLocation> locations) {
		Map<Integer, UberLocation> map = new HashMap<>();
		for (int i=0; i< locations.size(); i++) {
			map.put(i+1, locations.get(i));
		}
		return map;
	}
}
