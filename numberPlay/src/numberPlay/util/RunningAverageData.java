package numberPlay.util;

import java.util.ArrayList;

// 	Purpose - maintain running average based on the running average window size
public class RunningAverageData implements RunningAverageResultsI {
	private ArrayList<Integer> previousNumbers;
	private int runningAvgWindowSize;
	private int currentRunningAverage;

	public RunningAverageData(int runningAvgWindowSizeIn) {
		previousNumbers = new ArrayList<Integer>();
		runningAvgWindowSize = runningAvgWindowSizeIn;
		currentRunningAverage = 0;
	}

	// 	Purpose - add to 'previousNumbers'
	// 	@param i - value to be added to list
	@Override
	public void store(Integer i) {
		if (previousNumbers.size() == runningAvgWindowSize) {
			previousNumbers.remove(0);
		}
		previousNumbers.add(i);
	}

	// 	Purpose - compute the current running average
	@Override
	public Integer getRunningAverage() {
		Integer count = 0;
		for (Integer number : previousNumbers) {
			count += number;
		}
		return (int)(count/previousNumbers.size());
	}

	// 	Purpose - print values inside previousNumbers list
	@Override
	public String toString() {
		String retString = "Running Average Data:\n";
		for (Integer number : previousNumbers) {
			retString += number.intValue();
			retString += ",";
		}
		retString += getRunningAverage().toString();

		return retString;
	}
}
