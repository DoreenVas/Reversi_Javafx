package reversi;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.util.Pair;

/**
* class name: Player
* contains the possible Players in the game
*/
public abstract class Player {
	
	protected Display display;
	protected Contains type;
	protected int score;
	protected boolean noMoves;
	protected Color color;
	
	/**
	 * constructor name:Player
	 * @param display
	 * @param x
	 * initializes the score to (2),noMoves to (false),and disk to the char we got(x)
	 */
	public Player(Display display,Contains x){
		this.score=2;
		this.noMoves=false;
		this.type=x; 
		this.display=display;
	}

	/**
	 * function name: getType
	 * @return returns the type of the player
	 * direct access
	 */
	public Contains getType(){
	    return type;
	}

	/**
	 * function name: setScore
	 * @param score
	 * sets the score to the variable it received
	 */
	public void setScore(int score) {
	    this.score += score;
	}

	/**
	 * function name: getScore
	 * @return the score
	 * direct access
	 */
	public int getScore() {
	    return score;
	}
	
	/**
	 * function name: getColor
	 * @return Color
	 * direct access
	 */
	 public Color getColor(){
		 return color;
	 }
	 
	 /**
	 * function name: setColor
	 * @param Color
	 * direct access
	 */
	 public void setColor(Color color){
		 this.color=color;
	 }

	 /**
	  * function name: isNoMoves
	  * @return true/false
	  * direct access
	  */
	public boolean isNoMoves() {
	    return noMoves;
	}

	/**
	 * function name: setNoMoves
	 * @param noMoves
	 * direct access
	 */
	public void setNoMoves(boolean noMoves) {
	    this.noMoves = noMoves;
	}

	/**
	 * function name: possibleMovesVector
	 * @param board
	 * @return vector< pair<int,int> >
	 * goes throw the board and for each cell if he's an option adds
	 * it to the vector.returns the vector.
	 */
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
