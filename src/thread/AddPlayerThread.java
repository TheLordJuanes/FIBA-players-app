package thread;

import model.FIBA;
import dataStructures.AVLNode;
import dataStructures.AVLTree;
import dataStructures.BSTree;
import dataStructures.BSTNode;
import dataStructures.RBNode;
import java.util.ArrayList;

public class AddPlayerThread extends Thread {

    // -----------------------------------------------------------------
	// Attributes
    // -----------------------------------------------------------------

    private int typeData;

    // -----------------------------------------------------------------
	// Relations
    // -----------------------------------------------------------------

    private FIBA fiba;
    private String[] player;
    private int index;

    // -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

    public AddPlayerThread(FIBA f, String[] p, int tD, int ind) {
        setDaemon(true);
        fiba = f;
        typeData = tD;
        player = p;
        index=ind;
    }

    @Override
	public void run() {
        //"name,team,trueShooting,usage,assist,rebound,defensive,blocks"
        AVLNode<Double, ArrayList<Integer>> node1;
        BSTNode<Double, ArrayList<Integer>> node2;
        RBNode<Double, ArrayList<Integer>> node3;
        AVLNode<String, ArrayList<Integer>> node4;
        switch (typeData) {
            case 0:
                //Add in playersByTrueShooting
                double trueShooting = Double.parseDouble(player[2]);
                AVLTree<Double, ArrayList<Integer>> playersByTrueShooting = fiba.getPlayersByTrueShooting();
                node1 = playersByTrueShooting.search(playersByTrueShooting.getRoot(), trueShooting);
                if(node1==null){
                    ArrayList<Integer> positions = new ArrayList<>();
                    positions.add(index);
                    node1 = new AVLNode<>(trueShooting, positions);
                    playersByTrueShooting.insert(node1);
                }else{
                    node1.getValue().add(index);
                }
                break;
            case 1:
                //Add in playersByUsage
                double usage = Double.parseDouble(player[3]);
                AVLTree<Double, ArrayList<Integer>> playersByUsage = fiba.getPlayersByUsage();
                node1 = playersByUsage.search(playersByUsage.getRoot(), usage);
                if(node1==null){
                    ArrayList<Integer> positions = new ArrayList<>();
                    positions.add(index);
                    node1 = new AVLNode<>(usage, positions);
                    playersByUsage.insert(node1);
                }else{
                    node1.getValue().add(index);
                }
                break;
            case 2:
                //Add in playersByAssist
                double assist = Double.parseDouble(player[4]);
                AVLTree<Double, ArrayList<Integer>> playersByAssist = fiba.getPlayersByAssist();
                node1 = playersByAssist.search(playersByAssist.getRoot(), assist);
                if(node1==null){
                    ArrayList<Integer> positions = new ArrayList<>();
                    positions.add(index);
                    node1 = new AVLNode<>(assist, positions);
                    playersByAssist.insert(node1);
                }else{
                    node1.getValue().add(index);
                }
                break;
            case 3:
                //5
                //Add in playersByRebound
                double rebound = Double.parseDouble(player[5]);
                BSTree<Double, ArrayList<Integer>> playersByRebound = fiba.getPlayersByRebound();
                node2 = playersByRebound.search(playersByRebound.getRoot(), rebound);
                if(node2==null){
                    ArrayList<Integer> positions = new ArrayList<>();
                    positions.add(index);
                    node2 = new BSTree<Double, ArrayList<Integer>>(rebound, positions);
                    playersByAssist.insert(node2);
                }else{
                    node1.getValue().add(index);
                }




                node2 = new BSTNode<>(player.getRebound(), player);
                fiba.getPlayersByRebound().insert(node2);
                break;
            case 4:
                //Add in playersByDefensive
                node3 = new RBNode<>(player.getDefensive(), player);
                fiba.getPlayersByDefensive().insert(node3);
                break;
            case 5:
                //Add in playersById
                node4 = new AVLNode<String, Player>(player.getId(), player);
                fiba.getPlayersById().insert(node4);
                break;
            case 6:
                //Add in playersByBlocks
                node2 = new BSTNode<>(player.getBlocks(), player);
                fiba.getPlayersByBlocks().insert(node2);
                break;
            default:
                break;
        }
    }
}