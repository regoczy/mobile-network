package services;

import activities.Activity;

public class PaidSubscription extends Subscription {

	public PaidSubscription(String number, int callfee, int textfee, int internetfee) {
		super(number, callfee, textfee, internetfee);
	}

	@Override
	public int getAllActivityPrice() {
		int priceOfActivities = 0;

		for (Activity activity : activities) {
			priceOfActivities += this.getPriceOfActivity(activity);
		}

		return -1 * priceOfActivities;
	}

	public void payBill() {
	}

	@Override
	public int getAllBillPrice() {
		return 0;
	}

}