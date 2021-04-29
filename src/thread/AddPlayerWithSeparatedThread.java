package thread;

import java.io.File;
import java.io.IOException;
import com.opencsv.exceptions.CsvException;
import javafx.application.Platform;
import model.FIBA;
import ui.FibaGUI;

public class AddPlayerWithSeparatedThread extends Thread {

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private FIBA fiba;
    private FibaGUI fibaGUI;
    private File file;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /** Name: AddPlayerWithSeparatedThread <br>
	 * <br> Constructor method of a separated thread to add a player. <br>
     * @param fiba - Model controller - fiba = FIBA object, fiba != null
     * @param player - array with a player data - player = String[], player != null
     * @param typeData - Data type to add - typeData = int, typeData != null
     * @param index - player index in the players' list - index = int, index != null
	*/
    public AddPlayerWithSeparatedThread(FIBA fiba, FibaGUI fibaGUI, File file) {
        this.fiba = fiba;
        this.fibaGUI = fibaGUI;
        this.file = file;
    }

    /** Name: run <br>
	 * <br> Method used to run a separated thread to add a player. <br>
     * <br> pre: AddPlayerWithSeparatedThread object already created. <br>
     * <br> post: Separated threads to add a player ran. <br>
	*/
    public void run() {
        try {
            boolean s = fiba.addPlayerDataByTextFile(file);
            Platform.runLater(new Thread() {

                @Override
                public void run() {
                    fibaGUI.showAlerts(s);
                }
            });
        } catch (IOException ioe) {
            fibaGUI.showErrorAlert("Error", "Something went wrong", "There were problems reading the file");
        } catch (CsvException csve) {
            fibaGUI.showErrorAlert("Error", null, "There were problems reading the cvs");
        } catch (InterruptedException e) {
            fibaGUI.showErrorAlert("Error", null, "Something went wrong");
        }
	}
}