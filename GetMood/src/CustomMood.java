
import java.util.Scanner;

public class CustomMood {

	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		String cMood = "select track_name, artists.artist_name, songs.genre_num,song_genre, valence, danceability, energy from songs, attributes, artists, genre where songs.song_num = attributes.song_num and attributes.genre_num = genre.genre_num and genre.artist_name = artists.artist_name";
		double v,d,e;

		System.out.println("Enter Valence(between 0 and 1): ");
		v = in.nextDouble();
		cMood += val(v);


		System.out.println("Enter danceability(between 0 and 1): ");
		d = in.nextDouble();
		cMood += dance(d);

		System.out.print("Enter Energy(between 0 and 1): ");
		e = in.nextDouble();
		cMood += energy(e);
		System.out.print(cMood);

	}
	public static String val(double v) {
		double custom1 = 0;
		double custom2 = 0;
		String result="";
		while(true) {
			if(v > 0 && v <= .25) {
				custom1 += 0;
				custom2 += .25;
				result += " and valence > " + custom1 + " and valence <= " + custom2;
				break;
			}

			else if ( v > .25 && v <= .5) {
				custom1 += .25;
				custom2 += .5;
				result += " and valence > " + custom1 + " and valence <= " + custom2;
				break;
			}

			else if ( v > .5 && v <= .75) {
				custom1 += .5;
				custom2 += .75;
				result += " and valence > " + custom1 + " and valence <= " + custom2;
				break;
			}
			else if ( v > .75 && v <= 1) {
				custom1 += .75;
				custom2 += 1;
				result += " and valence > " + custom1 + " and valence <= " + custom2;
				break;
			}

			else {
				System.out.println("Error must be between 0 and 1");
				System.out.println("Enter Valence(between 0 and 1): ");
				Scanner in= new Scanner(System.in);
				v = in.nextDouble();
				continue;
			}
		}
		return result;
	}

	public static String dance(double d) {
		double custom3 = 0;
		double custom4 = 0;
		String result = "";
		while(true) {
			if(d > 0 && d <= .25) {
				custom3 += 0;
				custom4 += .25;
				result += " and danceability > " + custom3 + " and danceability <= " + custom4;
				break;
			}

			else if ( d > .25 && d <= .5) {
				custom3 += .25;
				custom4 += .5;
				result += " and danceability > " + custom3 + " and danceability <= " + custom4;
				break;
			}

			else if ( d > .5 && d <= .75) {
				custom3 += .5;
				custom4 += .75;
				result += " and danceability > " + custom3 + " and danceability <= " + custom4;
				break;
			}
			else if ( d > .75 && d <= 1) {
				custom3 += .75;
				custom4 += 1;
				result += " and danceability > " + custom3 + " and danceability <= " + custom4;
				break;
			}

			else {
				System.out.println("Error must be between 0 and 1");
				System.out.println("Enter danceability(between 0 and 1): ");
				Scanner in = new Scanner(System.in);
				d = in .nextDouble();
				continue;
			}
		}
		return result;
	}

	public static String energy(double e) {
		double custom5 = 0;
		double custom6 = 0;
		String result = "";
		while(true) {
			if(e > 0 && e <= .25) {
				custom5 += 0;
				custom6 += .25;
				result += " and energy > " + custom5 + " and energy <= " + custom6 + ";";
				break;
			}

			else if ( e > .25 && e <= .5) {
				custom5 += .25;
				custom6 += .5;
				result += " and energy > " + custom5 + " and energy <= " + custom6 + ";";
				break;
			}

			else if ( e > .5 && e <= .75) {
				custom5 += .5;
				custom6 += .75;
				result += " and energy > " + custom5 + " and energy <= " + custom6 + ";";
				break;
			}
			else if ( e > .75 && e <= 1) {
				custom5 += .75;
				custom6 += 1;
				result += " and energy > " + custom5 + " and energy <= " + custom6 + ";";
				break;
			}

			else {
				System.out.println("Error must be between 0 and 1");
				System.out.println("Enter Energy(between 0 and 1): ");
				Scanner in = new Scanner(System.in);
				e = in .nextDouble();
				continue;
			}
		}
		return result;
	}

}

