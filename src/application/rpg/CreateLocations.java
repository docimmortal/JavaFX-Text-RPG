package application.rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.rpg.entities.Location;
import application.rpg.utilities.FileUtilities;

public class CreateLocations {

	public static void main(String[] args) {
		Map<Integer, Location> map = new HashMap<>();
		int[] arr1 = {2,0,0,0};
		int[] arr2 = {0,0,1,0};
		map.put(1, new Location("Forest",1, arr1));
		map.put(2, new Location("Fields",2, arr2));
		
		List<Location> locations = new ArrayList<>(map.values());
		
		FileUtilities.writeLocations(locations, ".\\locations.dat");
	}
}
