/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: April, 27th 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package ui;

import java.io.IOException;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.FIBA;

public class FibaGUI {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    @FXML
    private JFXTextField trueShooting;

    @FXML
    private JFXTextField usage;

    @FXML
    private JFXTextField assist;

    @FXML
    private JFXTextField rebound;

    @FXML
    private JFXTextField defensive;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField age;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField team;

    private Stage primaryStage;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private FIBA fiba;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Name: FibaGUI <br>
     * <br> GUI constructor method. <br>
     * @param primaryStage - GUI primary stage - primaryStage = Stage
     */
    public FibaGUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
        fiba = new FIBA();
    }

    @FXML
    public void startMenu(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("start-menu.fxml"));
            FibaGUI fibaGUI = new FibaGUI(primaryStage);
            fxmlLoader.setController(fibaGUI);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Start Menu");
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void goBackToStart(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fiba.fxml"));
            FibaGUI fibaGUI = new FibaGUI(primaryStage);
            fxmlLoader.setController(fibaGUI);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("International Basketball Federation");
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void goBackToMenu(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("start-menu.fxml"));
            FibaGUI fibaGUI = new FibaGUI(primaryStage);
            fxmlLoader.setController(fibaGUI);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Start Menu");
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void addPlayer(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("new-player.fxml"));
            FibaGUI fibaGUI = new FibaGUI(primaryStage);
            fxmlLoader.setController(fibaGUI);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Start Menu");
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void addNewPlayer(ActionEvent event) {

    }

    @FXML
    public void deletePlayer(ActionEvent event) {

    }

    @FXML
    public void modifyPlayer(ActionEvent event) {

    }

    @FXML
    public void searchPlayer(ActionEvent event) {

    }
}