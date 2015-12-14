package good.game.studios.application.tomislav.babic.views;

import good.game.studios.application.tomislav.babic.model.Programmer;
import good.game.studios.application.tomislav.babic.util.SimpleLogger;

import java.util.List;

public class Question3 {

	List<Programmer> programmers;
	
	public Question3(List<Programmer> programmers) {
		super();
		this.programmers = programmers;
	}

	public void show() {
		answer();
	}
	
	private void answer() {
		
		SimpleLogger.info("");
		SimpleLogger.info("Question 3: How much time does the average programmer spend getting their coffee?");
		SimpleLogger.info("Asnwer:");
		SimpleLogger.info("Average time is {0} seconds", averageTimeForProgrammers());
	}
	
	private double averageTimeForProgrammers() {
		double sum = 0;
		for (Programmer programmer : programmers) {
			sum += programmer.getTimeToGetCoffeeInSeconds();
		}
		return sum/programmers.size();
	}
}
