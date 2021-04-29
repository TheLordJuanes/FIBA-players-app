package thread;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import model.FIBA;
import ui.FibaGUI;

public class SearchPlayerWithSeparatedThread extends Thread{
    private FIBA fiba;
    private FibaGUI fibaGUI;
    private Character symbol1;
    private Character symbol2;
    private Double value1;
    private Double value2;
    private String statistic;
    private ActionEvent event;

    public SearchPlayerWithSeparatedThread(ActionEvent event, FIBA fiba, FibaGUI fibaGUI, Character symbol1, String statistic, Double value1){
        this.fiba = fiba;
        this.fibaGUI = fibaGUI;
        this.symbol1 = symbol1;
        symbol2 = null;
        this.value1 = value1;
        value2 = null;
        this.statistic = statistic;
        this.event=event;
    }

    

    public SearchPlayerWithSeparatedThread(ActionEvent event, FIBA fiba, FibaGUI fibaGUI, Character symbol1, Character symbol2, Double value1, Double value2, String statistic) {
        this.fiba = fiba;
        this.fibaGUI = fibaGUI;
        this.symbol1 = symbol1;
        this.symbol2 = symbol2;
        this.value1 = value1;
        this.value2 = value2;
        this.statistic = statistic;
        this.event=event;
    }

    public void run(){
        ArrayList<ArrayList<String>> players = new ArrayList<>();
        if(symbol2==null){
            players = fiba.searchPlayerIn(symbol1, statistic, value1);
        }else{
            players = fiba.searchPlayer(symbol1, symbol2, statistic, value1, value2);
            System.out.println(statistic+symbol1+value1);
            System.out.println(statistic+symbol2+value2);
        }
        String textPlayers = "";
        if (players.size() == 0)
            textPlayers = "No player satisfy the search criteria";
        else{
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
        }
        fibaGUI.setTextPlayers(textPlayers);
        fibaGUI.setPlayers(players);
        fiba.setCurrentPlayers(players);
        Platform.runLater(new Thread() {
            @Override
            public void run() {
                fibaGUI.searchResult(event);
            }
        });
    }

}
