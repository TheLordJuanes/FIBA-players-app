package thread;

import java.io.File;
import java.io.IOException;
import com.opencsv.exceptions.CsvException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import model.FIBA;
import ui.FibaGUI;

public class AddPlayerWithSeparatedThread extends Thread {

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private FIBA fiba;
    private FibaGUI fibaGUI;
    private File file;
    private int origin;
    private ActionEvent event;

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
    public AddPlayerWithSeparatedThread(FIBA fiba, FibaGUI fibaGUI, File file, int origin) {
        this.fiba = fiba;
        this.fibaGUI = fibaGUI;
        this.file = file;
        this.origin = origin;
    }

    public AddPlayerWithSeparatedThread(FIBA fiba, FibaGUI fibaGUI, File file, int origin, ActionEvent event) {
        this.fiba = fiba;
        this.fibaGUI = fibaGUI;
        this.file = file;
        this.origin = origin;
        this.event=event;
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
                    switch(origin){
                        //Adding files after run the program
                        case 1:
                            fibaGUI.showAlerts(s);
                            break;
                        //Adding files when the program starts
                        case 2:
                            fibaGUI.showAlerts2(s);
                            fibaGUI.goToMenu(event);
                            break;
                    }
                    
                }
            });
        } catch (IOException ioe) {
            fibaGUI.showErrorAlert("Error", "Something went wrong", "There were problems reading the file");
        } catch (CsvException csve) {
            fibaGUI.showErrorAlert("Error", null, "There were problems reading the cvs");
        } catch (InterruptedException e) {
            fibaGUI.showErrorAlert("Error", null, "Something went wrong");
        }catch (NumberFormatException e) {
            fibaGUI.showErrorAlert("Error", null, "Some stadistics are not numbers");
        }
	}
}