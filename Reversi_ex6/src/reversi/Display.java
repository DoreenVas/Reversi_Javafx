package reversi;

import java.util.List;

import javafx.util.Pair;

public interface Display {
	 /***************************************************************************************
	    * function name: printBoard
	    * the input: Board *board
	    * the output:prints a visual table of the board we created
	    * the function operation:goes throw every cell in the board
	    ****************************************************************************************/
	    public void printBoard(Board board) ;
	    /***************************************************************************************
	     * function name: printChosenMove
	     * the input: the chosen pair and the type of the player
	     * the output:prints the chosen move according to the player who played it
	     * the function operation:direct access
	     ****************************************************************************************/
	    public void printChosenMove(Pair<Integer,Integer> chosenMove,Contains type);
	    /***************************************************************************************
	    * function name: printEnd
	    * the input: Contains type,int playerScore, int rivalScore
	    * the output: prints the winner and the score
	    * the function operation: checks to see who won and prints accordingly
	    ****************************************************************************************/
	    public void printEnd(Contains type,int playerScore, int rivalScore);
	    /***************************************************************************************
	    * function name: printMessage
	    * the input: string message
	    * the output: prints the message
	    * the function operation: direct access
	    ****************************************************************************************/
	    public void printMessage(String message);
	    /***************************************************************************************
	   * function name: noMoves
	   * the input: Contains type
	   * the output: prints to the player he has no moves
	   * the function operation: according to the player's type prints the correct output
	   ****************************************************************************************/
	    public void noMoves(Contains type);
	    /***************************************************************************************
	    * function name: printPossibleMoves
	    * the input:  vector<pair<int, int> > movesVec
	    * the output: prints the possible moves the player has
	    * the function operation:goes throw the vector of valid moves and prints each  move
	    ****************************************************************************************/
	    public void printPossibleMoves( List<Pair<Integer, Integer> > movesVec);
	    /***************************************************************************************
	   * function name: yourMove
	   * the input:  Contains type
	   * the output: informs the player it's his turn
	   * the function operation:prints according to the type received
	   ****************************************************************************************/
	    public void yourMove(Contains type);
	    /***************************************************************************************
	    * function name: enterMove
	    * the input:  none
	    * the output: prints request to make move
	    * the function operation:directly
	    ****************************************************************************************/
	    public void enterMove();
	
}
