import java.util.ArrayList;
import java.util.List;
//make this class abstract and remove function definitions
public class Player {

	private String name;
	private String colour;
	
	private int score;
	private ArrayList<Card> cards;
	
	
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
