/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: April, 27th 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package model;

public class FIBA {

	// -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

	/**
	 * Name: FIBA <br>
	 * <br> FIBA constructor method. <br>
	*/
	public FIBA() {
	}

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
	public boolean addPlayerData(String name, int id, String team, double trueShooting, double usage, double assist, double rebound, double defensive) {
		return false;
	}

	/**
	 *
	 * @param attribute
	 * @param valueD
	 * @param valueS
	*/
	public boolean modifyPlayerData(int attribute, double valueD, String valueS) {
		return false;
	}

	/**
	 *
	 * @param id
	*/
	public boolean deletePlayer(int id) {
		return false;
	}

	/**
	 *
	 * @param attribute
	 * @param value
	*/
	public String searchPlayer(int attribute, double value) {
		return "";
	}

	/**
	 *
	 * @param attribute
	 * @param value
	*/
	public String searchPlayer(int attribute, String value) {
		return "";
	}

	/**
	 *
	 * @param attribute1
	 * @param attribute2
	 * @param value1
	 * @param value2
	*/
	public String searchPlayer(int attribute1, int attribute2, double value1, double value2) {
		return "";
	}

	/**
	 *
	 * @param attribute1
	 * @param attribute2
	 * @param value1
	 * @param value2
	*/
	public String searchPlayer(int attribute1, int attribute2, double value1, String value2) {
		return "";
	}

	/**
	 *
	 * @param attribute1
	 * @param attribute2
	 * @param value1
	 * @param value2
	*/
	public String searchPlayer(int attribute1, int attribute2, String value1, String value2) {
		return "";
	}
}