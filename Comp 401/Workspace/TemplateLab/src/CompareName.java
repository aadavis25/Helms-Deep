
public class CompareName implements CompareElements{
//@returns
//   < 0 if first comes before second
//   0 no preference in relative position
//   > 1 if second comes before first
public int compare(AccFootballTeam first, AccFootballTeam second){
	if (first.getName().compareTo(second.getName()) <1 )
		return -1;
	else if (first.getName().compareTo(second.getName()) ==0 )
		return 0;
	return 2;
}
}
