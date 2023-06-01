package application.rpg.utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.rpg.entities.Location;

public class Convert {

	public static Map<Integer, Location> toMap(List<Location> locations) {
		Map<Integer, Location> map = new HashMap<>();
		for (int i=0; i< locations.size(); i++) {
			map.put(i+1, locations.get(i));
		}
		return map;
	}
	
}
