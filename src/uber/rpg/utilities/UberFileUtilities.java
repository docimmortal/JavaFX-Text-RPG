package uber.rpg.utilities;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import application.rpg.utilities.FileUtilities;
import uber.rpg.entities.Adventure;

public class UberFileUtilities extends FileUtilities {
	
	private static final String aFile = ".\\adventures.dat";
	/*
	 The next two classes creates a file with the list of adventures that you can play.
	 You can use a scanner to get the adventure number from the user.
	 If the adventure number is valid, fetch the adventure.
	 */
	
	@SuppressWarnings("unchecked")
	public static void writeAdventureDetails(Adventure adventure) {
		List<Adventure> adventures = new ArrayList<>();
		if (fileExists()) {
			adventures = (List<Adventure>) FileUtilities.read(aFile);
		}
		boolean exists = UberFileUtilities.objectExists(adventure, aFile);
		if (!exists) {
			adventures.add(adventure);
		} else {
			for (int i=0; i<adventures.size(); i++) {
				if (adventures.get(i).equals(adventure)) {
					adventures.set(i, adventure); // overwrite this entry
					break;
				}
			}
		}
		FileUtilities.write(adventures, aFile);
	}
	
	@SuppressWarnings("unchecked")
	public static int displayAdventures() {
		List<Adventure> adventures = (List<Adventure>) FileUtilities.read(aFile);
		for(int i=0; i<adventures.size(); i++) {
			System.out.println((i+1)+": "+adventures.get(i)+"\n");
		}
		return adventures.size()+1;
	}
	
	private static boolean fileExists() {
		return new File(aFile).isFile();
	}
	
	private static boolean objectExists(Object object, String filename) {
		
		boolean loop=true;
		boolean found=false;
		
		// If there is no file, obviously the object is not in the file.
		boolean fileExists = new File(filename).isFile();
		if (!fileExists) {
			return false;
		}
       try  (FileInputStream fileIn = new FileInputStream(filename);
           ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
       	do {
       		 Object obj = objectIn.readObject();
       		 if (obj != null) {
       			 // Requires overloading equals and hashCode
       			 if (object.equals(obj)) {
       				 found=true;
       				 break;
       			 }
       		 } else {
       			 loop=false;
       		 }
       	} while(loop);
       } catch (EOFException e) {
       } catch (Exception ex) {
           ex.printStackTrace();
       }
       return found;
   }
	
}
