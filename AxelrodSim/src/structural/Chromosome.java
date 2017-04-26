package structural;

import utility.SystemConstants;
import utility.UtilityMethods;

/**
 * 
 * @author rob
 * 
 * This class implememnts the Chromosome, the main macro-unit of information of an individual, which includes the all 
 * combinatorial moves to a past three moves of an adversiarial: it is composed by 64 genes
 *
 */
public class Chromosome {
	int[] genes;

	
	/**
	 * Constructs a random gene sequence, where C=0, D=1
	 */
	public Chromosome() {
		genes = new int[SystemConstants.CHROMOSOME_SIZE];
		for(int i=0;i<genes.length;i++){
			genes[i]= UtilityMethods.BinaryRandomGenerator();
		}
		
			
	}
	public Chromosome(int[] genes) {
		super();
		this.genes = genes;
	}
	/**
	 * Print of the gene sequence of the chromosome; formatted in 16 gene sequence for row
	 */
	public void printChromosome(){
		for(int j=0;j<genes.length;j++){
				
			if(j%16==0){
				System.out.println();
			}
			System.out.print(genes[j]);
		}
	}
	
		
	
	public int[] getGenes() {
		return genes;
	}
	public void setGenes(int[] genes) {
		this.genes = genes;
	}
	

}
