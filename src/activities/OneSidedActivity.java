package activities;

import services.Subscription;

public abstract class OneSidedActivity extends Activity {

	public OneSidedActivity(Subscription initiator) {
		this.initiator = initiator;
	}
}
