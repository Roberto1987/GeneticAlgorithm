package strategies;
/**
 * 
 * @author rob
 * 
 * This interface regroup all representatives under one type, enabling the polymorphism needed for 
 * let the indivduals play with all representatives, abstracting from which one it is
 * 
 * 
 *
 */
public interface Representative {
	/**
	 * How a representatives plays his move
	 * @return
	 */
	public int play();
	
	/**
	 * Updating the buffer for a representatives
	 * @param l
	 */
	public void updateBuffer(int l);

}
