
public class SpaceFilter implements CharacterFilter {
	
	char dontAccept = ' ';
	
	public boolean accept(char character){
		return (character == dontAccept);
	}

}
