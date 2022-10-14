import java.util.ArrayList;
import java.util.List;

public class TriantaEna extends Game implements Rules{

	private int numberOfPlayers;
	private TEPlayer[] playerArray;
	private int turn = 0;
	private TEDesk teDeck;
	private ArrayList<TEPlayer> winPlayers;
	private boolean gameEnd = false;


	// --------------new--------------
	@Override
	public TEPlayer[] getPlayerArray(){
		return playerArray;
	}

	// --------------new--------------
	public TEDesk getTeDeck(){
		return teDeck;
	}
	
	public void setTeDeck() {
		teDeck = new TEDesk();
	}

	public boolean getGameEnd(){
		int count = 0;
		for(TEPlayer player: playerArray){
			if(player.getStatus() != "bust"){
				count++;
			}
		}
		gameEnd = count < 3;
		return gameEnd;
	}

	public void setGameEnd(){
		gameEnd = true;
	}

	// --------------modified--------------
	@Override
	public void initializePlayers(ArrayList<String> playerNames) {
		//for players with names
		numberOfPlayers = playerNames.size();
		playerArray = new TEPlayer[numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; i++) {
			playerArray[i] = new TEPlayer();
			playerArray[i].setName(playerNames.get(i));
			if(i == numberOfPlayers - 1){
				playerArray[i].setAmountRemaining(300);
				playerArray[i].setType("Dealer");

			} else {
				playerArray[i].setAmountRemaining(100);
				playerArray[i].setType("Player");
			}
			playerArray[i].setCards(new ArrayList<Card>());
			playerArray[i].setStatus("hit");
			playerArray[i].setScore(0);
		}
	}

	public void initializePlayers(int n) {
		//for players without names
		playerArray = new TEPlayer[n];
		for (int i = 0; i < n; i++) {
			playerArray[i] = new TEPlayer();
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

	public void placeBet(TEPlayer p, TEPlayer dealer, int betAmount) {
		p.setBetAmount(betAmount);
		p.setAmountRemaining(p.getAmountRemaining() - betAmount);
		dealer.setAmountRemaining(betAmount + dealer.getAmountRemaining());
	}
	
	

	//no need?
	//check if all players are stand/bust
	public boolean checkStandOrBust(TEPlayer p) {
		if (p.getHandValue() > 31) {
			p.setStatus("lose");
			return true;
		} else if (p.getAmountRemaining() <= 0){
			p.setStatus("bust");
			return true;
		}
		return false;
	}

	// --------------new--------------
	public void switchDealer(int index){
		// always keep the dealer be the last player
		TEPlayer temp = playerArray[index];
		playerArray[index] = playerArray[playerArray.length];
		playerArray[playerArray.length] = temp;
	}

	// --------------new--------------
	@Override
	public void isEnd(){
		int count = 0;
		for(TEPlayer player: playerArray){
			if(player.getStatus() != "bust"){
				count++;
			}
		}
		System.out.println(count);
		gameEnd = count < 3;
	}


	// --------------new--------------
	@Override
	public void printCurrentCards(){
		System.out.println("---------Current Cards---------");
		String table = "";
		for (TEPlayer player : playerArray) {
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

	public void allCardVisible() {
		for(TEPlayer p: playerArray) {
			for(Card card : p.getCards()) {
				card.setVisible(true);
			}
		}
	}

	// --------------new--------------
	@Override
	public ArrayList<TEPlayer> getWinners(){
		winPlayers = new ArrayList<TEPlayer>();
		for(TEPlayer player: playerArray){
			if(player.getStatus().equals("win")){
				winPlayers.add(player);
			}
		}
		return winPlayers;
	}


	@Override
	public boolean isWin(TEPlayer player, TEPlayer dealer) {
		boolean isWin = false;
		if (player.getType() == "Player") {
			if (player.getStatus() != "lose" && player.getStatus() != "fold" && player.getStatus() != "bust") {
				if (player.getHandValue() == 31 || (player.getHandValue() > dealer.getHandValue() && player.getHandValue() <= 31 && dealer.getHandValue() >= 27)) {
					player.setStatus("win");
					player.setScore(player.getScore() + 1);
					isWin = true;
					player.setAmountRemaining((2 * player.getBetAmount()) + player.getAmountRemaining());
					dealer.setAmountRemaining(dealer.getAmountRemaining() - (2 * player.getBetAmount()));
				}
				if (dealer.getStatus() == "lose") {
					if (player.getHandValue() <= 31) {
						player.setStatus("win");
						player.setScore(player.getScore() + 1);
						isWin = true;
						player.setAmountRemaining((2 * player.getBetAmount()) + player.getAmountRemaining());
						dealer.setAmountRemaining(dealer.getAmountRemaining() - (2 * player.getBetAmount()));
					}
				}
			}

		}
		return isWin;
	}

	public void endGame() {
		for (TEPlayer p : playerArray) {
			p.setBetAmount(0);
			p.setCards(new ArrayList<Card>());
			p.setHandValue(0);
			System.out.println("Amount remaining for Player " + p.getName() + ": " + String.valueOf(p.getAmountRemaining()));
			if(!p.getStatus().equals("bust")){
				p.setStatus("hit");
			}
		}
	}
}
