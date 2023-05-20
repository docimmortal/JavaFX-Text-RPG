package application.rpg.utilities;
import java.util.ArrayList;
public class DiceRoller {
	private ArrayList<Integer> dice;
	private ArrayList<Integer> min;
	
	public DiceRoller() {
	}
	public DiceRoller(ArrayList<Integer> dice) {
		this.dice = dice;
		for(int i=0; i<dice.size(); i++) {
			min.add(1);
		}
	}
	public DiceRoller(ArrayList<Integer> dice, ArrayList<Integer> min) {
		this.dice = dice;
		int nextMin;
		for(int i=0; i<min.size(); i++) {
			if(i<dice.size()) {
				nextMin = min.get(i);
				min.add(nextMin);
			}
			else {
				break;
			}
		}
		for(int i=min.size(); i<dice.size(); i++) {
			min.add(1);
		}
	}
	
	public void addDie(int die) {
		dice.add(die);
		min.add(1);
	}
	public void addDie(int die, int minimum) {
		dice.add(die);
		min.add(minimum);
	}
	
	public void removeDie(int die, int minimum) {
		boolean removedSomething = false;
		for(int i=0; i<dice.size(); i++) {
			if(dice.get(i) == die && min.get(i) == minimum){
				dice.remove(i);
				min.remove(i);
				removedSomething = true;
				break;
			}
		}
		if(removedSomething) {
			System.out.println("A die was removed.");
		}
		else {
			System.out.println("A die with the given perimeters was not found.");
		}
	}
	
	public int rollDice() {
		int sum = 0;
		for(int i=0; i<dice.size(); i++) {
			sum += rollDie(dice.get(i), min.get(i));
		}
		return sum;
	}
	private int rollDie(int max, int min) {
		return (int)(((Math.random()) * (max - min + 1)) + min);
	}
}
