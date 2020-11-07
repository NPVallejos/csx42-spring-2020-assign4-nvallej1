package numberPlay.util;

// 	Purpose - provide important methods needed to be implemented
// 	by all States and the Context class
public interface StateI {
	//	Purpose - Determine if the 'item' can be purchased
	public void purchaseItem(String item);
	//	Purpose - store money and calculate which StateI
	//	the program is in after storing the money
	public StateI storeMoney(Integer money);
}
