/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: April, 27th 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package model;

import dataStructures.AVLTree;
import dataStructures.BSTree;
import dataStructures.RBTree;
import dataStructures.AVLNode;
import thread.AddPlayerThread;
import thread.DeletePlayerThread;

public class FIBA {
	
	public static final int NUMBER_OF_STATISTICS = 7;

	// -----------------------------------------------------------------
	// Relations
    // -----------------------------------------------------------------

	private AVLTree<Integer, Player> playersById;
	private AVLTree<Double, Player> playersByTrueShooting;
	private AVLTree<Double, Player> playersByUsage;
	private AVLTree<Double, Player> playersByAssist;
	private BSTree<Double, Player> playersByRebound;
	private BSTree<Double, Player> playersByBlocks;
	private RBTree<Double, Player> playersByDefensive;


	// -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

	/**
	 * Name: FIBA <br>
	 * <br> FIBA constructor method. <br>
	*/
	public FIBA() {
		playersById = new AVLTree<Integer, Player>();
		playersByTrueShooting = new AVLTree<Double, Player>();
		playersByUsage = new AVLTree<Double, Player>();
		playersByAssist = new AVLTree<Double, Player>();
		playersByRebound = new BSTree<Double, Player>();
		playersByDefensive = new RBTree<Double, Player>();
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
	 * @param blocks
	 * @throws InterruptedException
	*/
	public boolean addPlayerData(String name, int id, String team, double trueShooting, double usage, double assist, double rebound, double defensive, double blocks) throws InterruptedException {
		if (playersById.search(playersById.getRoot(), id) == null) {
			Player p = new Player(name, id, team, trueShooting, usage, assist, rebound, defensive, blocks);
			AddPlayerThread[] trees = new AddPlayerThread[NUMBER_OF_STATISTICS];
			for (int i = 0; i < trees.length; i++) {
				trees[i] = new AddPlayerThread(this, p, i);
				trees[i].start();
			}
			for (int i = 0; i < trees.length; i++)
				trees[i].join();
			return true;
		}
		return false;
	}

	/*public <K extends Comparable<K>, V> void addPlayerDataIn(AVLNode<K, V> node, AVLTree<K, V> tree){
		tree.insert(node);
	}

	public <K extends Comparable<K>, V> void addPlayerDataIn(BSTNode<K, V> node, BSTree<K, V> tree){
		tree.insert(node);
	}

	public <K extends Comparable<K>, V> void addPlayerDataIn(RBNode<K, V> node, RBTree<K, V> tree){
		tree.insert(node);
	}*/

	/**
	 *
	 * @param attribute
	 * @param valueD
	 * @param valueS
	*/
	public boolean modifyPlayerData(String attribute, double valueD, String valueS, int valueI, int id) {
		return false;
	}

	/**
	 *
	 * @param id
	 * @throws InterruptedException
	*/
	public boolean deletePlayer(int id) throws InterruptedException {
		AVLNode<Integer, Player> objSearch = playersById.search(playersById.getRoot(), id);
		if (objSearch != null) {
			Player p = objSearch.getValue();
			DeletePlayerThread[] trees = new DeletePlayerThread[NUMBER_OF_STATISTICS];
			for (int i = 0; i < trees.length; i++) {
				trees[i] = new DeletePlayerThread(this, p, i);
				trees[i].start();
			}
			for (int i = 0; i < trees.length; i++)
				trees[i].join();
			return true;
		}
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

    /**
     * @return AVLTree<Integer, Player> return the playersById
     */
    public AVLTree<Integer, Player> getPlayersById() {
        return playersById;
    }

    /**
     * @param playersById the playersById to set
     */
    public void setPlayersById(AVLTree<Integer, Player> playersById) {
        this.playersById = playersById;
    }

    /**
     * @return AVLTree<Double, Player> return the playersByTrueShooting
     */
    public AVLTree<Double, Player> getPlayersByTrueShooting() {
        return playersByTrueShooting;
    }

    /**
     * @param playersByTrueShooting the playersByTrueShooting to set
     */
    public void setPlayersByTrueShooting(AVLTree<Double, Player> playersByTrueShooting) {
        this.playersByTrueShooting = playersByTrueShooting;
    }

    /**
     * @return AVLTree<Double, Player> return the playersByUsage
     */
    public AVLTree<Double, Player> getPlayersByUsage() {
        return playersByUsage;
    }

    /**
     * @param playersByUsage the playersByUsage to set
     */
    public void setPlayersByUsage(AVLTree<Double, Player> playersByUsage) {
        this.playersByUsage = playersByUsage;
    }

    /**
     * @return AVLTree<Double, Player> return the playersByAssist
     */
    public AVLTree<Double, Player> getPlayersByAssist() {
        return playersByAssist;
    }

    /**
     * @param playersByAssist the playersByAssist to set
     */
    public void setPlayersByAssist(AVLTree<Double, Player> playersByAssist) {
        this.playersByAssist = playersByAssist;
    }

    /**
     * @return BSTree<Double, Player> return the playersByRebound
     */
    public BSTree<Double, Player> getPlayersByRebound() {
        return playersByRebound;
    }

    /**
     * @param playersByRebound the playersByRebound to set
     */
    public void setPlayersByRebound(BSTree<Double, Player> playersByRebound) {
        this.playersByRebound = playersByRebound;
    }

    /**
     * @return RBTree<Double, Player> return the playersByDefensive
     */
    public RBTree<Double, Player> getPlayersByDefensive() {
        return playersByDefensive;
    }

    /**
     * @param playersByDefensive the playersByDefensive to set
     */
    public void setPlayersByDefensive(RBTree<Double, Player> playersByDefensive) {
        this.playersByDefensive = playersByDefensive;
    }

	public BSTree<Double, Player> getPlayersByBlocks() {
		return playersByBlocks;
	}

	public void setPlayersByBlocks(BSTree<Double, Player> playersByBlocks) {
		this.playersByBlocks = playersByBlocks;
	}
}