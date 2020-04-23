package edu.etown.mooddj.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.etown.mooddj.MoodDJ;
import edu.etown.mooddj.RunPy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SignInPageController implements Initializable{
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private ProgressIndicator progressIndicator;
	@FXML
	private Label loadingText;
	
//	private void authenticateLogin() {
//		
//	}
	
	public void loginAndloadMoodSelection(ActionEvent event) {
//		progressIndicator.setVisible(true);
//		loadingText.setVisible(true);
		RunPy script = new RunPy("PythonScript\\dist\\pullSavedSongs.exe","pyThread");
		script.getUsername(usernameField.getText());
	
		script.start();
		loadMoodSelectionPage(event);
	}
	public void loadLandingPage(ActionEvent event) {
		MoodDJ.loadPage("view/LandingPage.fxml",event);
	}
	public void loadMoodSelectionPage(ActionEvent event) {
		MoodDJ.loadPage("view/MoodSelectionPage.fxml", event);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}