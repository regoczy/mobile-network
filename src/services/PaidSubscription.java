package services;


public class PaidSubscription extends Subscription {

	public PaidSubscription(String number, int callfee, int textfee, int internetfee) {
		super(number, callfee, textfee, internetfee);
	}

}