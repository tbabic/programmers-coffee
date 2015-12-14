package good.game.studios.application.tomislav.babic.model;

/**
 * This is enum for different payment types: CASH, CREDIT CARD
 * each value contains corresponding time to process payment
 * 
 * @author Tomislav Babic
 *
 */
public enum PaymentType {
	
	CASH (500),
	CREDIT_CARD (250);
	
	private int processingTime;

	private PaymentType(int processingTime) {
		this.processingTime = processingTime;
	}
	
	/**
	 * @return time to process payment in milliseconds
	 */
	public int getProcessingTime() {
		return this.processingTime;
	}
	
}