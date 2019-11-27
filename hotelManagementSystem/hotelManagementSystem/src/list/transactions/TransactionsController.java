/*
 * Joysbright Programming Crew.
 * http://joysbrightcrew.com
 * Expert in ICT jobs
 */
package list.transactions;

import database.DatabaseHandler;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import list.room.RoomController;

/**
 * FXML Controller class
 *
 * @author Sen-Joysbright
 */
public class TransactionsController implements Initializable {

    ObservableList<transaction> list = FXCollections.observableArrayList();
    @FXML
    private TableView<transaction> tableview;
    @FXML
    private TableColumn<transaction, String> idCol;
    @FXML
    private TableColumn<transaction, String> customeridCol;
    @FXML
    private TableColumn<transaction, String> customerCol;
    @FXML
    private TableColumn<transaction, String> roomidCol;
    @FXML
    private TableColumn<transaction, String> amountCol;
    @FXML
    private TableColumn<transaction, String> timeCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }    

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        roomidCol.setCellValueFactory(new PropertyValueFactory<>("roomid"));
        customeridCol.setCellValueFactory(new PropertyValueFactory<>("customerid"));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        
    }   
    
    private void loadData() {
           DatabaseHandler handler = DatabaseHandler.getInstance();
            int sn = 1;
           String qu = "SELECT * FROM transactions";
            ResultSet rs = handler.execQuery(qu);
        try {
            while (rs.next()){
                
                int idx = sn;
                sn = sn + 1;
                String roomidx = rs.getString("roomid");
                String customeridx = rs.getString("customerid");
                String customerx  = rs.getString("customerName");
                String timex = rs.getString("time");
                String amountx  = rs.getString("roomAmount");
                
                
                list.add(new transaction(idx, customeridx, customerx, roomidx,amountx,timex));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableview.getItems().setAll(list);
    }
    public static class transaction {
        private final SimpleStringProperty roomid;
        private final SimpleStringProperty customerid;
        private final SimpleStringProperty customerName;
        private final SimpleStringProperty time;
        private final SimpleStringProperty amount;
        private final SimpleStringProperty id;
        
         transaction(int id, String customerid, String customerName, String roomid, String amount, String time) {
            this.roomid = new SimpleStringProperty(roomid);
            this.id = new SimpleStringProperty(String.valueOf(id));
            this.customerid = new SimpleStringProperty(customerid);
            this.customerName = new SimpleStringProperty(customerName); 
            this.time = new SimpleStringProperty(time);  
            this.amount = new SimpleStringProperty(amount);
        }

        public String getRoomid() {
            return roomid.get();
        }

        public String getCustomerid() {
            return customerid.get();
        }

        public String getCustomerName() {
            return customerName.get();
        }

        public String getTime() {
            return time.get();
        }

        public String getAmount() {
            return amount.get();
        }

        public String getId() {
            return id.get();
        }
               
        
    }
    
}
