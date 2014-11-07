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

	protected String number;

	protected int internetfee;

	protected List<Activity> activities = new ArrayList<Activity>();

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

		System.out.println("Hívás megtörtént. Ára:" + length * callfee + " Ft");
		System.out.println("Aktuális egyenleg:" + this.getBalance() + " Ft");

		return call;
	}

	public int getPriceOfActivity(Activity activity) {
		if (activity instanceof Call) {
			return ((Call)activity).getLength() * callfee;
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
		return this.getAllActivityPrice() + this.getAllBillPrice();
	}

	public abstract int getAllBillPrice() ;

	public abstract int getAllActivityPrice();
}