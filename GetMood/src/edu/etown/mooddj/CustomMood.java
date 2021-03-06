
package edu.etown.mooddj;

public class CustomMood {
		final private String baseQuery = "select distinct track_name, "
				+ "artists.artist_name "
				+ "from Songs, attributes, artists, Genre "
				+ "where Songs.song_num = attributes.song_num "
				+ "and attributes.genre_num = Genre.genre_num "
				+ "and Genre.artist_name = artists.artist_name";;
		private String customMoodQuery;
		private String conditions = "";
		private String orderQuery = " ";
		private int i = 0;


	public CustomMood(){
		customMoodQuery = baseQuery;
	}

	public String getCustomMoodQuery() {
		return customMoodQuery;
	}
	
	public String getConditions() {
		return conditions;
	}

	public void setValence(double value) {
		conditions += valenceQuery(value);
	}

	public void setEnergy(double value) {
		conditions += energyQuery(value);
	}

	public void setDanceability(double value) {
		conditions += danceabilityQuery(value);
	}

	public String getOrderConditions() {
		return orderQuery;
	}
	public void setOrderConditions() {
		customMoodQuery += " order by " + orderQuery;
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
				if(i <= 1) {
					orderQuery += attribute + " asc, " ;
					i++;
					break;
				}
				else {
					orderQuery += attribute + " asc ";
					break;
				}
			}

			else if ( value > .25 && value <= .5) {
				custom5 += .25;
				custom6 += .5;
				result += String.format(" and %s > " + custom5 + " and %s <= ", attribute, attribute) + custom6;
				if(i <= 1) {
					orderQuery += attribute + " asc, " ;
					i++;
					break;
				}
				else {
					orderQuery += attribute + " asc ";
					break;
				}
			}

			else if ( value > .5 && value <= .75) {
				custom5 += .5;
				custom6 += .75;
				result += String.format(" and %s > " + custom5 + " and %s <= ", attribute, attribute) + custom6;
				if(i <= 1) {
					orderQuery += attribute + " desc, " ;
					i++;
					break;
				}
				else {
					orderQuery += attribute + " desc ";
					break;
				}
			}
			else if ( value > .75 && value <= 1) {
				custom5 += .75;
				custom6 += 1;
				result += String.format(" and %s > " + custom5 + " and %s <= ", attribute, attribute) + custom6;
				if(i <= 1) {
					orderQuery += attribute + " desc, " ;
					i++;
					break;
				}
				else {
					orderQuery += attribute + " desc ";
					break;
				}
			}

		}
		return result;
	}
}
