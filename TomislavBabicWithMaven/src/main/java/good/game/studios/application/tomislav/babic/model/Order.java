package good.game.studios.application.tomislav.babic.model;


/**
 * This class is a simple wrapper for Programmer/Customer and CoffeeType
 * 
 * @author Tomislav Babic
 *
 */
public class Order {

	private Programmer programmer;
	private CoffeeType coffeeType;
	
	
	public Order(Programmer programmer, CoffeeType coffeeType) {
		super();
		this.programmer = programmer;
		this.coffeeType = coffeeType;
	}
	
	public Programmer getProgrammer() {
		return programmer;
	}
	public CoffeeType getCoffeeType() {
		return coffeeType;
	}
	
}
