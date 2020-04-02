import java.io.*;

class runPyTest{
	public static void main(String a[]){
		RunPy script = new RunPy("PythonScript\\dist\\pullSavedSongs.exe");
		script.run();
	}
}
