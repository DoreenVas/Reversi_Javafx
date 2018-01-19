package reversi;
import javafx.util.Pair;

/**
* class name: GameLogic
* description: contains the rules of the game
*/
public abstract class GameLogic {
	protected Player player;// the player currently playing
	protected Player rival;// the player in "stand by"
	protected Board board;
	

	public abstract void makeMove(int row, int col);	
	public abstract void possibleMoves();
	
	/**
	 * constructor name:GameLogic
	 * @param player1P
	 * @param player2P
	 * @param board
	 * initializes the fields
	 */
	public GameLogic(Player player1P,Player player2P,Board board) {
	    player=player1P;
	    rival=player2P;
	    this.board=board;
	}

	/**
	 * function name:  resetPossibleMoves
	 * for every cell in board the option is now false for a move and the flipOption table is initialized
	 */
	public void resetPossibleMoves() {
	    for (int row = 0; row < board.getBoardSize(); row++) {
	        for (int col = 0; col < board.getBoardSize(); col++) {
	            board.cellAt(row,col).setOption(false);
	            board.cellAt(row,col).clearFlipOptions();
	        }
	    }
	}

	/**
	 * function name:  switchPlayer
	 * switches between the player and the rival to change who is currently playing
	 */
	public void switchPlayer(){
	    Player pSwap = this.player;
	    this.player = this.rival;
	    this.rival = pSwap;
	};

	/**
	 * function name:  getCurrentScore
	 * @return a pair of the score of each player
	 * returns the score of each player by direct access
	 */
	public Pair<Integer,Integer> getCurrentScore(){
		Pair<Integer,Integer>pair=new Pair<>(player.getScore(),rival.getScore());
	    return pair;
	}
}
