import java.util.Iterator;
public class StringPermutationIterator implements Iterator<String>{
	String buffer;
	int index;
	int length;
	StringPermutationIterator newSPI;
	
	public StringPermutationIterator(String s){
		buffer = s.toLowerCase();
		index=0;
		length = buffer.length();
		if (buffer.length() > 1)
			newSPI = new StringPermutationIterator(buffer.substring(1));
		else
			newSPI = null;
	}

	public boolean hasNext() {
		return index < length;
	}

	public String next() {
		return createNextElement();
	}
	
	public String createNextElement(){
		String result;	
		if (length<=1){
				index++;
				result = buffer;
		}
			
		else{
			result = buffer.charAt(index) + newSPI.next();
			if (!newSPI.hasNext()){ 
				index++;
				if(index < length){
					String rest = nextSubString(index);
					newSPI = new StringPermutationIterator(rest);
				}
			}
		}
		return result;
	}
	
	private String nextSubString(int index){
		return buffer.substring(0, index) + buffer.substring(index + 1);
	}
	
	public void remove(){
		return;
	}
}


	
