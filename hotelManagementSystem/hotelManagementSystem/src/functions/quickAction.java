
package functions;


import database.DatabaseHandler;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Sen-Joysbright
 */
public class quickAction {
    
    public Thread thread = null;
    public Task task = null;
    public AudioClip audio;
    public Thread thread2 = null;
    public Task task2 = null;
    public AudioClip audio2;
    
    
    public void loadWindow(String loc, String title){
        
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
             
        } catch (IOException ex) {
            Logger.getLogger(quickAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
   public void loadMain(){
        
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/main/main.fxml"));
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setTitle("Login");
            stage.setScene(new Scene(parent));
            stage.show();
            
             
        } catch (IOException ex) {
            Logger.getLogger(quickAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     public void makeFadeIn(Node node, int duration) {
        node.setOpacity(0);
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(duration));
        fadeTransition.setNode(node);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
     
     public void makeFadeOut(String location, Node node, int duration) {
        
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(duration));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0.8);
        
        fadeTransition.setOnFinished((ActionEvent event) -> {
            loadNextScene(location,node);
        });
        fadeTransition.play();
    }
     public void loadNextScene(String location, Node node){
        Parent secondView;
        try {
            secondView = (AnchorPane) FXMLLoader.load(getClass().getResource(location));
            
            Scene newScene = new Scene(secondView);
            
            Stage curStage = (Stage) node.getScene().getWindow();
            
            curStage.setScene(newScene);
            curStage.centerOnScreen();
        } catch (IOException ex) {
            Logger.getLogger(quickAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void buttonRotate(Node node){
         node.setRotate(-10);
         node.setRotationAxis(Point3D.ZERO);
         node.setCursor(Cursor.HAND);
     }
     
     public void resetButtonRotate(Node node){
         node.setRotate(0);
         node.setRotationAxis(Point3D.ZERO);
         node.setCursor(Cursor.DEFAULT);
     }
     
     public void playSound(String sound, int duration){
        Preferences preference = Preferences.getPreferences();
        if(preference.sound){
            task = new Task() {

                   @Override
                   protected Object call() throws Exception {
                       audio = new AudioClip(getClass().getResource(sound).toExternalForm());
                       audio.setVolume(0.5f);
                       audio.setCycleCount(duration);
                       audio.play();
                       return null;
                   }
               };
               thread = new Thread(task);
               thread.start();
            
        }
     }
     
     public void playTone(String sound, int duration){
        Preferences preference = Preferences.getPreferences();
        if(preference.sound){
            task2 = new Task() {

                   @Override
                   protected Object call() throws Exception {
                       audio2 = new AudioClip(getClass().getResource(sound).toExternalForm());
                       audio2.setVolume(0.5f);
                       audio2.setCycleCount(duration);
                       audio2.play();
                       return null;
                   }
               };
               thread2 = new Thread(task2);
               thread2.start();
            
        }
     }
       public void stopTone(){
           if (thread2 != null){  
            thread2.stop();
	    thread2 = null;
	}
            Preferences preference = Preferences.getPreferences();
             audio2.stop();
       }
     public void stopAudio(){
         if (thread != null){  
            thread.stop();
	    thread = null;
	}
            Preferences preference = Preferences.getPreferences();
             audio.stop();
         
     }
   
     public void closeStage(Node node) {
        ((Stage)node.getScene().getWindow()).close();
    }
    
   public void loadData(String tableName, int id) {
           DatabaseHandler handler = DatabaseHandler.getInstance();

           String qu = "SELECT * FROM " + tableName + " WHERE ID = " + id;
           
           System.out.println(qu);
            ResultSet rs = handler.execQuery(qu);           
            
        try {
            while (rs.next()){
//                Level1Controller qA = new Level1Controller();                
  //              qA.txtQuestion.setText(rs.getString("question"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(quickAction.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
     public void cheat(String tableName,int id) {
           DatabaseHandler handler = DatabaseHandler.getInstance();

           String qu = "SELECT * FROM " + tableName + " WHERE ID = " + id;
           
            ResultSet rs = handler.execQuery(qu);         
        try {
            while (rs.next()){ 
            System.out.println(rs.getString("answer"));          
            }
        } catch (SQLException ex) {
            Logger.getLogger(quickAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
