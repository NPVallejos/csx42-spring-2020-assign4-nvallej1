package numberPlay.validator;

import numberPlay.exceptions.NumberOfArgsException;
import numberPlay.exceptions.InvalidArgNameException;
import java.lang.IllegalArgumentException;

// purpose - store and validate command line arguments
// Reference: Person.java -> https://piazza.com/class/k5k3yuyx97612d?cid=37
public class CommandArgHandler {
	private int numArgs;
	private String[] args;
	private int requiredNumArgs;

	private static class ValidatorFetcher {
		// Purpose - Test if there are correct number of args
		public static Validator numArgsValidator(CommandArgHandler c) {
			return new Validator() {
				@Override
				public void run() throws NumberOfArgsException, InvalidArgNameException, IllegalArgumentException {
					if (c.numArgs != c.requiredNumArgs) {
						throw new NumberOfArgsException("Incorrect number of arguments. Expected "+ c.requiredNumArgs +" arguments and only "+ c.numArgs +" arguments are given.");
					}
				}
			};
		}
		// Purpose   - Test if cmd args are default values.
		// Important - The returned 'Validator' is based on the field 'checkPrimeDetectorArgs'
		// Other     - This is a hardcoded
		public static Validator argNamesValidator(CommandArgHandler c) {
			return new Validator() {
				@Override
				public void run() throws NumberOfArgsException, InvalidArgNameException, IllegalArgumentException {
					if (c.args[0].equals("${inputFile}")) {
						throw new InvalidArgNameException("Input file path not specified.");
					}
					else if (c.args[1].equals("${availableItemsFile}")) {
						throw new InvalidArgNameException("Total number of available items not specified.");
					}
					else if (c.args[2].equals("${runningAverageWindowSize}")) {
						throw new InvalidArgNameException("Running average window size not specified.");
					}
					else if (c.args[3].equals("${outputFile}")) {
						throw new InvalidArgNameException("Output file not specified.");
					}
				}
			};
		}
		// Purpose - Validate the values of the cmd args
		public static Validator argValuesValidator(CommandArgHandler c) {
			return new Validator() {
				@Override
				public void run() throws NumberOfArgsException, InvalidArgNameException, IllegalArgumentException {
					if (Integer.parseInt(c.args[2]) <= 0) {
						throw new IllegalArgumentException("Running average window size must be greater than 0!");
					}
				}
			};
		}
	}

	// Purpose - Constructor
	public CommandArgHandler(int numArgsIn, String[] argsIn, int requiredNumArgsIn) throws NumberOfArgsException, InvalidArgNameException, IllegalArgumentException {
		this.numArgs = numArgsIn;
		this.args = argsIn;
		this.requiredNumArgs = requiredNumArgsIn;

		// Validating fields
		ValidatorUtil.validate("Failed: ",
			ValidatorFetcher.numArgsValidator(this),
			ValidatorFetcher.argNamesValidator(this),
			ValidatorFetcher.argValuesValidator(this));
	}
}
