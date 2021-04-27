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
        index = ind;
    }

    @Override
	public void run() {
        if (player.length == 9) {
            //"firstName, lastName, team,trueShooting,usage,assist,rebound,defensive,blocks"
            AVLNode<Double, ArrayList<Integer>> node1;
            BSTNode<Double, ArrayList<Integer>> node2;
            RBNode<Double, ArrayList<Integer>> node3;
            ArrayList<Integer> positions = new ArrayList<>();
            switch (typeData) {
                case 0:
                    //Add in playersByTrueShooting
                    double trueShooting = Double.parseDouble(player[3]);
                    AVLTree<Double, ArrayList<Integer>, Integer> playersByTrueShooting = fiba.getPlayersByTrueShooting();
                    positions.add(index);
                    node1 = new AVLNode<>(trueShooting, positions);
                    playersByTrueShooting.insert(node1, index);
                    break;
                case 1:
                    //Add in playersByUsage
                    double usage = Double.parseDouble(player[4]);
                    AVLTree<Double, ArrayList<Integer>, Integer> playersByUsage = fiba.getPlayersByUsage();
                    positions.add(index);
                    node1 = new AVLNode<>(usage, positions);
                    playersByUsage.insert(node1, index);
                    break;
                case 2:
                    //Add in playersByAssist
                    double assist = Double.parseDouble(player[5]);
                    AVLTree<Double, ArrayList<Integer>, Integer> playersByAssist = fiba.getPlayersByAssist();
                    positions.add(index);
                    node1 = new AVLNode<>(assist, positions);
                    playersByAssist.insert(node1, index);
                    break;
                case 3:
                    //Add in playersByRebound
                    double rebound = Double.parseDouble(player[6]);
                    BSTree<Double, ArrayList<Integer>,Integer> playersByRebound = fiba.getPlayersByRebound();
                    positions.add(index);
                    node2 = new BSTNode<>(rebound, positions);
                    playersByRebound.insert(node2, index);
                    break;
                case 4:
                    //Add in playersByDefensive
                    double defensive = Double.parseDouble(player[7]);
                    RBTree<Double, ArrayList<Integer>,Integer> playersByDefensive = fiba.getPlayersByDefensive();
                    positions.add(index);
                    node3 = new RBNode<>(defensive, positions);
                    playersByDefensive.insert(node3, index);
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