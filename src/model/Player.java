package model;

public class Player {

	// -----------------------------------------------------------------
	// Attributes
    // -----------------------------------------------------------------

	private String name;
	private int id;
	private String team;
	private double trueShooting;
	private double usage;
	private double assist;
	private double rebound;
	private double defensive;

	// -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

	/**
	 * Name: Player <br>
	 * <br> Constructor method of a player.
	 * @param name - player's name - name = String, name != null, name != ""
	 * @param id - player's ID - id = int, id != null, id != 0
	 * @param team - player's team - team = String, team != null, team != ""
	 * @param trueShooting - player's true shooting - trueShooting = double, trueShooting != null
	 * @param usage - player's usage - usage = double, usage != null
	 * @param assist - player's assist - assist = double, assist != null
	 * @param rebound - player's rebound - rebound = double, rebound != null
	 * @param defensive - player's defensive - defensive = double, defensive != null
	*/
	public Player(String name, int id, String team, double trueShooting, double usage, double assist, double rebound, double defensive) {
		this.name = name;
		this.id = id;
		this.team = team;
		this.trueShooting = trueShooting;
		this.usage = usage;
		this.assist = assist;
		this.rebound = rebound;
		this.defensive = defensive;
	}

	public String getName() {
		return this.name;
	}

	/**
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public String getTeam() {
		return this.team;
	}

	/**
	 *
	 * @param team
	 */
	public void setTeam(String team) {
		this.team = team;
	}

	public double getTrueShooting() {
		return this.trueShooting;
	}

	/**
	 *
	 * @param trueShooting
	 */
	public void setTrueShooting(double trueShooting) {
		this.trueShooting = trueShooting;
	}

	public double getUsage() {
		return this.usage;
	}

	/**
	 *
	 * @param usage
	 */
	public void setUsage(double usage) {
		this.usage = usage;
	}

	public double getAssist() {
		return this.assist;
	}

	/**
	 *
	 * @param assist
	 */
	public void setAssist(double assist) {
		this.assist = assist;
	}

	public double getRebound() {
		return this.rebound;
	}

	/**
	 *
	 * @param rebound
	 */
	public void setRebound(double rebound) {
		this.rebound = rebound;
	}

	public double getDefensive() {
		return this.defensive;
	}

	/**
	 *
	 * @param defensive
	 */
	public void setDefensive(double defensive) {
		this.defensive = defensive;
	}

	/**
	 * private String name;
	private int id;
	private String team;
	private double trueShooting;
	private double usage;
	private double assist;
	private double rebound;
	private double defensive;
	 */

	public String toString() {
		String message="Name: "+name+"\n"+
		"Team:"+team+"\n"+
		"True shooting:"+trueShooting+"\n"+
		"Usage:"+usage+"\n"+
		"Assist:"+assist+"\n"+
		"Rebound:"+rebound+"\n"+
		"Defensive:"+defensive+"\n";
		return message;
	}
}