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
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import dataStructures.AVLNode;
import thread.AddPlayerThread;
import thread.DeletePlayerThread;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FIBA {

	// -----------------------------------------------------------------
	// Constants
    // -----------------------------------------------------------------

	public static final int NUMBER_OF_STATISTICS = 7;
	public static final String FILE_NAME = "data/players.csv";

	// -----------------------------------------------------------------
	// Attributes
    // -----------------------------------------------------------------

	private ArrayList<String[]> allData;
	private ArrayList<Double> playersByBlocks;
	private ArrayList<ArrayList<String>> currentPlayers;
	private double progress;
	private long timeTaken;

	// -----------------------------------------------------------------
	// Relations
    // -----------------------------------------------------------------

	private AVLTree<Double, ArrayList<Integer>, Integer> playersByTrueShooting;
	private AVLTree<Double, ArrayList<Integer>, Integer> playersByUsage;
	private AVLTree<Double, ArrayList<Integer>, Integer> playersByAssist;
	private BSTree<Double, ArrayList<Integer>,Integer> playersByRebound;
	private AVLTree<Double, ArrayList<Integer>,Integer> playersByDefensive;

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
		playersByRebound = new BSTree<Double, ArrayList<Integer>, Integer>();
		playersByDefensive = new AVLTree<Double, ArrayList<Integer>, Integer>();
		playersByBlocks = new ArrayList<Double>();
		allData = new ArrayList<String[]>();
		progress = 0;
		timeTaken = 0;
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}

    public long getTimeTaken() {
        return timeTaken;
    }

	public ArrayList<ArrayList<String>> getCurrentPlayers() {
		return currentPlayers;
	}


	public void setCurrentPlayers(ArrayList<ArrayList<String>> currentPlayers) {
		this.currentPlayers = currentPlayers;
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
    public AVLTree<Double, ArrayList<Integer>,Integer> getPlayersByDefensive() {
        return playersByDefensive;
    }

    /**
     * @param playersByDefensive the playersByDefensive to set
     */
    public void setPlayersByDefensive(AVLTree<Double, ArrayList<Integer>,Integer> playersByDefensive) {
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

	

	public boolean addPlayerDataByTextFile(File file) throws IOException, CsvException, InterruptedException {
		FileWriter fw;
		if(allData.size() == 0){
			fw = new FileWriter(FILE_NAME);
		}else{
			fw = new FileWriter(FILE_NAME, true);
		}
		CSVWriter csvWriter = new CSVWriter(fw);
		FileReader fr = new FileReader(file);
		CSVReader csvReader = new CSVReaderBuilder(fr).withSkipLines(1).build();
		if (allData.size() == 0) {
			allData = new ArrayList<>((LinkedList<String[]>) csvReader.readAll());
			String[] temp = { "firstName", "lastName", "team", "age", "trueShooting", "usage", "assist", "rebound", "defensive", "blocks" };
			csvWriter.writeNext(temp);
			for (int i = 0; i < allData.size(); i++) {
				progress = (i + 1) / (double) allData.size();
				csvWriter.writeNext(allData.get(i));
				AddPlayerThread[] trees = new AddPlayerThread[NUMBER_OF_STATISTICS];
				for (int j = 0; j < trees.length; j++) {
					trees[j] = new AddPlayerThread(this, allData.get(i), j, i);
					trees[j].start();
				}
				for (int j = 0; j < trees.length; j++)
					trees[j].join();
			}
		} else {
			ArrayList<String[]> newData = new ArrayList<>((LinkedList<String[]>) csvReader.readAll());
			int begin = allData.size();
			privateAddPlayerDataByTextFile(csvWriter, newData, begin);
		}
		csvWriter.close();
		return true;
	}

	private void privateAddPlayerDataByTextFile(CSVWriter writer, ArrayList<String[]> newData, int begin) throws InterruptedException {
		for (int i = 0; i < newData.size(); i++) {
			progress = (i + 1) / (double) newData.size();
			writer.writeNext(newData.get(i));
			allData.add(newData.get(i));
			AddPlayerThread[] trees = new AddPlayerThread[NUMBER_OF_STATISTICS];
			for (int j = 0; j < trees.length; j++) {
				trees[j] = new AddPlayerThread(this, newData.get(i), j, i + begin);
				trees[j].start();
			}
			for (int j = 0; j < trees.length; j++)
				trees[j].join();
		}
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
	public void addPlayerDataByPlatform(String name, String lastName, String age, String team, String trueShooting, String usage, String assist, String rebound, String defensive, String blocks) throws InterruptedException, IOException, CsvException {
		File dataFile = new File(FILE_NAME);
		FileWriter fw;
		if(allData.size()==0){
			fw = new FileWriter(FILE_NAME);
		}else{
			fw = new FileWriter(FILE_NAME, true);
		}
		
		CSVWriter csvWriter = new CSVWriter(fw);
		if (dataFile.length() == 0) {
			String[] temp = { "firstName", "lastName", "team", "age", "trueShooting", "usage", "assist", "rebound", "defensive", "blocks" };
			csvWriter.writeNext(temp);
		}
		String[] info = new String[10];
		info[0] = name;
		info[1] = lastName;
		info[2] = team;
		info[3] = age;
		info[4] = trueShooting;
		info[5] = usage;
		info[6] = assist;
		info[7] = rebound;
		info[8] = defensive;
		info[9] = blocks;
		csvWriter.writeNext(info);
		AddPlayerThread[] trees = new AddPlayerThread[NUMBER_OF_STATISTICS];
		for (int i = 0; i < trees.length; i++) {
			trees[i] = new AddPlayerThread(this, info, i, allData.size());
			trees[i].start();
		}
		for (int i = 0; i < trees.length; i++) {
			trees[i].join();
		}
		csvWriter.close();
		FileReader fr = new FileReader(FILE_NAME);
		CSVReader csvReader = new CSVReaderBuilder(fr).withSkipLines(1).build();
		allData = new ArrayList<>((LinkedList<String[]>) csvReader.readAll());
		csvReader.close();
	}

	/**
	 *
	 * @param attribute
	 * @param valueD
	 * @param valueS
	 * @throws IOException
	*/
	public boolean modifyPlayerData(String attribute,String valueS, int player) throws IOException {
		if(allData.get(player)==null){
			return false;
		}
		Integer position = Integer.parseInt(currentPlayers.get(player).get(currentPlayers.get(player).size()-1));
		switch (attribute) {
			case "Name":
				allData.get(position)[0] = valueS;
				currentPlayers.get(player).set(0,allData.get(position)[0]);
				break;
			case "Last Name":
				allData.get(position)[1] = valueS;
				currentPlayers.get(player).set(1,allData.get(position)[1]);
				break;
            case "Team":
				allData.get(position)[2] = valueS;
				currentPlayers.get(player).set(2,allData.get(position)[2]);
				break;
			case "Age":
				allData.get(position)[3] = valueS;
				currentPlayers.get(player).set(3,allData.get(position)[3]);
				break;
			case "True Shooting":
				allData.get(position)[4] = valueS;
				currentPlayers.get(player).set(4,allData.get(position)[4]);
				break;
			case "Usage":
				allData.get(position)[5] = valueS;
				currentPlayers.get(player).set(5,allData.get(position)[5]);
				break;
			case "Assist":
				allData.get(position)[6] = valueS;
				currentPlayers.get(player).set(6,allData.get(position)[6]);
				break;
			case "Rebound":
				allData.get(position)[7]=valueS;
				currentPlayers.get(player).set(7,allData.get(position)[7]);
				break;
			case "Defensive":
				allData.get(position)[8] = valueS;
				currentPlayers.get(player).set(8,allData.get(position)[8]);
				break;
			case "Blocks":
				allData.get(position)[9] = valueS;
				currentPlayers.get(player).set(9,allData.get(position)[9]);
				break;
		}
		FileWriter fw = new FileWriter(FILE_NAME);
		CSVWriter csvWriter= new CSVWriter(fw);
		ArrayList<String[]> allData2 = new ArrayList<>(allData);
		String[] temp = { "firstName", "lastName", "team", "age", "trueShooting", "usage", "assist", "rebound", "defensive", "blocks" };
		allData2.add(0, temp);
		csvWriter.writeAll(allData2);
		csvWriter.close();
		return true;
	}

	/**
	 *
	 * @param id
	 * @throws InterruptedException
	 * @throws IOException
	*/
	public boolean deletePlayer(ArrayList<ArrayList<String>> players, int toErase) throws InterruptedException, IOException {
		ArrayList<String> player = players.get(toErase);
		if (player == null) {
			return false;
		}
		BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
		BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));
		players.set(toErase, null);
		Integer position = Integer.parseInt(player.get(player.size() - 1));
		DeletePlayerThread[] trees = new DeletePlayerThread[NUMBER_OF_STATISTICS];
		for (int i = 0; i < trees.length; i++) {
			trees[i] = new DeletePlayerThread(this, position, i);
			trees[i].start();
		}
		for (int i = 0; i < trees.length; i++) {
			trees[i].join();
		}
		allData.set(position, null);
		String[] temp1 = { "firstName", "lastName", "team", "age", "trueShooting", "usage", "assist", "rebound", "defensive", "blocks" };
		bw.write(convertToLine(temp1, ','));
		for (int i = 0; i <= position; i++) {
			if (i == position)
				bw.write("\n");
			else {
				String line = convertToLine(allData.get(i), ',');
				bw.write(line);
			}
		}
		for (int i = position + 1; i < allData.size(); i++) {
			String line = convertToLine(allData.get(i), ',');
			bw.write(line);
		}
		br.close();
		bw.close();
		return true;
	}

	private String convertToLine(String[] data, char separator) {
		String info = "";
		if (data != null) {
			for (int i = 0; i < data.length; i++)
				info += "\"" + data[i] + "\"" + separator;
		}
		info += "\n";
		return info;
	}

	/**
	 *
	 * @param attribute
	 * @param value
	*/
	public ArrayList<ArrayList<String>> searchPlayerIn(char symbol, String statistic, double value) {
		ArrayList<ArrayList<String>> players = new ArrayList<ArrayList<String>>();
		switch (statistic) {
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
		}
		return players;
	}

	private void searchWith(char symbol, AVLTree<Double, ArrayList<Integer>,Integer> tree, ArrayList<ArrayList<String>> players, double value) {
		ArrayList<AVLNode<Double, ArrayList<Integer>>> nodes = new ArrayList<>();
		int size;
		long begin = System.currentTimeMillis();
		long end = 0;
		switch (symbol) {
			case '=':
				AVLNode<Double, ArrayList<Integer>> node;
				node = tree.search(value);
				end =System.currentTimeMillis();
				if (node != null)
					addPlayers(players, node);
				break;
			case '>':
				nodes = tree.searchMajor(value);
				end =System.currentTimeMillis();
				size = nodes.size();
				if (size != 0) {
					for (int i = 0; i < size; i++)
						addPlayers(players, nodes.get(i));
				}
				break;
			case '<':
				nodes = tree.searchMinor(value);
				end =System.currentTimeMillis();
				size = nodes.size();
				if (size != 0) {
					for (int i = 0; i < size; i++)
						addPlayers(players, nodes.get(i));
				}
				break;
			case '≥':
				nodes = tree.searchMajorEqual(value);
				end =System.currentTimeMillis();
				size = nodes.size();
				if (size != 0) {
					for (int i = 0; i < size; i++)
						addPlayers(players, nodes.get(i));
				}
				break;
			case '≤':
				nodes = tree.searchMinorEqual(value);
				end =System.currentTimeMillis();
				size = nodes.size();
				if (size != 0) {
					for (int i = 0; i < size; i++)
						addPlayers(players, nodes.get(i));
				}
				break;
		}
		timeTaken = end-begin;
	}

	private void addPlayers(ArrayList<ArrayList<String>> players, AVLNode<Double, ArrayList<Integer>> node) {
		ArrayList<Integer> positionsPlayers = node.getValue();
		for (int i = 0; i < positionsPlayers.size(); i++) {
			int index = positionsPlayers.get(i);
			String[] temp = allData.get(index);
			ArrayList<String> player = new ArrayList<String>();
			Collections.addAll(player, temp);
			player.add(String.valueOf(index));
			players.add(player);
		}
	}

	private void searchWith(char symbol, BSTree<Double, ArrayList<Integer>,Integer> tree, ArrayList<ArrayList<String>> players, double value) {
		ArrayList<BSTNode<Double, ArrayList<Integer>>> nodes = new ArrayList<>();
		int size;
		long begin = System.currentTimeMillis();
		long end = 0;
		switch (symbol) {
			case '=':
				BSTNode<Double, ArrayList<Integer>> node;
				node = tree.search(value);
				end =System.currentTimeMillis();
				if (node != null)
					addPlayers(players, node);
				break;
			case '>':
				nodes = tree.searchMajor(value);
				end =System.currentTimeMillis();
				size = nodes.size();
				if (size != 0) {
					for (int i = 0; i < size; i++)
						addPlayers(players, nodes.get(i));
				}
				break;
			case '<':
				nodes = tree.searchMinor(value);
				end =System.currentTimeMillis();
				size = nodes.size();
				if (size != 0) {
					for (int i = 0; i < size; i++)
						addPlayers(players, nodes.get(i));
				}
				break;
			case '≥':
				nodes = tree.searchMajorEqual(value);
				end =System.currentTimeMillis();
				size = nodes.size();
				if (size != 0) {
					for (int i = 0; i < size; i++)
						addPlayers(players, nodes.get(i));
				}
				break;
			case '≤':
				nodes = tree.searchMinorEqual(value);
				end =System.currentTimeMillis();
				size = nodes.size();
				if (size != 0) {
					for (int i = 0; i < size; i++)
						addPlayers(players, nodes.get(i));
				}
				break;
		}
		timeTaken = end-begin;
	}

	private void addPlayers(ArrayList<ArrayList<String>> players, BSTNode<Double, ArrayList<Integer>> node) {
		ArrayList<Integer> positionsPlayers = node.getValue();
		for (int i = 0; i < positionsPlayers.size(); i++) {
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
		long begin = System.currentTimeMillis();
		long end = 0;
		switch (statistic) {
			case "True Shooting":
				searchWith(symbol1, playersByTrueShooting, players, value1);
				searchWith(symbol2, playersByTrueShooting, playersTemp, value2);
				end =System.currentTimeMillis();
				players.retainAll(playersTemp);
				break;
			case "Usage":
				searchWith(symbol1, playersByUsage, players, value1);
				searchWith(symbol2, playersByUsage, playersTemp, value2);
				end =System.currentTimeMillis();
				players.retainAll(playersTemp);
				break;
			case "Assist":
				searchWith(symbol1, playersByAssist, players, value1);
				searchWith(symbol2, playersByAssist, playersTemp, value2);
				end =System.currentTimeMillis();
				players.retainAll(playersTemp);
				break;
			case "Rebound":
				searchWith(symbol1, playersByRebound, players, value1);
				searchWith(symbol2, playersByRebound, playersTemp, value2);
				end =System.currentTimeMillis();
				players.retainAll(playersTemp);
				break;
			case "Defensive":
				searchWith(symbol1, playersByDefensive, players, value1);
				searchWith(symbol2, playersByDefensive, playersTemp, value2);
				end =System.currentTimeMillis();
				players.retainAll(playersTemp);
				break;
			case "Blocks":
				searchWith(symbol1, playersByBlocks, players, value1);
				searchWith(symbol2, playersByBlocks, playersTemp, value2);
				end =System.currentTimeMillis();
				players.retainAll(playersTemp);
				break;
		}
		timeTaken = end-begin;
		return players;
	}

	private void searchWith(char symbol, ArrayList<Double> tree, ArrayList<ArrayList<String>> players, double value) {
		long begin = System.currentTimeMillis();
		long end = 0;
		switch (symbol) {
			case '=':
				for (int i = 0; i < tree.size(); i++) {
					double key = tree.get(i);
					if (key == value)
						addPlayer(players, i);
				}
				end =System.currentTimeMillis();
				break;
			case '>':
				searchMajorThanInBlocks(tree, players, value);
				end =System.currentTimeMillis();
				break;
			case '<':
				searchMinorThanInBlocks(tree, players, value);
				end =System.currentTimeMillis();
				break;
			case '≥':
				searchMajorEqualThanInBlocks(tree, players, value);
				end =System.currentTimeMillis();
				break;
			case '≤':
				searchMinorEqualThanInBlocks(tree, players, value);
				end =System.currentTimeMillis();
				break;
		}
		timeTaken = end-begin;
	}

	private void searchMajorThanInBlocks(ArrayList<Double> tree, ArrayList<ArrayList<String>> players, double value) {
		for (int i = 0; i < tree.size(); i++) {
			if(tree.get(i) != null){
				double key = tree.get(i);
				if (key > value)
					addPlayer(players, i);
			}
		}
	}

	private void searchMajorEqualThanInBlocks(ArrayList<Double> tree, ArrayList<ArrayList<String>> players, double value) {
		for (int i = 0; i < tree.size(); i++) {
			if(tree.get(i) != null){
				double key = tree.get(i);
				if (key >= value)
					addPlayer(players, i);
			}
		}
	}

	private void searchMinorThanInBlocks(ArrayList<Double> tree, ArrayList<ArrayList<String>> players, double value) {
		for (int i = 0; i < tree.size(); i++) {
			if (tree.get(i) != null) {
				double key = tree.get(i);
				if (key < value)
					addPlayer(players, i);
			}
		}
	}

	private void searchMinorEqualThanInBlocks(ArrayList<Double> tree, ArrayList<ArrayList<String>> players, double value) {
		for (int i = 0; i < tree.size(); i++) {
			if (tree.get(i) != null) {
				double key = tree.get(i);
				if (key <= value)
					addPlayer(players, i);
			}
		}
	}

	private void addPlayer(ArrayList<ArrayList<String>> players, int index) {
		String[] temp = allData.get(index);
		ArrayList<String> player = new ArrayList<String>();
		Collections.addAll(player, temp);
		player.add(String.valueOf(index));
		players.add(player);
	}
}