package edu.etown.mooddj.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;


import edu.etown.mooddj.MoodDJ;
import edu.etown.mooddj.dao.DBSongDAO;
import edu.etown.mooddj.model.Song;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MoodSelectionController implements Initializable{
	@FXML
	private Button customMoodButton;
	@FXML
	private CheckBox sizeCheckBox;
	@FXML
	private TextField sizeTextField;
	@FXML
	private Button happyBtn;
	@FXML
	private Button sadBtn;
	@FXML
	private Button danceBtn;
	@FXML
	private Button highEnergyBtn;

	/**
	 * The method called to display the page for selecting a custom mood . 
	 * Currently called by the Button customMoodButton.
	 * @param event
	 * @return Nothing
	 */
	public void loadCustomMoodPage(ActionEvent event) {
		MoodDJ.loadPage("view/CustomMoodPage.fxml", event);
	}

	public void getHappyPlaylist(ActionEvent event) {
		String happyConditions = "and energy > '.9' "
				+ "and valence > '.9' "
				+ "and danceability > '.9'";
		DBSongDAO database = MoodDJ.getDatabase();
		String loadQuery = database.getLoadQuery();

		database.addCondition(happyConditions);
		loadQuery = database.getLoadQuery();
		System.out.println(loadQuery);
		ArrayList<Song> playlist =  new ArrayList<Song>();
		playlist = database.loadSongs(loadQuery);

	}
	public void getSadPlaylist(ActionEvent event) {
		String energyConditions = "and energy < '.1'"
				+ "and valence < '.1'"
				+ "and danceability < '.1'";
		DBSongDAO database = MoodDJ.getDatabase();
		String loadQuery = database.getLoadQuery();

		database.addCondition(energyConditions);
		loadQuery = database.getLoadQuery();
		System.out.println(loadQuery);
		ArrayList<Song> playlist =  new ArrayList<Song>();
		playlist = database.loadSongs(loadQuery);
	}
	
	public void getDancePlaylist(ActionEvent event) {
		String energyConditions = "and danceability > '.9'"
								 +"and valence > '.9'";
		DBSongDAO database = MoodDJ.getDatabase();
		String loadQuery = database.getLoadQuery();

		database.addCondition(energyConditions);
		loadQuery = database.getLoadQuery();
		System.out.println(loadQuery);
		ArrayList<Song> playlist =  new ArrayList<Song>();
		playlist = database.loadSongs(loadQuery);
	}
	
	public void getHighEnergyPlaylist(ActionEvent event) {
		String energyConditions = "and energy = '.9'";
		DBSongDAO database = MoodDJ.getDatabase();
		String loadQuery = database.getLoadQuery();

		database.addCondition(energyConditions);
		loadQuery = database.getLoadQuery();
		System.out.println(loadQuery);
		ArrayList<Song> playlist =  new ArrayList<Song>();
		playlist = database.loadSongs(loadQuery);
	}

	/**
	 * Checks whether sizeCheckBox is selected. If sizeCheckBox is selected, 
	 * sizeTextField will be editable.
	 * @param event
	 * @return Nothing
	 */
	public void sizeTextFieldActive(ActionEvent event) {
		if(sizeCheckBox.isSelected()) {
			sizeTextField.setEditable(true);
		}
		else {
			sizeTextField.setEditable(false);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}


}