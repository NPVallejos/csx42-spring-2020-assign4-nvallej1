package numberPlay.util;

import java.io.IOException;

// Purpose - read input file and perform operations using currentState
public class Context implements StateI, ProcessorI {
	StateI currentState;
	FileProcessor inputFileProcessor;

	//	Purpose - Constructor which initializes the currentState with itemMapperIn and persisterIn
	public Context(FileProcessor inputFileProcessorIn, RunningAverageResultsI runningAverageDataIn, ItemMapperI itemMapperIn, PersisterI persisterIn) {
		inputFileProcessor = inputFileProcessorIn;
		currentState = new BasicState(runningAverageDataIn, itemMapperIn, persisterIn);
	}

	// Purpose - read data from the input file through inputFileProcessor
	@Override
	public void runProcessor() {
		try {
			String line = null;
			while ((line = inputFileProcessor.poll()) != null) {
				String[] pair = line.split(":");

				// Verify that the format of the input is correct = (<type>:<value>)
				if (pair.length != 2) {
					System.out.println("Bad format in input file: " + line);
					System.exit(0);
				}

				// Determine if input is "money" or an "item"
				if (pair[0].equals("money")) {
					// Determine if money value is an Integer
					try {
						// Store money in runningAverageData object
						Integer money = Integer.parseInt(pair[1]);

						// Check that the money earned is > 0
						if (money <= 0) {
							System.out.println("Money earned should be greater than 0. money = " + money);
							System.exit(0);
						}

						currentState = storeMoney(money);
					} catch (NumberFormatException e) {
						System.out.println(pair[1] + " is not of type Integer. Any number in the input file should be of type Integer.");
						e.printStackTrace();
						System.exit(0);
					}
				}
				else {
					// Attempt to purchase the item
					purchaseItem(pair[1]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	// Purpose - pass the call to currentState
	@Override
	public void purchaseItem(String item) {
		currentState.purchaseItem(item);
	}

	// Purpose - pass the call to currentState
	// Also possibly changes the currentState
	@Override
	public StateI storeMoney(Integer money) {
		return currentState.storeMoney(money);
	}
}
