package edu.etown.mooddj.model;

public class Song {
	private int songNum;
	private String trackID;
	private String trackName;
	
	
	Song(){
		
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
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}
	
	
	
}
