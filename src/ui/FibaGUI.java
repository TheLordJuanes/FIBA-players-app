/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: April, 27th 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.FIBA;

public class FibaGUI {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    @FXML
    private JFXButton btnTextFile;

    @FXML
    private JFXTextField txtBlocks;

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
            lbAddPlayer.setText("");
            lbtnDeletePlayer.set("");
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
            btnTextFile.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                    textFileAddition(event);
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
        showInformationAlert("Text Input Format", "The data of the players must be in this order separated by a coma \",\"", "name,team,trueShooting,usage,assist,rebound,defensive,blocks");
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Txt files", "*.txt"), new FileChooser.ExtensionFilter("Csv files", "*.csv"));
        File file = fc.showOpenDialog(stage);
        if (file != null) {
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                br.readLine();
                String line = br.readLine();
                boolean added1 = true;
                while (line != null) {
                    String[] data = line.split(",");
                    boolean added2 = fiba.addPlayerData(data[0], data[1], data[2], Double.valueOf(data[3]), Double.valueOf(data[4]), Double.valueOf(data[5]), Double.valueOf(data[6]), Double.valueOf(data[7]), Double.valueOf(data[8]));
                    if (!added2) {
                        added1 = false;
                    }
                    line = br.readLine();
                }
                br.close();
                if (added1)
                    showInformationAlert("Successful addition", null, "Players were successfully added!");
                else
                    showErrorAlert("Error", "Something went wrong", "Some players weren't added");
            } catch (FileNotFoundException fnfe) {
                showErrorAlert("Error", "Something went wrong", "The file wasn't found");
            } catch (IOException ioe) {
                showErrorAlert("Error", "Something went wrong", "There were problems reading the file");
            } catch (NumberFormatException nfe) {
                showErrorAlert("Error", "Something went wrong", "There data in the file isn't correct");
            } catch (InterruptedException e) {
                showErrorAlert("Error", null, "Something went wrong");
            }
        } else
            showInformationAlert("Missing File", null, "No file was selected ");
    }

    @FXML
    public void addNewPlayer(ActionEvent event) {
        try {
            boolean added = fiba.addPlayerData(txtName.getText(), txtID.getText(), txtTeam.getText(),
                    Double.valueOf(txtTrueShooting.getText()), Double.valueOf(txtUsage.getText()),
                    Double.valueOf(txtAssist.getText()), Double.valueOf(txtRebound.getText()),
                    Double.valueOf(txtDefensive.getText()), Double.valueOf(txtBlocks.getText()));
            lbAddPlayer.setText("Player successfully added!");
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
            btnTextFile.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                    textFileModification(event);
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
        boolean modified = fiba.modifyPlayerData(cbDataType.getValue(), valueD, valueS, valueI,
                Integer.valueOf(txtID.getText()));
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
            btnTextFile.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                    textFileDeletion(event);
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
        showInformationAlert("Text Input Format", "The players' IDs must be one per line as follows:", "ID1\nID2\nID3\n...");
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Txt files", "*.txt"));
        File file = fc.showOpenDialog(stage);
        if (file != null) {
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                br.readLine();
                String line = br.readLine();
                boolean deleted1 = true;
                while (line != null) {
                    boolean deleted2 = fiba.deletePlayer(line);
                    if (!deleted2) {
                        deleted1 = false;
                    }
                    line = br.readLine();
                }
                br.close();
                if (deleted1)
                    showInformationAlert("Successful deletion", null, "Players were successfully deleted!");
                else
                    showErrorAlert("Error", "Something went wrong", "Some players weren't added");
            } catch (FileNotFoundException fnfe) {
                showErrorAlert("Error", "Something went wrong", "The file wasn't found");
            } catch (IOException ioe) {
                showErrorAlert("Error", "Something went wrong", "There were problems reading the file");
            } catch (NumberFormatException nfe) {
                showErrorAlert("Error", "Something went wrong", "The data in the file isn't correct");
            } catch (InterruptedException e) {
                showErrorAlert("Error", null, "Something went wrong");
            }
        } else
            showInformationAlert("Missing File", null, "No file was selected ");
    }

    @FXML
    public void deletePlayer(ActionEvent event) {
        boolean deleted;
        try {
            deleted = fiba.deletePlayer(txtID.getText());
            if (deleted)
                lbDeletePlayer.setText("Player successfully deleted!");
            else
                lbDeletePlayer.setText("Player doesn't exist!");
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
            btnTextFile.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                    textFileSearch(event);
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

    public void showErrorAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void showWarningAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void showInformationAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}