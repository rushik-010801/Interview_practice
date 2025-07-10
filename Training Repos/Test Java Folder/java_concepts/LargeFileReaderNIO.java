import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class LargeFileReaderNIO {
	public static void main(String[] args) {
		Path filePath = Path.of("largefile.txt"); // Replace with your file path

		try (FileChannel fileChannel = FileChannel.open(filePath, StandardOpenOption.READ)) {
			int bufferSize = 1024 * 1024; // 1MB buffer size
			ByteBuffer buffer = ByteBuffer.allocate(bufferSize);

			// Using for-loop to read the file in chunks
			for (int bytesRead; (bytesRead = fileChannel.read(buffer)) > 0; buffer.clear()) {
				buffer.flip(); // Prepare buffer for reading

				// Convert buffer to String
				String chunk = StandardCharsets.UTF_8.decode(buffer).toString();
				System.out.println(chunk); // Process the chunk (printing in this case)
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
