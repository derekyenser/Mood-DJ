package edu.etown.mooddj.controller;

import java.net.URL;
import java.util.ResourceBundle;

import edu.etown.mooddj.MoodDJ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class ChooseSignInPageController implements Initializable{
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
	public void loadSignInPage(ActionEvent event) {
		MoodDJ.loadPage("view/SignInPage.fxml", event);
		
	}
	public void loadNSSignInPage(ActionEvent event) {
		MoodDJ.loadPage("view/NSSignInPage.fxml", event);
	}
	public void loadLandingPage(ActionEvent event) {
		MoodDJ.loadPage("view/LandingPage.fxml",event);
	}
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}