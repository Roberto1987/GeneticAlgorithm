package core;

import java.util.concurrent.TimeUnit;

import draw.Grapher;
import assets.Environment;
import assets.MatchingTable;
import utility.HistoryRecorder;
import utility.SystemConstants;
import utility.UtilityMethods;
/**
 *
 * @author rob
 * The execution class
 *
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
	//initializing the graphing object
	 Grapher grapher = new Grapher();
	 //set true to table to load the data, false to create a new table
	 MatchingTable.setLoad(true);
	 MatchingTable table=  MatchingTable.getMatchingTable();
     //Creating the environmment, with the individual's population and the representatives

     //number of simulation
  for(int s =0; s<40;s++){
	  Thread.sleep(5+UtilityMethods.getRandom().nextInt(200));
  	  Environment env = new Environment();
  	  HistoryRecorder rec = new HistoryRecorder();
     //One Generation
     for(int k=0;k<SystemConstants.GENERATIONS+500;k++){

    	 //System.out.println("Generation "+k);


       for(int i=0;i<SystemConstants.POPULATION;i++){

       	for(int j=0;j<SystemConstants.MOVES;j++){
       		for(int l=0;l<4;l++){
       			env.getPopulation()[i].fight(env.getRepresentatives()[l]);

       		}
       	}
       }
       //averaging the score

       for(int i=0;i<SystemConstants.POPULATION;i++){
    	   env.getPopulation()[i].scoreAvg();
       }
       //fitness function sorting
       env.fitnessSort();
       //score hystory recording
       rec.updateHistory(env.getPopulation());
       //draw the graph
       //grapher.Graphing(rec.getScoreRecorder());
       //Create offsprings
       TimeUnit.MILLISECONDS.sleep(0);
       env.matings();
       //Reset the new offsprigs population counter
       env.resetGenMating();
       //update the new population
       env.changeGeneration();

     }


     //Last generation
     for(int i=0;i<SystemConstants.POPULATION;i++){

        	for(int j=0;j<SystemConstants.MOVES;j++){
        		for(int l=0;l<4;l++){
        			env.getPopulation()[i].fight(env.getRepresentatives()[l]);
        		 //TimeUnit.MILLISECONDS.sleep(1);
        		}
        	}
        }

     //averaging the scores
     for(int i=0;i<SystemConstants.POPULATION;i++){
  	   env.getPopulation()[i].scoreAvg();
     }
     env.fitnessSort();

     //draw the graph
     grapher.Graphing(rec.getScoreRecorder(),s);
     env.printScore(20);
     table.createFiles(env.getPopulation(),s);
      System.out.println("Simulation Ended");
   rec.resetGeneration();


	}
  System.out.println("Stop");

	}



}
