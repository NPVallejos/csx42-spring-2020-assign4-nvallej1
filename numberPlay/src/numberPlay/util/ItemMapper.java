package numberPlay.util;

import java.util.HashMap;
import java.util.ArrayList;

import java.io.IOException;

//	Purpose - Map items to their associated type
//	(i.e. basic, moderatelyExpensive, superExpensive)
public class ItemMapper implements ItemMapperI, ProcessorI {
	private HashMap<String, ArrayList<String> > itemMap;
	private FileProcessor availableItemsFileProcessor;

	public ItemMapper(FileProcessor availableItemsFileProcessorIn) {
		availableItemsFileProcessor = availableItemsFileProcessorIn;
		itemMap = new HashMap<String, ArrayList<String> >();
	}

	// Purpose - extract and store data from availableItemsFileProcessor
	@Override
	public void runProcessor() {
		try {
			String line = null;
			while ((line = availableItemsFileProcessor.poll()) != null ) {
				String[] keyValuePair = line.split(":");

				// Verify that the format of the input is correct = (<type>:<value>)
				if (keyValuePair.length != 2) {
					System.out.println("Bad format in available items file: " + line);
					System.exit(0);
				}

				this.store(keyValuePair[0], keyValuePair[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	// Purpose - store item's from availableItemsFileProcessor into itemMap
	// @param key - item type (basic, moderatelyExpensive, superExpensive)
	// @param data - item name
	@Override
	public void store(String key, String item) {
		ArrayList<String> itemList = itemMap.get(key);

		// 	TODO: refactor the following block of code
		//	to make it cleaner
		if (itemList != null) {
			if (itemList.contains(item) == false) {
				itemList.add(item);
			}
		}
		else {
			itemList = new ArrayList<String>();
			itemList.add(item);
		}

		itemMap.put(key, itemList);
	}

	// Purpose - determines if the 'item' is associated with the 'key'
	public boolean checkItemType(String key, String item) {
		if (itemMap.isEmpty()) {
			return false;
		}

		ArrayList<String> itemList = itemMap.get(key);

		if (itemList == null) {
			return false;
		}

		return itemList.contains(item);
	}

	// Purpose - print what's inside the hashmap
	@Override
	public String toString() {
		return itemMap.toString();
	}
}
