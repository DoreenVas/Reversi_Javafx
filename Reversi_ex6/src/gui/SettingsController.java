package gui;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SettingsController implements Initializable {

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
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try{
			File settingsFile=new File("settings.txt");
			//if settings exist use them 
			//if no settings exist use default from fxml
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
		        startPlayer.setValue(settings.get(0));
		        colorPicker1.setValue(Color.valueOf(settings.get(1)));
		        colorPicker2.setValue(Color.valueOf(settings.get(2)));
		        boardSize.setValue(Integer.parseInt(settings.get(3)));
		        readFile.close();
			}		
		} catch (Exception e) {
            System.out.println("Problem reading from file");
        }
		
	}
    
    
	@FXML
	protected void submit() {
		Color color1 = colorPicker1.getValue();
        Color color2 = colorPicker2.getValue();
        //checks that the colors are different
        if(color1.toString().equals(color2.toString())) {
            this.showAlert("Players Colors cannot be the same!");
            return;
        }
        String startPlayer = (String)this.startPlayer.getValue();
        String colorPicker1 = color1.toString();
        String colorPicker2 = color2.toString();
        String boardSize = this.boardSize.getValue().toString();
        //writes the settings to file "settings.txt"
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
