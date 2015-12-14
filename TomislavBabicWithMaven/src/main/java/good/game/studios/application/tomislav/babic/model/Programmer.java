package good.game.studios.application.tomislav.babic.model;

import good.game.studios.application.tomislav.babic.util.Identifier;
import good.game.studios.application.tomislav.babic.util.SimpleLogger;

import java.util.List;
import java.util.Random;

public class Programmer extends Identifier {

	private static final int CHOOSE_COFFEE_TYPE = 500;
	private static final int FIND_CUP = 250;
	private static final int PLACE_CUP = 250;
	private static final int LEAVE = 250;
	
	public long timeToGetCoffee = 0;
	
	public Programmer() {
		super();
	}
	
	public void wantsCoffee() {
		timeToGetCoffee = System.currentTimeMillis();
		SimpleLogger.debug("{0} wants coffee", toString());
		CoffeeShop.getInLine(this);

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

	public CoffeeType chooseCoffeeType(List<CoffeeType> coffeeTypes) throws InterruptedException {
		Random rn = new Random();
		int index = rn.nextInt(coffeeTypes.size());
		Thread.sleep(CHOOSE_COFFEE_TYPE);
		return coffeeTypes.get(index);		
	}
	
	public void getCoffeeFromMachine(CoffeeType coffeeType) throws InterruptedException {
		Thread.sleep( FIND_CUP + PLACE_CUP +
				coffeeType.getProcessingTime());
		timeToGetCoffee=System.currentTimeMillis()-timeToGetCoffee;
		Thread.sleep(LEAVE);
	}
	
	public double getTimeToGetCoffeeInSeconds() {
		return ((double) timeToGetCoffee)/1000.0;
	}

}
