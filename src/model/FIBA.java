/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: April, 27th 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package model;

import dataStructures.AVLTree;
import dataStructures.BSTNode;
import dataStructures.BSTree;
import dataStructures.RBNode;
import dataStructures.RBTree;
import java.util.ArrayList;
import java.util.LinkedList;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import dataStructures.AVLNode;
import thread.AddPlayerThread;
import thread.DeletePlayerThread;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FIBA {

	// -----------------------------------------------------------------
	// Constants
    // -----------------------------------------------------------------

	public static final int NUMBER_OF_STATISTICS = 7;
	public static final String FILE_NAME="data/players.csv";

	// -----------------------------------------------------------------
	// Relations
    // -----------------------------------------------------------------

	private AVLTree<Double, ArrayList<Integer>> playersByTrueShooting;
	private AVLTree<Double, ArrayList<Integer>> playersByUsage;
	private AVLTree<Double, ArrayList<Integer>> playersByAssist;
	private BSTree<Double, ArrayList<Integer>> playersByRebound;
	private ArrayList<Double> playersByBlocks;
	private RBTree<Double, ArrayList<Integer>> playersByDefensive;
	private ArrayList<String[]> allData;


	// -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

	/**
	 * Name: FIBA <br>
	 * <br> FIBA constructor method. <br>
	*/
	public FIBA() {
		playersByTrueShooting = new AVLTree<Double, ArrayList<Integer>>();
		playersByUsage = new AVLTree<Double, ArrayList<Integer>>();
		playersByAssist = new AVLTree<Double, ArrayList<Integer>>();
		playersByRebound = new BSTree<Double, ArrayList<Integer>>();
		playersByDefensive = new RBTree<Double, ArrayList<Integer>>();
		playersByBlocks = new  ArrayList<Double>();
		allData = new ArrayList<>();
	}

	public boolean addPlayerDataByTextFile(File file) throws IOException, CsvException, InterruptedException {
		FileReader fr = new FileReader(file);
		CSVReader csvReader = new CSVReaderBuilder(fr).withSkipLines(1).build();
		allData = new ArrayList<>((LinkedList<String[]>) csvReader.readAll());
		System.out.println(allData.size());
		for(int i=0; i<allData.size(); i++){
			AddPlayerThread[] trees = new AddPlayerThread[NUMBER_OF_STATISTICS];
			for (int j = 0; j < trees.length; j++) {
				trees[j] = new AddPlayerThread(this, allData.get(i), j, i);
				trees[j].start();
			}
			for (int j = 0; j < trees.length; j++) {
				trees[j].join();
			}
			int number=i+1;
			System.out.println("Jugador "+number+" añadido");
		}
		return true;
	}

	/**
	 *
	 * @param name
	 * @param team
	 * @param trueShooting
	 * @param usage
	 * @param assist
	 * @param rebound
	 * @param defensive
	 * @param blocks
	 * @throws InterruptedException
	 * @throws CsvException
	 * @throws IOException
	*/
	public void addPlayerDatabyPlatform(String name, String team, double trueShooting, double usage, double assist, double rebound, double defensive, double blocks) throws InterruptedException, IOException, CsvException {
		FileWriter fw = new FileWriter(FILE_NAME);
		CSVWriter csvwriter= new CSVWriter(fw);
		String[] info = new String[8];
		info[0]=name;
		info[1]=team;
		info[2] = String.valueOf(trueShooting);
		info[3]= String.valueOf(usage);
		info[4] = String.valueOf(assist);
		info[5] = String.valueOf(rebound);
		info[6] = String.valueOf(defensive);
		info[7] = String.valueOf(blocks);
		csvwriter.writeNext(info);
		AddPlayerThread[] trees = new AddPlayerThread[NUMBER_OF_STATISTICS];
		for (int i = 0; i < trees.length; i++) {
			trees[i] = new AddPlayerThread(this, info, i, allData.size());
			trees[i].start();
		}
		for (int i = 0; i < trees.length; i++) {
			trees[i].join();
		}
		FileReader fr = new FileReader(FILE_NAME);
		CSVReader csvReader = new CSVReaderBuilder(fr).withSkipLines(1).build();
		allData = new ArrayList<>((LinkedList<String[]>) csvReader.readAll());
		csvwriter.close();
		csvReader.close();
	}

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
	/**
	public boolean deletePlayer(String id) throws InterruptedException {
		AVLNode<String, ArrayList<Integer>> objSearch = playersById.search(playersById.getRoot(), id);
		if (objSearch != null) {
			ArrayList<Integer> p = objSearch.getValue();
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
	**/
	/**
	 *
	 * @param attribute
	 * @param value
	*/
	public ArrayList<String[]> searchPlayerIn(char symbol, String statistic, double value) {
		ArrayList<String[]> players = new ArrayList<String[]>();
		switch(statistic){
			case "True Shooting":
				searchWith(symbol, playersByTrueShooting, players, value);
			case "Usage":
				searchWith(symbol, playersByUsage, players, value);
				break;
			case "Assist":
				searchWith(symbol, playersByAssist, players, value);
				break;
			case "Rebound":
				searchWith(symbol, playersByRebound, players, value);
				break;
			case "Defensive":
				searchWith(symbol, playersByDefensive, players, value);
				break;
			case "Blocks":
		
				break;
			default:
				break;
		}
		return players;
	}

	private void searchWith(char symbol, AVLTree<Double, ArrayList<Integer>> tree, ArrayList<String[]> players, double value){
		AVLNode<Double, ArrayList<Integer>> node = tree.search(value);
		if(node!=null){
			switch(symbol){
				case '=':
					addPlayers(players, node);
					break;
				case '>':
					getValues(players, node.getRight());
					break;
				case '<':
					getValues(players, node.getLeft());
					break;
				case '≥':
					addPlayers(players, node);
					getValues(players, node.getRight());
					break;
				case '≤':
					addPlayers(players, node);
					getValues(players, node.getLeft());
					break;
			}
		}
	}

	private void getValues(ArrayList<String[]> players, AVLNode<Double, ArrayList<Integer>> node){
		if(node != null){
			getValues(players, node.getLeft());
			addPlayers(players, node);
			getValues(players, node.getRight());
		}
	}

	private void addPlayers(ArrayList<String[]> players, AVLNode<Double, ArrayList<Integer>> node){
		ArrayList<Integer> positionsPlayers = node.getValue();
			for(int i=0; i<positionsPlayers.size();i++){
				players.add(allData.get(positionsPlayers.get(i)));
			}
	}

	private void searchWith(char symbol, BSTree<Double, ArrayList<Integer>> tree, ArrayList<String[]> players, double value){
		BSTNode<Double, ArrayList<Integer>> node = tree.search(value);
		if(node!=null){
			switch(symbol){
				case '=':
					addPlayers(players, node);
					break;
				case '>':
					getValues(players, node.getRight());
					break;
				case '<':
					getValues(players, node.getLeft());
					break;
				case '≥':
					addPlayers(players, node);
					getValues(players, node.getRight());
					break;
				case '≤':
					addPlayers(players, node);
					getValues(players, node.getLeft());
					break;
			}
		}
	}

	private void getValues(ArrayList<String[]> players, BSTNode<Double, ArrayList<Integer>> node){
		if(node != null){
			getValues(players, node.getLeft());
			addPlayers(players, node);
			getValues(players, node.getRight());
		}
	}

	private void addPlayers(ArrayList<String[]> players, BSTNode<Double, ArrayList<Integer>> node){
		ArrayList<Integer> positionsPlayers = node.getValue();
			for(int i=0; i<positionsPlayers.size();i++){
				players.add(allData.get(positionsPlayers.get(i)));
			}
	}

	private void searchWith(char symbol, RBTree<Double, ArrayList<Integer>> tree, ArrayList<String[]> players, double value){
		RBNode<Double, ArrayList<Integer>> node = tree.search(value);
		if(node!=null){
			switch(symbol){
				case '=':
					addPlayers(players, node);
					break;
				case '>':
					getValues(players, node.getRight());
					break;
				case '<':
					getValues(players, node.getLeft());
					break;
				case '≥':
					addPlayers(players, node);
					getValues(players, node.getRight());
					break;
				case '≤':
					addPlayers(players, node);
					getValues(players, node.getLeft());
					break;
			}
		}
	}

	private void getValues(ArrayList<String[]> players, RBNode<Double, ArrayList<Integer>> node){
		if(node != null){
			getValues(players, node.getLeft());
			addPlayers(players, node);
			getValues(players, node.getRight());
		}
	}

	private void addPlayers(ArrayList<String[]> players, RBNode<Double, ArrayList<Integer>> node){
		ArrayList<Integer> positionsPlayers = node.getValue();
			for(int i=0; i<positionsPlayers.size();i++){
				players.add(allData.get(positionsPlayers.get(i)));
			}
	}

	public ArrayList<String[]> searchPlayer(char symbol1, char symbol2, String statistic, double value1, double value2) {
		ArrayList<String[]> players = new ArrayList<String[]>();
		return players;
	}

    /**
     * @return AVLTree<Double, ArrayList<Integer>> return the playersByTrueShooting
     */
    public AVLTree<Double, ArrayList<Integer>> getPlayersByTrueShooting() {
        return playersByTrueShooting;
    }

    /**
     * @param playersByTrueShooting the playersByTrueShooting to set
     */
    public void setPlayersByTrueShooting(AVLTree<Double, ArrayList<Integer>> playersByTrueShooting) {
        this.playersByTrueShooting = playersByTrueShooting;
    }

    /**
     * @return AVLTree<Double, ArrayList<Integer>> return the playersByUsage
     */
    public AVLTree<Double, ArrayList<Integer>> getPlayersByUsage() {
        return playersByUsage;
    }

    /**
     * @param playersByUsage the playersByUsage to set
     */
    public void setPlayersByUsage(AVLTree<Double, ArrayList<Integer>> playersByUsage) {
        this.playersByUsage = playersByUsage;
    }

    /**
     * @return AVLTree<Double, ArrayList<Integer>> return the playersByAssist
     */
    public AVLTree<Double, ArrayList<Integer>> getPlayersByAssist() {
        return playersByAssist;
    }

    /**
     * @param playersByAssist the playersByAssist to set
     */
    public void setPlayersByAssist(AVLTree<Double, ArrayList<Integer>> playersByAssist) {
        this.playersByAssist = playersByAssist;
    }

    /**
     * @return BSTree<Double, ArrayList<Integer>> return the playersByRebound
     */
    public BSTree<Double, ArrayList<Integer>> getPlayersByRebound() {
        return playersByRebound;
    }

    /**
     * @param playersByRebound the playersByRebound to set
     */
    public void setPlayersByRebound(BSTree<Double, ArrayList<Integer>> playersByRebound) {
        this.playersByRebound = playersByRebound;
    }

    /**
     * @return ArrayList<Double> return the playersByBlocks
     */
    public ArrayList<Double> getPlayersByBlocks() {
        return playersByBlocks;
    }

    /**
     * @param playersByBlocks the playersByBlocks to set
     */
    public void setPlayersByBlocks(ArrayList<Double> playersByBlocks) {
        this.playersByBlocks = playersByBlocks;
    }

    /**
     * @return RBTree<Double, ArrayList<Integer>> return the playersByDefensive
     */
    public RBTree<Double, ArrayList<Integer>> getPlayersByDefensive() {
        return playersByDefensive;
    }

    /**
     * @param playersByDefensive the playersByDefensive to set
     */
    public void setPlayersByDefensive(RBTree<Double, ArrayList<Integer>> playersByDefensive) {
        this.playersByDefensive = playersByDefensive;
    }

    /**
     * @return ArrayList<String[]> return the allData
     */
    public ArrayList<String[]> getAllData() {
        return allData;
    }

    /**
     * @param allData the allData to set
     */
    public void setAllData(ArrayList<String[]> allData) {
        this.allData = allData;
    }
}