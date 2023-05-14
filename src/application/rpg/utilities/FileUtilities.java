package application.rpg.utilities;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import application.rpg.entities.Location;

public class FileUtilities {

	public static void writeLocations(List<Location> objects, String filename) {
		 
        try  (FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
        	for (Object object: objects) {
        		objectOut.writeObject(object);
        	}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
	public static List<Location> readLocations(String filename) {
		
		boolean loop=true;
		List<Location> locations = new ArrayList<>();
        try  (FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
        	while (loop) {
        		 Object obj = objectIn.readObject();
        		 if (obj != null) {
        			 locations.add((Location)obj);
        		 } else {
        			 loop=false;
        		 }
        	}
        } catch (EOFException ex) {
        	// do nothing
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return locations;
    }
}
