


public class CompareName implements CompareFiles{

	public CompareName(){
	
	}
	
	public int compare(Files file1, Files file2){
		if (file1.getName().compareToIgnoreCase(file2.getName())<0)
			return -1;
		else if (file1.getName().compareToIgnoreCase(file2.getName())>0)
			return 2;
		return 0;
	}
}
