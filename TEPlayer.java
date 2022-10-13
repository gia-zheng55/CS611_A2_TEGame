public class TEPlayer extends Player{

    private String status;
    private boolean hasAce = false;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getHasAce() {
        return hasAce;
    }

    public void setHasAce(boolean hasAce) {
        this.hasAce = hasAce;
    }

    @Override
    public void addCard(Card card){
        getCards().add(card);
        if(isInteger(card.getNumber())){
            setHandValue(getHandValue() + Integer.parseInt(card.getNumber()));
        }else{
            if(card.getNumber().equals("A")){
                setHandValue(getHandValue() + 11);
                setHasAce(true);
            }else{
                setHandValue(getHandValue() + 10);
            }
        }
    }

    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
