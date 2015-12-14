package good.game.studios.application.tomislav.babic.model;

import good.game.studios.application.tomislav.babic.util.SimpleLogger;

import java.util.Arrays;
import java.util.Queue;

/**
 * This class is responsible for taking programmers from Coffee Type queue and processing them
 * each customer chooses a coffee type and is then moved to cash registers queue
 * 
 * @author Tomislav Babic
 *
 */
public class CoffeeTypeTerminal extends CoffeeShopQueueProcessor<Programmer> implements Runnable {


	public CoffeeTypeTerminal(Queue<Programmer> queue) {
		super(queue);
	}
	
	protected void processCustomer(Programmer programmer) throws InterruptedException  {
		CoffeeType coffeeType = programmer.chooseCoffeeType(Arrays.asList(CoffeeType.values()));
		SimpleLogger.debug("Programmer {0} chose {1} on terminal {2}", programmer.getId(), coffeeType, getId());
		CoffeeShop.moveToCashRegister(programmer, coffeeType);
		
	}

}
