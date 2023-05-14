package application.rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.rpg.entities.GameContent;
import application.rpg.entities.Location;
import application.rpg.fxcomponents.ContentScreen;
import application.rpg.fxcomponents.LabelWithTextField;
import application.rpg.fxcomponents.MenuMaker;
import application.rpg.utilities.Convert;
import application.rpg.utilities.FileUtilities;
import application.rpg.utilities.GameAction;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	final GameAction gameAction = new GameAction();
	final ContentScreen content = new ContentScreen(new GameContent());
	final List<HBox> panes = new ArrayList<>();

	public static void main(String[] args) {	
		launch(args);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) throws Exception {
		
		Map<Integer, Location> map = new HashMap<>();
		
		List<Location> locs = FileUtilities.readLocations(".//locations.dat");
		map = Convert.toMap(locs);
		
		// load from method or file
		content.setLocations(map);
		
		
		panes.clear();
		panes.addAll(makePanes());
		
		HBox action = LabelWithTextField.getHBox("Action:", "Action");
		
		Button ok = LabelWithTextField.getOkButton();
		EventHandler<ActionEvent> event = new EventHandler() {

			@Override
			public void handle(Event event) {
				TextField x = (TextField)action.getChildren().get(1);
				gameAction.doAction(x.getText(), content.getGameContent());
				String results=gameAction.getResult();
				if (!results.equals("Exit")) {
					content.addInput(results);
					panes.set(1, content.getTextConsole());
					VBox pane = new VBox(3, panes.toArray(new HBox[0]));
					Scene scene = new Scene(pane, 500, 500);
					stage.setScene(scene);
				} else {
					stage.close();
					return;
				}
			}
			
		};
		ok.setOnAction(event);
		action.getChildren().add(ok);
		panes.add(action);
		VBox pane = new VBox(3, panes.toArray(new HBox[0]));
		Scene scene = new Scene(pane, 500, 500);
		
        stage.setScene(scene);
        stage.setTitle("RPG");
        stage.show();
		
	}

	
	private List<HBox> makePanes() {
		List<HBox> panes = new ArrayList<>();

		HBox menu = MenuMaker.makeMenu();
		panes.add(menu);
		
		Location loc = content.getLocation(1);
		content.setInput(loc.getInfo());
		panes.add(content.getTextConsole());
		
		return panes;
	}
}
