package numberPlay.util;

import java.io.IOException;

// 	Purpose - to be implemented by the Persister
public interface PersisterI {
	// 	Purpose - Instantiate FileWriter object
	public void open() throws IOException;
	// 	Purpose - write data to output file
	void writeToFile(String data) throws IOException;
	// 	Purpose - Close 'myFileWriter'
	void close() throws IOException;
}
