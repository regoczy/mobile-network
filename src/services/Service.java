package services;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Service {

	private String name;

	private String prefix;

	private int income;

	private List<Subscription> subscriptions = new ArrayList<Subscription>();

	public Service(String name, String prefix) {
		this.name = name;
		this.prefix = prefix;
	}

	public String getPrefix() {
	return prefix;
	}

	public int getDebits() {
		int debits = 0;

		for(Subscription subscription : subscriptions) {
			debits += subscription.getAllActivityPrice();
		}

		return debits;
	}

	public int getCredits() {
		int income = 0;

		for(Subscription subscription : subscriptions) {
			income += subscription.getAllBillPrice();
		}

		return income;
	}

	public void addToIncome() {
	}

	protected String generatePhoneNumber() {
		Random numberGenerator = new Random();
		String number = "";
		for (int i = 0; i<7; i++){
			number += numberGenerator.nextInt(9);
		}
		return number;
	}

	public void createAPrePaidSubscription(Subscriber subscriber) {
		PrePaidSubscription tmp = new PrePaidSubscription(generatePhoneNumber(), 20, 12, 5);

		subscriber.addSubscription(tmp);
		this.subscriptions.add(tmp);
	}

	public void createAPaidSubscription(Subscriber subscriber) {
		Subscription tmp = new PaidSubscription(generatePhoneNumber(), 15, 10, 10);

		subscriber.addSubscription(tmp);
		this.subscriptions.add(tmp);
	}

	public Subscription getSubscriptionByNumber(String phoneNumber) {
		for (Subscription subscription : subscriptions) {
			if (phoneNumber.equals(subscription.getNumber())) {
				return subscription;
			}
		}

		// TODO: exception
		return null;

	}

	public String getName() {
		return this.name;
	}

}