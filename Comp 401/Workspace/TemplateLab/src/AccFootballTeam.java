

public class AccFootballTeam {
	private String abbreviation;
	private String name;
	private String division;
	private int won;
	private int lost;
	
	public AccFootballTeam( String theAbbreviation, String theName
			, String theDivision, int theWon, int theLost ){
		abbreviation = theAbbreviation;
		name = theName;
		division = theDivision;
		won = theWon;
		lost = theLost;
	}
	
	public AccFootballTeam( String theAbbreviation, String theName
			, String theDivision){
		abbreviation = theAbbreviation;
		name = theName;
		division = theDivision;
		won = 0;
		lost = 0;
	}

	
	public String toString() {
		String result = abbreviation + "  "+ won + "-" + lost;
		return result;
	}
	
	public void incrementLosses() {
		lost++;
	}
	
	public void incrementWins() {
		won++;
	}
	
	/**
	 * @return the abbreviation
	 */
	public String getAbbreviation() {
		return abbreviation;
	}
	/**
	 * @param abbreviation the abbreviation to set
	 */
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the division
	 */
	public String getDivision() {
		return division;
	}
	/**
	 * @param division the division to set
	 */
	public void setDivision(String division) {
		this.division = division;
	}
	/**
	 * @return the won
	 */
	public int getWon() {
		return won;
	}
	/**
	 * @param won the won to set
	 */
	public void setWon(int won) {
		this.won = won;
	}
	/**
	 * @return the lost
	 */
	public int getLost() {
		return lost;
	}
	/**
	 * @param lost the lost to set
	 */
	public void setLost(int lost) {
		this.lost = lost;
	}
	
	
}