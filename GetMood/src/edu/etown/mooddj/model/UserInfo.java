package edu.etown.mooddj.model;

import javafx.collections.ObservableList;

public class UserInfo {
	private boolean isSpotifyUser;
	private String username;
	private ObservableList<String> genrePrefs;
	
	public void setIsSpotifyUser(boolean isSpotifyUser) {
		this.isSpotifyUser = isSpotifyUser;
	}
	public boolean isSpotifyUser() {
		return isSpotifyUser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public ObservableList<String> getGenrePrefs() {
		return genrePrefs;
	}
	public void setGenrePrefs(ObservableList<String> genrePrefs) {
		this.genrePrefs = genrePrefs;
	}
}
