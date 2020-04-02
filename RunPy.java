import java.io.*;
import java.util.*;

public class RunPy {

	public static BufferedReader inp;

	public static void print(String s) { // Prints the input from the python script
		System.out.println(s);
	}


	public static void main(String[] args) {

		String s;
		String cmd = "PythonScript\\dist\\pullSavedSongs\\pullSavedSongs.exe"; //Command to run the python script

		try {

			Process p = Runtime.getRuntime().exec(cmd); //Process to run the script

			inp = new BufferedReader( new InputStreamReader(p.getInputStream()) ); // Input buffer to get information from the python script
			s = inp.readLine();
			while(s.equals("quit") == false) { // Unless python sends quit keep getting input
				print(s);
				s = inp.readLine();
			}

			inp.close();
		}

		catch (Exception err) {
			err.printStackTrace();
		}
	}
}