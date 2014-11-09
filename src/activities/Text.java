package activities;

import services.Subscription;

public class Text extends TwoSidedActivity {

	private int characters;

	public Text(Subscription initiator, Subscription receiver, int characters) {
		super(initiator, receiver);
		this.characters = characters;
		
		/*System.out.println(
				"SMS sent from " + initiator.getNumber()
				+ " to " + receiver.getNumber() + " (" + characters + " karakter)"
			);*/
	}

	public int getCharacters() {
		return characters;
	}

}