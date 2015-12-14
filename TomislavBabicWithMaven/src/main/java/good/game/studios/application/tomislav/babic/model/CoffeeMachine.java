package good.game.studios.application.tomislav.babic.model;

import good.game.studios.application.tomislav.babic.util.Constants;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.apache.log4j.Logger;

/**
 * This class is responsible for taking elements from coffee machine queue and processing them
 * each customer gets coffee from machine and then leaves the coffee shop
 * 
 * @author Tomislav Babic
 *
 */
public class CoffeeMachine extends CoffeeShopQueueProcessor<Programmer> {

	private static final Logger logger = Logger.getLogger(Constants.LOGGER_NAME);
	
	private List<CoffeeMachineOrder> orders = new ArrayList<CoffeeMachineOrder>();
	
	public CoffeeMachine(Queue<Programmer> queue, CoffeeShop coffeeShop) {
		super(queue, coffeeShop);
	}
	
	@Override
	protected void processCustomer(Programmer programmer) throws InterruptedException {
		logger.debug(MessageFormat.format("Programmer {0} pours {1} on machine {2}", programmer.getId(), programmer.getChosenCoffee(), getId()));
		if(programmer.getChosenCoffee() != null) {
			orders.add(new CoffeeMachineOrder(this, programmer.getChosenCoffee()));
			programmer.getCoffeeFromMachine();
		}
		coffeeShop.leave(programmer);
	}
	
	public int getCoffeeDispensedByType(CoffeeType type) {
		int n = 0;
		for (CoffeeMachineOrder order : orders) {
			if (order.getCoffeeType() == type) {
				n++;
			}
		}
		return n;
	}
	
	public int getAllCoffeesDispensed() {
		return orders.size();
	}
	
	
}
