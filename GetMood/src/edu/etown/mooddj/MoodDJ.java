package edu.etown.mooddj;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class MoodDJ extends Application {

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		
		//loadInitialScreen("view/MoodSelectionPage.fxml",primaryStage);
		//loadInitialScreen("view/LandingPage.fxml",primaryStage);
		loadInitialScreen("view/CustomMoodPage.fxml",primaryStage);

	}

	public void showLandingPage() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MoodDJ.class.getResource("view/LandingPage.fxml"));
			GridPane landingPage = (GridPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(landingPage);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * A method for loading and displaying the given fxml file.
	 * @param file This is the fxml file that will be loaded
	 * @param event
	 * @return Nothing
	 */
	public static void loadPage(String file, ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MoodDJ.class.getResource(file));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadInitialScreen(String file, Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MoodDJ.class.getResource(file));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Mood DJ");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}