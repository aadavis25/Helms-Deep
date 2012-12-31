public class Bag extends BoundedBag{
	
	@Override
	public boolean add ( int theValue ){
	 
		if ( !(this.size() == contents.length) ){  //if the bag isn't full, just add
			super.add(theValue);
			return true;
		}
		else{
			BoundedBagIterator bagiterator2 = this.iterator();
			int[] newcontents = new int [contents.length * 2];
			
			int count = 0;							//iterate and add all the elements in contents to newcontents 
				while(true){							
					newcontents[count++] = bagiterator2.next();
				 	if( !bagiterator2.hasNext())
				 		break;
			}
			 
			contents = newcontents;
			super.add(theValue);
			return true;
		}
		}
}