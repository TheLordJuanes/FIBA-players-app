/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: April, 27th 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.opencsv.exceptions.CsvException;
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
import model.Spinner;
import thread.AddPlayerWithSeparatedThread;
import thread.SpinnerThread;

public class FibaGUI {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    @FXML
    private JFXSpinner spinner;

    @FXML
    private JFXButton btnSearchMenu;

    @FXML
    private Label initialLettersDataType;

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
    private Label lbModifyPlayer12;

    @FXML
    private Label searchName2;

    @FXML
    private Label lbModifyPlayer3;

    @FXML
    private Label lbModifyPlayer4;

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
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtTeam;

    @FXML
    private Label lbAddPlayer;

    @FXML
    private JFXComboBox<String> cbDataType;

    @FXML
    private JFXComboBox<String> cbDataTypeMod;

    @FXML
    private JFXTextField txtDataValue;

    @FXML
    private Label lbModifyPlayer;

    @FXML
    private JFXComboBox<String> cbModifyPlayer;

    @FXML
    private JFXComboBox<String> cbDeletePlayer;

    @FXML
    private Label lbDeletePlayer;

    private Stage primaryStage;

    private String textPlayers;

    private ArrayList<ArrayList<String>> players;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private FIBA fiba;
    private Spinner sp;

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
        /**
        File file = new File("resources/100.csv"); //QUITARRRRR, CAMBIARRR
        try {
            fiba.addPlayerDataByTextFile(file);
        } catch (IOException | CsvException | InterruptedException e) {
            e.printStackTrace();
        }**/
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
    public void goToMenu(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("start-menu.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Start Menu");
            if(fiba.getAllData().isEmpty()){
                btnSearchMenu.setDisable(true);
            }else{
                btnSearchMenu.setDisable(false);
            }
            spinner.setVisible(false);
            primaryStage.show();
            textPlayers = "";
            players = new ArrayList<ArrayList<String>>();
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
            btnAddPlayer.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (txtTrueShooting.getText().isEmpty() || txtAge.getText().isEmpty() || txtTeam.getText().isEmpty() || txtName.getText().isEmpty()  ||txtLastName.getText().isEmpty() || txtAssist.getText().isEmpty() || txtUsage.getText().isEmpty() || txtDefensive.getText().isEmpty() || txtBlocks.getText().isEmpty() || txtRebound.getText().isEmpty())
                        showWarningAlert("Missing parameters", null, "Every text field must be filled!");
                    else
                        addNewPlayer(event);
                }
            });
            primaryStage.setTitle("Player addition");
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void showAlerts(boolean added){
        if (added) {
            showInformationAlert("Successful addition", null, "Players were successfully added!");
        } else
            showErrorAlert("Error", "Something went wrong", "Some players weren't added");
    }

    @FXML
    public void textFileAddition(ActionEvent event) {
        showWarningAlert("Text Input Format", "The data of the players must be in this order separated by a coma \",\"", "firstname,lastname,team,age,trueShooting,usage,assist,rebound,defensive,blocks");
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Csv files", "*.csv"));
        File file = fc.showOpenDialog(stage);
        if (file != null) {
            new AddPlayerWithSeparatedThread(fiba, this, file).start();
            sp = new Spinner();
            new SpinnerThread(sp, this).start();
            btnSearchMenu.setDisable(false);
        } else
            showInformationAlert("Missing File", null, "No file was selected ");
    }

    @FXML
    public void addNewPlayer(ActionEvent event) {
        try {
            fiba.addPlayerDataByPlatform(txtName.getText(), txtLastName.getText(), txtAge.getText(), txtTeam.getText(), txtTrueShooting.getText(), txtUsage.getText(), txtAssist.getText(), txtRebound.getText(), txtDefensive.getText(), txtBlocks.getText());
            lbAddPlayer.setText("Player successfully added!");
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void modifyAPlayer(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modify-player.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            cbDataTypeMod.getItems().add("Name");
            cbDataTypeMod.getItems().add("Last Name");
            cbDataTypeMod.getItems().add("Age");
            cbDataTypeMod.getItems().add("Team");
            cbDataTypeMod.getItems().add("True Shooting");
            cbDataTypeMod.getItems().add("Usage");
            cbDataTypeMod.getItems().add("Assist");
            cbDataTypeMod.getItems().add("Rebound");
            cbDataTypeMod.getItems().add("Defensive");
            cbDataTypeMod.getItems().add("Blocks");
            primaryStage.setScene(new Scene(root));
            for (int i = 0; i < players.size(); i++) {
                cbModifyPlayer.getItems().add("Player" + (i + 1));
            }
            primaryStage.setTitle("Player modification form");
            primaryStage.show();
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
    public void modifyPlayer(ActionEvent event) throws IOException {
        int valueI = 0;
        String valueS = "";
        if (cbDataTypeMod.getValue().equals("Age"))
            valueI = Integer.valueOf(txtDataValue.getText());
        else {
            valueS = txtDataValue.getText();
        }
        int player = 0;
        for (int i = 0; i < cbModifyPlayer.getItems().size(); i++) {
            if (cbModifyPlayer.getItems().get(i).equals(cbModifyPlayer.getValue())) {
                player = i;
                break;
            }
        }
        fiba.modifyPlayerData(cbDataType.getValue(), valueS, valueI, player);
        lbModifyPlayer.setText("Player successfully modified!");
    }

    @FXML
    public void deleteAPlayer(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("delete-player.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            for (int i = 0; i < players.size(); i++) {
                cbDeletePlayer.getItems().add("Player " + (i + 1));
            }
            fiba.setCurrentPlayers(players);
            primaryStage.setTitle("Player elimination");
            primaryStage.show();
            cbDeletePlayer.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent event) {
                    if (!cbDeletePlayer.getSelectionModel().isEmpty())
                    btnDeletePlayer.setDisable(false);
                }
            });
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void deletePlayer(ActionEvent event) {
        try {
            String[] parts = cbDeletePlayer.getValue().split(" ");
            boolean deleted = fiba.deletePlayer(players, Integer.valueOf(parts[1]) - 1);
            if (deleted)
                lbDeletePlayer.setText("Player successfully deleted!");
            else
                lbDeletePlayer.setText("Player doesn't exist!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    @FXML
    public void searchAPlayer(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("search-player.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Player search");
            cbDataType.getItems().add("True Shooting");
            cbDataType.getItems().add("Usage");
            cbDataType.getItems().add("Assist");
            cbDataType.getItems().add("Rebound");
            cbDataType.getItems().add("Defensive");
            cbDataType.getItems().add("Blocks");
            cbSymbol1.getItems().add('=');
            cbSymbol1.getItems().add('>');
            cbSymbol1.getItems().add('<');
            cbSymbol1.getItems().add('≥');
            cbSymbol1.getItems().add('≤');
            cbSymbol2.getItems().add('>');
            cbSymbol2.getItems().add('<');
            cbSymbol2.getItems().add('≥');
            cbSymbol2.getItems().add('≤');
            cbSymbol3.getItems().add('>');
            cbSymbol3.getItems().add('<');
            cbSymbol3.getItems().add('≥');
            cbSymbol3.getItems().add('≤');
            primaryStage.show();
            cbDataType.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
                switch (newValue) {
                    case "True Shooting":
                        initialLettersDataType.setText("TS");
                        break;
                    case "Usage":
                        initialLettersDataType.setText("USG");
                        break;
                    case "Assist":
                        initialLettersDataType.setText("AST");
                        break;
                    case "Rebound":
                        initialLettersDataType.setText("TRB");
                        break;
                    case "Defensive":
                        initialLettersDataType.setText("DRB");
                        break;
                    case "Blocks":
                        initialLettersDataType.setText("BLK");
                        break;
                }});
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
        String statistic = cbDataType.getValue();
        Character symbol1 = cbSymbol1.getValue();
        Character symbol2 = cbSymbol2.getValue();
        Character symbol3 = cbSymbol3.getValue();
        if (symbol1 != null && (symbol2 != null || symbol3 != null))
            showErrorAlert("Problems with the equations", null, "You cannot write two inequalities, write only one");
        else {
            if (symbol1 != null) {
                double value1 = Double.parseDouble(searchValue1.getText());
                players = fiba.searchPlayerIn(symbol1, statistic, value1);
            } else {
                switch (symbol2) {
                case '>':
                    symbol2 = '<';
                    break;
                case '<':
                    symbol2 = '>';
                    break;
                case '≥':
                    symbol2 = '≤';
                    break;
                case '≤':
                    symbol2 = '≥';
                    break;
                }
                double value2 = Double.parseDouble(searchValue2.getText());
                double value3 = Double.parseDouble(searchValue3.getText());
                players = fiba.searchPlayer(symbol2, symbol3, statistic, value2, value3);
            }
            // "firstName, lastName,
            // team,trueShooting,usage,assist,rebound,defensive,blocks"
            for (int i = 0; i < players.size(); i++) {
                int number = i + 1;
                ArrayList<String> player = players.get(i);
                textPlayers += "Player " + number + "\n";
                textPlayers += "First Name: " + player.get(0) + "\n";
                textPlayers += "Last Name: " + player.get(1) + "\n";
                textPlayers += "Team: " + player.get(2) + "\n";
                textPlayers += "Age: " + player.get(3) + "\n";
                textPlayers += "True Shooting: " + player.get(4) + "\n";
                textPlayers += "Usage: " + player.get(5) + "\n";
                textPlayers += "Assist: " + player.get(6) + "\n";
                textPlayers += "Rebound: " + player.get(7) + "\n";
                textPlayers += "Defensive: " + player.get(8) + "\n";
                textPlayers += "Blocks: " + player.get(9) + "\n\n";
            }
            searchResult(event);
        }
    }

    @FXML
    public void searchResult(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchResult.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Search Result");
            taSearchResult.setText(textPlayers);
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

    public void spin() {
        double percentage = fiba.getProgress();
        spinner.setProgress(percentage);
        spinner.setVisible(true);
        if(percentage==1.0){
            sp.setSpin(false);
        }
    }

    @FXML
    public void exit(ActionEvent event) {
        System.exit(0);
    }
}