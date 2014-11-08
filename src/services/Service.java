package services;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Service {

	private String name;

	private String prefix;

	private int income;

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

		System.out.println("Szolgáltató létrehozva: " + this.name + " " + this.prefix);
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
		PrePaidSubscription tmp = new PrePaidSubscription(
				generatePhoneNumber(), prePaidCallfee, prePaidTextfee, prePaidInternetfee);

		tmp.loadBalance(3500);

		subscriber.addSubscription(tmp);
		this.subscriptions.add(tmp);
		System.out.println(this.name + " szolgáltatónál létrejött egy feltöltőkártyás előfizetes "
				+ subscriber.getName() + " számára (" + tmp.getNumber() + ")");
	}

	public void createAPaidSubscription(Subscriber subscriber) {
		Subscription tmp = new PaidSubscription(generatePhoneNumber(), paidCallfee, paidTextfee, paidInternetfee);

		subscriber.addSubscription(tmp);
		this.subscriptions.add(tmp);
		System.out.println(this.name + " szolgáltatónál létrejött egy számlás előfizetes "
				+ subscriber.getName() + " számára (" + tmp.getNumber() + ")");
	}

	public void setPrePaidFees(int callfee, int textfee, int internetfee) {
		this.prePaidCallfee = callfee;
		this.prePaidTextfee = textfee;
		this.prePaidInternetfee = internetfee;
		System.out.println("Feltöltőkártyás:"
				+ "\nHívás díj " + callfee + "\t SMS díj: " + textfee + "\tInternet díj: " + internetfee);
	}

	public void setPaidFees(int callfee, int textfee, int internetfee) {
		this.paidCallfee = callfee;
		this.paidTextfee = textfee;
		this.paidInternetfee = internetfee;
		System.out.println("Előfizetéses:"
				+ "\nHívás díj: " + callfee + "\t SMS díj: " + textfee + "\tInternet díj: " + internetfee);
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