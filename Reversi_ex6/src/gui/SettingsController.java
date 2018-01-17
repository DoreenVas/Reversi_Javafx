package gui;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SettingsController {

	@FXML
	private ChoiceBox<String> startPlayer;
	@FXML
    private ColorPicker colorPicker1;
    @FXML
    private ColorPicker colorPicker2;
    @FXML
    private ChoiceBox<Integer> boardSize;
    @FXML
    private Button submit;
    @FXML
    private Button back;
    

	@FXML
	protected void submit() {
		Color color1 = colorPicker1.getValue();
        Color color2 = colorPicker2.getValue();
        if(color1.toString().equals(color2.toString())) {
            this.showAlert("Players Colors cannot be the same!");
            return;
        }
        String startPlayer = (String)this.startPlayer.getValue();
        String colorPicker1 = color1.toString();
        String colorPicker2 = color2.toString();
        String boardSize = this.boardSize.getValue().toString();
        
        try {
			BufferedWriter write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("settings.txt")));
			write.write(startPlayer);
			write.newLine();
			write.write(colorPicker1);
			write.newLine();
			write.write(colorPicker2);
			write.newLine();
			write.write(boardSize);
			write.newLine();
			write.close();
        } catch (Exception e) {
            System.out.println("Problem writing to file");
        }
		
		//goes back to the Menu
		try {
			Stage stage = (Stage) submit.getScene().getWindow();
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Scene scene = new Scene(root,400,400);
			stage.setTitle("Reversi Game");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	protected void back() {
		try {
			Stage stage = (Stage) back.getScene().getWindow();
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Scene scene = new Scene(root,400,400);
			stage.setTitle("Reversi Game");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
	
}
