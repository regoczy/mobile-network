package services;

import activities.Activity;


public class PrePaidSubscription extends Subscription {

	public PrePaidSubscription(String number, int callfee, int textfee, int internetfee) {
		super(number, callfee, textfee, internetfee);
	}

	public void addToBalance() {
	}

	public void removeFromBalance() {
	}

	@Override
	public int getAllBillPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAllActivityPrice() {
		int priceOfActivities = 0;

		for (Activity activity : activities) {
			priceOfActivities += this.getPriceOfActivity(activity);
		}

		return -1 * priceOfActivities;
	}

}