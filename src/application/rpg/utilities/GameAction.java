package application.rpg.utilities;

import java.util.List;

import application.rpg.entities.GameContent;
import uber.rpg.entities.UberLocation;
import uber.rpg.entities.UberPlayer;

public class GameAction {

	private String result;
	
	private boolean debugOn=false;
	
	public void doAction(String action, GameContent gameContent) {
		action=upperCamelCase(action);
		gameContent.getPlayer().addXp(5);
		
		UberLocation current = gameContent.getCurrentLocation();
		UberPlayer player = gameContent.getPlayer();
		
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
		
		if (action.equals("?") || action.equals("H") || action.equals("Help")) {
			result= getCommands();
			result+=current.getInfo();
		} else if (loc > 0) {
			gameContent.setLocationId(loc);
			current = gameContent.getCurrentLocation();
			result= "Walked "+action.toLowerCase()+" to "+current.getName()+".\n";
			result+=current.getInfo();
		} else if (loc == -1 && !action.equals("Exit")) {
			if (action.contains("Take")&& current instanceof UberLocation) {
				boolean success = doTake(action.substring(5), current, player);
				if (success) {
					result="Took "+action.substring(5)+".\n";
				} else {
					result="There is no "+action.substring(5)+" here.\n";
				}
				result+=current.getInfo();
				gameContent.setCurrentLocation(current);
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
		} else {
			result=action;
		}
	}
	
	public String getCommands() {
		return "Commands: north, south, east, west, take, inv, exit\n";
	}
	
	private boolean doTake(String item, UberLocation location, UberPlayer player) {
		boolean found=location.containsItem(item);
		if (found) {
			player.addItem(location.getItem(item));
			location.removeItem(item);
		}
		return found;
	}
	
	public String getResult() {
		return result;
	}
	
	public String upperCamelCase(String action) {
		return action.substring(0,1).toUpperCase()+action.substring(1).toLowerCase();
	}
}
