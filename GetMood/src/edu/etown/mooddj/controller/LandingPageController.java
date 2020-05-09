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
	
	public void showChooseSignInPage(ActionEvent event) {
		MoodDJ.loadPage("view/ChooseSignInPage.fxml", event);

	}
	
	public void showSignUpPage(ActionEvent event) {
		MoodDJ.loadPage("view/SignUpPage.fxml", event);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}