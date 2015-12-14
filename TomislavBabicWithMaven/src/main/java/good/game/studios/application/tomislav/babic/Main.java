package good.game.studios.application.tomislav.babic;

import good.game.studios.application.tomislav.babic.controllers.CoffeeShopController;
import good.game.studios.application.tomislav.babic.util.SimpleLogger;
import good.game.studios.application.tomislav.babic.util.SimpleLogger.Level;

import java.util.Arrays;
import java.util.List;

/**
 * This is main class. Here you can define via array how many programmers
 * do you want to process in coffee shop.
 * 
 * @author Tomislav Babic
 *
 */
public class Main {

	private static final List<Integer> NUMBER_OF_PROGRAMMERS = Arrays.asList(10, 20, 50);
	private static final Level level = Level.INFO;
	private static CoffeeShopController coffeeShopController = new CoffeeShopController();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleLogger.level = level;
		coffeeShopController.processMultipleDays(NUMBER_OF_PROGRAMMERS);
	}
}
