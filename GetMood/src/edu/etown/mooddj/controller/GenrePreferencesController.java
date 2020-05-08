package edu.etown.mooddj.controller;

import java.net.URL;
import java.util.ResourceBundle;

import edu.etown.mooddj.MoodDJ;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
		ObservableList<String> genrePrefs= genreListView.getItems();
		for(Object o : genrePrefs) {
			System.out.println(o.toString());
		}
		MoodDJ.loadPage("view/MoodSelectionPage.fxml", event);
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
