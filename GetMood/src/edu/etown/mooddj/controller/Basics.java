package edu.etown.mooddj.controller;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

@SuppressWarnings("restriction")

public class Basics extends Application {
	@Override /* Method in Application class */
	public void start(Stage primaryStage) {
		int sceneWidth = 100, sceneHeight = 150;
		
		Button button = new Button("Fear The Turtle");
		Scene scene = new Scene(button, sceneWidth, sceneHeight);
		primaryStage.setTitle("FX Button");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
