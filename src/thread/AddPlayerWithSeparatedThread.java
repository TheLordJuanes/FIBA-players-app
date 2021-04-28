package thread;

import java.io.File;
import java.io.IOException;

import com.opencsv.exceptions.CsvException;

import javafx.application.Platform;
import model.FIBA;
import ui.FibaGUI;

public class AddPlayerWithSeparatedThread extends Thread{
    private FIBA fiba;
    private FibaGUI fGUI;
    private File file;

    public AddPlayerWithSeparatedThread(FIBA f, FibaGUI fg, File fl){
        fiba = f;
        fGUI = fg;
        file = fl;
    }

    public void run() {
        try {
            boolean s = fiba.addPlayerDataByTextFile(file);
            Platform.runLater(new Thread() {
			
                @Override
                public void run() {
                    fGUI.showAlerts(s);				
                }
            });
        } catch (IOException e) {
            fGUI.showErrorAlert("Error", "Something went wrong", "There were problems reading the file");
        } catch (CsvException e) {
            fGUI.showErrorAlert("Error", null, "There were problems reading the cvs");
        } catch (InterruptedException e) {
            fGUI.showErrorAlert("Error", null, "Something went wrong");
        }
		
		
		
	}

}
