package strategies;

import java.util.Random;

/**
 * The strategy of lookback is the following: it remember the ten previuos moves and extract from them the probability of
 * defects or cooperate: so it follows the 4 main rule: don't be envios, initially cooperate, not too clever and reciprocity.
 * 
 * 
 * @author rob
 *
 */
public class LookBack implements Representative {

	public int[] buffer = new int[10];
	int counter=0;
	public Random random = new Random(System.currentTimeMillis());
	
	/**
	 * IT counts the adversarial defection in the ten past moves, it normalizes to unitary and get from that the 
	 * probability to defect
	 */
	@Override
	public int play() {
		double p=0;
	   for(int i=0;i<buffer.length;i++){
		      p=p+buffer[i];
			}
	   
	   p=(double)p/10;
	   //System.out.println(p);
	   double x = random.nextDouble();
      if(x<p){
    	  //System.out.println("play 0, "+p+","+x);
    	  return 0;
      }
      else{
    	  //System.out.println("play 1, "+p+","+x);
    	  return 1;
      }
		
	}
/**
 * In this case updating the buffer use a circular array implemented with the modulus operator: it memorize the last ten
 * moves of the adversarial
 */
	@Override
	public void updateBuffer(int l) {
		buffer[counter%10]=l;
		counter++;
		 
		 /**
		  * System.out.println();
		  * for(int j=0;j<buffer.length;j++){
		  *	 System.out.print(buffer[j]+" "); 
		  *   }
		  *  	 System.out.println();
		  */
		
		
		
	}
	

}
