
public class CompareWins implements CompareElements {
	
	// @returns
	   //   < 0 if first comes before second
	   //   0 no preference in relative position
	   //   > 1 if second comes before first
	  public int compare(AccFootballTeam first, AccFootballTeam second){
		   return (first.getWon() - second.getWon());

	  }
}
