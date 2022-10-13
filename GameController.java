import java.util.ArrayList;
import java.util.Scanner;

public class GameController {

	private int playerNumber = 0;
	private ArrayList<String> players;
	
	Scanner scanner = new Scanner(System.in);
	
	private void beforeStart(){
		System.out.println("Please enter the number of players (3-7)");
		Scanner scanner = new Scanner(System.in);
		if(scanner.hasNext()){
			String next = scanner.next();
			while(!isInteger(next) || Integer.parseInt(next) > 7 || Integer.parseInt(next) < 3){
				System.out.println("Invalid player number, please enter an integer (3-7)");
				next = scanner.next();
			}
			playerNumber = Integer.parseInt(next);
			players = new ArrayList<>();
			setPlayerName();
			setBanker();
		}
	}

	private void setPlayerName(){
		for(int i = 1; i <= playerNumber; i++){
			System.out.println("Please enter the name of player " + i);
			Scanner scanner = new Scanner(System.in);
			if(scanner.hasNext()){
				String name = scanner.next();
				players.add(name);
			}
		}
	}

	private void setBanker(){
		System.out.println("Which player will become the banker(dealer)?");
		String output = "";
		for(int i = 1; i <= playerNumber; i++){
			output += i + ". " + players.get(i - 1) + "\n";
		}
		System.out.println(output);
		Scanner scanner = new Scanner(System.in);
		String bankerIndex = "";
		if(scanner.hasNext()){
			bankerIndex = scanner.next();
			while(!isInteger(bankerIndex) || Integer.parseInt(bankerIndex) < 1 || Integer.parseInt(bankerIndex) > playerNumber){
				System.out.println("Invalid player number, please enter a number from above");
				bankerIndex = scanner.next();
			}
		}
		int index = Integer.parseInt(bankerIndex) - 1;
		String dealerName = players.get(index);
		players.set(index, players.get(playerNumber - 1));
		players.set(playerNumber - 1, dealerName);
	}

	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean yOrnChoose(){
		
		String next = "";
		if(scanner.hasNext()){
			next = scanner.next();
			while(!next.equals("y") && !next.equals("n")){
				System.out.println("Invalid input, please enter y or n");
				next = scanner.next();
			}
		}
		return next.equals("y");
	}

	public void start(){
		System.out.println("Welcome to the Trianta Ena");
		beforeStart();
		TriantaEna te = new TriantaEna();
		te.initializePlayers(players);
		while (!te.getGameEnd()) {
			te.setTeDeck();
			boolean playerWins = false;
			// find the dealer(always be operated independently)
			TEPlayer dealer = te.getPlayerArray()[playerNumber - 1];

			// place players' 1st card
			for(TEPlayer player : te.getPlayerArray()){
				if(player.getType() == "Player" && player.getStatus() == "hit"){
					System.out.println("Player" + player.getName() + " gets a face down card");
					//TODO initialize deck
					player.addCard(te.getTeDeck().placeFaceDown());
					player.printCards();
				}
			}

			//place dealer's 1st card
			dealer.addCard(te.getTeDeck().placeFaceUp());
			System.out.println("Dealer" + dealer.getName() + " gets a face up card");
			dealer.printCards();
			
			//show current cards
			te.printCurrentCards();

			// bet or keep(fold)
			for(TEPlayer player : te.getPlayerArray()){
				if(player.getType() == "Player" && player.getStatus() == "hit"){
					System.out.println("Player" + player.getName() + ", do you want bet for this round? (y/n)");
					player.printCards();
					if(yOrnChoose()){
						te.placeBet(player, dealer, 10);
					}else{
						player.setStatus("fold");
					}
				}
			}

			// place 2nd and 3rd cards for players
			for(TEPlayer player : te.getPlayerArray()){
				if(player.getType() == "Player" && player.getStatus() == "hit"){
					System.out.println("Player" + player.getName() + " gets two face up card");
					player.addCard(te.getTeDeck().placeFaceUp());
					player.addCard(te.getTeDeck().placeFaceUp());
					player.printCards();
					if (player.getHasAce()) {
						if(!player.isHasAceAsOne()){
							System.out.println("Player" + player.getName() + ", you have Ace card, do you want to use it as 1? (y/n)");
							if(yOrnChoose()){
								player.setHandValue(player.getHandValue() - 10);
								player.setHasAceAsOne(true);
							}
						}
						
					}
				}
			}

			// place 2nd and 3rd cards for dealer
			dealer.addCard(te.getTeDeck().placeFaceUp());
			dealer.addCard(te.getTeDeck().placeFaceUp());
			System.out.println("Dealer" + dealer.getName() + " gets two face up card");
			dealer.printCards();
			
			if(dealer.getHasAce()){
				System.out.println("Dealer" + dealer.getName() + ", you have Ace card, do you want to use it as 1? (y/n)");
				if(yOrnChoose()){
					dealer.setHandValue(dealer.getHandValue() - 10);
				}
			}
			
			if(dealer.getHandValue() == 31){
				dealer.setStatus("win");
				dealer.setScore(dealer.getScore() + 1);
			}

			//check TE
			if (dealer.getStatus() != "win") {
				for(TEPlayer player : te.getPlayerArray()){
					if(player.getType() == "Player" && player.getStatus() == "hit"){
						if (te.isWin(player, dealer)) {
							playerWins = true;
						}
					}
					
				}
				if (te.getWinners().size() != 0) {
					te.allCardVisible();
					te.printCurrentCards();
					System.out.println("The winners of the game are: ");
					for (TEPlayer p : te.getWinners()) {
						System.out.println(p.getName());
					}
				}
				
				if (playerWins == true) {
					for(TEPlayer player : te.getPlayerArray()){
					System.out.println("Would you like to cash out?(y/n)");
					if (yOrnChoose()) {
						player.setStatus("bust");
					}
					}
					te.endGame();
					continue;
				}
				
			} else if (dealer.getStatus() == "win"){
				te.allCardVisible();
				te.printCurrentCards();
				System.out.println("The winner of the game is: " + dealer.getName());
				for(TEPlayer player : te.getPlayerArray()){
					System.out.println("Would you like to cash out?(y/n)");
					if (yOrnChoose()) {
						player.setStatus("bust");
					}
				}
				
				System.out.println("Score is: " + String.valueOf(dealer.getScore()));
				te.endGame();
				continue;
			}
			
			//show current cards
			te.printCurrentCards();


			//hit or stand (Player round)
			for(TEPlayer player : te.getPlayerArray()){
				if(player.getType() == "Player" && player.getStatus() == "hit"){
					while(player.getStatus() == "hit"){
						if (!te.checkStandOrBust(player)) {
							System.out.println("Player" + player.getName() + ", do you want to hit? (y/n)");
							if(yOrnChoose()){
								System.out.println("Player" + player.getName() + " gets one face up card");
								player.addCard(te.getTeDeck().placeFaceUp());
								player.printCards();
								if (player.getHasAce()) {
									if(!player.isHasAceAsOne()){
										System.out.println("Player" + player.getName() + ", you have Ace card, do you want to use it as 1? (y/n)");
										if(yOrnChoose()){
											player.setHandValue(player.getHandValue() - 10);
											player.setHasAceAsOne(true);
										}
									}
								}
							}else{
								player.setStatus("stand");
							}
						}
					} 
					if (player.getHandValue() > 31) {
						player.setStatus("lose");
					}
				}
			}

			//hit (Dealer round)
			while (dealer.getHandValue() <= 27){
				System.out.println("Dealer" + dealer.getName() + " gets one face up card");
				dealer.addCard(te.getTeDeck().placeFaceUp());
				dealer.printCards();
				if(dealer.getHasAce()){
					if(!dealer.isHasAceAsOne()){
					System.out.println("Dealer" + dealer.getName() + ", you have Ace card, do you want to use it as 1? (y/n)");
					if(yOrnChoose()){
						dealer.setHandValue(dealer.getHandValue() - 10);
						dealer.setHasAceAsOne(true);
					}
					}
				}
			}
			if (dealer.getHandValue() > 31) {
				dealer.setStatus("lose");
			}

			
			//find winner
			for(TEPlayer player : te.getPlayerArray()){
				if (te.isWin(player, dealer)) {
					playerWins = true;
				}
			}
			
			if (playerWins != true && dealer.getStatus() != "lose") {
				dealer.setStatus("win");
				dealer.setScore(dealer.getScore() + 1);
			}
			
			if (te.getWinners() != null) {
				System.out.println("The winners of the game are: ");
				for (TEPlayer p : te.getWinners()) {
					System.out.println(p.getName());
				}
			}
			te.allCardVisible();
			te.printCurrentCards();
			if (playerWins == true) {
				for(TEPlayer player : te.getPlayerArray()){
				System.out.println("Would you like to cash out?(y/n)");
				if (yOrnChoose()) {
					player.setStatus("bust");
				}
				}
			}
			
			te.endGame();
		}
	}

}
