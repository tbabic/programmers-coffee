package good.game.studios.application.tomislav.babic.model;

import good.game.studios.application.tomislav.babic.util.Constants;

import java.text.MessageFormat;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

public class Programmer extends BaseModel {

	private static final Logger logger = Logger.getLogger(Constants.LOGGER_NAME);
	
	private static final int CHOOSE_COFFEE_TYPE = 500;
	private static final int FIND_CUP = 250;
	private static final int PLACE_CUP = 250;
	private static final int PICK_COFFEE_TYPE = 250;
	private static final int LEAVE = 250;
	
	private CoffeeType chosenCoffee = null;
	public long timeToGetCoffee = 0;
	
	public Programmer() {
		super();
	}
	
	
	/**
	 * Programmer enters into the coffee shop
	 * @param coffeeShop
	 */
	public void enters(CoffeeShop coffeeShop) {
		timeToGetCoffee = System.currentTimeMillis();
		logger.debug(MessageFormat.format("{0} wants coffee", toString()));
		coffeeShop.getInLine(this);

	}
	
	/**
	 * @param paymentTypes
	 * @return randomly chosen PaymentType from the paymentTypes parameter
	 * @throws InterruptedException 
	 */
	public PaymentType choosePaymentType(List<PaymentType> paymentTypes) throws InterruptedException {
		Random rn = new Random();
		int index = rn.nextInt(paymentTypes.size());
		Thread.sleep(paymentTypes.get(index).getProcessingTime());
		return paymentTypes.get(index);		
	}

	public void chooseCoffeeType(List<CoffeeType> coffeeTypes) throws InterruptedException {
		Random rn = new Random();
		int index = rn.nextInt(coffeeTypes.size());
		Thread.sleep(CHOOSE_COFFEE_TYPE);
		chosenCoffee = coffeeTypes.get(index);
	}
	
	public void getCoffeeFromMachine() throws InterruptedException {
		Thread.sleep( FIND_CUP + PLACE_CUP + PICK_COFFEE_TYPE,
				chosenCoffee.getProcessingTime());
		timeToGetCoffee=System.currentTimeMillis()-timeToGetCoffee;
		Thread.sleep(LEAVE);
	}
	
	public double getTimeToGetCoffeeInSeconds() {
		return ((double) timeToGetCoffee)/1000.0;
	}

	public CoffeeType getChosenCoffee() {
		return chosenCoffee;
	}

}
