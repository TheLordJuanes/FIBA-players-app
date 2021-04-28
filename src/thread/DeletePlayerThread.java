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

    public DeletePlayerThread(FIBA f, int pos, int tD) {
        setDaemon(true);
        fiba = f;
        typeData = tD;
        position = pos;
    }

    /*
     * info[0]=name;
     * info[1]=lastName;
     * info[2]=team;
     * info[3] = String.valueOf(trueShooting);
     * info[4]= String.valueOf(usage);
     * info[5] = String.valueOf(assist);
     * info[6] = String.valueOf(rebound);
     * info[7] = String.valueOf(defensive);
     * info[8] = String.valueOf(blocks);
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
        case 6:

            break;
        default:
            break;
        }
    }
}