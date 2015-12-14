package good.game.studios.application.tomislav.babic.util;

import java.util.List;

import good.game.studios.application.tomislav.babic.model.CashRegister;
import good.game.studios.application.tomislav.babic.model.CoffeeMachine;
import good.game.studios.application.tomislav.babic.model.CoffeeShop;
import good.game.studios.application.tomislav.babic.model.CoffeeType;
import good.game.studios.application.tomislav.babic.model.Programmer;
import good.game.studios.application.tomislav.babic.model.PaymentType;


/**
 * This class contains static methods used for providing answers to questions in excercise
 * 
 * @author Tomislav Babic
 *
 */
public class Statistics {

	public static int totalCoffeeSold() {
		int sum = 0;
		for (PaymentType type : PaymentType.values()) {
			sum += soldByPaymentType(type);
		}
		return sum;
	}
	
	public static int soldByPaymentType(PaymentType type) {
		int sum = 0;
		for(CashRegister cashRegister : CoffeeShop.getCashRegisters()) {
			sum += cashRegister.getCoffeeSoldByType(type);
		}
		return sum;
	}
	
	public static int dispensedByMachine(CoffeeMachine machine) {
		int sum = 0;
		for (CoffeeType type : CoffeeType.values()) {
			sum += dispensedByMachineAndType(machine, type);
		}
		return sum;
	}
	
	public static int dispensedByMachineAndType(CoffeeMachine machine, CoffeeType type) {
		return machine.getCoffeeDispensedByType(type);
	}
	
	public static double averageTimeForProgrammers(List<Programmer> programmers) {
		double sum = 0;
		for (Programmer programmer : programmers) {
			sum += programmer.getTimeToGetCoffeeInSeconds();
		}
		return sum/programmers.size();
	}
	
	public static Programmer fastestProgrammer(List<Programmer> programmers) {
		double min = Double.MAX_VALUE;
		Programmer fastest = null;
		for (Programmer programmer : programmers) {
			if (programmer.getTimeToGetCoffeeInSeconds() < min) {
				min = programmer.getTimeToGetCoffeeInSeconds();
				fastest = programmer;
			}
		}
		return fastest;
	}
	
	public static Programmer slowestProgrammer(List<Programmer> programmers) {
		double max = -1;
		Programmer slowest = null;
		for (Programmer programmer : programmers) {
			if (programmer.getTimeToGetCoffeeInSeconds() > max) {
				max = programmer.getTimeToGetCoffeeInSeconds();
				slowest = programmer;
			}
		}
		return slowest;
	}
}
