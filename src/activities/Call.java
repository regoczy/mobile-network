package activities;

import services.Subscription;

public class Call extends TwoSidedActivity {

	private int length;

	public Call(Subscription initiator, Subscription receiver, int length) {
		super(initiator, receiver);
		this.length = length;
		System.out.println(
			"Telefonálás törtönt " + initiator.getNumber()
			+ " és " + receiver.getNumber() + " között(" + length + "s)"
		);
	}

	public int getLength()
	{
		return length;
	}

}