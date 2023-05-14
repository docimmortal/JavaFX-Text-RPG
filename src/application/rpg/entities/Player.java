package application.rpg.entities;

import java.io.Serializable;

public class Player implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int xp;

	public int getXp() {
		return xp;
	}

	public void addXp(int xp) {
		this.xp += xp;
	}

}
