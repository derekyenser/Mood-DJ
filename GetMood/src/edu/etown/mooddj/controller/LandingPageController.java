package edu.etown.mooddj.controller;

import java.net.URL;
import java.util.ResourceBundle;

import edu.etown.mooddj.MoodDJ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class LandingPageController implements Initializable{
	@FXML
	private Button LoginBtn;
	@FXML
	private Button SignUpBtn;
	
	public void showSignInPage(ActionEvent event) {
		//MoodDJ.loadPage("view/SignInPage.fxml", event);
		MoodDJ.loadPage("view/NewUserPage.fxml", event);
	}
	
	public void showNewUserPage(ActionEvent event) {
		//MoodDJ.loadPage("view/NewUserPage.fxml", event);
		MoodDJ.loadPage("view/SignUpPage.fxml", event);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}