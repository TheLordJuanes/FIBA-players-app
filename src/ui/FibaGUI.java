/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: April, 27th 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package ui;

import javafx.stage.Stage;
import model.FIBA;

public class FibaGUI {

    // -----------------------------------------------------------------
	// Attributes
    // -----------------------------------------------------------------

    private Stage primaryStage;

    // -----------------------------------------------------------------
	// Relations
    // -----------------------------------------------------------------

    private FIBA fiba;

    // -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

    /**
     * Name: FibaGUI
     * GUI constructor method. <br>
     * @param primaryStage - GUI primary stage - primaryStage = Stage
    */
    public FibaGUI(Stage primaryStage) {
		this.primaryStage = primaryStage;
        fiba = new FIBA();
	}
}