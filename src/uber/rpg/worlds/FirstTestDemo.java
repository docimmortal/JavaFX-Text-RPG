package uber.rpg.worlds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.rpg.entities.Location;
import uber.rpg.entities.Adventure;
import uber.rpg.utilities.UberFileUtilities;

public class FirstTestDemo {

	public static void main(String[] args) {
		Map<Integer, Location> map = new HashMap<>();
		int[] arr1 = {2,0,0,0};
		int[] arr2 = {0,0,1,0};
		map.put(1, new Location("Forest",1, arr1));
		map.put(2, new Location("Fields",2, arr2));
		
		List<Location> locations = new ArrayList<>(map.values());
		
		UberFileUtilities.write(locations, ".\\test.dat");
		
		Adventure adv = new Adventure("Test","First Test.",".\\test.dat");
		UberFileUtilities.writeAdventureDetails(adv);
		
		UberFileUtilities.displayAdventures();
	}
}
