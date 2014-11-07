package activities;

import services.Subscription;

public class Text extends TwoSidedActivity {

	private int characters;

	public Text(Subscription initiator, Subscription receiver, int characters) {
		super(initiator, receiver);
		this.characters = characters;
	}

public int getCharacters() {
	// TODO Auto-generated method stub
	return 0;
}

}