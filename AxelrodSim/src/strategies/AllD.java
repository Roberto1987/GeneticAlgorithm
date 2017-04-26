package strategies;

/**
 * AllD is the implementation of the strategy "always defect": it simply return all D, 1;
 * @author rob
 *
 */
public class AllD implements Representative{

	@Override
	public int play() {
		return 1;
	}

	@Override
	public void updateBuffer(int l) {
		// TODO Auto-generated method stub
		
	}
	

}
