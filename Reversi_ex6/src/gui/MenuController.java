package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Class: MenuController
 * the main Menu to get to setting and the game
 *
 */
public class MenuController {
	@FXML
	private Button play;
	@FXML
	private Button exit;
	@FXML
	private Button settings;
	
	/**
	 * function name: play
	 * when the button is clicked a new game window opens
	 */
	@FXML
	protected void play() {
		try {
			Stage stage = (Stage) play.getScene().getWindow();
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
	 * function name: settings
	 * when the button is clicked a new settings window opens
	 */
	@FXML
	protected void settings() {					
		try {
			Stage stage = (Stage) settings.getScene().getWindow();
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("Settings.fxml"));
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("settings.css").toExternalForm());
			stage.setTitle("Game Settings");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * function name: exit
	 * when the button is clicked the window closes
	 */
	@FXML
	protected void exit() {
		Stage stage = (Stage) exit.getScene().getWindow();
	    stage.close();
	}
}

