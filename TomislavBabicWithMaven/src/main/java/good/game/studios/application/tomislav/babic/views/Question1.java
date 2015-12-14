package good.game.studios.application.tomislav.babic.views;

import good.game.studios.application.tomislav.babic.model.CashRegister;
import good.game.studios.application.tomislav.babic.model.PaymentType;
import good.game.studios.application.tomislav.babic.util.SimpleLogger;

import java.util.List;

public class Question1 {

	List<CashRegister> cashRegisters;
	
	public Question1(List<CashRegister> cashRegisters) {
		super();
		this.cashRegisters = cashRegisters;
	}

	public void show() {
		answer();
	}
	
	private void answer() {
		SimpleLogger.info(""); //new line
		SimpleLogger.info("Question 1: How much coffee is sold (total and per payment type)?");
		SimpleLogger.info("Asnwer:");
		
		SimpleLogger.info("Total Coffee sold: {0}", CashRegister.getAllCoffeeSold(cashRegisters));
		for (PaymentType paymentType : PaymentType.values()) {
			int soldByType = CashRegister.getCoffeeSoldByType(paymentType, cashRegisters);
			SimpleLogger.info("Coffee sold {0} by payment type: {1}", soldByType, paymentType);
		}
	}
}
