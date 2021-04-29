package dataStructures;

import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;
import model.FIBA;

class FIBATest {

    private FIBA fiba;

    public void setup1() {
        fiba = new FIBA();
    }

    public void setup2() {
        try {
            fiba = new FIBA();
            File file = new File("resources/100.csv");
            fiba.addPlayerDataByTextFile(file);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (CsvException csve) {
            csve.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddPlayerDataByPlatform() {
        try {
            setup1();
            File file = new File("data/players.csv");
            fiba.addPlayerDataByPlatform("AAA", "BBB", "21", "CCC", "56.4", "23.6", "98.0", "77.2", "11.3", "33.3");
            assertTrue(file.exists());
            assertTrue(file.isFile());
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();
            assertEquals("\"AAA\",\"BBB\",\"CCC\",\"21\",\"56.4\",\"23.6\",\"98.0\",\"77.2\",\"11.3\",\"33.3\"", br.readLine());
            fiba.addPlayerDataByPlatform("Rayna","Madox","26","Phoenix Suns","41.76","7.91","46.04","30.66","72.4","47.09");
            assertEquals("\"Rayna\",\"Madox\",\"Phoenix Suns\",\"26\",\"41.76\",\"7.91\",\"46.04\",\"30.66\",\"72.4\",\"47.09\"", br.readLine());
            br.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (CsvException csve) {
            csve.printStackTrace();
        }
    }

    @Test
    public void testAddPlayerDataByTextFile() { // it can take a long duration while creating the trees and inserting the 200K nodes.
        try {
            setup1();
            File file = new File("resources/200k.csv");
            assertTrue(file.exists());
            assertTrue(file.isFile());
            BufferedReader br = new BufferedReader(new FileReader(file));
            assertEquals(200001, br.lines().count());
            File file2 = new File("data/players.csv");
            fiba.addPlayerDataByTextFile(file);
            BufferedReader br2 = new BufferedReader(new FileReader(file2));
            assertTrue(file2.exists());
            assertTrue(file2.isFile());
            assertTrue(br2.lines().count() >= 200001);
            br.close();
            br2.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (CsvException csve) {
            csve.printStackTrace();
        }
    }

    @Test
    public void testSearchPlayerIn() {
        setup2();
        ArrayList<ArrayList<String>> list = fiba.searchPlayerIn('>', "True Shooting", 80.0);
        assertEquals(22, list.size());
        for (int i = 0; i < list.size(); i++) {
            assertTrue(Double.valueOf(list.get(i).get(4)) > 80.0);
        }
        list = fiba.searchPlayerIn('=', "Usage", 7.91);
        assertEquals(1, list.size());
        for (int i = 0; i < list.size(); i++) {
            assertTrue(Double.valueOf(list.get(i).get(5)) == 7.91);
        }
        list = fiba.searchPlayerIn('<', "Rebound", 20.0);
        assertEquals(19, list.size());
        for (int i = 0; i < list.size(); i++) {
            assertTrue(Double.valueOf(list.get(i).get(7)) < 20.0);
        }
        list = fiba.searchPlayerIn('≥', "Blocks", 70.0);
        assertEquals(24, list.size());
        for (int i = 0; i < list.size(); i++) {
            assertTrue(Double.valueOf(list.get(i).get(9)) == 70.0 || Double.valueOf(list.get(i).get(9)) > 70.0);
        }
        list = fiba.searchPlayerIn('≤', "Assist", 50.0);
        assertEquals(49, list.size());
        for (int i = 0; i < list.size(); i++) {
            assertTrue(Double.valueOf(list.get(i).get(6)) == 50.0 || Double.valueOf(list.get(i).get(6)) < 50.0);
        }
        list = fiba.searchPlayerIn('≥', "Defensive", 30);
        assertEquals(70, list.size());
        for (int i = 0; i < list.size(); i++)
            assertTrue(Double.valueOf(list.get(i).get(8)) == 70.0 || Double.valueOf(list.get(i).get(8)) > 30.0);
    }

    @Test
    public void testSearchPlayer() {
        setup2();
        ArrayList<ArrayList<String>> list = fiba.searchPlayer('>', '<', "True Shooting", 50.0, 70.0);
        //assertEquals(45, list.size());
        for (int i = 0; i < list.size(); i++) {
            assertTrue(Double.valueOf(list.get(i).get(4)) > 50.0 && Double.valueOf(list.get(i).get(4)) < 70.0);
        }
        list = fiba.searchPlayer('≥', '≤', "True Shooting", 50.0, 70.0);
        //assertEquals(1, list.size());
        for (int i = 0; i < list.size(); i++) {
            assertTrue((Double.valueOf(list.get(i).get(4)) >= 50.0) && (Double.valueOf(list.get(i).get(4)) <= 70.0));
        }
        list = fiba.searchPlayerIn('≥', "Blocks", 70.0);
        //assertEquals(24, list.size());
        for (int i = 0; i < list.size(); i++) {
            assertTrue(Double.valueOf(list.get(i).get(9)) == 70.0 || Double.valueOf(list.get(i).get(9)) > 70.0);
        }
        list = fiba.searchPlayerIn('≤', "Assist", 50.0);
        //assertEquals(49, list.size());
        for (int i = 0; i < list.size(); i++) {
            assertTrue(Double.valueOf(list.get(i).get(6)) == 50.0 || Double.valueOf(list.get(i).get(6)) < 50.0);
        }
        list = fiba.searchPlayerIn('≥', "Defensive", 30);
        //assertEquals(70, list.size());
        for (int i = 0; i < list.size(); i++)
            assertTrue(Double.valueOf(list.get(i).get(8)) == 70.0 || Double.valueOf(list.get(i).get(8)) > 30.0);
        
    }
}