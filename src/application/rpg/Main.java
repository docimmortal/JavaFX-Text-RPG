package application.rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.rpg.entities.GameContent;
import application.rpg.entities.Location;
import application.rpg.fxcomponents.ContentScreen;
import application.rpg.fxcomponents.ImageLoader;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uber.rpg.entities.UberLocation;

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
		
		Map<Integer, UberLocation> map = new HashMap<>();
		
		// load locations from method or file
		List<UberLocation> locs = (List<UberLocation>) FileUtilities.read(".//demo.dat");
		map = Convert.toUberMap(locs);
		content.setLocations(map);
		
		panes.clear();
		panes.addAll(makePanes());
		
		TextField tf = LabelWithTextField.makeTextField("Action:", "Action",null);
		HBox action = LabelWithTextField.getHBox("Action:", tf, (EventHandler<KeyEvent>) getEvent(stage, tf));
		
		Button ok = LabelWithTextField.getOkButton();
		EventHandler<ActionEvent> event = (EventHandler<ActionEvent>) getEvent(stage, (TextField)action.getChildren().get(1));
		ok.setOnAction(event);
		action.getChildren().add(ok);
		panes.add(action);
		VBox pane = new VBox(3, panes.toArray(new HBox[0]));
		Scene scene = new Scene(pane, 1000, 700);
		
        stage.setScene(scene);
        stage.setTitle("RPG");
        stage.show();
		
	}

	
	private List<HBox> makePanes() {
		List<HBox> panes = new ArrayList<>();

		HBox menu = MenuMaker.makeMenu();
		panes.add(menu);
		
		Location loc = content.getLocation(1);
		
		if (loc instanceof UberLocation) {
			String filename =((UberLocation)loc).getImageFilename();
			if (filename != null) {
				ImageView imageView = ImageLoader.load(filename, false);
				panes.add(new HBox(imageView));
			}
		}
		
		content.setInput(gameAction.getCommands());
		content.addInput(loc.getInfo());
		panes.add(content.getTextConsole());
		
		return panes;
	}
	
	private EventHandler<? extends Event> getEvent(Stage stage, TextField textField) {
		return new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				boolean doAction = true;
				if (event instanceof KeyEvent) {
					// this is not the OK button
					// check to see if the key event is an enter. If so, doAction.
					if (!((KeyEvent)event).getCode().equals(KeyCode.ENTER)) {
						doAction=false;
					}
				}
				if (doAction) {
					gameAction.doAction(textField.getText(), content.getGameContent());
					String results=gameAction.getResult();
					if (!results.equals("Exit")) {
						content.setInput(results);
						Location loc=content.getCurrentLocation();
						if (loc instanceof UberLocation) {
							String filename = ((UberLocation)loc).getImageFilename();
							if (filename != null) {
								ImageView imageView = ImageLoader.load(filename, false);
								panes.set(1,new HBox(imageView));
							}
						}
						panes.set(2, content.getTextConsole());
						VBox pane = new VBox(3, panes.toArray(new HBox[0]));
						Scene scene = new Scene(pane, 1000, 700);
						stage.setScene(scene);
					} else {
						stage.close();
					}
				}
			}
			
		};
	}
}
