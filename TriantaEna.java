import java.util.ArrayList;
import java.util.List;

public class TriantaEna implements Rules{
	
	private int numberOfPlayers;
	Player[] playerArray;
	private int turn = 0;
	private TEDeck teDeck;

	// --------------new--------------
	public Player[] getPlayerArray(){
		return playerArray;
	}

	// --------------new--------------
	public TEDeck getTeDeck(){
		return teDeck;
	}

	// --------------modified--------------
	public void initializePlayers(ArrayList<String> playerNames) {
		//for players with names
		numberOfPlayers = playerNames.size();
		playerArray = new Player[numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; i++) {
			playerArray[i] = new Player();
			playerArray[i].setName(playerNames.get(i));
			if(i == numberOfPlayers - 1){
				playerArray[i].setAmountRemaining(300);
				playerArray[i].setType("Dealer");
			} else {
				playerArray[i].setAmountRemaining(100);
				playerArray[i].setType("Player");
			}
			playerArray[i].setStatus("hit");
		}
	}
	
	public void initializePlayers(int n) {
		//for players without names
		playerArray = new Player[n];
		for (int i = 0; i < n; i++) {
			playerArray[i] = new Player();
			playerArray[i].setAmountRemaining(100);
		}
	}

	// --------------new--------------
	// Change dealer
	public void changeDealer(int index){
		switchDealer(index);
		playerArray[playerArray.length].setType("Dealer");
		playerArray[index].setType("Player");
	}

	public void placeBet(Player p, int betAmount) {
		p.setBetAmount(betAmount);
		p.setAmountRemaining(p.getAmountRemaining() - betAmount);
	}

	public void takeHit(Player p) {
		p.setStatus("hit");
		//distribute card
		//make a function for randomly choosing a card
		//distributeCard(p);
		
		//check total for player
		if (p.getHandValue() > 31){
			p.setAmountRemaining(p.getAmountRemaining() - p.getBetAmount());
		}

	}

	//no need?
	//check if all players are stand/bust
	public boolean checkStandOrBust() {
		boolean standOrBust = false;
		for (int i=0; i<playerArray.length; i++) {
			String playerStatus = playerArray[i].getStatus();
			if (playerStatus == "hit"){
				return standOrBust;
			}
		}
		return true;
	}
	
	//if they are, dealer takes hit
	
	public void dealerTakesHit(Player d) {
		//get card till d.getFaceValue() >= 27
		int dealerHandValue = d.getHandValue();
		
		List<Integer> winners = getWinnerAfterDealerTakesHit(dealerHandValue);
	}
	
	public List<Integer> getWinnerAfterDealerTakesHit(int dealerHandValue){
		List<Integer> winners = new ArrayList<Integer>();
		
		for (int i = 0; i < playerArray.length; i++) {
			if (playerArray[i].getHandValue() >= dealerHandValue) {
				winners.add(i);
			}
		}
		return winners;
	}

	// --------------new--------------
	public void switchDealer(int index){
		// always keep the dealer be the last player
		Player temp = playerArray[index];
		playerArray[index] = playerArray[playerArray.length];
		playerArray[playerArray.length] = temp;
	}

	// --------------new--------------
	public boolean isEnd(){
		if(playerArray.length < 3){
			return true;
		}
		int count = 0;
		for(Player player: playerArray){
			if(player.getStatus() == "cash out" || player.getStatus() == "go bust"){
				count++;
			}
		}
		return count == playerArray.length;
	}

	public void takeTurn(){
		turn = (turn + 1) % playerArray.length;
	}

	// --------------new--------------
	public void printCurrentCards(){
		System.out.println("---------Current Cards---------");
		String table = "";
		for (Player player : playerArray) {
			table += player.getName() + ": ";
			for (Card card : player.getCards()) {
				if (card.isVisible()) {
					table += card + " ";
				} else {
					table += "* ";
				}
			}
			table += "\n";
		}
		System.out.println(table);
	}

	// --------------new--------------
	public void allCardVisible(){
		for(Player player: playerArray){
			for (Card card : player.getCards()) {
				card.setVisible(true);
			}
		}
	}

//	// --------------new--------------
//	public boolean isWin(Player player){
//		for(int sum: player.getHandValueForPlayer()){
//			if(sum == 31){
//				player.setStatus("win");
//				return true;
//			}
//		}
//		return false;
//	}
	
	@Override
	public boolean isValidMove() {
		return false;
	}

	@Override
	public String isWin(String[][] gb) {
		// TODO Auto-generated method stub
		return null;
	}

	// --------------modified--------------
	@Override
	public boolean play(Player p, String status, String card) {
		p.setStatus(status);
		if (status.equals("hit")) {
			//distribute card
//			p.getCards().add(card);
			//call function to check for winner here?
		}

		return false;
	}

	// --------------modified--------------
	@Override
	public void distributeCard(Player p, String card) {
		//call method from desk class
//		p.getCards().add(card);
		//face down or up?
	}

}
