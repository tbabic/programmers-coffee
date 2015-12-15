package good.game.studios.application.tomislav.babic.model;

import java.util.List;


public class Receipt extends BaseModel {

	private CashRegister cashRegister;
	private CoffeeType coffeeType;
	private PaymentType paymentType;
	
	public Receipt(CashRegister cashRegister, CoffeeType coffeeType,
			PaymentType paymentType) {
		super();
		this.cashRegister = cashRegister;
		this.coffeeType = coffeeType;
		this.paymentType = paymentType;
	}

	public CashRegister getCashRegister() {
		return cashRegister;
	}

	public CoffeeType getCoffeeType() {
		return coffeeType;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}
	
	public static int getReceiptsByPaymentType(
			PaymentType paymentType, List<Receipt> receipts) {
		int n = 0;
		for (Receipt receipt : receipts ) {
			if (receipt.paymentType == paymentType) {
				n++;
			}
		}
		return n;
		
	}
	
	
}
