package application.rpg.utilities;

public class Debug {

	
	public static void msg(boolean debugOn, String msg) {
		if (debugOn) {
			System.out.println(msg);
		}
	}
	
}
