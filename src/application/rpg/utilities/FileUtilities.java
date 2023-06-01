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
import uber.rpg.entities.Adventure;

public class FileUtilities {

	public static void write(List<? extends Object> objects, String filename) { 
        try  (FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
        	for (Object object: objects) {
        		objectOut.writeObject(object);
        		System.out.println(object);
        	}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
	public static List<? extends Object> read(String filename) {
		
		boolean loop=true;
		List<Location> locations = null;
		List<Player> players = null;
		List<Adventure> adventures = null;
        try  (FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
        	do {
        		 Object obj = objectIn.readObject();
        		 if (obj != null) {
        			 if (obj.getClass() == Location.class) {
        				 if (locations == null) {
        					 locations = new ArrayList<Location>();
        				 }
        				 locations.add((Location)obj);
        			 }  else if (obj instanceof Player) {
        				 if (players == null) {
        					 players = new ArrayList<Player>();
        				 }
        				 players.add((Player)obj);
        			 } else if (obj instanceof Adventure) {
        				 if (adventures == null) {
        					 adventures = new ArrayList<Adventure>();
        				 }
        				 adventures.add((Adventure)obj);
        			 } else {
        				 System.out.println("Unknown object.");
        			 }
        		 } else {
        			 loop=false;
        		 }
        	} while(loop);
        } catch (EOFException e) {
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (locations != null) {
        	return locations;
        } else if (adventures != null) {
        	return adventures;
        } else {
        	return players;
        }
    }
	
	
}
