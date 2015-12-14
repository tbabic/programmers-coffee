package good.game.studios.application.tomislav.babic.views;

import good.game.studios.application.tomislav.babic.model.CoffeeMachine;
import good.game.studios.application.tomislav.babic.model.CoffeeType;
import good.game.studios.application.tomislav.babic.util.Constants;

import java.text.MessageFormat;
import java.util.List;

import org.apache.log4j.Logger;

public class Question2 {
	
	private static final Logger logger = Logger.getLogger(Constants.VIEW_LOGGER_NAME);
	
	List<CoffeeMachine> coffeeMachines;
	
	public Question2(List<CoffeeMachine> coffeeMachines) {
		super();
		this.coffeeMachines = coffeeMachines;
	}

	public void show() {
		answer();
	}
	
	private void answer() {
		
		logger.info("");
		logger.info("Question 2: How much coffee is dispensed by each coffee machine (total and per each type of coffee)?");
		logger.info("Asnwer:");
		
		for (CoffeeMachine coffeeMachine : coffeeMachines) {
			logger.info(MessageFormat.format("Machine {0} dispensed: {1}", coffeeMachine.getId(), coffeeMachine.getAllCoffeesDispensed()));
			for (CoffeeType coffeeType : CoffeeType.values()) {
				logger.info(MessageFormat.format("Machine {0} dispensed {1} of type {2}", coffeeMachine.getId(), coffeeMachine.getCoffeeDispensedByType(coffeeType), coffeeType));
			}
		}
		
	}
}
