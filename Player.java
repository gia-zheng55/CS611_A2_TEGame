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
	private String status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getHandValue(){
		return handValue;
	}

//	calculate all possible sums
//	// --------------new--------------
//	public int[] getHandValueForPlayer() {
//		int countAce = 0;
//		int sum = 0;
//		for(Card card: cards){
//			String number = card.getNumber();
//			if(number.equals("A")){
//				countAce++;
//			}else if(number.equals("J") || number.equals("Q") || number.equals("K")){
//				sum += 10;
//			}else{
//				sum += Integer.parseInt(number);
//			}
//		}
//		if(countAce == 0){
//			return new int[]{sum + countAce*11, sum + (countAce-1)*11 + 1};
//		}else{
//			return new int[]{sum};
//		}
//	}

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
