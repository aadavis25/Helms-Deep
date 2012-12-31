
public class SetComposition implements ISet {
	private BoundedBag b;
    public SetComposition( ) {
       b = new BoundedBag( );
    }
   
    @Override
	public boolean add ( int theValue ){
    	if (contains(theValue) || b.topPosition == b.CAPACITY-1){
			return false;
		}
		else{ 
			b.contents[++(b.topPosition)]=theValue; 
			return true;
		}
	}
    
	public boolean contains(int theValue) {
		return b.contains(theValue);
	}
	
	public boolean remove(int theValue) {
		return b.remove(theValue);
	}
	
	public int size() {
		return b.size();
	}
	
	public String toString() {
		return b.toString();
	}
    
 
}
