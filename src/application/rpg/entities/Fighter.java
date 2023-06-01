package application.rpg.entities;

public class Fighter extends CharacterClass{
	public Fighter(String name) {
		setName(name);
		setCharacterClass("Fighter");
		setHpIncrement(12);
		setHp(12);
	}

}
