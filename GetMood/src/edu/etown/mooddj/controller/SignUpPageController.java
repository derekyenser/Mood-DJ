package edu.etown.mooddj.controller;

import java.net.URL;
import java.util.ResourceBundle;

import edu.etown.mooddj.MoodDJ;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class SignUpPageController implements Initializable{
	
	public void loadNewUserPage(ActionEvent event) {
		MoodDJ.loadPage("view/NewUserPage.fxml", event);
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
