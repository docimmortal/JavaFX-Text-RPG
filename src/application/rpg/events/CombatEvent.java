package application.rpg.events;

import application.rpg.entities.CharacterClass;
import application.rpg.entities.GameContent;
import application.rpg.entities.Monster;

public class CombatEvent extends Event {

	public static String doAction(String command, GameContent content) {
		
		int characterSpeed=0; // this will come from content.getCharacter().getSpeed() or something similar.
		int monsterSpeed=0; // this will come from content.getMonster().getSpeed() or something similar.
		
		if (characterSpeed < monsterSpeed) {
			attack(content.getMonster(), content.getCharacter());
			attack(command, content.getCharacter(), content.getMonster());
		} else {
			attack(command, content.getCharacter(), content.getMonster());
			attack(content.getMonster(), content.getCharacter());
		}
		return command;
	}
	
	private static void attack(Monster attacker, CharacterClass defender) {
		
	}
	
	private static void attack(String command, CharacterClass attacker, Monster defender) {
		
	}
}
