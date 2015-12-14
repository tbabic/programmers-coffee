package good.game.studios.application.tomislav.babic.util;

import java.text.MessageFormat;

/**
 * This class is a simple logger used for writing debug, info or error levels to console output
 * 
 * @author Tomislav Babic
 *
 */
public class SimpleLogger {

	public enum Level {
		DEBUG(0), INFO(1), ERROR(2);
		
		private int value;

		private Level(int value) {
			this.value = value;
		}
		
	}
	
	public static Level level;
	
	public static void debug(String message) {
		if (level.value <= Level.DEBUG.value) {
			log(message);
		}
	}
	
	public static void debug(String messageFormat, Object... arguments) {
		if (level.value <= Level.DEBUG.value) {
			log(messageFormat, arguments);
		}
	}
	
	public static void info(String message) {
		if (level.value <= Level.INFO.value) {
			log(message);
		}
	}
	
	public static void info(String messageFormat, Object... arguments) {
		if (level.value <= Level.INFO.value) {
			log(messageFormat, arguments);
		}
	}
	
	public static void error(String message) {
		if (level.value <= Level.ERROR.value) {
			log(message);
		}
	}
	
	public static void error(String messageFormat, Object... arguments) {
		if (level.value <= Level.ERROR.value) {
			log(messageFormat, arguments);
		}
	}
	
	private static void log(String message) {
		System.out.println(message);
	}
	
	
	private static void log(String messageFormat, Object... arguments) {
		System.out.println(MessageFormat.format(messageFormat, arguments));
	}
}
