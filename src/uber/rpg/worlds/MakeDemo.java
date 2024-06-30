package uber.rpg.worlds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.rpg.entities.Item;
import application.rpg.entities.Location;
import uber.rpg.entities.Adventure;
import uber.rpg.entities.enums.Property;
import uber.rpg.utilities.UberFileUtilities;

public class MakeDemo {

	/* This makes a simple "adventure" with 4 rooms
	 
	  [start] == [room 2]
	    ||            ||
	  [room 3 ] == [room 4]
	 
	*/
	public static void main(String[] args) {
		Map<Integer, Location> map = new HashMap<>();
		int exits1[] = {0,2,3,0};
		Location room1 = new Location("Living room",1,exits1);
		room1.setDescription("There is couch, a coffee table, and a chair.");
		Item key = new Item("key","copper key");
		key.addProperty(Property.KEY, "");
		room1.addItem(key);
		// The image is optional. Image will be last image shown if not found.
		room1.setImageFilename("images//living-room.jpg");
		map.put(1,room1);
		int exits2[] = {0,0,4,1};
		Location room2 = new Location("Dining Room",2,exits2);
		room2.setDescription("There is a wooden table with one chair on each side.");
		room2.setImageFilename("images//dining-room.jpg");
		map.put(2,room2);
		int exits3[] = {1,4,0,0};
		Location room3 = new Location("Kitchen",3,exits3);
		room3.setDescription("There is a fridge, stove, and a pantry.");
		Item apple = new Item("apple","A red apple.");
		apple.setArticle("an");
		apple.addProperty(Property.EDIBLE, "Crunch");
		room3.addItem(apple);
		room3.addItem(new Item("coffee mug","An empty black coffee mug."));
		room3.setImageFilename("images//kitchen.jpg"); 
		map.put(3,room3);
		int exits4[] = {2,0,0,3};
		Location room4 = new Location("Den",4,exits4);
		room4.setDescription("There is a bookcase filled with old books. A padded chair is in the middle of the room.");
		room4.setImageFilename("images//den.jpg"); 
		Item chest = new Item("chest","wooden chest");
		chest.addProperty(Property.LOCKED, key); // can be unlocked with key
		Item tome = new Item("tome","A dusty old tome.");
		tome.setInsideItem(chest);
		room4.addItem(chest);
		map.put(4,room4);
		
		
		List<Location> locations = new ArrayList<>(map.values());
		
		UberFileUtilities.write(locations, ".\\demo.dat");
		
		Adventure adv = new Adventure("Demo","Demo with four rooms.",".\\demo.dat");
		UberFileUtilities.writeAdventureDetails(adv);
		
		UberFileUtilities.displayAdventures();

	}

}
