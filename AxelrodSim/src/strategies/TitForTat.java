package strategies;

/**
 * @author rob
 * 
 * This class implements TIT FOT TAT: TFT looks only at the immediatly previous moves so it need a buffer of a single move;
 * It behaves as the following rules
 * :
 *   I: Initiate the game with cooperation, 1
 *   II: If the other player has cooperated in the previous move, it cooperate and plays 1
 *   II: If in the previous move the other player has defected, it defects and plays 0 
 * 
 *
 */
public class TitForTat implements Representative{
	int buffer;

	/**
	 * TitForTat Strategy; if adv has defected, it defect, otherwise it cooperate 
	 */
	
	@Override
	public int play() {
		int play;
		if(buffer==0){
						play=0;
						}
		else{
			play=1;
					}
		
		return play;
	}

	/**
	 * Methods wich update the buffer after a game's move
	 */
	
	@Override
	public void updateBuffer(int l) {
		buffer = l;		
		
	}
	

}
