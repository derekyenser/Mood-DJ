package edu.etown.mooddj;

import java.io.IOException;
import java.sql.*;

import edu.etown.mooddj.dao.DBSongDAO;
import edu.etown.mooddj.model.UserInfo;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.*;


public class MoodDJ extends Application {

	private Stage primaryStage;
	public static Connection conn = null;
	private static DBSongDAO database;
	private static UserInfo userInfo;
	@Override
	public void start(Stage primaryStage) {
		
		loadInitialScreen("view/LandingPage.fxml",primaryStage);

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
	
	public static DBSongDAO getDatabase() {
		return database;
	}
	
	public static UserInfo getUserInfo() {
		return userInfo;
	}
	
	public static void main(String[] args) {
		String user = "sql9334219";
		String password = "FrqdgTsjLk";
		String databaseURL = "jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9334219";
		
		database = new DBSongDAO();
		database.establishConnection(databaseURL,user,password);
		
		userInfo = new UserInfo();
		
		Application.launch(args);
	}
}