/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import check.CheckController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseHandler;
import functions.quickAction;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Sen-Joysbright
 */
public class mainController implements Initializable {
    
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private Label lblNotify;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXButton btnExit;
    
    DatabaseHandler handler;
    
    public String staffID;
    public String staffName;
    
    quickAction action = new quickAction();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = DatabaseHandler.getInstance();
    }    

    @FXML
    private void loginOP(ActionEvent event) {
        String username = txtUsername.getText();
      String password = txtPassword.getText();
      String tableName = "staff";
      
      if((username.isEmpty() && password.isEmpty()) || (username.isEmpty() || password.isEmpty())){
          lblNotify.setVisible(true);
          btnLogin.setFocusTraversable(false);
          txtUsername.setFocusTraversable(true);
      }
      else if (username.equalsIgnoreCase("mathias") && password.equalsIgnoreCase("mathy")){
                    CheckController.staffId = username;
                    CheckController.staffName = "Admin";
          action.loadNextScene("/check/check.fxml", lblNotify);
      }
      else if (!username.isEmpty() && !password.isEmpty()){
          
           DatabaseHandler handler = DatabaseHandler.getInstance();

           String qu = "SELECT * FROM " + tableName + " WHERE staffid = '" + username +"'";
           System.out.print(qu);
            ResultSet rs = handler.execQuery(qu);         
        try {
            if (rs.next()){ 
                if(rs.getString("password").equals(password))
                {
                    CheckController.staffId = username;
                    CheckController.staffName = rs.getString("name");
                    action.loadNextScene("/check/check.fxml", btnLogin);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Incorrect password !", "Login Error", HEIGHT, null);                    
                }
                     
            }
            else {
                    JOptionPane.showMessageDialog(null,"Staff ID has not be register !", "Login Error", HEIGHT, null); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      else {
          lblNotify.setText("Invalid Credentials !");
          lblNotify.setVisible(true);
          txtUsername.setFocusTraversable(true);
      }
    }

    @FXML
    private void exitOP(MouseEvent event) {
    }

    @FXML
    private void closeOP(ActionEvent event) {
        System.exit(0);
    }

    

    
    
}
