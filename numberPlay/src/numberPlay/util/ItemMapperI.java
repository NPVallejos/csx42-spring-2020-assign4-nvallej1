package numberPlay.util;

// 	Purpose - to be implemented by ItemMapper
public interface ItemMapperI {
	// 	Purpose - Store the item and its associated type into a hashmap
	// @param key - item type (basic, moderatelyExpensive, superExpensive)
	// @param data - item name
	public void store(String key, String data);
}
