import java.util.ArrayList;
import java.util.Scanner;

public class GameController {

    private int playerNumber = 0;
    private ArrayList<String> players;

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
        System.out.println("Which player gonna become the first banker(dealer)?");
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
        players.set(index, players.get(playerNumber));
        players.set(playerNumber, dealerName);
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
        Scanner scanner = new Scanner(System.in);
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
        while (!te.isEnd()) {
            // find the dealer(always be operated independently)
            Player dealer = te.getPlayerArray()[playerNumber - 1];

            // place players' 1st card
            for(Player player : te.getPlayerArray()){
                if(player.getType() == "Player" && player.getStatus() == "hit"){
                    System.out.println("Player" + player.getName() + " gets a face down card");
                    player.addCard(te.getTeDeck().placeFaceDown());
                    player.printCards();
                }
            }

            //place dealer's 1st card
            dealer.addCard(te.getTeDeck().placeFaceUp());

            //show current cards
            te.printCurrentCards();

            // bet or keep(fold)
            for(Player player : te.getPlayerArray()){
                if(player.getType() == "Player" && player.getStatus() == "hit"){
                    System.out.println("Player" + player.getName() + ", do you want bet for this round? (y/n)");
                    player.printCards();
                    if(yOrnChoose()){
                        te.placeBet(player, 10);
                    }else{
                        player.setStatus("fold");
                    }
                }
            }

            // place 2nd and 3rd cards for players
            for(Player player : te.getPlayerArray()){
                if(player.getType() == "Player" && player.getStatus() == "hit"){
                    System.out.println("Player" + player.getName() + " gets two face up card");
                    player.addCard(te.getTeDeck().placeFaceUp());
                    player.addCard(te.getTeDeck().placeFaceUp());
                    player.printCards();
                }
            }

            // place 2nd and 3rd cards for dealer
            dealer.addCard(te.getTeDeck().placeFaceUp());
            dealer.addCard(te.getTeDeck().placeFaceUp());

            //show current cards
            te.printCurrentCards();

            //check TE


            //hit or stand (Player round)
            for(Player player : te.getPlayerArray()){
                if(player.getType() == "Player" && player.getStatus() == "hit"){
                    while(player.getStatus() == "hit"){
                        System.out.println("Player" + player.getName() + ", do you want to hit? (y/n)");
                        if(yOrnChoose()){
                            System.out.println("Player" + player.getName() + " gets one face up card");
                            player.addCard(te.getTeDeck().placeFaceUp());
                            player.printCards();
                        }else{
                            player.setStatus("stand");
                        }
                    }
                }
            }

            //hit (Dealer round)
            

        }
    }

}
