package services;

import activities.Call;
import activities.Internet;
import activities.Text;



public class PrePaidSubscription extends Subscription {

	public PrePaidSubscription(String number, int callfee, int textfee, int internetfee) {
		super(number, callfee, textfee, internetfee);
	}

	@Override
	public Call call(String number, int length) {
		if (0 < this.getBalance()) {
			return super.call(number, length);
		}

		System.out.println("Call denied!");
		// TODO exception

		return null;
	}

	@Override
	public Text text(String number, int characters) {
		if (0 < this.getBalance()) {
			return super.text(number, characters);
		}

		System.out.println("SMS denied!");
		// TODO exception

		return null;
	}

	@Override
	public Internet internet(int datatraffic) {
		if (0 < this.getBalance()) {
			return super.internet(datatraffic);
		}
		System.out.println("Internet denied!");
		// TODO exception

		return null;
	}
}