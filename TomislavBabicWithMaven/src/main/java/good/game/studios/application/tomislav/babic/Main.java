package good.game.studios.application.tomislav.babic;

import good.game.studios.application.tomislav.babic.model.CoffeeMachine;
import good.game.studios.application.tomislav.babic.model.CoffeeShop;
import good.game.studios.application.tomislav.babic.model.CoffeeType;
import good.game.studios.application.tomislav.babic.model.Programmer;
import good.game.studios.application.tomislav.babic.model.PaymentType;
import good.game.studios.application.tomislav.babic.util.IdGenerator;
import good.game.studios.application.tomislav.babic.util.SimpleLogger;
import good.game.studios.application.tomislav.babic.util.SimpleLogger.Level;
import good.game.studios.application.tomislav.babic.util.Statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * This is main class. Here you can define via array how many programmers
 * do you want to process in coffee shop.
 * 
 * @author Tomislav Babic
 *
 */
public class Main {

	private static final int [] NUMBER_OF_PROGRAMMERS = {100, 200, 500, 1000};
	private static final Level level = Level.INFO;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleLogger.level = level;
		for (int n : NUMBER_OF_PROGRAMMERS) {
			SimpleLogger.info("");
			SimpleLogger.info("Processing {0} programmers", n);
			initializeAndProcess(n);
			IdGenerator.reset();
		}
	}


	private static void initializeAndProcess(int n) {
		List<Programmer> programmers = new ArrayList<Programmer>();
		for (int i = 0; i<n; i++) {
			programmers.add(new Programmer());
		}
		process(programmers);
		writeStatistics(programmers);
		CoffeeShop.reset();
	}
	
	
	private static void process(List<Programmer> programmers) {
		
		CoffeeShop.openUp(programmers);
		for (Programmer programmer : programmers) {
			programmer.wantsCoffee();
		}
		CoffeeShop.waitForClosing();
		SimpleLogger.debug("Done!!!!!!!!!!!!!!!!!!!!!!");
						
	}
	
	private static void writeStatistics(List<Programmer> programmers) {
		SimpleLogger.info(""); //new line
		SimpleLogger.info("Question 1: How much coffee is sold (total and per payment type)?");
		SimpleLogger.info("Asnwer:");
		SimpleLogger.info("Total Coffee sold: {0}", Statistics.totalCoffeeSold());
		for (PaymentType paymentType : PaymentType.values()) {
			SimpleLogger.info("Coffee sold {0} by payment type: {1}", Statistics.soldByPaymentType(paymentType), paymentType);
		}
		
		SimpleLogger.info("");
		SimpleLogger.info("Question 2: How much coffee is dispensed by each coffee machine (total and per each type of coffee)?");
		SimpleLogger.info("Asnwer:");
		for (CoffeeMachine coffeeMachine : CoffeeShop.getCoffeeMachine()) {
			SimpleLogger.info("Machine {0} dispensed: {1}", coffeeMachine.getId(), Statistics.dispensedByMachine(coffeeMachine));
			for (CoffeeType coffeeType : CoffeeType.values()) {
				SimpleLogger.info("Machine {0} dispensed {1} of type {2}", coffeeMachine.getId(), Statistics.dispensedByMachineAndType(coffeeMachine, coffeeType), coffeeType);
			}
		}
		
		SimpleLogger.info("");
		SimpleLogger.info("Question 3: How much time does the average programmer spend getting their coffee?");
		SimpleLogger.info("Asnwer:");
		SimpleLogger.info("Average time is {0} seconds", Statistics.averageTimeForProgrammers(programmers));
		
		SimpleLogger.info("");
		SimpleLogger.info("Question 4: How long did it take for fastest and slowest programmer to get her coffee?");
		SimpleLogger.info("Asnwer:");
		Programmer fastest = Statistics.fastestProgrammer(programmers);
		SimpleLogger.info("Fastest time is {0} seconds for programmer/programmer {1}", fastest.getTimeToGetCoffeeInSeconds(), fastest.getId());
		Programmer slowest = Statistics.slowestProgrammer(programmers);
		SimpleLogger.info("Slowest time is {0} seconds for programmer/programmer {1}", slowest.getTimeToGetCoffeeInSeconds(), slowest.getId());
	}

}
