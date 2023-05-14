package application.rpg.fxcomponents;

import java.util.Map;

import application.rpg.entities.GameContent;
import application.rpg.entities.Location;
import application.rpg.entities.Player;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class ContentScreen {
	
	private StringBuilder sb;
	private GameContent gameContent;
	
	public ContentScreen(GameContent gameContent) {
		this.gameContent=gameContent;
		sb = new StringBuilder();
	}
	
	public void setLocations(Map<Integer, Location> locations) {
		gameContent.setLocationMap(locations);
	}
	
	public Player getPlayer() {
		return gameContent.getPlayer();
	}
	
	public Location getLocation(int locationId) {
		return gameContent.getLocation(locationId);
	}
	
	public GameContent getGameContent() {
		return gameContent;
	}

	public void setInput(String data) {
		sb = new StringBuilder(data);
	}
	
	public void addInputLine(String data) {
		sb.append(data+"\n");
	}
	
	public void addInput(String data) {
		sb.append(data);
	}
	
	public HBox getTextConsole() {
		TextArea content = new TextArea();
		content.setMinWidth(500);
		content.setMinHeight(400);
		content.setText(sb.toString());
		content.setEditable(false);
		content.setMouseTransparent(true);
		content.setFocusTraversable(false);
		
		HBox contentBox = new HBox();
		contentBox.getChildren().add(content);
		return contentBox;
	}

}
