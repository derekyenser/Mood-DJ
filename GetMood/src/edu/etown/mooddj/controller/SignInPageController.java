package edu.etown.mooddj.controller;

import java.net.URL;
import java.util.ResourceBundle;

import edu.etown.mooddj.MoodDJ;
import edu.etown.mooddj.RunPy;
import edu.etown.mooddj.model.UserInfo;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;

public class SignInPageController implements Initializable{
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	
	
	public void loginAndLoadMoodSelection(ActionEvent event) {
		RunPy script = new RunPy("PythonScript\\dist\\pullSavedSongs.exe");
		String username = script.getUsername(usernameField.getText());
		script.run();
		loadMoodSelectionPage(event, username);
	}
	public void loadChooseSignInPage(ActionEvent event) {
		MoodDJ.loadPage("view/ChooseSignInPage.fxml",event);
	}
	public void loadMoodSelectionPage(ActionEvent event, String username) {
		UserInfo userInfo = MoodDJ.getUserInfo();
		userInfo.setIsSpotifyUser(true);
		userInfo.setUsername(username);
		MoodDJ.loadPage("view/MoodSelectionPage.fxml", event);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}