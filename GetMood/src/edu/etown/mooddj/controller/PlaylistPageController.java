package edu.etown.mooddj.controller;

import java.net.URL;
import java.util.*;

import edu.etown.mooddj.MoodDJ;
import edu.etown.mooddj.model.Song;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PlaylistPageController implements Initializable{
	@FXML
	private TableView<Song> tableView;
	@FXML TableColumn<Song,String> songTitleColumn;
	@FXML TableColumn<Song,String> artistColumn;
	
	public PlaylistPageController() {
		
	}
	
	public void loadMoodSelectionPage(ActionEvent event) {
		MoodDJ.loadPage("view/MoodSelectionPage.fxml", event);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Sets up the columns in the table
		songTitleColumn.setCellValueFactory(new PropertyValueFactory<Song,String>("trackName"));
		artistColumn.setCellValueFactory(new PropertyValueFactory<Song,String>("artistName"));
	}	
	/**
	 * Sets the items in the tableView
	 * @param list
	 */
	public void transferPlaylist(ArrayList<Song> list) {
		tableView.setItems(FXCollections.observableArrayList(list));
	}

	
	

}
