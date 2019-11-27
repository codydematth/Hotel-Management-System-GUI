/*
 * Joysbright Programming Crew.
 * http://joysbrightcrew.com
 * Expert in ICT jobs
 */
package search.customer;

import checkin.CheckinController;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseHandler;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Sen-Joysbright
 */
public class CustomerController implements Initializable {

    @FXML
    private JFXTextField txtCustomer;
    @FXML
    private ListView<String> listCustomer;
    
    ObservableList<String> customerDetails = FXCollections.observableArrayList();
    DatabaseHandler databaseHandler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         databaseHandler = DatabaseHandler.getInstance();
    }    

    @FXML
    private void customerOP(ActionEvent event) {
        try {
            String customerid = txtCustomer.getText().toUpperCase();
            
            String qu3 = "SELECT * FROM customer WHERE customerid = '" + customerid + "'";
            ResultSet rs3 = databaseHandler.execQuery(qu3);
            if(rs3.next()){
                customerDetails.clear();
                customerDetails.add("Customer ID: \t" + rs3.getString("customerid"));
                customerDetails.add("Full Name: \t" + rs3.getString("name"));
                customerDetails.add("Sex: \t" + rs3.getString("gender"));
                customerDetails.add("E-mail: \t" + rs3.getString("passport"));
                customerDetails.add("Mobile: \t" + rs3.getString("card"));
                customerDetails.add("Address: \t" + rs3.getString("address"));
            }
            
            else {
                customerDetails.clear();
                customerDetails.add("Customer ID does not exist !");
            }
            
            
            listCustomer.getItems().setAll(customerDetails);
        } catch (SQLException ex) {
            Logger.getLogger(CheckinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void customerOP(KeyEvent event) {
        try {
            String customerid = txtCustomer.getText().toUpperCase();
            
            String qu3 = "SELECT * FROM customer WHERE customerid = '" + customerid + "'";
            ResultSet rs3 = databaseHandler.execQuery(qu3);
            if(rs3.next()){
                customerDetails.clear();
                customerDetails.add("Customer ID: \t" + rs3.getString("customerid"));
                customerDetails.add("Full Name: \t" + rs3.getString("name"));
                customerDetails.add("Sex: \t" + rs3.getString("gender"));
                customerDetails.add("E-mail: \t" + rs3.getString("passport"));
                customerDetails.add("Mobile: \t" + rs3.getString("card"));
                customerDetails.add("Address: \t" + rs3.getString("address"));
            }
            
            else {
                customerDetails.clear();
                customerDetails.add("Customer ID does not exist !");
            }
            
            
            listCustomer.getItems().setAll(customerDetails);
        } catch (SQLException ex) {
            Logger.getLogger(CheckinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
