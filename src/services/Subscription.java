package services;

import java.util.ArrayList;
import java.util.List;

import activities.Activity;
import activities.Call;
import activities.Internet;
import activities.Text;
import countries.Country;

public abstract class Subscription {

	protected int callfee;

	protected int textfee;

	protected int internetfee;

	protected String number;

	protected List<Activity> activities = new ArrayList<Activity>();

	protected List<Integer> bills = new ArrayList<Integer>();

	public Subscription(String number, int callfee, int textfee, int internetfee) {
		this.number = number;
		this.callfee = callfee;
		this.textfee = textfee;
		this.internetfee = internetfee;
	}

	public String getNumber() {
		return number;
	}

	public Call call(String number, int length) {
		String countryCode = number.substring(0, 2);
		String serviceCode = number.substring(2, 4);
		String phoneNumber = number.substring(4);

		Subscription subscription = Country.getCountryByCode(countryCode)
			.getServiceByCode(serviceCode)
			.getSubscriptionByNumber(phoneNumber);

		Call call = new Call(this, subscription, length);
		this.activities.add(call);

		System.out.println("Hívás megtörtént. ára :" + ( length / 60 + 1 ) * callfee + " Ft");
		System.out.println("Aktuális egyenleg :" + this.getBalance() + " Ft");

		return call;
	}

	public Text text(String number, int characters) {
		String countryCode = number.substring(0, 2);
		String serviceCode = number.substring(2, 4);
		String phoneNumber = number.substring(4);

		Subscription subscription = Country.getCountryByCode(countryCode)
				.getServiceByCode(serviceCode)
				.getSubscriptionByNumber(phoneNumber);

		Text text = new Text(this, subscription, characters);
		this.activities.add(text);

		System.out.println("SMS elküldve. ára :" + ( characters / 160 + 1 ) * textfee + " Ft");
		System.out.println("Aktuális egyenleg :" + this.getBalance() + " Ft");

		return text;
	}

	public Internet internet(int datatraffic) {
		Internet internet = new Internet(this, datatraffic);
		this.activities.add(internet);

		System.out.println("Aktuális böngészés ára:" + datatraffic * internetfee + " Ft");
		System.out.println("Aktuális egyenleg :" + this.getBalance() + " Ft");

		return internet;
	}

	public int getPriceOfActivity(Activity activity) {
		if (activity instanceof Call) {
			return (((Call)activity).getLength() / 60 +1 ) * callfee;
		}

		if (activity instanceof Text) {
			return (((Text)activity).getCharacters() / 160 + 1) * textfee;
		}

		if (activity instanceof Internet) {
			return ((Internet)activity).getDataTraffic() * internetfee;
		}


		return 0;
	}

	public int getBalance()
	{
		return this.getAllBillPrice() - this.getAllActivityPrice();
	}

	public int getAllBillPrice() {
		int amountOfBills = 0;

		for (int bill : bills ) {
			amountOfBills += bill;
		}

		return amountOfBills;
	}

	public int getAllActivityPrice()
	{
		int amountOfActivities = 0;

		for (Activity activity : activities) {
			amountOfActivities += this.getPriceOfActivity(activity);
		}

		return amountOfActivities;
	}

	public void loadBalance(int amountOfBill)
	{
		this.bills.add(amountOfBill);
	}

	@Override
	public String toString() {
		return this.number;
	}

}