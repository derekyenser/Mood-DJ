package edu.etown.mooddj.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.ResourceBundle;


import edu.etown.mooddj.MoodDJ;
import edu.etown.mooddj.dao.DBSongDAO;
import edu.etown.mooddj.model.Song;
import edu.etown.mooddj.model.UserInfo;
import javafx.collections.ObservableList;
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
	private String username;
	private ObservableList<String> genrePrefs;
	private boolean isSpotifyUser = false;

	public MoodSelectionController() {

	}


	public void getHappyPlaylist(ActionEvent event) {
		String happyConditions = " and energy > '.75' "
				+ "and valence > '.75' and mode_A = 'major' order by energy desc, valence desc";
		getMoodPlaylist(event,happyConditions);
	}

	public void getSadPlaylist(ActionEvent event) {
		String sadConditions = " and energy < '.25'"
				+ "and valence < '.25'"
				+ "and danceability < '.25' and mode_A = 'minor' order by energy asc, valence asc, danceability asc ";
		getMoodPlaylist(event,sadConditions);
	}

	public void getDancePlaylist(ActionEvent event) {
		String danceConditions = " and danceability > '.75'"
				+ "and valence > '.75' order by danceability desc, valence desc";
		getMoodPlaylist(event,danceConditions);
	}

	public void getHighEnergyPlaylist(ActionEvent event) {
		String energyConditions = " and energy > '.75' order by energy desc";
		getMoodPlaylist(event,energyConditions);
	}

	private void getMoodPlaylist(ActionEvent event, String conditions) {
		DBSongDAO database = MoodDJ.getDatabase();
		UserInfo userInfo = MoodDJ.getUserInfo();
		String loadQuery;
		if(userInfo.isSpotifyUser()) {
			loadQuery = database.getLoadSpotifyQuery();
			username = userInfo.getUsername();
			String usernameCondition = String.format(" and user_name = \"%s\"",username);

			loadQuery += usernameCondition;
			loadQuery += conditions;
//			loadQuery = database.addConditionSpotify(usernameCondition);
//			loadQuery = database.addConditionSpotify(conditions);
		} else {
			loadQuery = database.getLoadQuery();
			String genreConditions = " and (";
			genrePrefs = userInfo.getGenrePrefs();
			Iterator<String> genreItr = genrePrefs.iterator();
			genreConditions += String.format("song_genre = \"%s\"",genreItr.next());
			while(genreItr.hasNext()) {
				genreConditions += String.format(" or song_genre = \"%s\"",genreItr.next());
				//System.out.println(genre.toString());
			}
			genreConditions += ")";
			loadQuery = database.getLoadQuery();
			loadQuery += genreConditions;
			loadQuery += conditions;
//			loadQuery = database.addCondition(genreConditions);
//			loadQuery = database.addCondition(conditions);
		}
		System.out.println(loadQuery);
		ArrayList<Song> playlist =  new ArrayList<Song>();
		playlist = database.loadSongs(loadQuery);
		loadPlaylistPageAndSendPlaylist(event,playlist);
	}

	public void getUsername(String username) {
		this.username = username;
	}

	public void getGenrePrefs(ObservableList<String> genrePrefs) {
		this.genrePrefs = genrePrefs;
		for(Object o : genrePrefs) {
			System.out.println(o.toString());
		}
	}
	public void setIsSpotifyUser(boolean isSpotifyUser) {
		this.isSpotifyUser = isSpotifyUser;
	}

	/**
	 * The method called to display the page for selecting a custom mood .
	 * Currently called by the Button customMoodButton.
	 * @param event
	 * @return Nothing
	 */
	public void loadCustomMoodPage(ActionEvent event) {
		//MoodDJ.loadPage("view/CustomMoodPage.fxml", event);
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(MoodDJ.class.getResource("view/CustomMoodPage.fxml"));
					Parent root = loader.load();
					CustomMoodController customMoodCtrl = loader.getController();
					customMoodCtrl.getUsername(username);
					//customMoodCtrl.getGenrePrefs(genrePrefs);
		
					Scene scene = new Scene(root);
					Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
					window.setScene(scene);
					window.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	public void loadPlaylistPageAndSendPlaylist(ActionEvent event, ArrayList<Song> list) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MoodDJ.class.getResource("view/PlaylistPage.fxml"));
			Parent root = loader.load();

			PlaylistPageController playlistPgCtrl = loader.getController();
			playlistPgCtrl.transferPlaylist(list);

			Scene scene = new Scene(root);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setResizable(true);
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
