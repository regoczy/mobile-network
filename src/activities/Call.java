package activities;

import services.Subscription;

public class Call extends TwoSidedActivity {

	private int length;

	public Call(Subscription initiator, Subscription receiver, int length) {
		super(initiator, receiver);
		this.length = length;
		
		/*System.out.println(
			"Call from " + initiator.getNumber()
			+ " to " + receiver.getNumber() + " (" + length + "s)"
		);*/
	}

	public int getLength()
	{
		return length;
	}

}