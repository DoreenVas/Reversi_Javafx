package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuController {
	@FXML
	private Button play;
	@FXML
	private Button exit;
	@FXML
	private Button settings;
	
	@FXML
	protected void play() {
		try {
			Stage stage = (Stage) play.getScene().getWindow();
			HBox root = (HBox)FXMLLoader.load(getClass().getResource("Game.fxml"));
			Scene scene = new Scene(root,400,400);
			stage.setTitle("Reversi Game");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	protected void settings() {					
		try {
			Stage stage = (Stage) settings.getScene().getWindow();
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("Settings.fxml"));
			Scene scene = new Scene(root,600,400);
			stage.setTitle("Game Settings");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	protected void exit() {
		Stage stage = (Stage) exit.getScene().getWindow();
	    stage.close();
	}
}

