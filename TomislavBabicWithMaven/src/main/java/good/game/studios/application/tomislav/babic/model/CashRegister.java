package good.game.studios.application.tomislav.babic.model;


import good.game.studios.application.tomislav.babic.util.Constants;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.log4j.Logger;


/**
 * This class is responsible for taking elements from Cash registers queue and processing them
 * each customer chooses a payment type and after that is moved to coffee machine queue
 * 
 * @author Tomislav Babic
 *
 */
public class CashRegister extends CoffeeShopQueueProcessor<Programmer>  {
	
	private static final Logger logger = Logger.getLogger(Constants.LOGGER_NAME);
	
	private List<Receipt> receipts = new ArrayList<Receipt>();
	
	public CashRegister(Queue<Programmer> queue, CoffeeShop coffeeShop) {
		super(queue, coffeeShop);
	}
	
	protected void processCustomer(Programmer programmer) throws InterruptedException  {
		PaymentType paymentType = programmer.choosePaymentType(Arrays.asList(PaymentType.values()));
		logger.debug(MessageFormat.format("Cash register {0} processing programmer {1} with payment type {2}", 
				getId(), programmer.getId(), paymentType));
		receipts.add(new Receipt(this, programmer.getChosenCoffee(), paymentType));
		coffeeShop.moveToCoffeeMachine(programmer);
	}

	public int getCoffeeSoldByType(PaymentType paymentType) {
		return Receipt.getReceiptsByPaymentType(paymentType, receipts);
	}
	
	public int getAllCoffeeSold() {
		return receipts.size();
	}
	
	public List<Receipt> getReceipts() {
		return this.receipts;
	}
	
	public static int getAllCoffeeSold(List<CashRegister> cashRegisters) {
		int sum = 0;
		for (CashRegister cashRegister : cashRegisters) {
			sum += cashRegister.getAllCoffeeSold();
		}
		return sum;
	}
	
	public static int getCoffeeSoldByType(
			PaymentType paymentType, List<CashRegister> cashRegisters) {
		int sum = 0;
		List<Receipt> allReceipts = new ArrayList<Receipt>();
		for (CashRegister cashRegister : cashRegisters) {
			sum+= cashRegister.getCoffeeSoldByType(paymentType);
		}
		return sum;
	}
	
}
