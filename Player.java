import java.util.ArrayList;
import java.util.List;

public class Player {

	private String name;
	private String colour;
	private String type;
	private int betAmount;
	private int amountRemaining;
	private int score;
	private ArrayList<Card> cards;
	// fold / hit(init) / cash out / stand / go bust
	private int handValue;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(int betAmount) {
		this.betAmount = betAmount;
	}

	public int getAmountRemaining() {
		return amountRemaining;
	}

	public void setAmountRemaining(int amountRemaining) {
		this.amountRemaining = amountRemaining;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	// --------------new--------------
	public void printCards(){
		String handCards = "Your cards: ";
		for(Card card: cards){
			handCards += card + " ";
		}
		System.out.println(handCards);
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public int getHandValue(){
		return handValue;
	}

	public void setHandValue(int handValue) {
		this.handValue = handValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	// --------------new--------------
	public void addCard(Card card){
		cards.add(card);
	}
}
