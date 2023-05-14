package application.rpg.utilities;

import application.rpg.entities.GameContent;

public class GameAction {

	private String result;
	
	private boolean debugOn=false;
	
	public void doAction(String action, GameContent gameContent) {
		action=upperCamelCase(action);
		gameContent.getPlayer().addXp(5);
		
		int loc=-1;
		Debug.msg(debugOn,"HERE: "+gameContent.getCurrentLocation().getThisLocationId());
		if (action.equals("North")) {
			loc=gameContent.getCurrentLocation().getNorthLocationId();
			Debug.msg(debugOn,"Checking north: "+loc);
		} else if (action.equals("East")) {
			loc=gameContent.getCurrentLocation().getEastLocationId();
			Debug.msg(debugOn,"Checking east: "+loc);
		} else if (action.equals("South")) {
			loc=gameContent.getCurrentLocation().getSouthLocationId();
			Debug.msg(debugOn,"Checking south: "+loc);
		} else if (action.equals("West")) {
			loc=gameContent.getCurrentLocation().getWestLocationId();
			Debug.msg(debugOn,"Checking west: "+loc);
		}
		
		if (loc > 0) {
			gameContent.setLocationId(loc);
			result= "Walked "+action.toLowerCase()+" to "+gameContent.getCurrentLocation().getName()+".\n";
			result+=gameContent.getCurrentLocation().getInfo();
		} else if (loc == -1 && !action.equals("Exit")) {
			result = action+" does something (total XP: "+gameContent.getPlayer().getXp()+")\n";
		} else if (loc == 0){
			result="You cannot go "+action.toLowerCase()+".\n";
		} else {
			result=action;
		}
	}
	
	public String getResult() {
		return result;
	}
	
	public String upperCamelCase(String action) {
		return action.substring(0,1).toUpperCase()+action.substring(1).toLowerCase();
	}
}
