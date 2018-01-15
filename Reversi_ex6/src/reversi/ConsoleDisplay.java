package reversi;

import java.util.List;

import javafx.util.Pair;

public class ConsoleDisplay implements Display {

	@Override
	public void printBoard(Board board) {
		System.out.print ("   ");
	    for (int i = 0; i < board.getBoardSize(); ++i) {
	    	System.out.print((i + 1)+ "  ");
	    }
	    System.out.println();

	    for (int row = 0; row < board.getBoardSize(); row++) {
	    	System.out.print((row + 1)+ "| ");
	        for (int col = 0; col < board.getBoardSize(); col++) {
	            switch (board.cellAt(row,col).getContains()) {
	                case Empty:
	                	System.out.print(" | ");
	                    break;
	                case White:
	                	System.out.print("o| ");
	                    break;
	                case Black:
	                	System.out.print("x| ");
	                    break;
	            }
	        }
	        System.out.println();
	        System.out.print( "..");
	        for (int i = 0; i < board.getBoardSize(); i++) {
	        	 System.out.print( "...");
	        }
	        System.out.println();

	    }
		
	}

	@Override
	public void printChosenMove(Pair<Integer, Integer> chosenMove, Contains type) {
		 System.out.print((playerDisk(type))+" played  " +chosenMove.getKey()+1+","+chosenMove.getValue()+1);  
		 System.out.println();
	}

	@Override
	public void printEnd(Contains type, int playerScore, int rivalScore) {
		 char playerDisk=' ';
		 char rivalDisk=' ';
		    switch (type){
		        case Black: {
		            playerDisk = 'X';
		            rivalDisk='O';
		            break;
		        }
		        case White: {
		            playerDisk = 'O';
		            rivalDisk='X';
		            break;
		        }
		    }
		    if (playerScore > rivalScore) {
		    	 System.out.print( "The winner is " + playerDisk + "!!  with a score of: " + playerScore);
		    } else if (playerScore < rivalScore) {
		    	 System.out.print( "The winner is " + rivalDisk + "!!  with a score of: " + rivalScore);
		    } else  System.out.print( "its a tie!");
		
	}

	@Override
	public void printMessage(String message) {
		 System.out.print(message);
		 System.out.println();
	}

	@Override
	public void noMoves(Contains type) {
		System.out.print( playerDisk(type)+" also has no moves available, So the game is over");
		System.out.println();
	}

	@Override
	public void printPossibleMoves(List<Pair<Integer, Integer>> movesVec) {
		 for (int i=0;i<movesVec.size();i++){
			 System.out.print( "(" +(movesVec.get(i).getKey()+1)+ "," +(movesVec.get(i).getValue()+1)+ ") ");
		    }
	}

	@Override
	public void yourMove(Contains type) {
		System.out.println(playerDisk(type)+ ": It's your move" );
		System.out.print("Your possible moves are: ");
		
	}

	@Override
	public void enterMove() {
		System.out.println();
		System.out.println("Please enter your move, write row than space than col: ");
		
	}
	
	private char playerDisk(Contains type){
	  char playerDisk=' ';
      switch (type){
          case Black: {
              playerDisk = 'X';
              break;
          }
          case White: {
              playerDisk = 'O';
              break;
          }
      }
      return playerDisk;
	}
}
