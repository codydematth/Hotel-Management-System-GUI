/*
 * Joysbright Programming Crew.
 * http://joysbrightcrew.com
 * Expert in ICT jobs
 */
package admin;

import check.CheckController;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseHandler;
import static java.awt.image.ImageObserver.HEIGHT;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import main.mainController;

/**
 * FXML Controller class
 *
 * @author Sen-Joysbright
 */
public class AdminController implements Initializable {

    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtID;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXTextField txtRank;
    
    DatabaseHandler handler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registerOP(ActionEvent event) {
        userValues();
        handler = DatabaseHandler.getInstance();
        
        String table_name = "staff";
        
            

            Boolean flag = table_name.isEmpty() || name.isEmpty()|| password.isEmpty() ;
            if(flag){
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please supply value to all fields");
                alert.showAndWait();
                return;
            }
            
            //Choose staff ID            
            String qu = "SELECT * FROM staff";
           System.out.print(qu);
            ResultSet rs = handler.execQuery(qu); 
            int rows = 1;
        try {
            while (rs.next()){                 
                  rows = rows + 1;   
            }
        } catch (SQLException ex) {
            Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        String staffid = "KHS-"+rows;
             String st = "INSERT INTO " + table_name + "(staffid,name,rank,password)" + 
                     " VALUES (" +
                    "'" + staffid + "', "+
                    "'" + name + "', "+
                    "'" + rank + "', "+
                    "'" + password + "'" +              
                    ")";
            System.out.println(st); 
            if (handler.execAction(st)) {
                JOptionPane.showMessageDialog(null,"Staff Registered! Your Staff ID is:\t" + staffid,"Account creation success",HEIGHT,null);
                resetValues();
            }
            else{
                 JOptionPane.showMessageDialog(null,"Adding Fail !","Insertion Error",HEIGHT,null);
            }
            }

    @FXML
    private void updateOP(ActionEvent event) {
        String table_name = "staff";
        String staffid = txtID.getText();
        String name = txtName.getText();
        String rank = txtRank.getText();
        String password =txtPassword.getText();
        
        Boolean flag = table_name.isEmpty() || rank.isEmpty() || ( name.isEmpty() && password.isEmpty());
        if(flag){
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please supply value to important fields");
            alert.showAndWait();
            return;
        }
                
        //Update
        
         /*  String st = "UPDATE " + table_name + " SET rank = '" + rank +  
                 "',name = '" + name + "',password = '" + password + "' WHERE staffid = " + staffid + "";
          
            System.out.println(st); 
            try {
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
                catch (Exception ex) {
            
        }
               
        */
    }


       
    
        String id;
        String name;
        String rank;
        String password;
    private void userValues() {
         name = txtName.getText().toUpperCase();
         rank = txtRank.getText();
         password = txtPassword.getText();
    }
    
    private void resetValues() {
        txtID.setText("");
        txtName.setText("");
        txtRank.setText("");
        txtPassword.setText("");
    }

  

    @FXML
    private void searchOP(ActionEvent event) {
        String staffid = txtID.getText().toUpperCase();
       if(!staffid.isEmpty()){
           DatabaseHandler handler = DatabaseHandler.getInstance();

           String qu = "SELECT * FROM staff"  + " WHERE staffid = '" + staffid +"'";
           System.out.println(qu);
            ResultSet rs = handler.execQuery(qu);         
        try {
            if (rs.next()){ 
                txtID.setText(rs.getString("id")); 
                txtName.setText(rs.getString("name")); 
                txtPassword.setText(rs.getString("password")); 
                txtRank.setText(rs.getString("rank"));
                     
            }
            else {
                JOptionPane.showMessageDialog(null,"Invalid Staff ID !", "Query Error", HEIGHT, null); 
            }
        } catch (SQLException ex) {
            
        }
       }
       else
       {
           JOptionPane.showMessageDialog(null,"Supply Staff ID to refresh !", "Query Error", HEIGHT, null); 
       }
         
    }
}
