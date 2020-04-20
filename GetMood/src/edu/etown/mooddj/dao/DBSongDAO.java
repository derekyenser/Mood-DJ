package edu.etown.mooddj.dao;

import java.sql.*;
import java.util.ArrayList;

import edu.etown.mooddj.model.Song;

public class DBSongDAO {
	private Connection conn;
	private String loadQuery;
	public DBSongDAO() {
		loadQuery = "select distinct track_name, "
				+ "artists.artist_name "
				+ "from Songs, attributes, artists, Genre "
				+ "where Songs.song_num = attributes.song_num "
				+ "and attributes.genre_num = Genre.genre_num "
				+ "and Genre.artist_name = artists.artist_name";
	}
	public void establishConnection(String url, String user,String pw) {
		try {
			conn = DriverManager.getConnection(url,user,pw);
			if (conn!=null) {
				System.out.println("Connected to the database");
			}

		} catch (SQLException ex) {
			System.out.println("An error occurred");
			ex.printStackTrace();
		}
	}
	public ArrayList<Song> loadSongs(String loadQuery) {
		ArrayList<Song> playlist = new ArrayList<Song>();
		try {
			Statement loadStmt = conn.createStatement();
			ResultSet result = loadStmt.executeQuery(loadQuery);
			
			while(result.next()) {
				Song song = new Song();
				song.setTrackName(result.getString("track_name"));
				song.setArtistName(result.getString("artist_name"));;
				playlist.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return playlist;
	}
	public Song loadSong(String loadQuery) {
		Song song = new Song();
		try {
			Statement loadStmt = conn.createStatement();
			ResultSet result = loadStmt.executeQuery(loadQuery);
			if(!result.next()) return null;
			song.setTrackName(result.getString("track_name"));
			song.getArtist().setArtistName(result.getString("artist_name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return song;
	}
	public void addCondition(String condition) {
		loadQuery = loadQuery.concat(condition);
	}
	public Connection getConnection() {
		return conn;
	}
	public String getLoadQuery() {
		return loadQuery;
	}
	public void setLoadQuery(String loadQuery) {
		this.loadQuery = loadQuery;
	}
	
}
