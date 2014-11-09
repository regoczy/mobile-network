package services;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Service {

	private String name;

	private String prefix;

	private List<Subscription> subscriptions = new ArrayList<Subscription>();

	private int prePaidCallfee;

	private int prePaidTextfee;

	private int prePaidInternetfee;

	private int paidCallfee;

	private int paidTextfee;

	private int paidInternetfee;

	public Service(String name, String prefix) {
		this.name = name;
		this.prefix = prefix;

		System.out.println("Service created: " + this.name + " (" + this.prefix + ")");
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

	protected String generatePhoneNumber() {
		Random numberGenerator = new Random();
		String number = "";
		for (int i = 0; i<7; i++){
			number += numberGenerator.nextInt(9);
		}
		return number;
	}

	public void createAPrePaidSubscription(Subscriber subscriber) {
		PrePaidSubscription tmp = new PrePaidSubscription(
				generatePhoneNumber(), prePaidCallfee, prePaidTextfee, prePaidInternetfee);

		tmp.loadBalance(3500);

		subscriber.addSubscription(tmp);
		this.subscriptions.add(tmp);
		System.out.println("Pre-Paid type subscription is created in " + this.name + " for "
				+ subscriber.getName() + " (" + tmp.getNumber() + ")");
	}

	public void createAPaidSubscription(Subscriber subscriber) {
		Subscription tmp = new PaidSubscription(generatePhoneNumber(), paidCallfee, paidTextfee, paidInternetfee);

		subscriber.addSubscription(tmp);
		this.subscriptions.add(tmp);
		System.out.println("Paid type subscription is created in " + this.name + " for "
				+ subscriber.getName() + " (" + tmp.getNumber() + ")");
	}

	public void setPrePaidFees(int callfee, int textfee, int internetfee) {
		this.prePaidCallfee = callfee;
		this.prePaidTextfee = textfee;
		this.prePaidInternetfee = internetfee;
		System.out.println(this.getName() + "'s Pre-Paid type fees:"
				+ "\nCall: " + callfee + "\t SMS: " + textfee + "\tInternet: " + internetfee);
	}

	public void setPaidFees(int callfee, int textfee, int internetfee) {
		this.paidCallfee = callfee;
		this.paidTextfee = textfee;
		this.paidInternetfee = internetfee;
		System.out.println(this.getName() + "'s Paid type fees:"
				+ "\nCall: " + callfee + "\t SMS: " + textfee + "\tInternet: " + internetfee);
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

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

}