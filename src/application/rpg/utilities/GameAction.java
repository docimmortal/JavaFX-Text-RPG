package application.rpg.utilities;

import java.util.List;

import application.rpg.entities.GameContent;
import application.rpg.entities.Location;
import application.rpg.entities.Player;
import application.rpg.events.ChurchEvent;
import application.rpg.events.CombatEvent;
import application.rpg.events.LocationEvent;
import application.rpg.events.ShopEvent;

public class GameAction {

	private String result;
	
	private boolean debugOn=false;
	
	public void doAction(String action, GameContent gameContent) {
		action=upperCamelCase(action);
		
		Location current = gameContent.getCurrentLocation();
		Player player = gameContent.getPlayer();
		
		Debug.msg(debugOn,"HERE: "+current.getThisLocationId());
		
		if (action.equals("?") || action.equals("H") || action.equals("Help")) {
			result= getCommands();
			result+=current.getInfo();
		} else if (action.equals("Inv")) {
			List<String> items = player.getInventory();
			result="Inventory: ";
			String stuff="";
			for (String item:items) {
				stuff+=item+"_";
			}
			if (stuff.length()>0) {
				result+=stuff.substring(0,stuff.length()-1).replaceAll("_", ", ");
				result+="\n";
			} else {
				result+="Nothing\n";
			}
			result+=current.getInfo();
		} else if (!action.equals("Exit")){
			String locationName = gameContent.getCurrentLocation().getName();
			if (locationName.equals("Shop")) {
				result=ShopEvent.doAction(action, gameContent);
			} else if (locationName.equals("Church")) {
				result=ChurchEvent.doAction(action, gameContent);
			} else if (locationName.equals("Forest")) {
				result=CombatEvent.doAction(action, gameContent);
			} else {
				result=LocationEvent.doAction(action, gameContent);
			}
		} else {
			result=action;
		}
	}
	
	public String getCommands() {
		return "Commands: north, south, east, west, take, inv, exit\n";
	}
	
	public String getResult() {
		return result;
	}
	
	public String upperCamelCase(String action) {
		return action.substring(0,1).toUpperCase()+action.substring(1).toLowerCase();
	}
}
