import java.util.Random;

public class TEDesk extends Desk{

    
    Deck deck = new Deck();

    private Card chooseCard(){
        Random r = new Random();
        int index = r.nextInt(deck.getCards().size());
        Card card = deck.getCards().get(index);
        deck.getCards().remove(card);
        return card;
    }

    @Override
    public Card placeFaceUp(){
        return chooseCard();
    }

    @Override
    public Card placeFaceDown(){
        Card card = chooseCard();
        card.setVisible(false);
        return card;
    }

}
