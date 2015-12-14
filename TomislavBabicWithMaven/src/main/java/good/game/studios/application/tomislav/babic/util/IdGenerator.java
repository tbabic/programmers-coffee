package good.game.studios.application.tomislav.babic.util;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for generating new identifiers
 * class has a map of classes and last generated id for that class
 * new id for an object is calculated by autoincrementing the last generated id
 * for the object's class
 * 
 * @author Tomislav Babic
 *
 */
public class IdGenerator {

	private static Map<Class<?>, Integer> classIds = new HashMap<Class<?>, Integer>();
	
	
	/**
	 * 
	 * @param clazz
	 * @return returns new id to be used for object of class clazz
	 */
	public static <T> int getNewId(Class<T> clazz) {
		int id = 0;
		if(classIds.containsKey(clazz)) {
			id = classIds.get(clazz)+1;
		}
		classIds.put(clazz, id);
		return id;			
	}
	
	public static void reset() {
		classIds.clear();
	}
	
}
