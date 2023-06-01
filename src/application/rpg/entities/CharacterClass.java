package application.rpg.entities;

public class CharacterClass {
		private int hp = 0;
		private int xp = 0;
		private int hpIncrement = 0;
		private int level = 1;
		private String characterClass = "";
		private String characterName = "";
		
		protected void setName(String newName) {
			characterName = newName;
		}
		protected void setCharacterClass(String characterClass) {
			this.characterClass = characterClass;
			setHp(hpIncrement);
		}
		public String getCharacterClass() {
			return characterClass;
		}
		
		public void setXp(int hp) {
			this.hp=hp;
		}
		public void addXp(int xpGain) {
			xp += xpGain;
			setLevel();
		}
		public int getXp() {
			return xp;
		}
		
		public void setHpIncrement(int hpIncrement) {
			this.hpIncrement = hpIncrement;
		}
		
		public int getLevel() {
			return level;
		}
		private void setLevel() {
			int xpOverLevels = xp;
			int levelUps = 0;
			int i;
			
			for(i=1; i<level+1; i++) {
				xpOverLevels -= (i*10);
			}
			
			while(xpOverLevels>=(i*10)) {
				xpOverLevels -= (i*10);
				levelUps++;
				i++;
			}
			if(levelUps > 0){
			level += levelUps;
			int currentHP = hp;
			int hpDifference;
			setHp(hpIncrement * level);
			hpDifference = hp - currentHP;
			System.out.println(characterName+" has leveled up to level "+level+".");
			System.out.println(characterName+" has gained "+hpDifference+" hitpoints and now has "+hp+" hitpoints.");
			}
		}
		
		protected void setHp(int hp) {
			this.hp=hp;
			/*
			if(characterClass.equals("Bandit")) {
				hp = 10 * level;
			}
			else if(characterClass.equals("Fighter")) {
				hp = 12 * level;
			}
			else if(characterClass.equals("Wizard")) {
				hp = 8 * level;
			}*/
		}
		public int getHP() {
			return hp;
		}
}
