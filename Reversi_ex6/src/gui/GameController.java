package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;
import reversi.*;

public class GameController implements Initializable {
	@FXML
	private HBox root; 
    @FXML
    private Button start;
    @FXML
    private Button end;
    @FXML
    private Label player1Score;
    @FXML
    private Label player2Score;
    @FXML
    private Label currentPlayer;

    private GameBoard boardfx;
    private Color color1;
    private Color color2;
    private Player player1;
    private Player player2;
    private int currentP;
    private int boardSize;
    private Board board;
    private GameLogic logic;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setVariables();
		this.board=new Board(boardSize);
		Display display=new ConsoleDisplay();
		player1=new HumanPlayer(display,Contains.Black);
		player2= new HumanPlayer(display,Contains.White);
		player1.setColor(color1);
		player2.setColor(color2);
		if(currentP==1){//player 1 starts
			logic=new DefaultLogic(player1,player2,board);
		}else{//player 2 starts
			logic=new DefaultLogic(player2,player1,board);
		}
		this.boardfx=new GameBoard(board,color1,color2);
		boardfx.setPrefWidth(400);
		boardfx.setPrefHeight(400);
		root.getChildren().add(0, boardfx);
		//set labels texts
		currentPlayer.setText("Player "+currentP);
		player1Score.setText("2");
		player2Score.setText("2");
		boardfx.setOnMouseClicked(event ->{
			playOneTurn();
		});
		
		//resize window
		root.widthProperty().addListener((observable, oldValue, newValue) -> {
			 double boardNewWidth = newValue.doubleValue() - 120;
			 boardfx.setPrefWidth(boardNewWidth);
			 boardfx.draw();
		});

		root.heightProperty().addListener((observable, oldValue, newValue) -> {
			boardfx.setPrefHeight(newValue.doubleValue());
			boardfx.draw();
		});
	}
	
	@FXML
	protected void start() {
		try {
			Stage stage = (Stage) start.getScene().getWindow();
			HBox root = (HBox)FXMLLoader.load(getClass().getResource("Game.fxml"));
			Scene scene = new Scene(root,600,400);
			stage.setTitle("Play Game");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	protected void end() {
		try {
			Stage stage = (Stage) end.getScene().getWindow();
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Scene scene = new Scene(root,400,400);
			stage.setTitle("Reversi Game");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setVariables(){
		//set default
		this.color1=Color.valueOf("0x000000ff");
		this.color2=Color.valueOf("0xffffffff");
		this.currentP=1;
		this.boardSize=8;
		try{
			File settingsFile=new File("settings.txt");
			//if settings exist use them otherwise use default
			if(settingsFile.exists()){
				BufferedReader readFile = new BufferedReader(new InputStreamReader(new FileInputStream(settingsFile)));
		        String line = readFile.readLine();
		        ArrayList<String> settings = new ArrayList<>();
		        while(line != null) {
		        	settings.add(line);
		            line = readFile.readLine();
		        }
		        //settings corrupted, use default
		        if(settings.size()!=4){
		        	readFile.close();
		        	return;
		        }
		        if (settings.get(0).equals("Player 1"))
		        	this.currentP=1;
		        else this.currentP=2;
		        this.color1=Color.valueOf(settings.get(1));
		        this.color2=Color.valueOf(settings.get(2));
		        this.boardSize=Integer.parseInt(settings.get(3));
		        readFile.close();
			}		
		} catch (Exception e) {
	        System.out.println("Problem reading from file");
	    }
	}
	
	private void playOneTurn(){
		logic.possibleMoves();
		Player player,rival;
		if(currentP==1){
			player=player1;
			rival=player2;
		}else{
			player=player2;
			rival=player1;
		}
		List< Pair<Integer,Integer> > movesVec=player.possibleMovesVector(board);
		if (player.isNoMoves() && !rival.isNoMoves()){
			showAlert("No moves available, your turn is passed");
		}
		else if (player.isNoMoves() && rival.isNoMoves()){
			showAlert("no moves for you too \n Game Over");
			return;
		}
		else{
			Pair<Integer,Integer> chosenMove=boardfx.chosenMove();
			if (chosenMove==null || !movesVec.contains(chosenMove)){
				showAlert("Invalid choice, try again");
				return;
			}
			logic.makeMove(chosenMove.getKey(),chosenMove.getValue());
			boardfx.draw();
			logic.resetPossibleMoves();
		}
		if(player.getScore()+rival.getScore()==board.getBoardSize()*board.getBoardSize()){
			showAlert("Game Over");
			return;
		}
		currentP=3-currentP;
		logic.switchPlayer();
		currentPlayer.setText("Player "+currentP);
		player1Score.setText(Integer.toString(player1.getScore()));
		player2Score.setText(Integer.toString(player2.getScore()));
		
	}
	
	private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}






































