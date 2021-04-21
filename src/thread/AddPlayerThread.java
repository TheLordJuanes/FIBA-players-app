package thread;

import model.FIBA;
import model.Player;
import dataStructures.AVLNode;
import dataStructures.BSTNode;
import dataStructures.RBNode;

public class AddPlayerThread extends Thread {

    // -----------------------------------------------------------------
	// Attributes
    // -----------------------------------------------------------------

    private int typeData;

    // -----------------------------------------------------------------
	// Relations
    // -----------------------------------------------------------------

    private FIBA fiba;
    private Player player;

    // -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

    public AddPlayerThread(FIBA f, Player p, int tD) {
        setDaemon(true);
        fiba = f;
        typeData = tD;
        player = p;
    }

    @Override
	public void run() {
        AVLNode<Double, Player> node1;
        BSTNode<Double, Player> node2;
        RBNode<Double, Player> node3;
        AVLNode<String, Player> node4;
        switch (typeData) {
            case 0:
                //Add in playersByTrueShooting
                node1 = new AVLNode<>(player.getTrueShooting(), player);
                fiba.getPlayersByTrueShooting().insert(node1);
                break;
            case 1:
                //Add in playersByUsage
                node1 = new AVLNode<>(player.getUsage(), player);
                fiba.getPlayersByUsage().insert(node1);
                break;
            case 2:
                //Add in playersByAssist
                node1 = new AVLNode<>(player.getAssist(), player);
                fiba.getPlayersByAssist().insert(node1);
                break;
            case 3:
                //Add in playersByRebound
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