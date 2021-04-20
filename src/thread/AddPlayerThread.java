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

    public AddPlayerThread(FIBA f,  Player p, int tD) {
        fiba = f;
        typeData = tD;
        player = p;
    }

    @Override
	public void run() {
        AVLNode<Double, Player> node1;
        BSTNode<Double, Player> node2;
        RBNode<Double, Player> node3;
        AVLNode<Integer, Player> node4;
        switch (typeData) {
            case 0:
                //Add in playersByTrueShooting
                node1 = new AVLNode<>(player.getTrueShooting(), player);
                fiba.addPlayerDataIn(node1, fiba.getPlayersByTrueShooting());
                break;
            case 1:
                //Add in playersByUsage
                node1 = new AVLNode<>(player.getUsage(), player);
                fiba.addPlayerDataIn(node1, fiba.getPlayersByUsage());
                break;
            case 2:
                //Add in playersByAssist
                node1 = new AVLNode<>(player.getAssist(), player);
                fiba.addPlayerDataIn(node1, fiba.getPlayersByAssist());
                break;
            case 3:
                //Add in playersByRebound
                node2 = new BSTNode<>(player.getRebound(), player);
                fiba.addPlayerDataIn(node2, fiba.getPlayersByRebound());
                break;
            case 4:
                //Add in playersByDefensive
                node3 = new RBNode<>(player.getDefensive(), player);
                fiba.addPlayerDataIn(node3, fiba.getPlayersByDefensive());
                break;
            case 5:
                //Add in playersById
                node4 = new AVLNode<>(player.getId(), player);
                fiba.addPlayerDataIn(node4, fiba.getPlayersById());
                break;
            default:
                break;
        }
    }
}