import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import services.Service;
import services.Subscriber;
import countries.Country;


public class Main
{
	public static List<Service> services = new ArrayList<Service>();
	public static List<Country> countries = new ArrayList<Country>();
	public static List<Subscriber> subscribers = new ArrayList<Subscriber>();

	public static void main(String[] args)
	{
		/*Country hungary = new Country("Hungary", "06");

		Service telenor = new Service("Telenor", "20");
		hungary.addService(telenor);

		Subscriber pitt = new Subscriber("Brad Pitt");
		Subscriber ricsi = new Subscriber("Reg�czy Rich�rd");

		telenor.createAPrePaidSubscription(pitt);
		telenor.createAPaidSubscription(ricsi);

		pitt.call(
			0,
			hungary.getCode() +
			telenor.getPrefix() +
			ricsi.getSubscriptions().get(0).getNumber(),
			126);

		pitt.text(
			0,
			hungary.getCode() +
			telenor.getPrefix() +
			ricsi.getSubscriptions().get(0).getNumber(),
			61);
 /*
		ricsi.call(
			0,
			hungary.getCode() +
			telenor.getPrefix() +
			pitt.getSubscriptions().get(0).getNumber(),
			23
			);

		ricsi.text(
			0,
			hungary.getCode() +
			telenor.getPrefix() +
			pitt.getSubscriptions().get(0).getNumber(),
			50
			);

		ricsi.useInternet(0, 64);

		System.out.println(telenor.getName() + " fel� tartoz�sok:" + telenor.getDebits());*/

		countries = generateCountries();
		services = generateServices();
		subscribers = generateSubscribers();
		bindServicesToCountries();
		generateSubscriptions();
		generateActivities();
		for (Service service : services) {
			System.out.println(service.getName() + "bevétele: " + service.getDebits());
		}

	}

	public static List<Country> generateCountries()
	{
		List<Country> countries = new ArrayList<Country>();

		Random r = new Random();
		int numberOfCountries = r.nextInt(2) + 1;

		for (int i = 0; i < numberOfCountries; i++) {
			countries.add(new Country("ország" + i, "" + (r.nextInt(89) + 10)));
		}

		return countries;
	}

	public static List<Service> generateServices()
	{
		List<Service> services = new ArrayList<Service>();

		Random r = new Random();
		int	numberOfServices = r.nextInt(3) + 1;

		for (int i = 0; i < numberOfServices; i++) {
			Service tmp = new Service("szolgáltató" + i, "" + (r.nextInt(89) + 10));
			tmp.setPrePaidFees(r.nextInt(20) + 1, r.nextInt(20) + 1, r.nextInt(20) + 1);
			tmp.setPaidFees(r.nextInt(20) + 1, r.nextInt(20) + 1, r.nextInt(20) + 1);
			services.add(tmp);
		}

		return services;
	}


	public static void bindServicesToCountries() {
		Random r = new Random();

		for (Service service : services) {

			countries.get(r.nextInt(countries.size())).addService(service);
		}
	}

	public static List<Subscriber> generateSubscribers()
	{
		List<Subscriber> subscribers = new ArrayList<Subscriber>();

		Random r = new Random();
		int numberOfSubscribers = r.nextInt(9) + 1;

		for (int i = 0; i < numberOfSubscribers; i++) {
			subscribers.add(new Subscriber("előfizető" + i));
		}

		return subscribers;
	}

	public static void generateSubscriptions(){
		Random r = new Random();

		for (Subscriber subscriber : subscribers){
			int numberOfSubscriptions = r.nextInt(3) + 1;
			for (int i = 0; i < numberOfSubscriptions; i++){
				if (r.nextBoolean()){
					services.get(r.nextInt(services.size())).createAPaidSubscription(subscriber);
				}
				else{
					services.get(r.nextInt(services.size())).createAPrePaidSubscription(subscriber);
				}
			}
		}
	}

	public static void generateActivities(){
		Random r = new Random();

		for (Subscriber subscriber : subscribers){
			int numberOfActivities = r.nextInt(5) + 1;
			for (int i = 0; i < numberOfActivities; i++){
				int typeOfActivity = r.nextInt(3);
				switch (typeOfActivity) {
					case 0:
						subscriber.call(
								r.nextInt(subscriber.getSubscriptions().size()),
								getRandomPhoneNumber(),
								r.nextInt(600) + 1);
						break;
					case 1:
						subscriber.text(
								r.nextInt(subscriber.getSubscriptions().size()),
								getRandomPhoneNumber(),
								r.nextInt(320) + 1);
						break;
					case 2:
						subscriber.useInternet(r.nextInt(subscriber.getSubscriptions().size()), r.nextInt(100) +1);
						break;
				}
			}
		}
	}
	public static String getRandomPhoneNumber(){
		String phoneNumber = "";

		Random r = new Random();

		Country tmpCountry = countries.get(r.nextInt(countries.size()));
		phoneNumber += tmpCountry.getCode();

		if (0 == tmpCountry.getServices().size()) {

			return getRandomPhoneNumber();
		}

		Service tmpService = tmpCountry.getServices().get(r.nextInt(tmpCountry.getServices().size()));
		phoneNumber += tmpService.getPrefix();

		if (0 == tmpService.getSubscriptions().size()) {

			return getRandomPhoneNumber();
		}

		phoneNumber += tmpService.getSubscriptions().get(r.nextInt(tmpService.getSubscriptions().size())).getNumber();

		return phoneNumber;
	}
}
