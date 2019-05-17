package finalGame.objects;
/*
 * Authors:
 * 		Matthias
 * 		Sean
 * 		Zach
 * 		Richard
 * 
 */


public class Card {
	private CardEnum color;
	private CardEnum nomination;
	
	public Card(CardEnum color, CardEnum nomination) {
		this.color = color;
		this.nomination = nomination;
	}
	
	public CardEnum getColor() {
		return color;
	}
	
	public void setColor(CardEnum color) {
		this.color = color;
	}
	
	public CardEnum getNomination() {
		return nomination;
	}
	
	public void setNomination(CardEnum nomination) {
		this.nomination = nomination;
	}
	
	public boolean compare(Card testCard) {
		if (this.color == testCard.getColor() || this.nomination == testCard.getNomination() || this.color.stringValue().contentEquals("black")) {
			return true;
		} return false;
	}
	
	@Override
	public String toString() {
		return String.format("Color: %s\nNomination: %s\n", this.color.toString(), this.nomination.toString());
	}
}
