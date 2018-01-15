package reversi;

public class Main {

	public static void main(String[] args) {
		    Board board=new Board(6);
		    Display display=new ConsoleDisplay();
		    Player player1=new HumanPlayer(display,Contains.Black);// it's player's turn
		    Player player2= new HumanPlayer(display,Contains.White);
		    GameLogic logic=new DefaultLogic(player1,player2,board);
		    Game game=new Game(player1,player2,board,logic,display);
		    game.play();

	}

}
