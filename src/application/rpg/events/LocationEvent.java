package application.rpg.events;

import java.util.List;

import application.rpg.entities.GameContent;
import application.rpg.entities.Location;
import application.rpg.entities.Player;
import application.rpg.utilities.Debug;

public class LocationEvent extends Event {

	private static boolean debugOn=false;
	private static String result;
	
	public static String doAction(String action, GameContent gameContent) {
		Location current = gameContent.getCurrentLocation();
		Player player = gameContent.getPlayer();
		result="";
		
		int loc=-1;
		Debug.msg(debugOn,"HERE: "+current.getThisLocationId());
		
		if (action.equals("North")) {
			loc=current.getNorthLocationId();
			Debug.msg(debugOn,"Checking north: "+loc);
		} else if (action.equals("East")) {
			loc=current.getEastLocationId();
			Debug.msg(debugOn,"Checking east: "+loc);
		} else if (action.equals("South")) {
			loc=current.getSouthLocationId();
			Debug.msg(debugOn,"Checking south: "+loc);
		} else if (action.equals("West")) {
			loc=current.getWestLocationId();
			Debug.msg(debugOn,"Checking west: "+loc);
		}
		
		// we were able to move to a new location if loc > 0
		if (loc > 0) {
			gameContent.setLocationId(loc);
			current = gameContent.getCurrentLocation();
			result= "Walked "+action.toLowerCase()+" to "+current.getName()+".\n";
			result+=current.getInfo();
		} else if (loc == -1) {
			if (action.contains("Take")&& current instanceof Location) {
				if (action.length() > 6 ) {
					boolean success = doTake(action.substring(5), current, player);
					System.out.println(">>>>>>>>"+action.substring(5)+" : "+success);
					if (success) {
						result="Took "+action.substring(5)+".\n";
					} else {
						result="There is no "+action.substring(5)+" here.\n";
					}
					result+=current.getInfo();
					gameContent.setCurrentLocation(current);
				} else {
					result = "Take what?\n";
					result+=current.getInfo();
				}
			} else if (action.equals("Inv")) {
				List<String> items = player.getInventory();
				result="Inventory: ";
				String stuff="";
				for (String item:items) {
					stuff+=item+"_";
				}
				result+=stuff.substring(0,stuff.length()-1).replaceAll("_", ", ");
				result+="\n"+current.getInfo();
			} else {
				result = action+" does something (total XP: "+player.getXp()+")\n";
				result+=current.getInfo();
			}
		} else if (loc == 0){
			result="You cannot go "+action.toLowerCase()+".\n";
		}
		return result;
	}
	
	public String getResult() {
		return result;
	}
	
	private static boolean doTake(String item, Location location, Player player) {
		System.out.println(location);
		boolean found=location.containsItem(item);
		if (found) {
			player.addItem(location.getItem(item));
			location.removeItem(item);
		}
		return found;
	}
	
}
