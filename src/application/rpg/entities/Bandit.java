package application.rpg.entities;

public class Bandit extends CharacterClass{
	public Bandit(String name) {
		setName(name);
		setCharacterClass("Bandit");
		setHpIncrement(10);
		setHp(10);
	}

}
