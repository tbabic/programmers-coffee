package good.game.studios.application.tomislav.babic.views;

import good.game.studios.application.tomislav.babic.model.Programmer;
import good.game.studios.application.tomislav.babic.util.Constants;

import java.text.MessageFormat;
import java.util.List;

import org.apache.log4j.Logger;

public class Question3 {

	private static final Logger logger = Logger.getLogger(Constants.VIEW_LOGGER_NAME);
	
	List<Programmer> programmers;
	
	public Question3(List<Programmer> programmers) {
		super();
		this.programmers = programmers;
	}

	public void show() {
		answer();
	}
	
	private void answer() {
		
		logger.info("");
		logger.info("Question 3: How much time does the average programmer spend getting their coffee?");
		logger.info("Asnwer:");
		logger.info(MessageFormat.format("Average time is {0} seconds", averageTimeForProgrammers()));
	}
	
	private double averageTimeForProgrammers() {
		double sum = 0;
		for (Programmer programmer : programmers) {
			sum += programmer.getTimeToGetCoffeeInSeconds();
		}
		return sum/programmers.size();
	}
}
