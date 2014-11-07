package activities;

import services.Subscription;

public abstract class TwoSidedActivity extends Activity {
	Subscription receiver;

	public TwoSidedActivity(Subscription initiator, Subscription receiver2)
	{
		this.initiator = initiator;
		this.receiver = receiver2;
	}
}
