public class TEPlayer extends Player{

    private String status;
    private boolean hasAce = false;
    private boolean hasAceAsOne = false;
    private String type; // fold / hit(init) / cash out / stand / go bust
	private int betAmount;

	private int amountRemaining;
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

	public int getHandValue() {
		return handValue;
	}

	public void setHandValue(int handValue) {
		this.handValue = handValue;
	}

    public boolean isHasAceAsOne() {
		return hasAceAsOne;
	}

	public void setHasAceAsOne(boolean hasAceAsOne) {
		this.hasAceAsOne = hasAceAsOne;
	}

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
