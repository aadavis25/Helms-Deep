


public class CompareSize implements CompareFiles{

	public CompareSize(){
		
	}
	
	public int compare(Files file1, Files file2){
		if (file1.getSize() < file2.getSize())
			return -1;
		else if (file1.getSize() > file2.getSize())
			return 2;
		return 0;
	}
}
