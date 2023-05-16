package application.rpg.fxcomponents;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

/*
Label and TextField are typically paired together
This contains a method that creates the Label and TextField and returns an HBox.
 */
public class LabelWithTextField {
	
	public static HBox getHBox(String labelName, TextField textField, EventHandler<KeyEvent> eHandler) {
		Label label = makeLabel(labelName);
		textField.setOnKeyPressed(eHandler);
		HBox box = new HBox(20, label, textField);
		box.setPadding(new Insets(10));
		return box;
	}
	
	public static HBox getHBox(String labelName, TextField textField) {
		Label label = makeLabel(labelName);
		HBox box = new HBox(20, label, textField);
		box.setPadding(new Insets(10));
		return box;
	}
	
	public static HBox getHBox(String labelName, String textMessage, String id, EventHandler<KeyEvent> eHandler) {
		TextField textField = makeTextField(textMessage, id, eHandler);
		return getHBox(labelName, textField);
	}
	
	public static HBox getHBox(String labelName, String id, EventHandler<KeyEvent> eHandler) {
		return getHBox(labelName, "", id, eHandler);
	}
	
	private static Label makeLabel(String name) {
		Label label = new Label(name);
		label.setMinWidth(100);
		label.setAlignment(Pos.BOTTOM_RIGHT);
		return label;
	}

	public static TextField makeTextField(String prompt, String id, EventHandler<KeyEvent> eHandler) {
		TextField textField = new TextField();
		textField.setMinWidth(200);
		textField.setMaxWidth(200);
		textField.setPromptText(prompt);
		textField.setId(id);
		if (eHandler != null) {
			textField.setOnKeyPressed(eHandler);
		}
		/*new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent ke) {
		        if (ke.getCode().equals(KeyCode.ENTER)) {
		            System.out.println("ENTER hit");
		        }
		    }});*/
		return textField;
	}
	
	public static Button getOkButton() {
		Button okButton = new Button("OK");
		okButton.setMinWidth(75);
		return okButton;
	}

}
