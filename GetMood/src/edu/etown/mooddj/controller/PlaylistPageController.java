package edu.etown.mooddj.controller;

import java.net.URL;
import java.util.ResourceBundle;

import edu.etown.mooddj.model.Song;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PlaylistPageController implements Initializable{
	@FXML
	private TableView<Song> tableView;
	@FXML TableColumn<Song,String> songTitleColumn;
	@FXML TableColumn<Song,String> ArtistColumn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
