package numberPlay.driver;

//  Command Arg Validator Necessary Imports
import numberPlay.validator.CommandArgHandler;
import numberPlay.exceptions.InvalidArgNameException;
import numberPlay.exceptions.NumberOfArgsException;
import java.lang.IllegalArgumentException;

//  File Processor related imports
import numberPlay.util.FileProcessor;
import java.nio.file.InvalidPathException;
import java.io.FileNotFoundException;
import java.io.IOException;
import numberPlay.exceptions.EmptyFileException;

//	Interfaces located in util
import numberPlay.util.ItemMapperI;
import numberPlay.util.PersisterI;
import numberPlay.util.RunningAverageResultsI;

//	Concrete classes in util
import numberPlay.util.ItemMapper;
import numberPlay.util.Context;
import numberPlay.util.Persister;
import numberPlay.util.RunningAverageData;

public class Driver {
	public static void main(String[] args) {
		// Command Arg Validation Test
		try {
			int requiredNumArgs = 4;
			CommandArgHandler cmdArgHandler = new CommandArgHandler(args.length, args, requiredNumArgs);
		} catch (NumberOfArgsException | InvalidArgNameException | IllegalArgumentException e) {
			e.printStackTrace();
			System.exit(0);
		}

		// Store cmd args in readable variables
		String inputFilePath = args[0];
		String availableItemsFilePath = args[1];
		Integer runningAvgWindowSize = Integer.parseInt(args[2]);
		String outputFilePath = args[3];

		// Test file validations
		try {
			// 	Initialize FileProcessor objects
			//	Necessary file checks are made when initializing each FileProcessor
			FileProcessor inputFileProcessor = new FileProcessor(inputFilePath);
			FileProcessor availableItemsFileProcessor = new FileProcessor(availableItemsFilePath);

			// Create our runningAverageData instance
			RunningAverageResultsI runningAverageData = new RunningAverageData(runningAvgWindowSize);

			// Process and store the items found in availableItemsFile
			ItemMapperI itemMapper = new ItemMapper(availableItemsFileProcessor);
			((ItemMapper)itemMapper).runProcessor();

			//	Create the Persister object (handles file writing)
			PersisterI persister = new Persister(outputFilePath);
			//	open() it to get it ready to write to outputFilePath
			persister.open();

			//	Context is passed inputFileProcessor, runningAvgWindowSize, itemMapper, and persister
			Context context = new Context(inputFileProcessor, runningAverageData, itemMapper, persister);

			// Start reading the input file
			context.runProcessor();

			//	close() the persister
			persister.close();
		} catch (InvalidPathException | SecurityException | IOException | EmptyFileException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
