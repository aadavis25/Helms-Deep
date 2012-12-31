

public interface CompareFiles {

	   // @returns
	   //   < 0 if first comes before second
	   //   0 first and second are "equal"
	   //   > 1 if second comes before first
	  public int compare(Files file1, Files file2);
	
} 