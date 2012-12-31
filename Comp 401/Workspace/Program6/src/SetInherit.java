
public class SetInherit extends BoundedBag implements ISet{
	public SetInherit( ){
		super();
	}
	@Override
	public boolean add ( int theValue ){
		if (this.contains(theValue)){
			return false;
		}
		else{ 
			this.contents[this.topPosition+1]=theValue; 
			this.topPosition = this.topPosition + 1;
			return true;
		}
	}
}
