package good.game.studios.application.tomislav.babic.model;

import good.game.studios.application.tomislav.babic.util.Identifier;

public class CoffeeMachineOrder extends Identifier {

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
