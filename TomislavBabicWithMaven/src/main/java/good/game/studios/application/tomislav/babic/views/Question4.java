package good.game.studios.application.tomislav.babic.views;

import good.game.studios.application.tomislav.babic.model.Programmer;
import good.game.studios.application.tomislav.babic.util.Constants;

import java.text.MessageFormat;
import java.util.List;

import org.apache.log4j.Logger;

public class Question4 {

	private static final Logger logger = Logger.getLogger(Constants.VIEW_LOGGER_NAME);
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
				
		logger.info("");
		logger.info("Question 4: How long did it take for fastest and slowest programmer to get her coffee?");
		logger.info("Asnwer:");
		logger.info(MessageFormat.format("Fastest time is {0} seconds for programmer/programmer {1}", fastest.getTimeToGetCoffeeInSeconds(), fastest.getId()));
		logger.info(MessageFormat.format("Slowest time is {0} seconds for programmer/programmer {1}", slowest.getTimeToGetCoffeeInSeconds(), slowest.getId()));
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
