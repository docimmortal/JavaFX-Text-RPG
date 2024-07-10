package application.rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import application.rpg.entities.GameContent;
import application.rpg.entities.Item;
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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	final GameAction gameAction = new GameAction();
	final ContentScreen content = new ContentScreen(new GameContent());
	Stage thisStage;

	public static void main(String[] args) {	
		launch(args);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) throws Exception {
		
		thisStage=stage;
		
		Map<Integer, Location> map = new HashMap<>();
		
		// load locations from method or file
		List<Location> locs = (List<Location>) FileUtilities.read(".//demo.dat");
		map = Convert.toMap(locs);
		content.setLocations(map);
		
		List<HBox> panes = new ArrayList<>(makePanes(thisStage));
		//panes.clear();
		

		VBox pane = new VBox(3, panes.toArray(new HBox[0]));
		Scene scene = new Scene(pane, 1000, 700);
		
        stage.setScene(scene);
        stage.setTitle("RPG");
        stage.show();
		
	}
	
	@SuppressWarnings("unchecked")
	private HBox setTextInputField(Stage stage, List<HBox> panes) {
		TextField tf = LabelWithTextField.makeTextField("Action:", "Action",null);
		HBox hboxAction = LabelWithTextField.getHBox("Action:", tf, (EventHandler<KeyEvent>) getEvent(stage, panes, tf));
		
		Button ok = LabelWithTextField.getOkButton();
		EventHandler<ActionEvent> event = (EventHandler<ActionEvent>) getEvent(stage, panes, (TextField)hboxAction.getChildren().get(1));
		ok.setOnAction(event);
		hboxAction.getChildren().add(ok);
		return hboxAction;
	}

	
	private List<HBox> makePanes(Stage stage) {
		List<HBox> panes = new ArrayList<>();

		HBox menu = MenuMaker.makeMenu();
		panes.add(menu);
		
		Location loc = content.getLocation(1);
		updateLocation(loc,panes);

		content.setInput(gameAction.getCommands());
		content.addInput(loc.getInfo());
		panes.add(content.getTextConsole());
		panes.add(setTextInputField(stage, panes));
		
		return panes;
	}
	
	private void updateLocation(Location loc, List<HBox> panes) {
		if (loc instanceof Location) {
			String filename =((Location)loc).getImageFilename();
			if (filename != null) {
				ImageView imageView = ImageLoader.load(filename, false);
				Group group = new Group();
				group.getChildren().add(imageView);
				addItems(loc, group);
				if (panes.size()>1) {
					panes.set(1,new HBox(group));
				} else {
					panes.add(new HBox(group));
				}
			}
		}
	}
	
	private void addItems(Location loc, Group group) {
		Set<Item> items = loc.getAllItems();
		for (Item item: items) {
			String itemFilename=item.getFilename();
			if (itemFilename != null && itemFilename.length()>0) {
				ImageView imageView2 = ImageLoader.load(itemFilename,false);
				imageView2.setLayoutX(item.getxLoc());
				imageView2.setLayoutY(item.getyLoc());
				group.getChildren().add(imageView2);
			}
		}
	}
	
	private EventHandler<? extends Event> getEvent(Stage stage, List<HBox> panes, TextField textField) {
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
						updateLocation(loc,panes);
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
