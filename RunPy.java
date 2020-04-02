import java.io.*;
import java.util.*;

public class RunPy {

	public static BufferedReader inp;
	public static String cmd;

	public RunPy(String path) {
		cmd = path;
	}

	public void run() {
		String s;
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

	private static void print(String s) { // Prints the input from the python script
		System.out.println(s);
	}
}