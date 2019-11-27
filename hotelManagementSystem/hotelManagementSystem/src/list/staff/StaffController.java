/*
 * Joysbright Programming Crew.
 * http://joysbrightcrew.com
 * Expert in ICT jobs
 */
package list.staff;

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
public class StaffController implements Initializable {

    ObservableList<staff> list = FXCollections.observableArrayList();
    @FXML
    private TableView<staff> tableview;
    @FXML
    private TableColumn<staff, String> idCol;
    @FXML
    private TableColumn<staff, String> staffCol;
    @FXML
    private TableColumn<staff, String> nameCol;
    @FXML
    private TableColumn<staff, String> rankCol;

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
        staffCol.setCellValueFactory(new PropertyValueFactory<>("staff"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        rankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));
        
    }  
    
  
     private void loadData() {
           DatabaseHandler handler = DatabaseHandler.getInstance();

           String qu = "SELECT * FROM staff";
            ResultSet rs = handler.execQuery(qu);
        try {
            while (rs.next()){
                String idx = rs.getString("id");
                String staffx = rs.getString("staffid");
                String namex = rs.getString("name");
                String rankx  = rs.getString("rank");
                
                
                list.add(new StaffController.staff(idx, staffx, namex, rankx));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableview.getItems().setAll(list);
    }
    
 public static class staff{

        private final SimpleStringProperty rank;
        private final SimpleStringProperty staff;
        private final SimpleStringProperty name;
        private final SimpleStringProperty id;
       
        
        staff(String id, String staff, String name, String rank){
            this.staff = new SimpleStringProperty(staff);
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.rank = new SimpleStringProperty(rank);       
        }

        public String getRank() {
            return rank.get();
        }

        public String getStaff() {
            return staff.get();
        }

        public String getName() {
            return name.get();
        }

        
        
        public String getId() {
            return id.get();
        }
     }
    
}
