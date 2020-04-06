package edu.etown.mooddj.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.etown.mooddj.MoodDJ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LandingPageController implements Initializable{
	@FXML
	private Button LoginBtn;
	@FXML
	private Button SignUpBtn;
	
	public void showSignInPage(ActionEvent event) {
		try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MoodDJ.class.getResource("view/SignInPage.fxml"));
            GridPane signInPage = (GridPane) loader.load();
			
            // Show the scene containing the root layout.
            Scene scene = new Scene(signInPage);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void showSignUpPage(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MoodDJ.class.getResource("view/SignUpPage.fxml"));
			GridPane signUpPage = (GridPane) loader.load();
			Scene scene = new Scene(signUpPage);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
