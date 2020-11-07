package numberPlay.util;

import java.io.IOException;

//	Purpose - implements all moderatelyExpensive state behavior
public class LuxuriousState implements StateI {
	private RunningAverageResultsI runningAverageData;
	private ItemMapperI itemMapper;
	private PersisterI persister;
	private String[] itemTypes;

	//	Purpose - constructor
	public LuxuriousState(RunningAverageResultsI runningAverageDataIn, ItemMapperI itemMapperIn, PersisterI persisterIn) {
		runningAverageData = runningAverageDataIn;
		itemMapper = itemMapperIn;
		persister = persisterIn;
		itemTypes = new String[2];
		itemTypes[0] = "basic";
		itemTypes[1] = "moderatelyExpensive";
	}

	//	Purpose - Determine if the 'item' can be purchased
	@Override
	public void purchaseItem(String item) {
		try {
			//	Determine if this item is a 'basic' item
			if (((ItemMapper)itemMapper).checkItemType(itemTypes[0], item) == true) {
				persister.writeToFile("LUXURIOUS::" + item + "--YES");
			}
			else if (((ItemMapper)itemMapper).checkItemType(itemTypes[1], item) == true) {
				persister.writeToFile("LUXURIOUS::" + item + "--YES");
			}
			else {
				persister.writeToFile("LUXURIOUS::" + item + "--NO");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	//	Purpose - store money and calculate which StateI
	//	the program is in after storing the money
	@Override
	public StateI storeMoney(Integer money) {
		runningAverageData.store(money);
		if (runningAverageData.getRunningAverage() >= 50000) {
			return new ExtravagantState(runningAverageData, itemMapper, persister);
		}
		else if (runningAverageData.getRunningAverage() >= 10000) {
			return this;
		}
		return new BasicState(runningAverageData, itemMapper, persister);
	}
}
