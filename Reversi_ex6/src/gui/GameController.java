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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Pair;
import reversi.*;

/**
 * Class:GameController
 * game platform
 * 
 */
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
    private Display display;
    private static final int height =400;

    /**
     * function name: initialize
     * creates the game objects, sets a listener for the board
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setVariables();
		this.board=new Board(boardSize);
		display=new GuiDisplay();
		player1=new HumanPlayer(display,Contains.Black);
		player2= new HumanPlayer(display,Contains.White);
		player1.setColor(color1);
		player2.setColor(color2);
		Color currentColor;
		if(currentP==1){//player 1 starts
			currentColor=color1;
			logic=new DefaultLogic(player1,player2,board);
		}else{//player 2 starts
			currentColor=color2;
			logic=new DefaultLogic(player2,player1,board);
		}
		this.boardfx=new GameBoard(board,color1,color2, height);
		boardfx.setPrefWidth(height);
		boardfx.setPrefHeight(height);
		root.getChildren().add(0, boardfx);
		//set labels texts
		currentPlayer.setText("Player "+currentP);
		currentPlayer.setTextFill(currentColor);
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
	
	/**
	 * function name: start
	 * when the button is clicked the game starts over 
	 */
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
	
	/**
	 * function name: end
	 * when the button is clicked the game is over and we return to the Menu
	 */
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
	
	/**
	 * function name: setVariables
	 * we set default variables, if we can read from the file we replace them
	 */
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
	
	/**
	 * function name: playOneTurn
	 * we play a single turn (if possible), update the right labels and check if the game ended
	 */
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
			display.printMessage("no moves available, so your turn is passed");
		}
		else if (player.isNoMoves() && rival.isNoMoves()){
			display.noMoves(player.getType());
			display.printEnd(player.getType(), player.getScore(), rival.getScore());
			return;
		}
		else{
			Pair<Integer,Integer> chosenMove=boardfx.chosenMove();
			if (chosenMove==null || !movesVec.contains(chosenMove)){
				display.printMessage("Invalid choice, try again");
				return;
			}
			logic.makeMove(chosenMove.getKey(),chosenMove.getValue());
			boardfx.draw();
			logic.resetPossibleMoves();
		}
		if(player.getScore()+rival.getScore()==board.getBoardSize()*board.getBoardSize()){
			player1Score.setText(Integer.toString(player1.getScore()));
			player2Score.setText(Integer.toString(player2.getScore()));
			display.printEnd(player.getType(), player.getScore(), rival.getScore());
			return;
		}
		currentP=3-currentP;
		logic.switchPlayer();
		currentPlayer.setText("Player "+currentP);
		currentPlayer.setTextFill(rival.getColor());
		player1Score.setText(Integer.toString(player1.getScore()));
		player2Score.setText(Integer.toString(player2.getScore()));
		
	}
	
}
