package good.game.studios.application.tomislav.babic.views;

import good.game.studios.application.tomislav.babic.model.CashRegister;
import good.game.studios.application.tomislav.babic.model.PaymentType;
import good.game.studios.application.tomislav.babic.util.Constants;

import java.text.MessageFormat;
import java.util.List;

import org.apache.log4j.Logger;

public class Question1 {

	private static final Logger logger = Logger.getLogger(Constants.VIEW_LOGGER_NAME);
	
	List<CashRegister> cashRegisters;
	
	public Question1(List<CashRegister> cashRegisters) {
		super();
		this.cashRegisters = cashRegisters;
	}

	public void show() {
		answer();
	}
	
	private void answer() {
		logger.info(""); //new line
		logger.info("Question 1: How much coffee is sold (total and per payment type)?");
		logger.info("Asnwer:");
		
		logger.info(MessageFormat.format("Total Coffee sold: {0}", CashRegister.getAllCoffeeSold(cashRegisters)));
		for (PaymentType paymentType : PaymentType.values()) {
			int soldByType = CashRegister.getCoffeeSoldByType(paymentType, cashRegisters);
			logger.info(MessageFormat.format("Coffee sold {0} by payment type: {1}", soldByType, paymentType));
		}
	}
}
