package good.game.studios.application.tomislav.babic.model;

import good.game.studios.application.tomislav.babic.util.SimpleLogger;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * This class is responsible for taking elements from coffee machine queue and processing them
 * each customer gets coffee from machine and then leaves the coffee shop
 * 
 * @author Tomislav Babic
 *
 */
public class CoffeeMachine extends CoffeeShopQueueProcessor<Order> {

	private Map<CoffeeType, Integer> coffeeSoldByType = new HashMap<CoffeeType, Integer>();
	
	public CoffeeMachine(Queue<Order> queue) {
		super(queue);
	}
	
	@Override
	protected void processCustomer(Order order) throws InterruptedException {
		Programmer programmer = order.getProgrammer();
		SimpleLogger.debug("Programmer {0} pours {1} on machine {2}", programmer.getId(), order.getCoffeeType(), getId());
		processStatistics(order.getCoffeeType());
		programmer.getCoffeeFromMachine(order.getCoffeeType());
		CoffeeShop.leave(programmer);
	}
	
	public int getCoffeeDispensedByType(CoffeeType type) {
		int currentNumber = 0;
		if (coffeeSoldByType.containsKey(type)) {
			currentNumber = coffeeSoldByType.get(type);
		}
		return currentNumber;
	}
	
	private void processStatistics(CoffeeType type) {
		int currentNumber = getCoffeeDispensedByType(type);
		coffeeSoldByType.put(type, currentNumber+1);
	}
	
	
}
