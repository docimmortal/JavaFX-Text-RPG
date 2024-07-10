package application.rpg.fxcomponents;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import application.rpg.utilities.GameAction;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ImageLoader {

	// Loads and image and stores it in an ImageView object (used by JavaFX)
	// If external=false, the image is in this project under images.
	// If external=true, the image is stored somewhere else on the hard drive.
	public static ImageView load(String filename, String itemName, boolean external, GameAction action,  ContentScreen content, List<HBox> panes, HBox hboxAction, Stage stage) {
	    ImageView imageView = new ImageView();
	    Image image = null;
	    if (external) {
	    	image=getImageFromFileSystem(filename);
	    } else {
	    	image=getProjectImage(filename);
	    }
	    if (itemName!=null && itemName.length()>0) {
	    	// clickable item on screen
		    imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		    	@Override
		        public void handle(MouseEvent event) {
		    		action.doAction("take "+itemName, content.getGameContent());
		    		// how to update text and remove image???
		    		String result = action.getResult();
		    		content.setInput(result);
		    		panes.set(1, content.getTextConsole());
		    		//panes.add(hboxAction);
		    		System.out.println(">>>>>>"+panes.size());
		    		HBox[] array = panes.toArray(new HBox[panes.size()]);
		    		System.out.println(">>>>>>"+array.length);
		    		VBox pane = new VBox(2, array);
					Scene scene = new Scene(pane, 1000, 700);
					stage.setScene(scene);
		            event.consume();
		        }
		    });
	    }
	    imageView.setImage(image);
	    imageView.setX(0);
	    imageView.setY(0);
	    imageView.setPreserveRatio(true);
		return imageView;
	}

	// Loads and image and stores it in an ImageView object (used by JavaFX)
	// If external=false, the image is in this project under images.
	// If external=true, the image is stored somewhere else on the hard drive.
	public static ImageView load(String filename, boolean external) {
		ImageView imageView = new ImageView();
		Image image = null;
		if (external) {
			image=getImageFromFileSystem(filename);
		} else {
			image=getProjectImage(filename);
		}
		imageView.setImage(image);
		imageView.setX(0);
		imageView.setY(0);
		imageView.setPreserveRatio(true);
		return imageView;
	}

	private static Image getProjectImage(String filename) {
		String workingDir = System.getProperty("user.dir");
		return getImageFromFileSystem(workingDir+"\\"+filename);
	}

	private static Image getImageFromFileSystem(String filename) {
		Image image = null;
		try (InputStream stream = new FileInputStream(filename)) {
			image = new Image(stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
}
