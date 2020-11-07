package numberPlay.util;

// Purpose - to be implemented by RunningAverageData
public interface RunningAverageResultsI {
	// 	Purpose - add integer to a list
	// 	@param i - value to be added to list
	public void store(Integer i);
	// 	Purpose - compute the current running average
	public Integer getRunningAverage();
}
