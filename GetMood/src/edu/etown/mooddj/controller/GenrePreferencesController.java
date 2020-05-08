package edu.etown.mooddj.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.etown.mooddj.MoodDJ;
import edu.etown.mooddj.model.UserInfo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GenrePreferencesController implements Initializable{
	@FXML
	private ComboBox<String> genreBox;
	@FXML ListView<String> genreListView;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		populateCombobox(genreBox);	
	}
	
	public void addGenre(ActionEvent event) {
		String value = genreBox.getValue();
		genreListView.getItems().add(value);
	}
	public void loadMoodSelectionPage(ActionEvent event){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MoodDJ.class.getResource("view/MoodSelectionPage.fxml"));
			Parent root = loader.load();

			MoodSelectionController moodSelectionCtrl = loader.getController();
			ObservableList<String> genrePrefs= genreListView.getItems();
			
			UserInfo userInfo = MoodDJ.getUserInfo();
			userInfo.setGenrePrefs(genrePrefs);
			
			userInfo.setIsSpotifyUser(false);

			Scene scene = new Scene(root);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//MoodDJ.loadPage("view/MoodSelectionPage.fxml", event);
	}
	public void populateCombobox(ComboBox<String> combobox) {
		combobox.getItems().add("Movie");
		combobox.getItems().add("R&B");
		combobox.getItems().add("A Capella");
		combobox.getItems().add("Alternative");
		combobox.getItems().add("Country");
		combobox.getItems().add("Dance");
		combobox.getItems().add("Electronic");
		combobox.getItems().add("Anime");              
		combobox.getItems().add("Folk");
		combobox.getItems().add("Blues");
		combobox.getItems().add("Opera");
		combobox.getItems().add("Hip-Hop");
		combobox.getItems().add("Children s Music");
		combobox.getItems().add("Rap");
		combobox.getItems().add("Indie");
		combobox.getItems().add("Classical");
		combobox.getItems().add("Pop");           
		combobox.getItems().add("Reggae");            
		combobox.getItems().add("Reggaeton");          
		combobox.getItems().add("Jazz");              
		combobox.getItems().add("Rock");              
		combobox.getItems().add("Ska");                
		combobox.getItems().add("Comedy");
		combobox.getItems().add("Soul");
		combobox.getItems().add("Soundtrack");
		combobox.getItems().add("World");
	}
}
