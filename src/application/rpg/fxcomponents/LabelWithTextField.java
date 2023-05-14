package application.rpg.fxcomponents;

import java.util.HashMap;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/*
Label and TextField are typically paired together
This contains a method that creates the Label and TextField and returns an HBox.
 */
public class LabelWithTextField {
	
	public static HBox getHBox(String labelName, String textMessage, String id) {
		Label label = makeLabel(labelName);
		TextField textField = makeTextField(textMessage, id);
		HBox box = new HBox(20, label, textField);
		box.setPadding(new Insets(10));
		return box;
	}
	
	public static HBox getHBox(String labelName, String id) {
		Label label = makeLabel(labelName);
		TextField textField = makeTextField("", id);
		HBox box = new HBox(20, label, textField);
		box.setPadding(new Insets(10));
		return box;
	}
	
	private static Label makeLabel(String name) {
		Label label = new Label(name);
		label.setMinWidth(100);
		label.setAlignment(Pos.BOTTOM_RIGHT);
		return label;
	}

	private static TextField makeTextField(String prompt, String id) {
		TextField textField = new TextField();
		textField.setMinWidth(200);
		textField.setMaxWidth(200);
		textField.setPromptText(prompt);
		textField.setId(id);
		return textField;
	}
	
	public static Button getOkButton() {
		Button okButton = new Button("OK");
		okButton.setMinWidth(75);
		return okButton;
	}

}
