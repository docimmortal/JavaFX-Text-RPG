package application.rpg.fxcomponents;

import java.io.FileInputStream;
import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageLoader {

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
	
	public static Image getProjectImage(String filename) {
		String workingDir = System.getProperty("user.dir");
	    return getImageFromFileSystem(workingDir+"\\"+filename);
	}
	
	public static Image getImageFromFileSystem(String filename) {
		Image image = null;
		try (InputStream stream = new FileInputStream(filename)) {
		    image = new Image(stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
}
