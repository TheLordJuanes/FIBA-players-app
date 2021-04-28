package thread;

import javafx.application.Platform;
import model.Spinner;
import ui.FibaGUI;

public class SpinnerThread extends Thread {

    // -----------------------------------------------------------------
	// Relations
	// -----------------------------------------------------------------

    private Spinner spinner;
    private FibaGUI fibaGUI;

    // -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------

    /** Name: SpinnerThread
	 * Constructor method of a spinner thread. <br>
     * @param fibaGUI - GUI controller - fibaGUI = FibaGUI object, fibaGUI != null
	*/
    public SpinnerThread(Spinner spinner, FibaGUI fibaGUI) {
        setDaemon(true);
        this.spinner = spinner;
        this.fibaGUI = fibaGUI;
    }

    /** Name: run
	 * Method used to run the spinner thread created. <br>
     * <b>pre: </b> Spinner object already created. List of squares already initialized. <br>
     * <b>post: </b> Spinner threads ran. <br>
	*/
    @Override
    public void run() {
        while (spinner.getSpin()) {
            Platform.runLater(new Thread() {

                @Override
                public void run() {
                    fibaGUI.spin();
                }
            });
            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}