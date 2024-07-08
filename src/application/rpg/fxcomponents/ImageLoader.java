package application.rpg.fxcomponents;

import java.io.FileInputStream;
import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageLoader {

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
