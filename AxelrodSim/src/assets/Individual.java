package assets;

import strategies.Representative;
import structural.Buffer;
import structural.Chromosome;
import structural.RandomPast;
import utility.SystemConstants;
import utility.UtilityMethods;

/**
 * 
 * @author rob
 * 
 * Implementation of an individual: it starts as a random generated chromosome and past and implements comparable, used to
 * sort the array representing the total population; it also implements the operation of playing games and updating its
 * own status
 *
 */

public class Individual implements Comparable<Individual> {

	private RandomPast randomPast;
	private Chromosome chromosome;
	private Buffer buffer;
	private double score;
	/**
	 * Constructor designed for the creation of an offspring
	 * 
	 * @param past
	 * @param chromosome
	 */

	public Individual(RandomPast past, int[] chromosome){
		randomPast = past;
		this.chromosome = new Chromosome(chromosome);
		this.buffer = new Buffer(past.getPast());
	}

	
	/**
	 * Constructor designed the creation of initial population, with no argument because the initial population is 
	 * composed by random individual
	 * 
	 * [I] Note that randomPast and buffer have a reference to the same Triple object: updating the buffer also update the 
	 * random past: it can be avoided creating a new Triple with the getPast() value, but its not important to the scope
	 * of the software
	 * 	 
	 */
	public Individual() {
		randomPast = new RandomPast();
		chromosome = new Chromosome();
		// [I]
		buffer = new Buffer(randomPast.getPast());
		
	}
	
	
	/**
	 * This methods search the number of the gene related to the buffer in the genome table and play the strategy
	 * written in its chromosome: for example, the buffer corresponds to the genome 15, and the individual respond with
	 * the 15th gene in its chromosome
	 * 
	 * @return
	 * @throws Exception
	 */
	public int play() throws Exception{
		int response = MatchingTable.getMatchingTable().geneSearch(buffer.getBuffer());
		return chromosome.getGenes()[response];
	}
	public void updateBuffer(int selfMove, int otherMove){
		int[] tmpBuffer = new int[SystemConstants.FICTITIOUS_PAST_SIZE/2];
		//Shifting the moves: buffer[0]=first move, buffer[1]=second move, buffer[3]=third move
		//TmpBuffer is 0 cause it will not be written in the cycle
		for(int i =0;i<2;i++){
			tmpBuffer[i+1]=buffer.getBuffer().getMoves()[i]; 
		}
		
		tmpBuffer[0]=UtilityMethods.translate(selfMove,otherMove);
		
		buffer.getBuffer().setMoves(tmpBuffer);
		
		
		
		
		
	}
	
	
	public void print(){
		chromosome.printChromosome();
		System.out.println();
		buffer.print();
		randomPast.print();
		System.out.println("score: "+score);
	}

	public RandomPast getRandomPast() {
		return randomPast;
	}
	
	public Chromosome getChromosome() {
		return chromosome;
	}

	public Buffer getBuffer() {
		return buffer;
	}

	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;
	}
	
	public double getScore() {
		return score;
	}

	/**
	 * Method whic add the game point to the total score points
	 * @param score
	 */
	public void updateScore(int score) {
		this.score = this.score +score;
	}
	/**
	 * Methods implements a match between an individual and a representatives
	 * @param player
	 */
	public void fight(Representative player){
		try {
			
			int myMove =this.play();
			
			int advMove = player.play();
			//update the buffers
			
			this.updateBuffer(myMove, advMove);
		    //this.buffer.print();
			player.updateBuffer(advMove);
			//Translate the resulting game 
			int gameResult = UtilityMethods.translate(myMove, advMove);
			//updating the score
					/**
					 * CC = 1  :00
					 * DC = 2  :10
					 * CD = 3  :01
					 * DD = 4  :11
					 */
							
			if (gameResult==1){	this.updateScore(3);}
			if (gameResult==2){	this.updateScore(5);}
			if (gameResult==3){	this.updateScore(0);}
			if (gameResult==4){	this.updateScore(1);}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	/**
	 * Implementation of compareTo for Comparable, to implements a comparation of the individual by its scores
	 */
	@Override
	public int compareTo(Individual other) {
		 return Double.compare(other.score, this.score);
		
	}

	public void scoreAvg(){
		this.score = this.score/SystemConstants.REPRESENTATIVES;
	}
	
}
