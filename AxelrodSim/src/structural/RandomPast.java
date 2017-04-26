package structural;

import utility.UtilityMethods;

/**
 * 
 * @author rob
 *
 * This class represent the "fictitious" past of random individuals
 * RandomPast is dipendent from the individual's chromosome: three "adversiarial moves" are random generated and they are 
 * played with the chromosomic's rule of the individual
 */
public class RandomPast {
	private Triple past;
	
	/**
	 * Constructor designed for settin the fictitious past of an offspring, translated by its 
	 * inheritate full chromosome's  six initial genes
	 * 
	 * @param inheritatePast
	 */
	public RandomPast(Triple inheritatePast){
		past = inheritatePast;
	}
	
	/**
	 * Constructor designed for a randomPast creation, by the initial random individuals population generation 
	 */
	public RandomPast() {
		past = new Triple(UtilityMethods.RandomGenerator(),UtilityMethods.RandomGenerator(),UtilityMethods.RandomGenerator());
	}

	public Triple getPast() {
		return past;
	}

	public void setPast(Triple past) {
		this.past = past;
	}

	public void print(){
		past.print();
	}
	
	
	
	
	
	

}
