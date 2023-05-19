package uber.rpg.worlds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.rpg.utilities.FileUtilities;
import uber.rpg.entities.Adventure;
import uber.rpg.entities.UberLocation;
import uber.rpg.utilities.UberFileUtilities;

public class MakeDemo {

	/* This makes a simple "adventure" with 4 rooms
	 
	  [start] == [room 2]
	    ||            ||
	  [room 3 ] == [room 4]
	 
	*/
	public static void main(String[] args) {
		Map<Integer, UberLocation> map = new HashMap<>();
		int exits1[] = {0,2,3,0};
		UberLocation room1 = new UberLocation("Living room",1,exits1);
		room1.setDescription("There is couch, a coffee table, and a chair.");
		map.put(1,room1);
		int exits2[] = {0,0,4,1};
		UberLocation room2 = new UberLocation("Dining Room",2,exits2);
		room2.setDescription("There is a square wooden table with one chair on each side.");
		map.put(2,room2);
		int exits3[] = {1,4,0,0};
		UberLocation room3 = new UberLocation("Kitchen",3,exits3);
		room3.setDescription("There is a fridge, stove, and a pantry.");
		map.put(3,room3);
		int exits4[] = {2,0,0,3};
		UberLocation room4 = new UberLocation("Den",4,exits4);
		room4.setDescription("There is a bookcase filled with old books. A padded chair is in the middle of the room.");
		map.put(4,room4);
		
		
		List<UberLocation> locations = new ArrayList<>(map.values());
		
		UberFileUtilities.write(locations, ".\\demo.dat");
		
		Adventure adv = new Adventure("Demo","Demo with four rooms.",".\\demo.dat");
		UberFileUtilities.writeAdventureDetails(adv);
		
		UberFileUtilities.displayAdventures();

	}

}
