package structural;

/**
 * 
 * @author rob
 * 
 * This class implements the buffer of the individual, the memory of the previous third moves used to find a correspondence
 * with his "genes"
 *
 */

public class Buffer {
	private Triple memory;

	/**
	 * 
	 * @param memory
	 */
	public Buffer(Triple memory) {
		super();
		this.memory = memory;
	}

	public Triple getBuffer() {
		return memory;
	}

	public void setBuffer(Triple memory) {
		this.memory = memory;
	}
	
	public void print(){
		memory.print();;
	}

}
