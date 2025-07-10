package DSA_pactice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
* <b>Thumb Rule</b>
*   Changing code should be encapsulated.
*
* Here business logic under the try block is decoupled and encapsulated to another interface of FileReaderProcessor.
* This is a example of Behavior Parameterization and encapsulation.
*/
public class Encapsulation {
	public static void main(String[] args) {
	}

	// Problem statement
	static String readFirstLineFromFile(String path) throws IOException {
		try (FileReader fr = new FileReader(path); BufferedReader br = new BufferedReader(fr)) {
			// Custom logic based on requirement
			return br.readLine();
		}
	}

	// Can be changed to this
	static String readFirstLineFromFile(String path, FileReaderProcessor pr) throws IOException {
		try (FileReader fr = new FileReader(path); BufferedReader br = new BufferedReader(fr)) {
			// Logic can be written in process method based on specific implementation
			return pr.process();
		}
	}

	// Encapsulated readLine method
	interface FileReaderProcessor {
		String process();
	}
}
