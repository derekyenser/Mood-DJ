package edu.etown.mooddj.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


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
	
	/**
	 * The method called to display the page for selecting a custom mood . 
	 * Currently called by the Button customMoodButton.
	 * @param event
	 * @return Nothing
	 */
	public void loadCustomMoodPage(ActionEvent event) {
		MoodDJ.loadPage("view/CustomMoodPage.fxml", event);
	}
	
	/**
	 * Checks whether sizeCheckBox is selected. If sizeCheckBox is selected, 
	 * sizeTextField will be editable.
	 * @param event
	 * @return Nothing
	 */
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