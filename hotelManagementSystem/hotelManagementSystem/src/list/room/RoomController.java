/*
 * Joysbright Programming Crew.
 * http://joysbrightcrew.com
 * Expert in ICT jobs
 */
package list.room;

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

/**
 * FXML Controller class
 *
 * @author Sen-Joysbright
 */
public class RoomController implements Initializable {

    ObservableList<Room> list = FXCollections.observableArrayList();
    @FXML
    private TableView<Room> tableview;
    @FXML
    private TableColumn<Room, String> idCol;
    @FXML
    private TableColumn<Room, String> roomCol;
    @FXML
    private TableColumn<Room, String> typeCol;
    @FXML
    private TableColumn<Room, String> floorCol;
    @FXML
    private TableColumn<Room, String> numberCol;
    @FXML
    private TableColumn<Room, String> amountCol;
    @FXML
    private TableColumn<Room, String> statusCol;

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
        roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        floorCol.setCellValueFactory(new PropertyValueFactory<>("floor"));
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("statusCol"));
        
    }   
  
     private void loadData() {
           DatabaseHandler handler = DatabaseHandler.getInstance();

           String qu = "SELECT * FROM room";
            ResultSet rs = handler.execQuery(qu);
        try {
            while (rs.next()){
                String idx = rs.getString("id");
                String roomx = rs.getString("roomid");
                String typex = rs.getString("type");
                String floorx  = rs.getString("floor");
                String numberx = rs.getString("availability");
                String amountx  = rs.getString("amount");
                String statusx = rs.getString("availability");
                
                
                list.add(new RoomController.Room(idx, roomx, typex, floorx,numberx,amountx,statusx));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableview.getItems().setAll(list);
    }
 public static class Room{

       
        private final SimpleStringProperty room;
        private final SimpleStringProperty type;
        private final SimpleStringProperty floor;
        private final SimpleStringProperty number;
        private final SimpleStringProperty amount;
        private final SimpleStringProperty id;
        private final SimpleStringProperty status;
       
        
        Room(String id, String room, String type, String floor, String number, String amount, String status){
            this.room = new SimpleStringProperty(room);
            this.id = new SimpleStringProperty(id);
            this.type = new SimpleStringProperty(type);
            this.floor = new SimpleStringProperty(floor); 
            this.number = new SimpleStringProperty(number);  
            this.amount = new SimpleStringProperty(amount); 
            this.status = new SimpleStringProperty(status);
        }

        public String getRoom() {
            return room.get();
        }

        public String getStatus() {
            return status.get();
        }

        public String getType() {
            return type.get();
        }

        public String getFloor() {
            return floor.get();
        }

        public String getNumber() {
            return number.get();
        }

        public String getAmount() {
            return amount.get();
        }
        
        public String getId() {
            return id.get();
        }
     }
}
