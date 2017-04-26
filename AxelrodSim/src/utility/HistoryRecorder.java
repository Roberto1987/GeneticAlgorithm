package utility;

import assets.Individual;

/**
 * This class store the data in the simulations
 * @author rob
 *
 */
public class HistoryRecorder {
	private double[][] scoreRecorder;
	private int generation;
	
	public HistoryRecorder(){
		scoreRecorder = new double[SystemConstants.POPULATION][SystemConstants.GENERATIONS+500];
		generation=0;
		
	}
	/**
	 * Updating the hystory of the score evolution; 
	 * @param population
	 */
	public void updateHistory(Individual[] population){
		for(int j=0;j<SystemConstants.POPULATION;j++){
			scoreRecorder[j][generation]= population[j].getScore();
			//System.out.print(" up "+ j);
			
		}
		//System.out.println(" "+generation);
		generation++;
		
	}
	public void resetGeneration(){
		generation=0;
	}
	public double[][] getScoreRecorder() {
		return scoreRecorder;
	}
	public void setScoreRecorder(double[][] scoreRecorder) {
		this.scoreRecorder = scoreRecorder;
	}
	
	
}
