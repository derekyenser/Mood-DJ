package edu.etown.mooddj.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import edu.etown.mooddj.MoodDJ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MoodSelectionController implements Initializable{
	@FXML
	private Button customMoodButton;
	@FXML
	private CheckBox sizeCheckBox;
	@FXML
	private TextField sizeTextField;
	
	
	public void loadCustomMoodPage(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MoodDJ.class.getResource("view/CustomMoodPage.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sizeTextFieldActive(ActionEvent event) {
		if(sizeCheckBox.isSelected()) {
			sizeTextField.setEditable(true);
		}
		else {
			sizeTextField.setEditable(false);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	

}