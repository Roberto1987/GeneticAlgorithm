package strategies;

/**
 * 
 * TitForTwoTat behave like TitForTat, with the difference that it defects only after two defection by the adversarial:
 * the buffer count the number of defection and is resetted only when the adversarial cooperate
 * 
 * @author rob
 *
 */

public class TitForTwoTat implements Representative {
	int buffer;


	public TitForTwoTat() {
		super();
		buffer=0;
		}

	
	@Override
	public int play() {
		int play;
		if(buffer<2){
			play=0;
				}
		else{
			play=1;
		}
		return play;
	}

	@Override
	public void updateBuffer(int l) {
		if(l==0){
			buffer=0;
		}
		else{
			buffer++;
		}
		
}
	

}
