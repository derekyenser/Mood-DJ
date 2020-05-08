package edu.etown.mooddj.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.etown.mooddj.MoodDJ;
import edu.etown.mooddj.RunPy;
import edu.etown.mooddj.dao.DBSongDAO;
import edu.etown.mooddj.model.UserInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SignInPageController implements Initializable{
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	
//	private void authenticateLogin() {
//		
//	}
	
	public void loginAndLoadMoodSelection(ActionEvent event) {
		RunPy script = new RunPy("PythonScript\\dist\\pullSavedSongs.exe");
		String username = script.getUsername(usernameField.getText());
		script.run();
		loadMoodSelectionPage(event, username);
	}
	public void loadLandingPage(ActionEvent event) {
		MoodDJ.loadPage("view/LandingPage.fxml",event);
	}
	public void loadMoodSelectionPage(ActionEvent event, String username) {
//		MoodDJ.loadPage("view/MoodSelectionPage.fxml", event);
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MoodDJ.class.getResource("view/MoodSelectionPage.fxml"));
			Parent root = loader.load();

			MoodSelectionController moodSelectionCtrl = loader.getController();
			//moodSelectionCtrl.getUsername(username);
			UserInfo userInfo = MoodDJ.getUserInfo();
			userInfo.setIsSpotifyUser(true);
			userInfo.setUsername(username);

			Scene scene = new Scene(root);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}