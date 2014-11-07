package services;

import java.util.ArrayList;
import java.util.List;

public class Subscriber {

	private String name;

	private List<Subscription> subscriptions = new ArrayList<Subscription>();

	public Subscriber(String name) {
		this.name = name;
	}

	public int getCash() {
	return 0;
	}

	public void removeCash() {
	}

	public void text(String receiver, int characters) {
	}

	public void useInternet(int datatraffic) {
	}

	public void addCash() {
	}

	public void addSubscription(Subscription subscription) {
		this.subscriptions.add(subscription);


	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void call(int indexOfSubscription, String phoneNumber, int length) {
		System.out.println(this.name + " hívás kezdeményezett(" + phoneNumber + ")");
		this.getSubscriptions().get(indexOfSubscription).call(phoneNumber, length);
	}

}