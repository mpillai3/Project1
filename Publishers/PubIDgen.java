package publishers;

public class PubIDgen {

	private static long pubIDs = 0L;
	
	protected static long getNewPubID() {
		return pubIDs++;
	}
}
