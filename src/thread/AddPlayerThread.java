package thread;

import model.FIBA;
import dataStructures.AVLNode;
import dataStructures.AVLTree;
import dataStructures.BSTree;
import dataStructures.BSTNode;
import dataStructures.RBTree;
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
        if(player.length==9){
            //"firstName, lastName, team,trueShooting,usage,assist,rebound,defensive,blocks"
            AVLNode<Double, ArrayList<Integer>> node1;
            BSTNode<Double, ArrayList<Integer>> node2;
            RBNode<Double, ArrayList<Integer>> node3;
            switch (typeData) {
                case 0:
                    //Add in playersByTrueShooting
                    double trueShooting = Double.parseDouble(player[3]);
                    AVLTree<Double, ArrayList<Integer>, Integer> playersByTrueShooting = fiba.getPlayersByTrueShooting();
                    node1 = playersByTrueShooting.search(trueShooting);
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
                    double usage = Double.parseDouble(player[4]);
                    AVLTree<Double, ArrayList<Integer>, Integer> playersByUsage = fiba.getPlayersByUsage();
                    node1 = playersByUsage.search(usage);
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
                    double assist = Double.parseDouble(player[5]);
                    AVLTree<Double, ArrayList<Integer>, Integer> playersByAssist = fiba.getPlayersByAssist();
                    node1 = playersByAssist.search(assist);
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
                    //Add in playersByRebound
                    double rebound = Double.parseDouble(player[6]);
                    BSTree<Double, ArrayList<Integer>,Integer> playersByRebound = fiba.getPlayersByRebound();
                    node2 = playersByRebound.search(rebound);
                    if(node2==null){
                        ArrayList<Integer> positions = new ArrayList<>();
                        positions.add(index);
                        node2 = new BSTNode<>(rebound, positions);
                        playersByRebound.insert(node2);
                    }else{
                        node2.getValue().add(index);
                    }
                    break;
                case 4:
                    //Add in playersByDefensive
                    double defensive = Double.parseDouble(player[7]);
                    RBTree<Double, ArrayList<Integer>,Integer> playersByDefensive = fiba.getPlayersByDefensive();
                    node3 = playersByDefensive.search(defensive);
                    if(node3==null){
                        ArrayList<Integer> positions = new ArrayList<>();
                        positions.add(index);
                        node3 = new RBNode<>(defensive, positions);
                        playersByDefensive.insert(node3);
                    }else{
                        node3.getValue().add(index);
                    }
                    break;
                case 5:
                    double blocks = Double.parseDouble(player[8]);
                    fiba.getPlayersByBlocks().add(blocks); 
                    break;
                default:
                    break;
            }
        }
    }
}