package java_concepts;

/*
    This question was asked to one of the colleagues in Warner Bros. Interviews

    Question : You are getting streams from two different place, you have to read them concurrently
    and write them to another file using Executable Service and Futures of Java.


 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MultiThreadingQ2 {
	private static final String streamFilePath1 = "/Users/rushik.avula/Documents/Training Repos/Test Java Folder/resources/StreamFile1.txt";
	private static final String streamFilePath2 = "/Users/rushik.avula/Documents/Training Repos/Test Java Folder/resources/StreamFile2.txt";
	private static final String streamFileOutPath = "/Users/rushik.avula/Documents/Training Repos/Test Java Folder/resources/StreamFileOut.txt";

	public static void main(String args[]) {
		try (BufferedReader reader = new BufferedReader(new FileReader(streamFilePath1))) {
			String line;
			while (true) {
				line = reader.readLine();
				if (line != null)
					System.out.println(line);
			}
		} catch (IOException e) {
			System.out.println("Unexpected Error Occured : " + e.getMessage());
		}
	}
}
