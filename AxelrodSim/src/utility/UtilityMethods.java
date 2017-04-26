package utility;

import java.util.Random;

import assets.Individual;
import structural.Triple;
/**
 * 
 * @author rob
 *
 * This class implements some static methods of utility, especially a PNG random generator
 */
public class UtilityMethods {
	
	private static Random random = new Random(System.nanoTime());
	



	public static int CutPointRandomGenerator(){
		//Random random = new Random();
		return (random.nextInt(71));
	}
	
	
	/**
	 * This method return a random number between [0,1]
	 * @return
	 */
	public static int BinaryRandomGenerator(){
		//Random random = new Random(System.currentTimeMillis());
		return  (random.nextInt(2));
	}
	/**
	 * This method return a random number between [1,4]
	 * 
	 * @return
	 */
	public static int RandomGenerator(){
		//Random random = new Random();
		return  (random.nextInt(4)+1);
	}
	
	   /**
     * This methods return true if two genes are equal, false otherwise
     * 
     * @param gene1
     * @param gene2
     * @return
     */
   
	 public static boolean isEqual(Triple gene1, Triple gene2){
	    	boolean isEqual;
	    	int[] g1;
	    	int[] g2;
	    	g1 = gene1.getMoves();
	    	g2 = gene2.getMoves();
	    	if((g1[0]==g2[0]) && (g1[1]==g2[1]) && (g1[2]==g2[2]) ) {
	    		isEqual = true;
	    	}
	    	else{
	    		isEqual=false;
	    	}
	    	return isEqual;
	    }
	 
	 /**
	  * Genome translation table
	  * CC = 1
	  *	DC = 2
	  *	CD = 3
	  *	DD = 4
      * 
	  * @param a
	  * @param b
	  * @return
	  */
		public static int translate(int a, int b) {
	        if(a+b==2){
				return 4; //defect defect, 1+1=2
	        }
			else if(a+b==0){
						return 1; //cooperate cooperate, 0+0=0
					}
					else if(a==0 && b ==1){
						return 3;
						//cooperate Defect
					}
						else {
							return 2;
							}
			
		}
		/**
		 * This method translate the fictitious past in genome: it enlarge the past array from 3 to 6, to represent
		 * the past in terms of genome code, 0,1; 
		 * 
		 * @param past
		 * @return
		 */
		public static int[] inverseTranslate(int[] past){
			int[] translGenome = new int[SystemConstants.FICTITIOUS_PAST_SIZE];
			int j = 0;
			// Jump of two: i obtaine a twice of the size array than the starting once
			for(int i=0;i<translGenome.length;i=i+2){
				
			  if(past[j]==4){
					translGenome[i]=1;
					translGenome[i+1]=1;
					//defect defect, 1+1=2
		        }
				else if(past[j]==1){
						translGenome[i]=0;
						translGenome[i+1]=0;
						 //cooperate cooperate, 0+0=0
						}
						else if(past[j]==3){
							translGenome[i]=0;
							translGenome[i+1]=1;
							//cooperate defect
						}
							else {
								translGenome[i]=1;
								translGenome[i+1]=0;
								//defect cooperate
								}
			  j++;
			}
			
			return translGenome;
			
		}
		
		/**
		 * This method implements the creation of a full genome for a parent before the crossover:
		 * we obtain a chromosome of 64+6 = 70 gene
		 * 
		 * @param parent
		 * @return 
		 */
		public static int[] fullChromosome(Individual parent){
			int[] pastChromosome = inverseTranslate(parent.getRandomPast().getPast().getMoves());
			//creating the 70 genes chromosome, 6+64
			int[] fullChromosome = new int[pastChromosome.length+parent.getChromosome().getGenes().length];
			System.arraycopy(pastChromosome, 0, fullChromosome, 0, pastChromosome.length);
			System.arraycopy(parent.getChromosome().getGenes(), 0, fullChromosome, pastChromosome.length, parent.getChromosome().getGenes().length);
			/*
			for(int j=0;j<fullChromosome.length;j++){
				
				if(j%16==0){
					System.out.println();
				}
				
				System.out.print(fullChromosome[j]);
			}
			*/
			return fullChromosome;
		}
		/**
		 * This method implement the mating order for the first 6 top scorer individual
		 */
		 public static int[] topMatingsOrder(){;
		    	//Random random = new Random();
		    	int[] maters = {0,1,2,3,4,5};
		    	int repeat= random.nextInt(10);
		    
		    for(int j=0;j<=repeat;j++){
		    	for(int i=0;i<maters.length;i++){
			    	  int x = random.nextInt(6);
			    	  //random changing position
			    	  int tmp =maters [x];
			    	  maters[x]=maters[i];
			    	  maters[i]=tmp;
			    	}
		    }
		    /*
		    for(int i=0;i<maters.length;i++){
		    	System.out.print(maters[i]);	
		    }
		    */
		    return maters;
		    	
		    }
		 
		 public static int[] bottomMatingsOrder(){;
	    	//Random random = new Random();
	    	int[] maters = {6,7,8,9,10,11,12,13};
	    	int repeat= random.nextInt(10);
	    
	    for(int j=0;j<=repeat;j++){
	    	for(int i=0;i<maters.length;i++){
		    	  int x = random.nextInt(8);
		    	  //random changing position
		    	  int tmp =maters [x];
		    	  maters[x]=maters[i];
		    	  maters[i]=tmp;
		    	}
	    }
	    /*
	    for(int i=0;i<maters.length;i++){
	    	System.out.print(" "+maters[i]+" ");	
	    }
	    System.out.println();
	    */
	    
	    return maters;
	    	
	    }
		 
		 /**
		  *  
		  * This methods transalte the number coded genes in C and D; cooperate or defect
		  * Genome:
		  *	CC = 1  :00
		  *	DC = 2  :10
		  *	CD = 3  :01
		  *	DD = 4  :11
          *
		  * @param number
		  * @return
		  */
		 
	public static String numberToString(int number){
		String choice ="";
		if (number == 1){ choice = "|C,C|";}
		if (number == 2){ choice = "|D,C|";}
		if (number == 3){ choice = "|C,D|";}
		if (number == 4){ choice = "|D,D|";}
		return choice;
	}
	public static Random getRandom() {
		return random;
	}


	public static void setRandom(Random random) {
		UtilityMethods.random = random;
	}
	
}
