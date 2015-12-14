package good.game.studios.application.tomislav.babic.views;

import good.game.studios.application.tomislav.babic.model.CoffeeMachine;
import good.game.studios.application.tomislav.babic.model.CoffeeType;
import good.game.studios.application.tomislav.babic.util.SimpleLogger;

import java.util.List;

public class Question2 {

	List<CoffeeMachine> coffeeMachines;
	
	public Question2(List<CoffeeMachine> coffeeMachines) {
		super();
		this.coffeeMachines = coffeeMachines;
	}

	public void show() {
		answer();
	}
	
	private void answer() {
		
		SimpleLogger.info("");
		SimpleLogger.info("Question 2: How much coffee is dispensed by each coffee machine (total and per each type of coffee)?");
		SimpleLogger.info("Asnwer:");
		
		for (CoffeeMachine coffeeMachine : coffeeMachines) {
			SimpleLogger.info("Machine {0} dispensed: {1}", coffeeMachine.getId(), coffeeMachine.getAllCoffeesDispensed());
			for (CoffeeType coffeeType : CoffeeType.values()) {
				SimpleLogger.info("Machine {0} dispensed {1} of type {2}", coffeeMachine.getId(), coffeeMachine.getCoffeeDispensedByType(coffeeType), coffeeType);
			}
		}
		
	}
}
