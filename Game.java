import java.util.ArrayList;

public abstract class Game {
	
	public abstract Player[] getPlayerArray();
	
	public abstract void initializePlayers(ArrayList<String> playerNames);
	
	public abstract TEDesk getTeDeck();
	
	public abstract void printCurrentCards();
	
	public abstract ArrayList<TEPlayer> getWinners();
}
