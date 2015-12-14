package good.game.studios.application.tomislav.babic.model;


/**
 * This is enum for different coffee types: ESPRESSO, LATTE, MACCHIATO, CAPPUCCION
 * each value contains corresponding time to pour coffee to cup
 * 
 * @author Tomislav Babic
 *
 */
public enum CoffeeType {
	
	ESPRESSO (250),
	LATTE (500),
	MACCHIATO (500),
	CAPPUCCINO (750);
	
	private int processingTime;
	
	private CoffeeType(int processingTime) {
		this.processingTime = processingTime;
	}
	
	
	/**
	 * @return time to pour coffee to cup in milliseconds
	 */
	public int getProcessingTime() {
		return this.processingTime;
	}
}
