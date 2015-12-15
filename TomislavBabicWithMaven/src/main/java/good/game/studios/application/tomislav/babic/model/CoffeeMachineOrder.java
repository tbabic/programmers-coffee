package good.game.studios.application.tomislav.babic.model;


public class CoffeeMachineOrder extends BaseModel {

	private CoffeeMachine coffeeMachine;
	private CoffeeType coffeeType;
	public CoffeeMachineOrder(CoffeeMachine coffeeMachine, CoffeeType coffeeType) {
		super();
		this.coffeeMachine = coffeeMachine;
		this.coffeeType = coffeeType;
	}
	public CoffeeMachine getCoffeeMachine() {
		return coffeeMachine;
	}
	public CoffeeType getCoffeeType() {
		return coffeeType;
	}
	
	
}
