package assets;

import java.util.Arrays;
import java.util.Random;

import strategies.AllD;
import strategies.LookBack;
import strategies.Representative;
import strategies.TitForTat;
import strategies.TitForTwoTat;
import structural.RandomPast;
import structural.Triple;
import utility.SystemConstants;
import utility.UtilityMethods;

/**
 * 
 * @author rob
 *
 * This class define the environment of the simulation. Maybe be some older trace of a singleton implementation.
 *
 */

public class Environment {
	
	private Individual[] population ;
	private Representative[] representatives;
	private Individual[] transitionalPop;
	private int generationMating=0;
	
	/***
	 * The constructor create the population of individuals and 
	 * the representatives strategies
	 * 
	 */
	     public Environment() {
	    	 population = new Individual[SystemConstants.POPULATION];
	    	 transitionalPop = new Individual[SystemConstants.POPULATION];
	    	 //creating individuals population
	    	 for(int i =0;i<population.length;i++){
	    		 population[i]=new Individual();
	    	 }
	    	 //creating the representatives strategies
	    	representatives = new Representative[4];
	    	representatives[0] = new TitForTat();
	    	representatives[1] = new AllD();
	    	representatives[2] = new TitForTwoTat();
	    	representatives[3] = new LookBack(); //temporary
	    	    	 	    	 
	     }
	 
	
	  
	    /**
	     * This methods sort the individuals per score points: it implements the fitness function
	     */
	    
	    public void fitnessSort(){
			Arrays.sort(population);
		}
	    /**
	     * Method wich print the top l scores
	     * @param l
	     */
	    public void printScore(int l){
	    	System.out.println();
	    	for(int i=0;i<l;i++){
	    		if(i%4==0){System.out.println();}
	    		//System.out.print("Individual: "+i+" score: "+population[i].getScore());
	    		System.out.print("|");
	    		System.out.print(population[i].getScore());
	    		System.out.print("|");
	    	}
	    	System.out.println();
	    }
	    
	    /**
	     * This method resets the counter for the number of mating
	     */
	    public void resetGenMating(){
	    	generationMating=0;
	    }
	    /**
	     * Update the new generation with the offsprings of the previus generation
	     * 
	     */
	    public void changeGeneration(){
	    	population = transitionalPop;
	    }
	    
	    
	    /**
	     * This method implements the reproduction with genetic's crossover: he need a submethods which 
	     * translate the "fictitious past" in genome for a correct inheritance process: the kind of reproduction
	     * requested its deterministic in the crossover genetic's mixing events, then it always happen .
	     * It also implements mutations as a rare event.
	     *  
	     * @param father
	     * @param mother
	     */
	    public void mate(Individual father, Individual mother){
	    	Individual off1 = new Individual();
	    	Individual off2 = new Individual();
	    	int cutpoint;
	    		//Creating the full 70 gene's chromosome
	    		int[] fatherChromosome = UtilityMethods.fullChromosome(father);
	    		int[] motherChromosome = UtilityMethods.fullChromosome(mother);
	    		//Extract a random cutpoint
	    		cutpoint=UtilityMethods.CutPointRandomGenerator();
	    		//Create a full chromosome also for offsprings
	    		int[] off1Chromosome = UtilityMethods.fullChromosome(off1);
	    		int[] off2Chromosome = UtilityMethods.fullChromosome(off2);
	    		//creation of the offsprings chromosomes
	    		for(int i = 0;i<off1Chromosome.length;i++){
	    			if(i<cutpoint){
	    				off1Chromosome[i]= fatherChromosome[i];
	    				off2Chromosome[i]= motherChromosome[i];
	    			}
	    			else{
	    				off1Chromosome[i]=motherChromosome[i]; 
	    				off2Chromosome[i]=fatherChromosome[i]; 
	    			}
	    		     			
	    		}
	    				
	    		//translating the first six chromosome in a randomPast
	    		
	    		int[] past1 = new int[SystemConstants.FICTITIOUS_PAST_SIZE/2];
	    		int[] past2 = new int[SystemConstants.FICTITIOUS_PAST_SIZE/2];
	    		//double index "for" cycle, i have to jump in two of two
	    	    for(int i = 0, j=0;i<SystemConstants.FICTITIOUS_PAST_SIZE;i=i+2,j++){
	    	    	past1[j] = UtilityMethods.translate(off1Chromosome[i], off1Chromosome[i+1]);
	    	    	past2[j] = UtilityMethods.translate(off2Chromosome[i], off2Chromosome[i+1]);
	    	    	
	    	    }
	    	    
	    	    
	    	    
	    	    RandomPast off1Past = new RandomPast(new Triple(past1[0],past1[1],past1[2]));
	    	    RandomPast off2Past = new RandomPast(new Triple(past2[0],past2[1],past2[2]));
	    	    
	    	    //Mutation: random mutation on the offsprings genome: extremely rare
	    	    Random mutation = UtilityMethods.getRandom();
	    	    Random mutatedGene = UtilityMethods.getRandom();
	    	    int mutated=mutatedGene.nextInt(70);
	    	    if(((double)mutation.nextInt(1001)/1000)<=0.075){
	    	    	//System.out.println("mutation!");
	    	    	if(off1Chromosome[mutated]==0) {
	    	    		off1Chromosome[mutated]=1;
	    	    	}
	    	    	else{
	    	    		off1Chromosome[mutated]=0;
	    	    	}
	    	    }
	    	    
	    	    if(((double)mutation.nextInt(1001)/1000)<=0.075){
	    	    	//System.out.println("mutation!");
	    	    	if(off2Chromosome[mutated]==0) {
	    	    		off2Chromosome[mutated]=1;
	    	    	}
	    	    	else{
	    	    		off2Chromosome[mutated]=0;
	    	    	}
	    	    }
	    	    //cutting the 70 chromosome to 64
	    	    int[] finalOff1Chrom = new int[SystemConstants.CHROMOSOME_SIZE];
	    	    int[] finalOff2Chrom = new int[SystemConstants.CHROMOSOME_SIZE];
	    	    for(int i=0;i<SystemConstants.CHROMOSOME_SIZE;i++){
	    	    	finalOff1Chrom[i] = off1Chromosome[i+SystemConstants.FICTITIOUS_PAST_SIZE];
	    	    	finalOff2Chrom[i] = off2Chromosome[i+SystemConstants.FICTITIOUS_PAST_SIZE];
	    	    }
	    	    
	    	   
	    	    
	    	    //Creation of the two offsprings
	    	    off1 = new Individual(off1Past,finalOff1Chrom);
	    	    off2 = new Individual(off2Past,finalOff2Chrom);
	    	    
	    	    //for debug, temporary
	    	    transitionalPop[generationMating]=off1;
	    	    generationMating++;
	    	    transitionalPop[generationMating]=off2;
	    	    generationMating++;
	    }
		
	   
	    
	    public void printPopulation(){
	    	for(int i=0; i<population.length;i++){
	    		population[i].print();
	    	}
	    }


		public Individual[] getPopulation() {
			return population;
		}
		
		public Representative[] getRepresentatives() {
			return representatives;
		}


		public Individual[] getTransitionalPop() {
			return transitionalPop;
		}


		public void setTransitionalPop(Individual[] transitionalPop) {
			this.transitionalPop = transitionalPop;
		}
		
		
	   /**
	    * This method implements the matings of the top and average individuals in the population
	    */
		public void matings(){
			int[] mateCouples = UtilityMethods.topMatingsOrder();
			//first top matings
			for(int i=0;i<mateCouples.length;i=i+2){
				//System.out.println("mating: "+mateCouples[i]+","+mateCouples[i+1]);
				this.mate(population[mateCouples[i]], population[mateCouples[i+1]]);	
			}
			mateCouples = UtilityMethods.topMatingsOrder();
			//second top matings
			for(int i=0;i<mateCouples.length;i=i+2){
				//System.out.println("mating: "+mateCouples[i]+","+mateCouples[i+1]);
				this.mate(population[mateCouples[i]], population[mateCouples[i+1]]);	
			}
			
			mateCouples = UtilityMethods.bottomMatingsOrder();
			//the only one matings for averages
			for(int i=0;i<mateCouples.length;i=i+2){
				//System.out.println("mating: "+mateCouples[i]+","+mateCouples[i+1]);
				this.mate(population[mateCouples[i]+6], population[mateCouples[i+1]+6]);	
			}
			
			
		}
		
	    
	    
}
