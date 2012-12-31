import java.io.File;


public class Files {
	/*
	 * class that keeps all data members of a file in the object 
	 * and methods for getting those data members
	 * 
	 * size()
	 * name()
	 * getSize()
	 * getName()
	 */
	private Long size;
	private String name;
	
	public Files(String filePath){
		File file = new File(filePath);
		size = file.length();
		name = file.getName();
	}
	
	public Long getSize(){
		return size;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		String result = (getName() +" "+ getSize().toString()+" bytes");
		return result;
	}
	
	
}
