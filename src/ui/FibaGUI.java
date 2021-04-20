/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: April, 27th 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package ui;

import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.FIBA;

public class FibaGUI {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    @FXML
    private JFXButton btnPlatform;

    @FXML
    private JFXTextArea taSearchResult;

    @FXML
    private JFXButton btnSearchPlayers;

    @FXML
    private JFXTextField searchValue2;

    @FXML
    private JFXComboBox<Character> cbSymbol1;

    @FXML
    private JFXTextField searchValue1;

    @FXML
    private Label lbModifyPlayer1;

    @FXML
    private JFXTextField searchValue3;

    @FXML
    private JFXComboBox<Character> cbSymbol2;

    @FXML
    private Label searchName1;

    @FXML
    private JFXComboBox<Character> cbSymbol3;

    @FXML
    private JFXTextField searchValue5;

    @FXML
    private JFXComboBox<Character> cbSymbol4;

    @FXML
    private JFXTextField searchValue4;

    @FXML
    private Label lbModifyPlayer12;

    @FXML
    private JFXTextField searchValue6;

    @FXML
    private JFXComboBox<Character> cbSymbol5;

    @FXML
    private Label searchName2;

    @FXML
    private JFXComboBox<Character> cbSymbol6;

    @FXML
    private Label lbModifyPlayer3;

    @FXML
    private Label lbModifyPlayer4;

    @FXML
    private JFXComboBox<Character> cbDataType2;

    @FXML
    private TextField txtTest;

    @FXML
    private JFXButton btnDeletePlayer;

    @FXML
    private JFXButton btnModifyPlayer;

    @FXML
    private JFXButton btnAddPlayer;

    @FXML
    private JFXTextField txtTrueShooting;

    @FXML
    private JFXTextField txtUsage;

    @FXML
    private JFXTextField txtAssist;

    @FXML
    private JFXTextField txtRebound;

    @FXML
    private JFXTextField txtDefensive;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAge;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtTeam;

    @FXML
    private Label lbAddPlayer;

    @FXML
    private JFXComboBox<String> cbDataType;

    @FXML
    private JFXTextField txtDataValue;

    @FXML
    private Label lbModifyPlayer;

    @FXML
    private Label lbDeletePlayer;

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
            fxmlLoader.setController(this);
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
            fxmlLoader.setController(this);
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
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Start Menu");
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void addAPlayer(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("additionForm.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Player addition form");
            stage.show();
            btnPlatform.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                    platformAddition(event);
                }
            });
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void platformAddition(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-player.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Player addition");
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void textFileAddition(ActionEvent event) {

    }

    @FXML
    public void addNewPlayer(ActionEvent event) {
        try {
            boolean added = fiba.addPlayerData(txtName.getText(), Integer.valueOf(txtID.getText()), txtTeam.getText(), Double.valueOf(txtTrueShooting.getText()), Double.valueOf(txtUsage.getText()), Double.valueOf(txtAssist.getText()), Double.valueOf(txtRebound.getText()), Double.valueOf(txtDefensive.getText()));
            if (added)
                lbAddPlayer.setText("Player successfully added!");
            else
                lbAddPlayer.setText("Player already exists!");
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void modifyAPlayer(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modificationForm.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Player modification form");
            stage.show();
            btnPlatform.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                    platformModification(event);
                }
            });
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void platformModification(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modify-player.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Player modification");
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void textFileModification(ActionEvent event) {

    }


    @FXML
    public void modifyPlayer(ActionEvent event) {
        int valueI = 0;
        double valueD = 0;
        String valueS = "";
        if (cbDataType.getValue().equals("Age"))
            valueI = Integer.valueOf(txtDataValue.getText());
        else if (cbDataType.getValue().equals("Name") || cbDataType.getValue().equals("Team"))
            valueS = txtDataValue.getText();
        else {
            valueD = Double.valueOf(txtDataValue.getText());
        }
        boolean modified = fiba.modifyPlayerData(cbDataType.getValue(), valueD, valueS, valueI, Integer.valueOf(txtID.getText()));
        if (modified)
            lbModifyPlayer.setText("Player successfully modified!");
        else
            lbModifyPlayer.setText("Player doesn't exist!");
    }

    @FXML
    public void deleteAPlayer(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("deletionForm.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Player elimination form");
            stage.show();
            btnPlatform.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                    platformDeletion(event);
                }
            });
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void platformDeletion(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("delete-player.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Player elimination");
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void textFileDeletion(ActionEvent event) {

    }

    @FXML
    public void deletePlayer(ActionEvent event) {
        boolean deleted = fiba.deletePlayer(Integer.valueOf(txtID.getText()));
        if (deleted)
            lbDeletePlayer.setText("Player successfully deleted!");
        else
            lbDeletePlayer.setText("Player doesn't exist!");
    }

    @FXML
    public void searchAPlayer(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchForm.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Player search form");
            stage.show();
            btnPlatform.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                    platformSearch(event);
                }
            });
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void platformSearch(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("search-player.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Player search");
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void textFileSearch(ActionEvent event) {

    }

    @FXML
    public void searchPlayer(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchResult.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Player search");
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}