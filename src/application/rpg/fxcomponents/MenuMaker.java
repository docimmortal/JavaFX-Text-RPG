package application.rpg.fxcomponents;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;

public class MenuMaker {

	public static HBox makeMenu() {
		Menu fileMenu = new Menu("_Game");
        MenuItem newFileMenuItem = new MenuItem("_New...");
        
        // For Mac and PC support, use setAccelerator instead of setMnemonicParsing
        newFileMenuItem.setAccelerator(
            KeyCombination.keyCombination("SHORTCUT+N")
        );
        newFileMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("New game!");
            }
        });
        
        fileMenu.getItems().add(
                newFileMenuItem
        );

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().setAll(
                fileMenu
        );
        HBox hbox = new HBox(menuBar);
        hbox.setPrefSize(50, 800);
        return hbox;
	}
}
