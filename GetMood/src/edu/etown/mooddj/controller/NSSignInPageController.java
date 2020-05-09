package edu.etown.mooddj.controller;

import java.sql.*;

import edu.etown.mooddj.MoodDJ;
import edu.etown.mooddj.dao.DBSongDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class NSSignInPageController {
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Label errorLabel;
	
	public void loginAndLoadMoodSelection(ActionEvent event) {
		String username = usernameField.getText();
		String password = passwordField.getText();
		if(authenticateUser(username,password)) {
			System.out.println("Logged in sucessfully");
			MoodDJ.loadPage("view/GenrePreferencesPage.fxml",event);
		} else {
			errorLabel.setVisible(true);
		}
	}
	public boolean authenticateUser(String username, String password){
		DBSongDAO database = MoodDJ.getDatabase();
		if(database.selectUser(username,password)) {
			return true;
		} else return false;
	}
	public void loadChooseSignInPage(ActionEvent event) {
		MoodDJ.loadPage("view/ChooseSignInPage.fxml",event);
	}
}
