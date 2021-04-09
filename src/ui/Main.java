/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: April, 27th 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	/**
	 * Name: main
	 * Main method.
	 * @param args - Arguments - args = String[]
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Name: start
	 * GUI start method.
	 * @param primaryStage - GUI primary stage - primaryStage = Stage
	 * @throws Exception - to indicate the conditions this program might want to catch.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("start-menu.fxml"));
		FibaGUI fibaGUI = new FibaGUI(primaryStage);
		fxmlLoader.setController(fibaGUI);
		Parent root = fxmlLoader.load();
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("International Basketball Federation");
		primaryStage.show();
	}
}