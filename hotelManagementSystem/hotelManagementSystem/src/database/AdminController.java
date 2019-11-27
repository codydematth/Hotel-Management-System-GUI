/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Sen-Joysbright
 */
public class AdminController implements Initializable {

    @FXML
    private JFXTextField txtTable;
    @FXML
    private JFXTextField txtQuestion;
    @FXML
    private JFXTextField txtAnswer;
    @FXML
    private JFXButton btnInsert;
    
    DatabaseHandler handler;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXTextField txtNum;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = DatabaseHandler.getInstance();
    }    

    @FXML
    private void insertOP(ActionEvent event) {
        String table_name = txtTable.getText();
        String question = txtQuestion.getText();
        String answer = txtAnswer.getText();
        
        Boolean flag = table_name.isEmpty() || question.isEmpty() || answer.isEmpty() ;
        if(flag){
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please supply value to all fields");
            alert.showAndWait();
            return;
        }
        
         String st = "INSERT INTO " + table_name + "(question ,answer)" + 
                 " VALUES (" +
                "'" + question + "', "+
                "'" + answer + "'" +              
                ")";
        System.out.println(st); 
        if (handler.execAction(st)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Question and Answer Added !");
            alert.showAndWait();
        }
        else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Adding failed !");
            alert.showAndWait();
        }
    }

    @FXML
    private void editOP(ActionEvent event) {
        String table_name = txtTable.getText();
        String question = txtQuestion.getText();
        String answer = txtAnswer.getText();
        int number = Integer.parseInt(txtNum.getText());
        
        Boolean flag = table_name.isEmpty() || number == 0 || ( answer.isEmpty() && question.isEmpty());
        if(flag){
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please supply value to important fields");
            alert.showAndWait();
            return;
        }
        
        //Update Question only
        if(!question.isEmpty() && answer.isEmpty()){
            String st = "UPDATE " + table_name + " SET question = '" + question +  
                 "' WHERE id = " + number + "";
            System.out.println(st); 
            if (handler.execAction(st)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Updated !");
                alert.showAndWait();
            }
            else{
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Updating failed !");
                alert.showAndWait();
            }
        }       
        
        // Both question and answer to be updated
        else if(!question.isEmpty() && !answer.isEmpty()){
            String st = "UPDATE " + table_name + " SET answer = '" + answer +  
                 "', question = '" + question + "' WHERE id = " + number + "";
            System.out.println(st); 
            if (handler.execAction(st)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Updated !");
                alert.showAndWait();
            }
            else{
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Updating failed !");
                alert.showAndWait();
            }
        }
        // Update Answer only
         else{
            String st = "UPDATE " + table_name + " SET answer = '" + answer +  
                 "' WHERE id = " + number + "";
            System.out.println(st);
            if (handler.execAction(st)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Updated !");
                alert.showAndWait();
            }
            else{
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Updating failed !");
                alert.showAndWait();
            }
        }
    }
    
}
