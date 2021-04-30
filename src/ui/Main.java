/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: April, 27th 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package ui;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	/**
	 * Name: main <br>
	 * <br> Main method. <br>
	 * @param args - Java command line arguments - args = String[]
	*/
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Name: start <br>
	 * <br> GUI start method. <br>
	 * @param primaryStage - GUI primary stage - primaryStage = Stage
	 * @throws Exception - to indicate the conditions this program might want to catch.
	*/
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fiba.fxml"));
		FibaGUI fibaGUI = new FibaGUI(primaryStage);
		fxmlLoader.setController(fibaGUI);
		Parent root = fxmlLoader.load();
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("International Basketball Federation");
		primaryStage.show();
		File file = new File("resources/music.wav");
 		if (file.exists()) {
 			AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
 			Clip clip = AudioSystem.getClip();
 			clip.open(audioInput);
 			clip.start();
 			clip.loop(Clip.LOOP_CONTINUOUSLY);
 		}
		//fibaGUI.checkForData();
	}
}