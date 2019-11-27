/*
 * Joysbright Programming Crew.
 * http://joysbrightcrew.com
 * Expert in ICT jobs
 */
package search.transactions;

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
public class TransactionsController implements Initializable {

    @FXML
    private JFXTextField txtRoom;
    @FXML
    private ListView<String> listRoom;

    DatabaseHandler databaseHandler;
    ObservableList <String> customerDetails = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseHandler = DatabaseHandler.getInstance();
    }    

    @FXML
    private void transactionOP(ActionEvent event) {
        searchTransaction();
    }

    @FXML
    private void transactionOP(KeyEvent event) {
        searchTransaction();
    }
    
    void searchTransaction(){
        try {
            String roomid = txtRoom.getText().toUpperCase();
            
            String qu3 = "SELECT * FROM transactions WHERE roomid = '" + roomid + "'";
            ResultSet rs3 = databaseHandler.execQuery(qu3);
            if(rs3.next()){
                customerDetails.clear();
                customerDetails.add("Customer ID: \t" + rs3.getString("customerid"));
                customerDetails.add("Customer Name: \t" + rs3.getString("customerName"));
                customerDetails.add("Room ID: \t" + rs3.getString("roomid"));
                customerDetails.add("Room Number: \t" + rs3.getString("number"));
                customerDetails.add("Room Amount: \t#" + rs3.getString("roomAmount"));
                customerDetails.add("Date and Time: \t" + rs3.getString("time"));
            }
            
            else {
                customerDetails.clear();
                customerDetails.add("Room ID does not exist !");
            }
            
            
            listRoom.getItems().setAll(customerDetails);
        } catch (SQLException ex) {
            Logger.getLogger(CheckinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
