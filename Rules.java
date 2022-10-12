public interface Rules {
	
	public abstract boolean isValidMove();
	
	public abstract String isWin(String[][] gb);	
	
	public boolean play(Player p, String status, String card);
	
	public abstract void distributeCard(Player p, String card);
	
}
