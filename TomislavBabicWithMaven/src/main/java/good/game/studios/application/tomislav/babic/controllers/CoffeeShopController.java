package good.game.studios.application.tomislav.babic.controllers;

import good.game.studios.application.tomislav.babic.model.CoffeeShop;
import good.game.studios.application.tomislav.babic.model.Programmer;
import good.game.studios.application.tomislav.babic.util.SimpleLogger;
import good.game.studios.application.tomislav.babic.views.Question1;
import good.game.studios.application.tomislav.babic.views.Question2;
import good.game.studios.application.tomislav.babic.views.Question3;
import good.game.studios.application.tomislav.babic.views.Question4;

import java.util.ArrayList;
import java.util.List;

public class CoffeeShopController {
	
	
	public void processMultipleDays(List<Integer> numberOfCustomersPerDay) {
		CoffeeShop coffeeShop = new CoffeeShop();
		for (int numberOfCustomers : numberOfCustomersPerDay) {
			processSingleDay(coffeeShop, numberOfCustomers);
			coffeeShop.newDay();
		}
		
	}
	
	public void processOnlyOneDay(int numberOfCustomers) {
		CoffeeShop coffeeShop = new CoffeeShop();
		processSingleDay(coffeeShop, numberOfCustomers);
						
	}
	
	private void processSingleDay(CoffeeShop coffeeShop, int numberOfCustomers) {
		
		List<Programmer> programmers = getCustomers(numberOfCustomers);
		coffeeShop.openUp(programmers);
		for (Programmer programmer : programmers) {
			programmer.enters(coffeeShop);
		}
		coffeeShop.waitForClosing();
		SimpleLogger.debug("Done!!!!!!!!!!!!!!!!!!!!!!");
		showViews(coffeeShop, programmers);
						
	}
	
	private List<Programmer> getCustomers(int n) {
		List<Programmer> programmers = new ArrayList<Programmer>();
		for (int i = 0; i<n; i++) {
			programmers.add(new Programmer());
		}
		return programmers;
	}
	
	private void showViews(CoffeeShop coffeeShop, List<Programmer> programmers) {
		Question1 q1 = new Question1(coffeeShop.getCashRegisters());
		q1.show();
		Question2 q2 = new Question2(coffeeShop.getCoffeeMachine());
		q2.show();
		Question3 q3 = new Question3(programmers);
		q3.show();
		Question4 q4 = new Question4(programmers);
		q4.show();
	}
}


