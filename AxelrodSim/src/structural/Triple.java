package structural;

import java.io.Serializable;


import utility.SystemConstants;

/**
 * 
 * @author rob
 * 
 * Implements the structural object which represent the 3 previous moves of a game
 *
 */
public class Triple implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] moves;
	

	/**
	 * 
	 * @param m1
	 * @param m2
	 * @param m3
	 */
	
	public Triple(int m1,int m2, int m3) {
		moves= new int[SystemConstants.FICTITIOUS_PAST_SIZE/2];
		moves[0]=m1;
		moves[1]=m2;
		moves[2]=m3;
		
	} 


	public int[] getMoves() {
		return moves;
	}

	public void setMoves(int[] moves) {
		this.moves = moves;
	}
	
 public void print(){
	 System.out.println(" "+moves[0]+","+moves[1]+","+moves[2]);
 }

   

	
	

}
