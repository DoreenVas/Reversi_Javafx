package reversi;
import java.util.List;
import java.util.Scanner;
import javafx.util.Pair;

public class HumanPlayer extends Player {
	protected Pair<Integer, Integer> chosenMove;// a vector of all the cells which are possible moves
	
	public HumanPlayer(Display display,Contains x){
		super(display,x);
	}	
	
	public void preMovePrint(Board board) {
	display.yourMove(type);
	List< Pair<Integer,Integer> > movesVec=possibleMovesVector(board);
	display.printPossibleMoves(movesVec);
	}
	
	public Pair<Integer,Integer> chooseMove(Board board,GameLogic logic) {
		int a=0,b=0;
		boolean validInput=false;
		while (!validInput) {
			display.printMessage("\n"+"Please enter row space than col:");
			Scanner s = new Scanner(System.in);
			a=s.nextInt();
			b=s.nextInt();
		    if (a > board.getBoardSize() || a < 1 || b > board.getBoardSize() || b < 1) {
		    	System.out.print("incorrect choice");
		    } else if (!board.cellAt(a - 1, b - 1).isOption()) {
		    	System.out.print("that cell is not a possible move, try again");
		    } else {
		        validInput = true;
		    }
		}
		
		
		this.chosenMove=new Pair<>(a-1,b-1);
		return this.chosenMove;
	}
}
