package application.rpg.entities;

public class Wizard extends CharacterClass{
	public Wizard(String name) {
		setName(name);
		setCharacterClass("Wizard");
		setHpIncrement(8);
		setHp(8);
	}
}
