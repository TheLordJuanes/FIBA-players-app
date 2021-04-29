package thread;

import model.FIBA;

public class DeletePlayerThread extends Thread {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private int typeData;
    private int position;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private FIBA fiba;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /** Name: DeletePlayerThread <br>
	 * <br> Constructor method of a thread to delete a player. <br>
     * @param fiba - Model controller - fiba = FIBA object, fiba != null
     * @param position - Position in the players' list - position = int, position != null
     * @param typeData - Data type to delete - typeData = int, typeData != null
	*/
    public DeletePlayerThread(FIBA fiba, int position, int typeData) {
        setDaemon(true);
        this.fiba = fiba;
        this.typeData = typeData;
        this.position = position;
    }

    /** Name: run <br>
	 * <br> Method used to run the thread to delete a player. <br>
     * <br> pre: DeletePlayerThread object already created. <br>
     * <br> post: Threads to delete a player ran. <br>
	*/
    @Override
    public void run() {
        switch (typeData) {
            case 0:
                // Delete in playersByTrueShooting
                fiba.getPlayersByTrueShooting().delete(Double.parseDouble(fiba.getAllData().get(position)[4]), position);
                break;
            case 1:
                // Delete in playersByUsage
                fiba.getPlayersByUsage().delete(Double.parseDouble(fiba.getAllData().get(position)[5]), position);
                break;
            case 2:
                // Delete in playersByAssist
                fiba.getPlayersByAssist().delete(Double.parseDouble(fiba.getAllData().get(position)[6]), position);
                break;
            case 3:
                // Delete in playersByRebound
                fiba.getPlayersByRebound().delete(Double.parseDouble(fiba.getAllData().get(position)[7]), position);
                break;
            case 4:
                // Delete in playersByDefensive
                fiba.getPlayersByDefensive().delete(Double.parseDouble(fiba.getAllData().get(position)[8]), position);
                break;
            case 5:
                // Delete in playersByBlocks
                fiba.getPlayersByBlocks().set(position, null);
                break;
            default:
                break;
        }
    }
}