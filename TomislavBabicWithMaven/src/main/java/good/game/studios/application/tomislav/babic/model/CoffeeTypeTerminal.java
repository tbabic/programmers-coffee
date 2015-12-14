package good.game.studios.application.tomislav.babic.model;

import good.game.studios.application.tomislav.babic.util.Constants;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Queue;

import org.apache.log4j.Logger;

/**
 * This class is responsible for taking programmers from Coffee Type queue and processing them
 * each customer chooses a coffee type and is then moved to cash registers queue
 * 
 * @author Tomislav Babic
 *
 */
public class CoffeeTypeTerminal extends CoffeeShopQueueProcessor<Programmer> implements Runnable {

	private static final Logger logger = Logger.getLogger(Constants.LOGGER_NAME);
	
	public CoffeeTypeTerminal(Queue<Programmer> queue, CoffeeShop coffeeShop) {
		super(queue, coffeeShop);
	}
	
	protected void processCustomer(Programmer programmer) throws InterruptedException  {
		programmer.chooseCoffeeType(Arrays.asList(CoffeeType.values()));
		logger.debug(MessageFormat.format("Programmer {0} chose {1} on terminal {2}", programmer.getId(), programmer.getChosenCoffee(), getId()));
		coffeeShop.moveToCashRegister(programmer);
	}

}
