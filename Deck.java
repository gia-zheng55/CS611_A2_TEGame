import java.util.LinkedList;
import java.util.Random;

public class Deck {

    private LinkedList<Card> cards;

    private final String[] cardType = new String[]{"Spade", "Heart", "Club", "Dianmond"};
    private final String[] cardNumber = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    public Deck(){
        cards = new LinkedList<>();
        initCards();
    }

    public LinkedList<Card> getCards(){
        return cards;
    }

    private void initCards(){
        for(String num: cardNumber){
            for(String type: cardType){
                Card card = new Card(type, num, true);
                cards.add(card);
            }
        }
    }

    public boolean isEmpty(){
        return cards.size() == 0;
    }

}
