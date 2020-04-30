package edu.etown.mooddj.controller;

import java.net.URL;
import java.util.ResourceBundle;

import edu.etown.mooddj.MoodDJ;
import edu.etown.mooddj.dao.DBSongDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class SignUpPageController implements Initializable{
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private PasswordField confirmPasswordField;
	@FXML
	private Label errorLabel;
	
	public void loadNewUserPage(ActionEvent event) {
		MoodDJ.loadPage("view/NewUserPage.fxml", event);

	}
	
	public void signUpUser(ActionEvent event) {
		String username = usernameField.getText();
		String password = passwordField.getText();
		String confirmPassword = confirmPasswordField.getText();
		if(passwordMatch(password,confirmPassword)) {
			DBSongDAO database = MoodDJ.getDatabase();
			database.saveUser(username,password);
			MoodDJ.loadPage("view/GenrePreferencesPage.fxml", event);
		} else {
			errorLabel.setVisible(true);
			System.out.println("Signup Unsuccessful");
		}
		
	}
	
	public boolean passwordMatch(String pass1, String pass2) {
		if(pass1.equals(pass2)) {
			return true;
		}
		else return false;
	}
	//public void Username(pass enteredvalues) {
		//signup += "'" + enteredvalue + "',";
	//}
	
	//public void Password(pass enteredvalues ) {
		//signup += "'" + enteredvalue + "');";
		//DBSongDAO database = MoodDJ.getDatabase();
		//String Addvalue = database.getLoadQuery();
		
		//Addvalue = database.addCondition(signup);
		//System.out.print(Addvalue);
		
		//}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}	
}
