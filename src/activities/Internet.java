package activities;

import services.Subscription;


public class Internet extends OneSidedActivity {

	public int datatraffic;

	public Internet(Subscription initiator, int datatraffic) {
		super(initiator);

		this.datatraffic = datatraffic;
	}

	public int getDataTraffic() {
		return datatraffic;
	}

}