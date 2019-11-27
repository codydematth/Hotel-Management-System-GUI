/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package check;

import functions.quickAction;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import main.mainController;

/**
 * FXML Controller class
 *
 * @author Sen-Joysbright
 */
public class CheckController implements Initializable {

    quickAction action = new quickAction();
    @FXML
    private Pane centerPane;
    @FXML
    private BorderPane centerCarrier;
    @FXML
    private Label lblStaffName;
    @FXML
    private Label lblStaffID;
    
    public static String staffId;
    public static String staffName;
    
    mainController staff = new mainController();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Load check in as the first
         replaceCenter("/checkin/checkin.fxml");
         
         //Load Logged in user details
         lblStaffID.setText("Staff ID: "+staffId);
         lblStaffName.setText("Active: "+staffName);
    }    

    @FXML
    private void exitOP(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText(null);
        alert.setContentText("Do you really want to Exit?" );
        
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK){            
            System.exit(0);
        }
            
    }

    @FXML
    private void printOP(ActionEvent event) {
        
    }
    
    @FXML
    private void logoutOP(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure to Logout?" );
        
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK){            
            action.loadNextScene("/main/main.fxml", centerCarrier);
        }
        
    }

    @FXML
    private void checkoutOP(ActionEvent event) {
        
        replaceCenter("/checkOut/checkout.fxml");
    }

    @FXML
    private void checkinOP(ActionEvent event) {
        replaceCenter("/checkin/checkin.fxml");
    }

    @FXML
    private void setupUserOP(ActionEvent event) {
         replaceCenter("/setupUser/setup.fxml");
    }
    
    void replaceCenter(String Loc) {
        try {
            AnchorPane gpane = FXMLLoader.load(getClass().getResource(Loc));
            gpane.getHeight();
            gpane.getWidth();
            centerCarrier.setCenter(gpane);
        } catch (IOException ex) {
            Logger.getLogger(CheckController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void adminOP(ActionEvent event) {
        String accessCode = JOptionPane.showInputDialog("Supply Access Code");
        
        if(accessCode.equals("12345")){
            replaceCenter("/admin/admin.fxml");
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Invalid Credentials !");
            alert.showAndWait();
        }
    }

    @FXML
    private void listRoomOP(ActionEvent event) {
        replaceCenter("/list/room/room.fxml");
    }

    @FXML
    private void listStaffOP(ActionEvent event) {
        replaceCenter("/list/staff/staff.fxml");
    }

    @FXML
    private void listCustomerOP(ActionEvent event) {
        replaceCenter("/list/customer/customer.fxml");
    }

    @FXML
    private void transactionOP(ActionEvent event) {
        replaceCenter("/list/transactions/transactions.fxml");
    }

    @FXML
    private void helpOP(ActionEvent event) {
        replaceCenter("/help/help.fxml");
    }

    @FXML
    private void about1OP(ActionEvent event) {
        replaceCenter("/about/software/software.fxml");
    }

    @FXML
    private void about2OP(ActionEvent event) {
        replaceCenter("/about/developer/developer.fxml");
    }


    @FXML
    private void creditOP(ActionEvent event) {
        replaceCenter("/credit/credit.fxml");
    }

    @FXML
    private void searchCustomerOP(ActionEvent event) {
        replaceCenter("/search/customer/customer.fxml");
    }

    @FXML
    private void searchRoomOP(ActionEvent event) {
        replaceCenter("/search/room/room.fxml");
    }

    @FXML
    private void searchTransactionOP(ActionEvent event) {
        replaceCenter("/search/transactions/transactions.fxml");
    }


    
    
}
