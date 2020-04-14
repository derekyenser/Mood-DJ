package edu.etown.mooddj.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.etown.mooddj.MoodDJ;
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
	
//	private void authenticateLogin() {
//		
//	}
	
	private void showMoodSelectionPage() {
//		try {
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(MoodDJ.class.getResource("view/MoodSelectionPage.fxml"));
//			GridPane signUpPage = (GridPane) loader.load();
//			Scene scene = new Scene(signUpPage);
//			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//			window.setScene(scene);
//			window.show();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
