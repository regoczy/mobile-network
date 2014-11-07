import services.Service;
import services.Subscriber;
import countries.Country;


public class Main
{

	public static void main(String[] args)
	{
		Country hungary = new Country("Hungary", "06");

		Service telenor = new Service("Telenor", "20");
		hungary.addService(telenor);

		Subscriber pitt = new Subscriber("Brad Pitt");
		Subscriber ricsi = new Subscriber("Regőczy Richárd");

		telenor.createAPrePaidSubscription(pitt);
		telenor.createAPaidSubscription(ricsi);

		pitt.call(
			0,
			hungary.getCode() +
			telenor.getPrefix() +
			ricsi.getSubscriptions().get(0).getNumber(),
			126);

		ricsi.call(
			0,
			hungary.getCode() +
			telenor.getPrefix() +
			pitt.getSubscriptions().get(0).getNumber(),
			23
			);


		System.out.println(telenor.getName() + " felé tartozások:" + telenor.getDebits());
	}
}
