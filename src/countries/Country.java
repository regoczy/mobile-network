package countries;

import java.util.ArrayList;
import java.util.List;

import services.Service;

public class Country {

	public static List<Country> countries = new ArrayList<Country>();

	private String name;

	private String code;

	public List<Service> services = new ArrayList<Service>();

	public static Country getCountryByCode(String code) {
		for (Country country : countries) {
			if (code.equals(country.code)) {
				return country;
			}
		}

		// TODO: exciption
		return null;
	}

	public Country(String name, String code) {
		this.name = name;
		this.code = code;
		countries.add(this);
	}

	public String getCode() {
		return code;
	}

	public void addService(Service service)
	{
		services.add(service);
	}

	public Service getServiceByCode(String serviceCode) {
		for (Service service : services) {
			if (serviceCode.equals(service.getPrefix())) {

				return service;
			}
		}

		// TODO: exception
		return null;

	}

}