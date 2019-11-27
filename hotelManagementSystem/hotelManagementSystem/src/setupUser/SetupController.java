/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setupUser;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.layout.HBox;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sen-Joysbright
 */
public class SetupController implements Initializable {

    @FXML
    private HBox setupHbox;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtID;
    @FXML
    private JFXComboBox<String> txtGender;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtPassport;
    @FXML
    private JFXTextField txtCard;
    @FXML
    private JFXButton btnAddCustomer;
    @FXML
    private JFXButton btnUpdateCustomer;
    @FXML
    private JFXButton btnDeleteCustomer;
    @FXML
    private JFXComboBox<String> txtRoomType;
    @FXML
    private JFXTextField txtRoomNumber;
    @FXML
    private JFXTextField txtFloor;
    @FXML
    private JFXTextField txtAmount;
    @FXML
    private JFXTextField txtRoomNum;
    @FXML
    private JFXTextField txtDiscount;

    DatabaseHandler handler;
    
        String id;
        String name;
        String gender;
        String address;
        String passport;
        String card;
        String number;
        String type;
        String floor;
        String amount;
        
    private void userValues() {
         name = txtName.getText().toUpperCase();
         gender = (String) txtGender.getValue();
         address = txtAddress.getText();
         passport = txtPassport.getText();
         card = txtCard.getText();
    }
    
    private void userValues2() {
         number = txtRoomNumber.getText().toUpperCase();
         type = (String) txtRoomType.getValue();
         floor = txtFloor.getText();
         amount = txtAmount.getText();
    }
    private void resetValues() {
        txtID.setText("");
        txtName.setText("");
        txtGender.setValue("Male");
        txtAddress.setText("");
        txtPassport.setText("");
        txtCard.setText("");
    }
    private void resetValues2() {
        txtRoomNumber.setText("");
        txtFloor.setText("");
        txtRoomType.setValue("Single");
        txtAmount.setText("");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtRoomType.getItems().addAll("Single","Double","Deluxe");
        txtGender.getItems().addAll("Male","Female");
        handler = DatabaseHandler.getInstance();
    }    

    @FXML
    private void addCustomer(ActionEvent event) {
        try {
            userValues();
            handler = DatabaseHandler.getInstance();
            
            String table_name = "customer";
            
            
            Boolean flag = card.isEmpty() || name.isEmpty() || address.isEmpty() || passport.isEmpty() ;
            if(flag){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please supply value to all fields");
                alert.showAndWait();
                return;
            }
            
            //Create customer ID
            int rows = 1;
            String qu = "SELECT * FROM customer";
            System.out.println(qu);
            ResultSet rs = handler.execQuery(qu);
            while (rs.next()){ 
                 rows = rows + 1;
            }
            String customerid = "KHS/CUS/" + rows;
            String st = "INSERT INTO " + table_name + "(name,address,gender,customerid,passport,card)" +
                    " VALUES (" +
                    "'" + name + "', "+
                    "'" + address + "', "+
                    "'" + gender + "', "+
                    "'" + customerid + "', "+
                    "'" + passport + "', "+
                    "'" + card + "'" +              
                    ")";
            System.out.println(st); 
            if (handler.execAction(st)) {
                JOptionPane.showMessageDialog(null,"Account created ! ","Account creation success",HEIGHT,null);
                resetValues();
            }
            else{
                JOptionPane.showMessageDialog(null,"Adding Fail !","Insertion Error",HEIGHT,null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SetupController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
             
    }

    @FXML
    private void updateCustomer(ActionEvent event) {
    }

    @FXML
    private void deleteCustomer(ActionEvent event) {
    }

    @FXML
    private void addRoom(ActionEvent event) {
        
        try {
            userValues2();
            handler = DatabaseHandler.getInstance();
            
            String table_name = "room";
            
            
            Boolean flag = number.isEmpty() || floor.isEmpty() || amount.isEmpty() || type.isEmpty() ;
            if(flag){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please supply value to all fields");
                alert.showAndWait();
                return;
            }
            
            //Create room ID (Useless)
            int rows = 1;
            String qu = "SELECT * FROM room";
            System.out.println(qu);
            ResultSet rs = handler.execQuery(qu);
            while (rs.next()){ 
                 rows = rows + 1;
            }
            String discount = "0";
            String roomid = "KHS/ROM/" + floor +"/"+number;
            String st = "INSERT INTO " + table_name + "(type,number,amount,roomid,floor,discount,availability)" +
                    " VALUES (" +
                    "'" + type + "', "+
                    "'" + number + "', "+
                    "'" + amount + "', "+
                    "'" + roomid + "', "+
                    "'" + floor + "', "+
                    "'" + discount + "'," + 
                    "'" + "Available" + "'" +              
                    ")";
            System.out.println(st); 
            if (handler.execAction(st)) {
                JOptionPane.showMessageDialog(null,"Room created ! ","Account creation success",HEIGHT,null);
                resetValues();
            }
            else{
                JOptionPane.showMessageDialog(null,"Creation Fail !","Insertion Error",HEIGHT,null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SetupController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
             
    }

    @FXML
    private void updateRoom(ActionEvent event) {
    }

    @FXML
    private void deleteRoom(ActionEvent event) {
    }

    @FXML
    private void addPrice(ActionEvent event) {
    }

    @FXML
    private void updatePrice(ActionEvent event) {
    }

    @FXML
    private void deletePrice(ActionEvent event) {
    }
    
}
