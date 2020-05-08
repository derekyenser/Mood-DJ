package edu.etown.mooddj.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

import edu.etown.mooddj.*;
import edu.etown.mooddj.dao.DBSongDAO;
import edu.etown.mooddj.model.Song;
import edu.etown.mooddj.model.UserInfo;
import javafx.beans.value.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CustomMoodController implements Initializable{
	@FXML
	private Slider valenceSlider;
	@FXML
	private Slider energySlider;
	@FXML
	private Slider danceabilitySlider;
	@FXML
	private TextField valenceTextField;
	@FXML
	private TextField energyTextField;
	@FXML
	private TextField danceabilityTextField;
	private String username;
	private ObservableList<String> genrePrefs;


	public CustomMoodController() {
		
	}
	public void setValenceSliderValue(ActionEvent event) {
		setSliderValue(valenceTextField,valenceSlider);
	}

	public void setEnergySliderValue(ActionEvent event) {
		setSliderValue(energyTextField,energySlider);
	}

	public void setDanceabilitySliderValue(ActionEvent event) {
		setSliderValue(danceabilityTextField,danceabilitySlider);
	}
	public void setSliderValue(TextField textfield, Slider slider) {
		String string = textfield.getText();
		double value = Double.valueOf(string);
		slider.setValue(value);
	}

	public void loadMoodSelectionPage(ActionEvent event) {
		MoodDJ.loadPage("view/MoodSelectionPage.fxml", event);
	}

	public void createCustomMood(ActionEvent event) {
		double valence = valenceSlider.getValue();
		double energy = energySlider.getValue();
		double danceability = danceabilitySlider.getValue();
		CustomMood customMood = new CustomMood();
		DBSongDAO database = MoodDJ.getDatabase();
		UserInfo userInfo = MoodDJ.getUserInfo();
		String query;
		customMood.setValence(valence);
		customMood.setEnergy(energy);
		customMood.setDanceability(danceability);
		
		if(userInfo.isSpotifyUser()) {
			query = database.getLoadSpotifyQuery();
			query += customMood.getConditions();
			username = userInfo.getUsername();
			String usernameCondition = String.format(" and user_name = \"%s\"",username);
			query += usernameCondition;
		} else {
			query = database.getLoadQuery();
			query += customMood.getConditions();
			String genreConditions = " and (";
			genrePrefs = userInfo.getGenrePrefs();
			Iterator<String> genreItr = genrePrefs.iterator();
			genreConditions += String.format("song_genre = \"%s\"",genreItr.next());
			while(genreItr.hasNext()) {
				genreConditions += String.format(" or song_genre = \"%s\"",genreItr.next());
				//System.out.println(genre.toString());
			}
			genreConditions += ")";
			query += genreConditions;
		}
		System.out.println(query);
		ArrayList<Song> playlist =  new ArrayList<Song>();
		playlist = database.loadSongs(query);
		loadPlaylistPageAndSendPlaylist(event,playlist);
		
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
	
	public void getUsername(String username) {
		this.username = username;
	}
	public void getGenrePrefs(ObservableList<String> genrePrefs) {
		this.genrePrefs = genrePrefs;
		for(Object o : genrePrefs) {
			System.out.println(o.toString());
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		sliderListener(valenceSlider,valenceTextField);
		sliderListener(energySlider,energyTextField);
		sliderListener(danceabilitySlider,danceabilityTextField);

	}

	public void sliderListener(Slider slider, TextField textField) {
		DecimalFormat df = new DecimalFormat("#.##");
		slider.valueProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {


			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				double valenceValue = slider.getValue();
				String valenceString = Double.toString(valenceValue);
				valenceString = df.format(valenceValue);
				textField.setText(valenceString);

			}
		});
	}

}
