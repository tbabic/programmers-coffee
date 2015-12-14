package good.game.studios.application.tomislav.babic.model;

import good.game.studios.application.tomislav.babic.util.SimpleLogger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;


/**
 * This class is responsible for taking elements from Cash registers queue and processing them
 * each customer chooses a payment type and after that is moved to coffee machine queue
 * 
 * @author Tomislav Babic
 *
 */
public class CashRegister extends CoffeeShopQueueProcessor<Order>  {
	
	private Map<PaymentType, Integer> coffeeSoldByType = new HashMap<PaymentType, Integer>();
	
	public CashRegister(Queue<Order> queue) {
		super(queue);
	}
	
	protected void processCustomer(Order order) throws InterruptedException  {
		PaymentType paymentType = order.getProgrammer().choosePaymentType(Arrays.asList(PaymentType.values()));
		SimpleLogger.debug("Cash register {0} processing programmer {1} with payment type {2}", 
				getId(), order.getProgrammer().getId(), paymentType);
		processStatistics(paymentType);
		CoffeeShop.moveToCoffeeMachine(order);
	}

	public int getCoffeeSoldByType(PaymentType type) {
		int currentNumber = 0;
		if (coffeeSoldByType.containsKey(type)) {
			currentNumber = coffeeSoldByType.get(type);
		}
		return currentNumber;
	}
	
	private void processStatistics(PaymentType type) {
		int currentNumber = getCoffeeSoldByType(type);
		coffeeSoldByType.put(type, currentNumber+1);
	}
	
}
