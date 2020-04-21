package edu.etown.mooddj;
import java.io.*;
import java.util.*;

public class RunPy {

	public static BufferedReader inp;
	public static BufferedWriter out;
	public static String cmd;
	public static Process p;

	public RunPy(String path) {
		cmd = path;
		try {
			p = Runtime.getRuntime().exec(cmd); //Process to run the script
			out = new BufferedWriter( new OutputStreamWriter(p.getOutputStream()) );
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	private static void pipe(String msg) {
		try {
			out.write( msg + "\n" );
			out.flush();
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}
// Use if you want to use a different database than the default (Right now it is set to use my local pc. Use if you want to test on your computer
	public void getDBCreds() {
		Scanner in = new Scanner(System.in);
		System.out.println("Please Enter the credenntials for the database you want to use");
		
		System.out.print("User (Usually root): ");
		pipe(in.nextLine());
		
		System.out.print("Password used to login to the DB: ");
		pipe(in.nextLine());
		
		System.out.print("Host IP address (If database is on local pc then use 127.0.0.1): ");
		pipe(in.nextLine());
		
		System.out.print("Database name: ");
		pipe(in.nextLine());
		
		pipe("Done");
	}
	
	public void getDBCreds(String user,String password,String ipaddress, String dbName) {
		//Scanner in = new Scanner(System.in);
		//System.out.println("Please Enter the credenntials for the database you want to use");
		
		//System.out.print("User (Usually root): ");
		pipe(user);
		
		//System.out.print("Password used to login to the DB: ");
		pipe(password);
		
		//System.out.print("Host IP address (If database is on local pc then use 127.0.0.1): ");
		pipe(ipaddress);
		
		//System.out.print("Database name: ");
		pipe(dbName);
		
		pipe("Done");
	}
	
	public void getUsername(String username) {
		pipe(username);
	}
	
	public void run() {
		pipe("Never");
		String s;
		try {

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
