package edu.etown.mooddj.dao;

import java.sql.*;
import java.util.ArrayList;

import edu.etown.mooddj.model.Song;

public class DBSongDAO {
	private Connection conn;
	private String loadSpotifyQuery;
	public DBSongDAO() {
		loadSpotifyQuery = "select distinct track_name, "
				+ "artists.artist_name "
				+ "from Songs, attributes, artists, Genre, playlist, UserS "
				+ "where UserS.Users_id = playlist.user_id "  
				+ "and playlist.song_num = Songs.song_num "
				+ "and Songs.song_num = attributes.song_num "
				+ "and attributes.genre_num = Genre.genre_num "
				+ "and Genre.artist_name = artists.artist_name";
	}
	
	public void establishConnection(String url, String user,String pw) {
		try {
			System.out.println("Attempting to connect...");
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
	
	public void saveUser(String username, String password) {
		String query = "INSERT INTO UserS (user_name,User_password) VALUES (?, ?)";
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(query);
			statement.setString(1,username);
			statement.setString(2,password);
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
			    System.out.println("A new user was inserted successfully!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String addConditionSpotify(String condition) {
		String query = loadSpotifyQuery.concat(condition);
		return query;
	}
	public Connection getConnection() {
		return conn;
	}
	public String getLoadSpotifyQuery() {
		return loadSpotifyQuery;
	}
	public void setLoadSpotifyQuery(String loadQuery) {
		this.loadSpotifyQuery = loadQuery;
	}
	
}
