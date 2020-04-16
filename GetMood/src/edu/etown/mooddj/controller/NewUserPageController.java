package edu.etown.mooddj.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.etown.mooddj.MoodDJ;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NewUserPageController implements Initializable{
	@FXML
	private Button loginSpotifyBtn;
	@FXML
	private Button newAccountBtn;
	/**
	 * The method called to display the page to sign up new users. 
	 * Currently called by the Button newAccountButton.
	 * @param event
	 * @return Nothing
	 */
	public void loadSignUpPage(ActionEvent event) {
		MoodDJ.loadPage("view/SignUpPage.fxml", event);
		
	}
	public void loadLandingPage(ActionEvent event) {
		MoodDJ.loadPage("view/LandingPage.fxml",event);
	}
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}