package reversi;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.util.Pair;

public class GuiDisplay implements Display {

	@Override
	public void printEnd(Contains type, int playerScore, int rivalScore) {
		 String player="Player 1";
		 String rival="Player 2";
		 if (type==Contains.White){
			 player="Player 2";
			 rival="Player 1";
		 }
		 if (playerScore > rivalScore) {
			 showAlert( "The winner is " + player + "!!  with a score of: " + playerScore);
		 } else if (playerScore < rivalScore) {
			 showAlert( "The winner is " + rival + "!!  with a score of: " + rivalScore);
		 } else  showAlert( "its a tie!");
	}

	@Override
	public void printMessage(String message) {
		 showAlert(message);
	}

	@Override
	public void noMoves(Contains type) {
		String player="Player 1";
		if (type==Contains.White){
			 player="Player 2";
		}
		showAlert(player+" also has no moves available, So the game is over");
	}
	
	private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
	
	 public void printBoard(Board board){};

	 public void printChosenMove(Pair<Integer,Integer> chosenMove,Contains type){};

	 public void printPossibleMoves( List<Pair<Integer, Integer> > movesVec){};

	 public void yourMove(Contains type){};

	 public void enterMove(){};
	
}

