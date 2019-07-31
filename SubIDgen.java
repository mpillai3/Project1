package subscribers;

public class SubIDgen {

	private static long subIDs = 0L;
	
	protected static long getNewSubID() {
		return subIDs++;
	}
}
