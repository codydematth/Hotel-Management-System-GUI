/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseHandler;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Sen-Joysbright
 */
public class CheckinController implements Initializable {

    @FXML
    private JFXTextField txtCustomer;
    @FXML
    private ListView<String> listCustomer;
    ObservableList<String> customerDetails = FXCollections.observableArrayList();
    @FXML
    private JFXComboBox<String> txtRoomType;
    @FXML
    private JFXTextField txtRoomNumber;
    @FXML
    private JFXTextField txtFloor;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblID;
    @FXML
    private Label lblPrice;
    @FXML
    private JFXRadioButton btnPaid;
    
    DatabaseHandler databaseHandler;
    @FXML
    private JFXButton btnProceed;
    
    String customerName,customerID, roomAmount;
    
    boolean customerReady = false, roomReady = false, checker = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                databaseHandler = DatabaseHandler.getInstance();
                txtRoomType.getItems().add("Single");
                txtRoomType.getItems().add("Double");
                txtRoomType.getItems().add("Deluxe");
    }    

   /* void checkIfIssued(){
        String roomid = lblID.getText();
        String qu = "SELECT * FROM checkin WHERE roomid = '" + roomid + "'";
        ResultSet rsChecker = databaseHandler.execQuery(qu);
        try {
            if(rsChecker.next() ){
                checker = true;                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
    
    @FXML
    private void proceedOP(ActionEvent event) {
        if(lblStatus.getText().equalsIgnoreCase("available")){
            checker = true;
        }
        if(btnPaid.isSelected()){
            if(customerReady && roomReady && checker){
                

                        //Confirm process operation
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Check In Operation");
                alert.setHeaderText(null);
                alert.setContentText("Proceed?" );

                Optional<ButtonType> response = alert.showAndWait();
                if(response.get() == ButtonType.OK){
                    
                    //Check the user in
                     String str = "INSERT INTO checkin(customerid,customerName,roomid,roomAmount) VALUES ( "
                                + "'" + customerID + "',"
                                + "'" + customerName + "',"
                                + "'" + lblID.getText() + "',"
                                + "'" + roomAmount + "')";
                      //Save to transactions
                      String str2 = "INSERT INTO transactions(customerid,customerName,roomid,roomAmount) VALUES ( "
                                + "'" + customerID + "',"
                                + "'" + customerName + "',"
                                + "'" + lblID.getText() + "',"
                                + "'" + roomAmount + "')";
                      //Update room details
                      String str3 = "UPDATE room SET availability = 'Unavailable' WHERE roomid = '" + lblID.getText() + "'";

                      System.out.println(str + "\n" + str2 + "\n" + str3);
                     if(databaseHandler.execAction(str) && databaseHandler.execAction(str2) && databaseHandler.execAction(str3)) {
                            customerReady = false; roomReady = false;
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("Successful");
                            alert1.setHeaderText(null);
                            alert1.setContentText("Customer Checked In !" );
                            alert1.showAndWait();
                        }
                        else {
                            Alert alert1 = new Alert(Alert.AlertType.ERROR);
                            alert1.setTitle("Failed");
                            alert1.setHeaderText(null);
                            alert1.setContentText("Operation Failed" );
                            alert1.showAndWait();
                        }
                    
                   
                    
                }
                else if(response.get() == ButtonType.CANCEL) {
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Canceled");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Operation Failed" );
                }
            }
        }
    }

    @FXML
    private void customerOP(KeyEvent event) {
        searchCustomer();
    }

    @FXML
    private void customerOP(InputMethodEvent event) {
        searchCustomer();
    }

    @FXML
    private void customerOP(ActionEvent event) {
        searchCustomer();
    }
    
    void searchCustomer() {
        
        try {
            String customerid = txtCustomer.getText().toUpperCase();
            
            String qu3 = "SELECT * FROM customer WHERE customerid = '" + customerid + "'";
            ResultSet rs3 = databaseHandler.execQuery(qu3);
            if(rs3.next()){
                customerDetails.clear();
                customerDetails.add("Customer ID: \t" + rs3.getString("customerid"));
                customerID = rs3.getString("customerid");
                customerName = rs3.getString("name");
                customerDetails.add("Full Name: \t" + rs3.getString("name"));
                customerDetails.add("Sex: \t" + rs3.getString("gender"));
                customerDetails.add("E-mail: \t" + rs3.getString("passport"));
                customerDetails.add("Mobile: \t" + rs3.getString("card"));
                customerDetails.add("Address: \t" + rs3.getString("address"));
                customerReady = true;
            }
            
            else {
                customerDetails.clear();
                customerDetails.add("Customer ID does not exist !");
                customerReady = false;
            }
            
            
            listCustomer.getItems().setAll(customerDetails);
        } catch (SQLException ex) {
            Logger.getLogger(CheckinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void roomOP(ActionEvent event) {
        searchRoom();
    }

    private void floorOP(ActionEvent event) {
        
        
    }

    @FXML
    private void roomOP(KeyEvent event) {
        searchRoom();
    }
    
    void searchRoom(){
        
        try {
            lblID.setText("KHS/ROM/" + txtFloor.getText() + "/" + txtRoomNumber.getText());
            
            //Get Room Details
            String qu3 = "SELECT * FROM room WHERE roomid = '" + lblID.getText() + "' AND type = '" + (String) txtRoomType.getValue() + "'";
            System.out.println(qu3);
            ResultSet rs3 = databaseHandler.execQuery(qu3);
            if (rs3.next()){
                lblStatus.setText(rs3.getString("availability"));
                lblPrice.setText("#" + rs3.getString("amount"));
                roomAmount = rs3.getString("amount");
                roomReady = true;
            }
            else {
                lblStatus.setText("Room does not exist");
                lblPrice.setText("#0");
                roomReady = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckinController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void floorOP(KeyEvent event) {
        lblID.setText("KHS/ROM/" + txtFloor.getText() + "/");
    }

    @FXML
    private void paidOP(ActionEvent event) {
        if(btnProceed.isDisable()){
            btnProceed.setDisable(false);
        }
        else {
            btnProceed.setDisable(true);
        }
    }
}
