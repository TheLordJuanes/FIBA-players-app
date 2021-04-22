package thread;

import model.FIBA;
import dataStructures.RBNode;

public class DeletePlayerThread extends Thread {

    // -----------------------------------------------------------------
	// Attributes
    // -----------------------------------------------------------------

    private int typeData;

    // -----------------------------------------------------------------
	// Relations
    // -----------------------------------------------------------------

    private FIBA fiba;

    // -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

    public DeletePlayerThread(FIBA f, int tD) {
        setDaemon(true);
        fiba = f;
        typeData = tD;
    }

    @Override
	public void run() {
        /** 
        switch (typeData) {
            case 0:
                // Delete in playersByTrueShooting
                fiba.getPlayersByTrueShooting().delete(player.getTrueShooting());
                break;
            case 1:
                // Delete in playersByUsage
                fiba.getPlayersByUsage().delete(player.getUsage());
                break;
            case 2:
                // Delete in playersByAssist
                fiba.getPlayersByAssist().delete(player.getAssist());
                break;
            case 3:
                // Delete in playersByRebound
                fiba.getPlayersByRebound().delete(player.getRebound());
                break;
            case 4:
                // Delete in playersByDefensive
                RBNode<Double, Player> node = new RBNode<>(player.getDefensive(), player);
                fiba.getPlayersByDefensive().delete(node);
                break;
            case 5:
                // Delete in playersById
                fiba.getPlayersById().delete(player.getId());
                break;
            case 6:
                // Delete in playersByBlocks
                fiba.getPlayersByBlocks().delete(player.getBlocks());
                break;
            default:
                break;
        }
        **/
    }
}