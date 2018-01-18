package reversi;

import java.util.List;

import javafx.util.Pair;

/**
* Interface name: Display
* in charge of displaying the game to the players
*/
public interface Display {

	/**
	* function name: printBoard
	* @param board
	* prints the board by cells
	*/    
	public void printBoard(Board board);

	/**
	* function name: printChosenMove
	* @param chosenMov
	* @param Contains type
	* direct access
	*/ 
	public void printChosenMove(Pair<Integer,Integer> chosenMove,Contains type);

	/**
	* function name: printEnd
	* @param Contains type
	* @param int playerScore
	* @param int rivalScore
	* checks to see who won and prints accordingly
	*/    
	public void printEnd(Contains type,int playerScore, int rivalScore);

	/**
	* function name: printMessage
	* @param String message
	* direct access
	*/    
	public void printMessage(String message);

	/**
	* function name: noMoves
	* @param Contains type
	* according to the player's type prints the correct output
	*/     
	public void noMoves(Contains type);
	
	/**
	* function name: printPossibleMoves
	* @param vector<pair<int, int> > movesVec
	* goes throw the vector of valid moves and prints each  move
	*/     
	public void printPossibleMoves( List<Pair<Integer, Integer> > movesVec);

	/**
	* function name: yourMove
	* @param Contains type
	* informs the player it's his turn
	*/    
	public void yourMove(Contains type);

	/**
	* function name: enterMove
	* direct access
	*/    
	public void enterMove();
	
}
