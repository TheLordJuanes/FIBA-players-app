package model;

public class Player {

	private String name;
	private int id;
	private String team;
	private double trueShooting;
	private double usage;
	private double assist;
	private double rebound;
	private double defensive;

	/**
	 * 
	 * @param name
	 * @param id
	 * @param team
	 * @param trueShooting
	 * @param usage
	 * @param assist
	 * @param rebound
	 * @param defensive
	 */
	public Player(String name, int id, String team, double trueShooting, double usage, double assist, double rebound, double defensive) {
		// TODO - implement Player.Player
		throw new UnsupportedOperationException();
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

	public String toString() {
		// TODO - implement Player.toString
		throw new UnsupportedOperationException();
	}

}