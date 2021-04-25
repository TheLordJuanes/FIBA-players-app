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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import dataStructures.AVLNode;
import thread.AddPlayerThread;
import thread.DeletePlayerThread;
import java.io.File;
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

	private AVLTree<Double, ArrayList<Integer>, Integer> playersByTrueShooting;
	private AVLTree<Double, ArrayList<Integer>, Integer> playersByUsage;
	private AVLTree<Double, ArrayList<Integer>, Integer> playersByAssist;
	private BSTree<Double, ArrayList<Integer>,Integer> playersByRebound;
	private ArrayList<Double> playersByBlocks;
	private RBTree<Double, ArrayList<Integer>,Integer> playersByDefensive;
	private ArrayList<String[]> allData;

	// -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

	/**
	 * Name: FIBA <br>
	 * <br> FIBA constructor method. <br>
	*/
	public FIBA() {
		playersByTrueShooting = new AVLTree<Double, ArrayList<Integer>, Integer>();
		playersByUsage = new AVLTree<Double, ArrayList<Integer>, Integer>();
		playersByAssist = new AVLTree<Double, ArrayList<Integer>, Integer>();
		playersByRebound = new BSTree<Double, ArrayList<Integer>,Integer>();
		playersByDefensive = new RBTree<Double, ArrayList<Integer>,Integer>();
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
			int number=i+1; //QUITARRR
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
	public void addPlayerDatabyPlatform(String name, String lastName,String team, double trueShooting, double usage, double assist, double rebound, double defensive, double blocks) throws InterruptedException, IOException, CsvException {
		FileWriter fw = new FileWriter(FILE_NAME);
		CSVWriter csvwriter= new CSVWriter(fw);
		String[] info = new String[8];
		info[0]=name;
		info[1]=lastName;
		info[2]=team;
		info[3] = String.valueOf(trueShooting);
		info[4]= String.valueOf(usage);
		info[5] = String.valueOf(assist);
		info[6] = String.valueOf(rebound);
		info[7] = String.valueOf(defensive);
		info[8] = String.valueOf(blocks);
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

	public boolean deletePlayer(ArrayList<ArrayList<String>> players,int toErase) throws InterruptedException {
		Integer position = Integer.parseInt(players.get(toErase).get(players.size()-1));
		DeletePlayerThread[] trees = new DeletePlayerThread[NUMBER_OF_STATISTICS];
		for (int i = 0; i < trees.length; i++) {
			trees[i] = new DeletePlayerThread(this, position, i);
			trees[i].start();
		}
		for (int i = 0; i < trees.length; i++){
			trees[i].join();
		}
		return true;
	}
	//""

	/**
	 *
	 * @param attribute
	 * @param value
	*/
	public ArrayList<ArrayList<String>> searchPlayerIn(char symbol, String statistic, double value) {
		ArrayList<ArrayList<String>> players = new ArrayList<ArrayList<String>>();
		switch(statistic){
			case "True Shooting":
				searchWith(symbol, playersByTrueShooting, players, value);
				break;
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
				searchWith(symbol, playersByBlocks, players, value);
				break;
			default:
				break;
		}
		return players;
	}

	private void searchWith(char symbol, AVLTree<Double, ArrayList<Integer>,Integer> tree, ArrayList<ArrayList<String>> players, double value){
		ArrayList<AVLNode<Double, ArrayList<Integer>>> nodes = new ArrayList<>();
		int size;
		switch(symbol){
			case '=':
				AVLNode<Double, ArrayList<Integer>> node;
				node = tree.search(value);
				if(node!=null){
					addPlayers(players, node);
				}
				break;
			case '>':
				nodes = tree.searchMajor(value);
				size = nodes.size();
				if(size!=0){
					for(int i=0; i<size; i++){
						addPlayers(players, nodes.get(i));
					}
				}
				break;
			case '<':
				nodes = tree.searchMinor(value);
				size = nodes.size();
				if(size!=0){
					for(int i=0; i<size; i++){
						addPlayers(players, nodes.get(i));
					}
				}
				break;
			case '≥':
				nodes = tree.searchMajorEqual(value);
				size = nodes.size();
				if(size!=0){
					for(int i=0; i<size; i++){
						addPlayers(players, nodes.get(i));
					}
				}
				break;
			case '≤':
				nodes = tree.searchMinorEqual(value);
				size = nodes.size();
				if(size!=0){
					for(int i=0; i<size; i++){
						addPlayers(players, nodes.get(i));
					}
				}
				break;
		}
	}

	private void addPlayers(ArrayList<ArrayList<String>> players, AVLNode<Double, ArrayList<Integer>> node){
		ArrayList<Integer> positionsPlayers = node.getValue();
		for(int i=0; i<positionsPlayers.size();i++){
			int index = positionsPlayers.get(i);
			String[] temp = allData.get(index);
			ArrayList<String> player = new ArrayList<String>();
			Collections.addAll(player, temp);
			player.add(String.valueOf(index));
			players.add(player);
		}
	}

	private void searchWith(char symbol, BSTree<Double, ArrayList<Integer>,Integer> tree, ArrayList<ArrayList<String>> players, double value){
		ArrayList<BSTNode<Double, ArrayList<Integer>>> nodes = new ArrayList<>();
		int size;
		switch(symbol){
			case '=':
				BSTNode<Double, ArrayList<Integer>> node;
				node = tree.search(value);
				if(node!=null){
					addPlayers(players, node);
				}
				break;
			case '>':
				nodes = tree.searchMajor(value);
				size = nodes.size();
				if(size!=0){
					for(int i=0; i<size; i++){
						addPlayers(players, nodes.get(i));
					}
				}
				break;
			case '<':
				nodes = tree.searchMinor(value);
				size = nodes.size();
				if(size!=0){
					for(int i=0; i<size; i++){
						addPlayers(players, nodes.get(i));
					}
				}
				break;
			case '≥':
				nodes = tree.searchMajorEqual(value);
				size = nodes.size();
				if(size!=0){
					for(int i=0; i<size; i++){
						addPlayers(players, nodes.get(i));
					}
				}
				break;
			case '≤':
				nodes = tree.searchMinorEqual(value);
				size = nodes.size();
				if(size!=0){
					for(int i=0; i<size; i++){
						addPlayers(players, nodes.get(i));
					}
				}
				break;
		}
	}

	private void addPlayers(ArrayList<ArrayList<String>> players, BSTNode<Double, ArrayList<Integer>> node){
		ArrayList<Integer> positionsPlayers = node.getValue();
		for(int i=0; i<positionsPlayers.size();i++){
			int index = positionsPlayers.get(i);
			String[] temp = allData.get(index);
			ArrayList<String> player = new ArrayList<String>();
			Collections.addAll(player, temp);
			player.add(String.valueOf(index));
			players.add(player);
		}
	}

	private void searchWith(char symbol, RBTree<Double, ArrayList<Integer>,Integer> tree, ArrayList<ArrayList<String>> players, double value){
		ArrayList<RBNode<Double, ArrayList<Integer>>> nodes = new ArrayList<>();
		int size;
		switch(symbol){
			case '=':
				RBNode<Double, ArrayList<Integer>> node;
				node = tree.search(value);
				if(node!=null){
					addPlayers(players, node);
				}
				break;
			case '>':
				nodes = tree.searchMajor(value);
				size = nodes.size();
				if(size!=0){
					for(int i=0; i<size; i++){
						addPlayers(players, nodes.get(i));
					}
				}
				break;
			case '<':
				nodes = tree.searchMinor(value);
				size = nodes.size();
				if(size!=0){
					for(int i=0; i<size; i++){
						addPlayers(players, nodes.get(i));
					}
				}
				break;
			case '≥':
				nodes = tree.searchMajorEqual(value);
				size = nodes.size();
				if(size!=0){
					for(int i=0; i<size; i++){
						addPlayers(players, nodes.get(i));
					}
				}
				break;
			case '≤':
				nodes = tree.searchMinorEqual(value);
				size = nodes.size();
				if(size!=0){
					for(int i=0; i<size; i++){
						addPlayers(players, nodes.get(i));
					}
				}
				break;
		}

	}

	private void addPlayers(ArrayList<ArrayList<String>> players, RBNode<Double, ArrayList<Integer>> node){
		ArrayList<Integer> positionsPlayers = node.getValue();
		for(int i=0; i<positionsPlayers.size();i++){
			int index = positionsPlayers.get(i);
			String[] temp = allData.get(index);
			ArrayList<String> player = new ArrayList<String>();
			Collections.addAll(player, temp);
			player.add(String.valueOf(index));
			players.add(player);
		}
	}

	public ArrayList<ArrayList<String>> searchPlayer(char symbol1, char symbol2, String statistic, double value1, double value2) {
		ArrayList<ArrayList<String>> players = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> playersTemp = new ArrayList<ArrayList<String>>();
		switch(statistic){
			case "True Shooting":
				searchWith(symbol1, playersByTrueShooting, players, value1);
				searchWith(symbol2, playersByTrueShooting, playersTemp, value2);
				players.retainAll(playersTemp);
				break;
			case "Usage":
				searchWith(symbol1, playersByUsage, players, value1);
				searchWith(symbol2, playersByUsage, playersTemp, value2);
				players.retainAll(playersTemp);
				break;
			case "Assist":
				searchWith(symbol1, playersByAssist, players, value1);
				searchWith(symbol2, playersByAssist, playersTemp, value2);
				players.retainAll(playersTemp);
				break;
			case "Rebound":
				searchWith(symbol1, playersByRebound, players, value1);
				searchWith(symbol2, playersByRebound, playersTemp, value2);
				players.retainAll(playersTemp);
				break;
			case "Defensive":
				searchWith(symbol1, playersByDefensive, players, value1);
				searchWith(symbol2, playersByDefensive, playersTemp, value2);
				players.retainAll(playersTemp);
				break;
			case "Blocks":
				searchWith(symbol1, playersByBlocks, players, value1);
				searchWith(symbol2, playersByBlocks, playersTemp, value2);
				players.retainAll(playersTemp);
				break;
			default:
				break;
		}
		return players;
	}

	private void searchWith(char symbol, ArrayList<Double> tree, ArrayList<ArrayList<String>> players, double value){
		switch(symbol){
			case '=':
				for(int i=0; i<tree.size(); i++){
					double key= tree.get(i);
					if(key==value){
						addPlayer(players, i);
					}
				}	
				break;
			case '>':
				searchMajorThanInBlocks(tree, players, value);
				break;
			case '<':
				searchMinorThanInBlocks(tree, players, value);
				break;
			case '≥':
				searchMajorEqualThanInBlocks(tree, players, value);
				break;
			case '≤':
				searchMinorEqualThanInBlocks(tree, players, value);
				break;
		}
	}

	private void searchMajorThanInBlocks(ArrayList<Double> tree, ArrayList<ArrayList<String>> players, double value){
		for(int i=0; i<tree.size(); i++){
			double key= tree.get(i);
			if(key>value){
				addPlayer(players, i);
			}
		}
	}

	private void searchMajorEqualThanInBlocks(ArrayList<Double> tree, ArrayList<ArrayList<String>> players, double value){
		for(int i=0; i<tree.size(); i++){
			double key= tree.get(i);
			if(key>value){
				addPlayer(players, i);
			}
		}
	}

	private void searchMinorThanInBlocks(ArrayList<Double> tree, ArrayList<ArrayList<String>> players, double value){
		for(int i=0; i<tree.size(); i++){
			double key= tree.get(i);
			if(key<value){
				addPlayer(players, i);
			}
		}
	}

	private void searchMinorEqualThanInBlocks(ArrayList<Double> tree, ArrayList<ArrayList<String>> players, double value){
		for(int i=0; i<tree.size(); i++){
			double key= tree.get(i);
			if(key<=value){
				addPlayer(players, i);
			}
		}
	}

	private void addPlayer(ArrayList<ArrayList<String>> players, int index){
		String[] temp = allData.get(index);
		ArrayList<String> player = new ArrayList<String>();
		Collections.addAll(player, temp);
		player.add(String.valueOf(index));
		players.add(player);
	}

    /**
     * @return AVLTree<Double, ArrayList<Integer>> return the playersByTrueShooting
     */
    public AVLTree<Double, ArrayList<Integer>, Integer> getPlayersByTrueShooting() {
        return playersByTrueShooting;
    }

    /**
     * @param playersByTrueShooting the playersByTrueShooting to set
     */
    public void setPlayersByTrueShooting(AVLTree<Double, ArrayList<Integer>, Integer> playersByTrueShooting) {
        this.playersByTrueShooting = playersByTrueShooting;
    }

    /**
     * @return AVLTree<Double, ArrayList<Integer>> return the playersByUsage
     */
    public AVLTree<Double, ArrayList<Integer>, Integer> getPlayersByUsage() {
        return playersByUsage;
    }

    /**
     * @param playersByUsage the playersByUsage to set
     */
    public void setPlayersByUsage(AVLTree<Double, ArrayList<Integer>, Integer> playersByUsage) {
        this.playersByUsage = playersByUsage;
    }

    /**
     * @return AVLTree<Double, ArrayList<Integer>> return the playersByAssist
     */
    public AVLTree<Double, ArrayList<Integer>, Integer> getPlayersByAssist() {
        return playersByAssist;
    }

    /**
     * @param playersByAssist the playersByAssist to set
     */
    public void setPlayersByAssist(AVLTree<Double, ArrayList<Integer>, Integer> playersByAssist) {
        this.playersByAssist = playersByAssist;
    }

    /**
     * @return BSTree<Double, ArrayList<Integer>> return the playersByRebound
     */
    public BSTree<Double, ArrayList<Integer>,Integer> getPlayersByRebound() {
        return playersByRebound;
    }

    /**
     * @param playersByRebound the playersByRebound to set
     */
    public void setPlayersByRebound(BSTree<Double, ArrayList<Integer>,Integer> playersByRebound) {
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
    public RBTree<Double, ArrayList<Integer>,Integer> getPlayersByDefensive() {
        return playersByDefensive;
    }

    /**
     * @param playersByDefensive the playersByDefensive to set
     */
    public void setPlayersByDefensive(RBTree<Double, ArrayList<Integer>,Integer> playersByDefensive) {
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