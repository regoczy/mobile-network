package activities;

import services.Subscription;

public class Text extends TwoSidedActivity {

	private int characters;

	public Text(Subscription initiator, Subscription receiver, int characters) {
		super(initiator, receiver);
		this.characters = characters;
		System.out.println(
				"SMS küldés " + initiator.getNumber()
				+ " és " + receiver.getNumber() + " között(" + characters + " karakter)"
			);
	}

	public int getCharacters() {
		return characters;
	}

}