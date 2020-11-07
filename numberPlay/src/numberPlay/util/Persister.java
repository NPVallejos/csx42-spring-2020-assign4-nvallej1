package numberPlay.util;

import java.io.FileWriter;

import java.io.IOException;

//  Purpose - Write results to output file
public class Persister implements PersisterI {
	private String outputFilePath;
	private FileWriter myFileWriter;

	public Persister(String outputFilePathIn) {
		outputFilePath = outputFilePathIn;
	}

	// 	Purpose - Instantiate FileWriter object
	@Override
	public void open() throws IOException {
		myFileWriter = new FileWriter(outputFilePath);
	}

	// 	Purpose - write data to output file
	@Override
	public void writeToFile(String data)  throws IOException {
		myFileWriter.write(data + "\n");
	}

	// 	Purpose - Close 'myFileWriter'
	@Override
	public void close() throws IOException{
		myFileWriter.close();
	}
}
