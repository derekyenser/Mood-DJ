package edu.etown.mooddj;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class MoodDJ extends Application {
	
	private Stage primaryStage;
	//private GridPane rootLayout;
	private BorderPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Mood DJ");
		
		showLandingPage();
		//showSignInPage();
		
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
	
	
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
