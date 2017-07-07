package assets;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import structural.Triple;
import utility.SystemConstants;
import utility.UtilityMethods;
/**
 *
 * @author rob
 *
 * This is the "Genomic code", a map that creates a correspondence
 *  from a gene to the triples representing the game results in the terms of the three previous moves
 *
 */
public class MatchingTable {


	private static MatchingTable matchingTable;
	private Triple[] genomeCode;
	static private boolean load;


	public static boolean isLoad() {
		return load;
	}


	public static void setLoad(boolean load) {
		MatchingTable.load = load;
	}


	/**
	 * Private constructor: if it's not loading data from memory, it begins the creation of the table, else
	 * it loads it from memory
	 */
	 private MatchingTable() {
		 if (load){
			 genomeCode = this.loadTable();
		 }
		 else{
			 genomeCode =  CreateGenomeCode();
		     this.saveTable();
			 }

		 }


	 /**
	  * Create genome procedure is analog to creating a truth table with 4 simbols
	  *
	  * Note: The raw implementation is due to the simplest operation implemented: a more smart implementation
	  * was possible but reducing the code was not the better moves cause it will cause a delay in the development that was
	  * not justified with a significal increment of the speed.
	  */

     private Triple[] CreateGenomeCode() {
    	 Triple[] genome = new Triple[SystemConstants.CHROMOSOME_SIZE];
    	 int [][] supMatrix = new int[SystemConstants.CHROMOSOME_SIZE][3];
    	 //First step
		for(int i=0;i<SystemConstants.CHROMOSOME_SIZE;i++){
			if(i>=0 && i<16) { supMatrix[i][0] = 1;}
			if(i>=16 && i<32){ supMatrix[i][0] = 2;}
			if(i>=32 && i<48){ supMatrix[i][0] = 3;}
			if(i>=48 && i<64){ supMatrix[i][0] = 4;}
		}
		//Second step

		for(int i=0;i<SystemConstants.CHROMOSOME_SIZE;i++){
			if(i>=0 && i<4)  { supMatrix[i][1] = 1;}
			if(i>=4 && i<8)  { supMatrix[i][1] = 2;}
			if(i>=8 && i<12) { supMatrix[i][1] = 3;}
			if(i>=12 && i<16){ supMatrix[i][1] = 4;}

			if(i>=16 && i<20){ supMatrix[i][1] = 1;}
			if(i>=20 && i<24){ supMatrix[i][1] = 2;}
			if(i>=24 && i<28){ supMatrix[i][1] = 3;}
			if(i>=28 && i<32){ supMatrix[i][1] = 4;}

			if(i>=32 && i<36){ supMatrix[i][1] = 1;}
			if(i>=36 && i<40){ supMatrix[i][1] = 2;}
			if(i>=40 && i<44){ supMatrix[i][1] = 3;}
			if(i>=44 && i<48){ supMatrix[i][1] = 4;}

			if(i>=48 && i<52){ supMatrix[i][1] = 1;}
			if(i>=52 && i<56){ supMatrix[i][1] = 2;}
			if(i>=56 && i<60){ supMatrix[i][1] = 3;}
			if(i>=60 && i<64){ supMatrix[i][1] = 4;}

		}
		//third step
		for(int i=0;i<SystemConstants.CHROMOSOME_SIZE;i++){
			if(i%4==0){supMatrix[i][2] = 1;}
			if(i%4==1){supMatrix[i][2] = 2;}
			if(i%4==2){supMatrix[i][2] = 3;}
			if(i%4==3){supMatrix[i][2] = 4;}
		}
		for(int i=0;i<SystemConstants.CHROMOSOME_SIZE;i++){
			genome[i] = new Triple(supMatrix[i][0],supMatrix[i][1],supMatrix[i][2]);
		}

		return genome;

	}





	public static MatchingTable getMatchingTable() {
        if (matchingTable == null) {
        	matchingTable = new MatchingTable();

        }
        return matchingTable;


    }
    /**
     * This methods save the table in memory at specific address
     * default: /home/rob/eclipse/WorkspaceFileStore/
     */
    public void saveTable(){
    	try {
    		FileOutputStream saveGenome = new FileOutputStream (SystemConstants.SOURCE_ADDRESS+"genome.data");
			ObjectOutputStream out = new ObjectOutputStream(saveGenome);
			out.writeObject(genomeCode);
			out.close();
			saveGenome.close();
			System.out.println("Genome's file saved in "+SystemConstants.SOURCE_ADDRESS+" under genome.data's name");
		} catch( Exception e) {
			System.out.println("Throwed IO Exception on saving the genome");
			e.printStackTrace();

		}

    }

    /**
     * This methods load the table in memory at specific address
     * default: /home/rob/eclipse/WorkspaceFileStore/
     */

    public Triple[] loadTable(){
    	Triple[] load=null;
		Object tmp;

		FileInputStream loadGenome;
		try {
			Triple[] loadedGenome;
			loadGenome = new FileInputStream(SystemConstants.SOURCE_ADDRESS+"genome.data");

			ObjectInputStream in = new ObjectInputStream(loadGenome);
			tmp = in.readObject();
			loadedGenome = (Triple[])tmp;

			in.close();
			loadGenome.close();
		    System.out.println("Genome succesfully loaded");

		    return loadedGenome;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Throwed IO Exception on loading the genome");
			e.printStackTrace();
			return  load;
		}

    }

    /**
     * This method search for the number associated with the gene in the genome code: if it doens't find anything, it throws
     * an exception;
     *
     * @param gene
     * @return
     * @throws Exception
     */

    public int geneSearch(Triple gene) throws Exception{
    	int i=0;
    	int choice = -1;
    	boolean catched=false;

    	while( i<genomeCode.length && catched==false ){
    		if (UtilityMethods.isEqual(gene, genomeCode[i])){
    			choice = i;
    			catched = true;
    			//System.out.println("Finded!");
    		}
    		i++;

    	}
    	if (catched==true){
    		return choice;
    		}
    	else {
    		gene.print();
    		throw new Exception("Strategy not found");
    	}

    }


	/**
	 *  Methods that print the table
	 */
    public void print(){
    	for(int i=0; i<genomeCode.length;i++){
    		genomeCode[i].print();
    	}
    }


    /**
     * Methods that print the table and the chromosome of and individual
     * @param ind
     */
    public void printWihInd(Individual ind){
    	for(int i=0; i<genomeCode.length;i++){
    		for(int j=0;j<3;j++){
    			System.out.print(UtilityMethods.numberToString(genomeCode[i].getMoves()[j]));
    		}
    		System.out.print(" : ");
             if(ind.getChromosome().getGenes()[i]==0){
            	 System.out.print(" C ");
             }
             else{
            	 System.out.print(" D ");
             }

    		System.out.println();
    	}
    }
    /**
     * Print the best player's genome for each simulation
     * @param ind
     */
	public void createFiles(Individual ind[], int gen){
		PrintWriter writer;
		try {
			writer = new PrintWriter(SystemConstants.INDIVIDUALS_ADDRESS+"TopIndividual_"+gen+".txt", "UTF-8");
			for(int k=0;k<SystemConstants.PRINT_NUMBER;k++){
				writer.print((int)ind[k].getScore()+",");
				if((k+1)%4==0){writer.println();}
			}
			writer.println();
			writer.println("                "+": 1 2 3 4 5 6 7 8 9 ");
		    writer.println();
			for(int i=0; i<genomeCode.length;i++){
    		for(int j=0;j<3;j++){
    			writer.print(UtilityMethods.numberToString(genomeCode[i].getMoves()[j]));
    		}

    		writer.print(" : ");
    		//lenght-10, too individuals
    		for(int k=0;k<SystemConstants.PRINT_NUMBER;k++){
             if(ind[k].getChromosome().getGenes()[i]==0){
            	 writer.print("C ");
             }
             else{
            	 writer.print("D ");
             }

             writer.print("");
    		}

         writer.println();
    	}


			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}



	}


