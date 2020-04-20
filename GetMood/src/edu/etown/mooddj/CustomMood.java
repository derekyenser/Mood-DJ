
package edu.etown.mooddj;

import java.util.Scanner;

public class CustomMood {
		final private String baseQuery = "select track_name, "
				+ "artists.artist_name, "
				+ "Songs.genre_num,song_genre, "
				+ "valence, danceability, energy "
				+ "from Songs, attributes, artists, Genre "
				+ "where Songs.song_num = attributes.song_num "
				+ "and attributes.genre_num = Genre.genre_num "
				+ "and Genre.artist_name = artists.artist_name";
		private String customMoodQuery;
//		private String valence;
//		private String energy;
//		private String danceability;
		
	
	public CustomMood(){
		customMoodQuery = baseQuery;
	}
	
	public String getCustomMoodQuery() {
		return customMoodQuery;
	}
	
	public void setValence(double value) {
		customMoodQuery += valenceQuery(value);
	}
	
	public void setEnergy(double value) {
		customMoodQuery += energyQuery(value);
	}
	
	public void setDanceability(double value) {
		customMoodQuery += danceabilityQuery(value);
	}
	
	public void endQuery() {
		customMoodQuery += ";";
	}
	
	private String valenceQuery(double value) {
		return attributeQuery(value,"valence");
	}

	private String danceabilityQuery(double d) {
		return attributeQuery(d,"danceability");

	}

	private String energyQuery(double value) {
		return attributeQuery(value,"energy");
	}
	private String attributeQuery(double value, String attribute) {
		double custom5 = 0;
		double custom6 = 0;
		String result = "";
		while(true) {
			if(value > 0 && value <= .25) {
				custom5 += 0;
				custom6 += .25;
				result += String.format(" and %s > " + custom5 + " and %s <= ", attribute, attribute) + custom6;
				break;
			}

			else if ( value > .25 && value <= .5) {
				custom5 += .25;
				custom6 += .5;
				result += String.format(" and %s > " + custom5 + " and %s <= ", attribute, attribute) + custom6;
				break;
			}

			else if ( value > .5 && value <= .75) {
				custom5 += .5;
				custom6 += .75;
				result += String.format(" and %s > " + custom5 + " and %s <= ", attribute, attribute) + custom6;
				break;
			}
			else if ( value > .75 && value <= 1) {
				custom5 += .75;
				custom6 += 1;
				result += String.format(" and %s > " + custom5 + " and %s <= ", attribute, attribute) + custom6;
				break;
			}

			else {
				System.out.println("Error must be between 0 and 1");
				System.out.println("Enter Energy(between 0 and 1): ");
				Scanner in = new Scanner(System.in);
				value = in .nextDouble();
				continue;
			}
		}
		return result;
	}
}
