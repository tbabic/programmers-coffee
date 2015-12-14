package good.game.studios.application.tomislav.babic.views;

import good.game.studios.application.tomislav.babic.model.Programmer;
import good.game.studios.application.tomislav.babic.util.SimpleLogger;

import java.util.List;

public class Question4 {

	List<Programmer> programmers;
	
	public Question4(List<Programmer> programmers) {
		super();
		this.programmers = programmers;
	}

	public void show() {
		answer();
	}
	
	private void answer() {
		Programmer fastest = fastestProgrammer();
		Programmer slowest = slowestProgrammer();
				
		SimpleLogger.info("");
		SimpleLogger.info("Question 4: How long did it take for fastest and slowest programmer to get her coffee?");
		SimpleLogger.info("Asnwer:");
		SimpleLogger.info("Fastest time is {0} seconds for programmer/programmer {1}", fastest.getTimeToGetCoffeeInSeconds(), fastest.getId());
		SimpleLogger.info("Slowest time is {0} seconds for programmer/programmer {1}", slowest.getTimeToGetCoffeeInSeconds(), slowest.getId());
	}
	
	public Programmer fastestProgrammer() {
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
	
	public Programmer slowestProgrammer() {
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
