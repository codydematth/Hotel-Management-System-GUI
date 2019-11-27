/*
 * Joysbright Programming Crew.
 * http://joysbrightcrew.com
 * Expert in ICT jobs
 */
package search.room;

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
public class RoomController implements Initializable {

    @FXML
    private JFXTextField txtRoom;
    @FXML
    private ListView<String> listRoom;

    DatabaseHandler databaseHandler;
    ObservableList<String> customerDetails = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseHandler = DatabaseHandler.getInstance();
    }    

    @FXML
    private void roomOP(ActionEvent event) {
        searchRoom();
    }

    @FXML
    private void roomOP(KeyEvent event) {
        searchRoom();
    }
    
    void searchRoom(){
        try {
            String roomid = txtRoom.getText().toUpperCase();
            
            String qu3 = "SELECT * FROM room WHERE roomid = '" + roomid + "'";
            ResultSet rs3 = databaseHandler.execQuery(qu3);
            if(rs3.next()){
                customerDetails.clear();
                customerDetails.add("Room ID: \t" + rs3.getString("roomid"));
                customerDetails.add("Room Type: \t" + rs3.getString("type"));
                customerDetails.add("Floor: \t" + rs3.getString("floor"));
                customerDetails.add("Room Number: \t" + rs3.getString("number"));
                customerDetails.add("Amount: \t#" + rs3.getString("amount"));
                customerDetails.add("Availability: \t" + rs3.getString("availability"));
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
