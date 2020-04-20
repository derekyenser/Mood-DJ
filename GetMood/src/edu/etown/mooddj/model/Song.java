package edu.etown.mooddj.model;

import javafx.beans.property.*;

public class Song {
	private int songNum;
	private String trackID;
	private SimpleStringProperty trackName;
	private Artist artist;
	private SimpleStringProperty artistName;
	
	
	public Song(){
		this(null,null);
	}
	public Song(String trackName,String artistName) {
		this.trackName = new SimpleStringProperty(trackName);
		artist = new Artist();
		artist.setArtistName(artistName);
		this.artistName = new SimpleStringProperty(artistName);
	}
	
	public int getSongNum() {
		return songNum;
	}
	
	public void setSongNum(int songNum) {
		this.songNum = songNum;
	}

	public String getTrackID() {
		return trackID;
	}

	public void setTrackID(String trackID) {
		this.trackID = trackID;
	}

	public String getTrackName() {
		return trackName.get();
	}

	public void setTrackName(String trackName) {
		this.trackName.set(trackName);
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public String getArtistName() {
		return artistName.get();
	}

	public void setArtistName(String artistName) {
		this.artistName.set(artistName);
	}
	
}