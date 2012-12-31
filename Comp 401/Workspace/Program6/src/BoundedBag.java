public class BoundedBag {

	protected int EMPTY = -1;
	protected int NOT_FOUND = -999;
	protected int CAPACITY = 4;
	
	// Data members
	protected int [] contents; // Elements of the BoundedBag.
	protected int topPosition; // Index of highest used position in contents. 

	/**
	 * Default constructor - creates an empty BoundedBag.
	 */
	public BoundedBag() {
		contents = new int [CAPACITY];
		topPosition = EMPTY;
	}

	public boolean add( int theValue) {
		if (topPosition < CAPACITY-1){
			contents[topPosition++]=theValue; 
			topPosition = topPosition + 1;
			return true;
		}
		else{ 
			return false;
		}
	}
	
	private int findIndex(int theValue){
		for(int index=0; index <= topPosition; index++)
			if(contents[index] == theValue) return index;
		return NOT_FOUND;
	}
	
	// returns true if theValue is in the BoundedBag
	public boolean contains(int theValue ) {
		return findIndex(theValue) != NOT_FOUND;
	}
		
	// deletes theValue from the BoundedBag
	// returns true if theValue was in the Bag,
	// otherwise return false.
	public boolean remove(int theValue) {
		int index = findIndex(theValue);
		if (index == NOT_FOUND ) return false;
		contents[index] = contents[topPosition--];
		return true;
	}
	/**
	 * Return number of elements in the BoundedBag.
	 */
	public int size() {
		return 1 + topPosition;
	}
	
	/**
	 * Convert the BoundedBag to a String.
	 * Optional challenge - find a MUCH more efficient
	 * way to do this.
	 */
	public String toString() {
		String retValue = "";
		if( -1 == topPosition ) return "empty";
		else {
			retValue += contents[0];
			for(int i = 1; i <= topPosition; i++ ) {
				retValue = retValue + ", " + contents[i];
			}
		
		return retValue;
	}
}

	   /**
		 * Return the number of occurrences of theValue in the bag.
		 * @param boundedBag converted to string using bag.toString
		 * @returns number of occurrences of theValue in bag
		 **/
		public int countOccurrences( int theValue ) {
			int occurrences = 0;
			for(int index=0; index<=this.topPosition; index++){
				if (this.contents[index] == theValue){
					occurrences++;
				}
			}
			
			return occurrences;
		}


		/**
		 * Compares theObject with <CODE>this</CODE> object for 
		 * equivalence. Do not concern yourself with runtime
		 * efficiency.
		 * @param theObject to be compared with this object
		 * @return true iff theObject is equivalent to this
		 * object.
		 */
		@Override public  
		boolean equals( Object theObject ){
			if (theObject == null) return false;
			if(!(theObject instanceof BoundedBag)) return false;
			if (this == theObject) return true;
			BoundedBag obj = (BoundedBag) theObject;
			
			//check if sizes are the same
			if( this.size() != obj.size() ) return false;
			
			//Count occurrences of each integer in <CODE>this</CODE> and compares to theObject
			for(int index=0; index<this.topPosition; index++){
				if (this.countOccurrences(this.contents[index]) != obj.countOccurrences(this.contents[index])){
					return false;
				}
			}
			
			//Count the cumulative total in this and theObject
			int objtotal=0;
			int thistotal=0;
			for(int index=this.topPosition; index>=0; index--){
				objtotal += obj.contents[index];
				thistotal += this.contents[index];
			}
			if (thistotal != objtotal)return false;
		return true;		
		}
			

		// You will get a compiler complaint if this is left out.
		// Note - this is the world's worst hashCode function.
		// See Eff. Java item 9 for writing a good hash function.
		@Override public int hashCode( ) { return 1; }

		


}
