/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 *
 * @author Sen-Joysbright
 */
public class Preferences2 {
    public static final String CONFIG_FILE = "trophy.txt";
    int level;
    String diff;
    int answered;
    
    public Preferences2(){
        level = 1;
        answered = 0;
        diff = "easy";
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAnswered() {
        return answered;
    }

    public void setAnswered(int answered) {
        this.answered = answered;
    }
    
    public static void initConfig(){
        Writer writer = null;
        try {
            Preferences2 preferences = new Preferences2();
            Gson gson = new Gson();
            writer = new FileWriter(CONFIG_FILE);
            gson.toJson(preferences,writer);
        } catch (IOException ex) {
            Logger.getLogger(Preferences2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Preferences2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public static Preferences2 getPreferences2(){
        Gson gson = new Gson();
        Preferences2 preferences = new Preferences2();
        try {
            
            preferences = gson.fromJson(new FileReader(CONFIG_FILE), Preferences2.class);
        } catch (FileNotFoundException ex) {
            initConfig();
            Logger.getLogger(Preferences2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return preferences;
    }
    
    public static void writePreferenceToFile(Preferences2 preferences){
        Writer writer = null;
        try {
            Gson gson = new Gson();
            writer = new FileWriter(CONFIG_FILE);
            gson.toJson(preferences,writer);
            
            
        } catch (IOException ex) {
            Logger.getLogger(Preferences2.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                writer.close();
                System.exit(0);
            } catch (IOException ex) {
                Logger.getLogger(Preferences2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
