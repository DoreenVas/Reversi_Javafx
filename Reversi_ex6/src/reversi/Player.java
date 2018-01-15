package reversi;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public abstract class Player {
	
	protected Display display;
	protected Contains type;
	protected int score;
	protected boolean noMoves;
	
	/***************************************************************************************************
	* constructor name:Player
	* the input: char x
	* the output: none
	* the function operation:initializes the score to (2),noMoves to (false),and disk to the char we got(x)                               
	***************************************************************************************************/
	
	public Player(Display display,Contains x){
		this.score=2;
		this.noMoves=false;
		this.type=x; 
		this.display=display;
	}

	public Contains getType(){
	    return type;
	}

	public void setScore(int score) {
	    this.score += score;
	}

	public int getScore() {
	    return score;
	}


	public boolean isNoMoves() {
	    return noMoves;
	}

	public void setNoMoves(boolean noMoves) {
	    this.noMoves = noMoves;
	}

	public List<Pair<Integer, Integer> > possibleMovesVector(Board board) {
		List<Pair<Integer, Integer>> movesVec = new ArrayList<Pair<Integer,Integer>>();
	    for (int row = 0; row < board.getBoardSize(); row++) {
	        for (int col = 0; col < board.getBoardSize(); col++) {
	            if (board.cellAt(row,col).isOption()){
	            	Pair<Integer,Integer> pair = new Pair<>(row,col);
	            	movesVec.add(pair);
	            }
	        }
	    }
	    return movesVec;
	}
	
	public abstract void preMovePrint(Board board);
	public abstract Pair<Integer,Integer> chooseMove(Board board,GameLogic logic);
	
}
