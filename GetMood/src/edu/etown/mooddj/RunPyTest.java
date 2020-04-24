package edu.etown.mooddj;

import java.io.*;

class runPyTest{
	public static void main(String a[]){
		RunPy script = new RunPy("PythonScript\\dist\\pullSavedSongs.exe");
		script.getUsername("The email adress of the user's spotify account");
		script.getDBCreds(); // The addres of the databse is in the code, so this line is unecessary
		script.run();
	}
}
