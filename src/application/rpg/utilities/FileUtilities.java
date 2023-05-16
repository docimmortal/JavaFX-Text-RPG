package application.rpg.utilities;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import application.rpg.entities.Location;
import application.rpg.entities.Player;

public class FileUtilities {

	public static void write(List<? extends Object> objects, String filename) { 
        try  (FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
        	for (Object object: objects) {
        		objectOut.writeObject(object);
        	}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
	public static List<? extends Object> read(String filename) {
		
		boolean loop=true;
		List<Location> locations = null;
		List<Player> players = null;
        try  (FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
        	do {
        		 Object obj = objectIn.readObject();
        		 if (obj != null) {
        			 if (obj instanceof Location) {
        				 if (locations == null) {
        					 locations = new ArrayList<Location>();
        				 }
        				 locations.add((Location)obj);
        			 } else {
        				 if (players == null) {
        					 players = new ArrayList<Player>();
        				 }
        				 players.add((Player)obj);
        			 }
        		 } else {
        			 loop=false;
        		 }
        	} while(loop);
        } catch (EOFException e) {
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (locations == null)?players:locations;
    }
}
