package services;

import java.util.ArrayList;
import java.util.List;

public class Subscriber {

	private String name;

	private List<Subscription> subscriptions = new ArrayList<Subscription>();

	public Subscriber(String name) {
		this.name = name;
		System.out.println("Új előfizető: " + name);
	}

	public void addSubscription(Subscription subscription) {
		this.subscriptions.add(subscription);
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void call(int indexOfSubscription, String phoneNumber, int length) {
		System.out.println(this.name + " hívást kezdmenyézett(" + phoneNumber + ")");
		this.getSubscriptions().get(indexOfSubscription).call(phoneNumber, length);
	}

	public void text(int indexOfSubscription, String phoneNumber, int characters) {
		System.out.println(this.name + " SMS-t küldött(" + phoneNumber + ")");
		this.getSubscriptions().get(indexOfSubscription).text(phoneNumber, characters);
	}

	public void useInternet(int indexOfSubscription, int datatraffic) {
		System.out.println(this.name + " használta a mobil internetet");
		this.getSubscriptions().get(indexOfSubscription).internet(datatraffic);
	}

	public String getName() {
		return name;
	}

}