package edu.etown.mooddj.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import edu.etown.mooddj.MoodDJ;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
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
	public void loadPlaylistPage(ActionEvent event) {
		MoodDJ.loadPage("view/PlaylistPage.fxml",event);
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