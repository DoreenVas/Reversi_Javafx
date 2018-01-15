package reversi;
import javafx.util.Pair;

public abstract class GameLogic {
	protected Player player;// the player currently playing
	protected Player rival;// the player in "stand by"
	protected Board board;
	
	public abstract void makeMove(int row, int col);
	public abstract void possibleMoves();
	
	public GameLogic(Player player1P,Player player2P,Board board) {
	    player=player1P;
	    rival=player2P;
	    this.board=board;
	}

	public void resetPossibleMoves() {
	    for (int row = 0; row < board.getBoardSize(); row++) {
	        for (int col = 0; col < board.getBoardSize(); col++) {
	            board.cellAt(row,col).setOption(false);
	            board.cellAt(row,col).clearFlipOptions();
	        }
	    }
	}

	public void switchPlayer(){
	    Player pSwap = this.player;
	    this.player = this.rival;
	    this.rival = pSwap;
	};

	public Pair<Integer,Integer> getCurrentScore(){
		Pair<Integer,Integer>pair=new Pair<>(player.getScore(),rival.getScore());
	    return pair;
	}
}
