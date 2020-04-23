package edu.etown.mooddj.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.etown.mooddj.MoodDJ;
import edu.etown.mooddj.RunPy;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LandingPageController implements Initializable{
	@FXML
	private Button LoginBtn;
	@FXML
	private Button SignUpBtn;
	
	public void showSignInPage(ActionEvent event) {
		MoodDJ.loadPage("view/SignInPage.fxml", event);
//		RunPy script = new RunPy("PythonScript\\dist\\pullSavedSongs.exe");
//		script.run();
//		
//		loadMoodSelectionPage(event);
		
	}
	public void loadMoodSelectionPage(ActionEvent event) {
		MoodDJ.loadPage("view/MoodSelectionPage.fxml", event);
	}
	public void showNewUserPage(ActionEvent event) {
		MoodDJ.loadPage("view/NewUserPage.fxml", event);
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}