package numberPlay.util;

import java.io.IOException;

//	Purpose - implements all basic state behavior
public class BasicState implements StateI {
	private RunningAverageResultsI runningAverageData;
	private ItemMapperI itemMapper;
	private PersisterI persister;
	private String itemType;

	//	Purpose - constructor
	public BasicState(RunningAverageResultsI runningAverageDataIn, ItemMapperI itemMapperIn, PersisterI persisterIn) {
		runningAverageData = runningAverageDataIn;
		itemMapper = itemMapperIn;
		persister = persisterIn;
		itemType = "basic";
	}

	//	Purpose - Determine if the 'item' can be purchased
	@Override
	public void purchaseItem(String item) {
		try {
			//	Determine if this item is a 'basic' item
			if (((ItemMapper)itemMapper).checkItemType(itemType, item) == true) {
				persister.writeToFile("BASIC::" + item + "--YES");
			}
			else {
				persister.writeToFile("BASIC::" + item + "--NO");
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
			return new LuxuriousState(runningAverageData, itemMapper, persister);
		}
		return this;
	}
}
