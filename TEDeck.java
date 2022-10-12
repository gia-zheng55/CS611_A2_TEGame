import java.util.Random;

public class TEDeck extends Deck{

    public TEDeck(){
        super();
    }

    private Card chooseCard(){
        Random r = new Random();
        int index = r.nextInt(getCards().size());
        Card card = getCards().get(index);
        getCards().remove(card);
        return card;
    }

    public Card placeFaceUp(){
        return chooseCard();
    }

    public Card placeFaceDown(){
        Card card = chooseCard();
        card.setVisible(false);
        return card;
    }

}
