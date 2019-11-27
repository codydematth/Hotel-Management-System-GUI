/*
 * Joysbright Programming Crew.
 * http://joysbrightcrew.com
 * Expert in ICT jobs
 */
package list.customer;

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


public class CustomerController implements Initializable {

     ObservableList<Customer> list = FXCollections.observableArrayList();
     
    @FXML
    private TableView<Customer> tableview;
    @FXML
    private TableColumn<Customer, String> idCol;
    @FXML
    private TableColumn<Customer, String> nameCol;
    @FXML
    private TableColumn<Customer, String> genderCol;
    @FXML
    private TableColumn<Customer, String> addressCol;
    @FXML
    private TableColumn<Customer, String> passportCol;
    @FXML
    private TableColumn<Customer, String> cardCol;

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
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        passportCol.setCellValueFactory(new PropertyValueFactory<>("passport"));
        cardCol.setCellValueFactory(new PropertyValueFactory<>("card"));
        
    }
    
     private void loadData() {
           DatabaseHandler handler = DatabaseHandler.getInstance();

           String qu = "SELECT * FROM customer";
            ResultSet rs = handler.execQuery(qu);
        try {
            while (rs.next()){
                String idx = rs.getString("customerid");
                String namex = rs.getString("name");
                String genderx = rs.getString("gender");
                String addressx  = rs.getString("address");
                String passportx = rs.getString("passport");
                String cardx  = rs.getString("card");
                
                
                list.add(new Customer(idx, namex, genderx, addressx,passportx,cardx));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableview.getItems().setAll(list);
    }
    
    public static class Customer{

       
        private final SimpleStringProperty name;
        private final SimpleStringProperty address;
        private final SimpleStringProperty gender;
        private final SimpleStringProperty passport;
        private final SimpleStringProperty card;
        private final SimpleStringProperty id;
       
        
        Customer(String id, String name, String gender, String address, String passport, String card){
            this.name = new SimpleStringProperty(name);
            this.id = new SimpleStringProperty(id);
            this.address = new SimpleStringProperty(address);
            this.gender = new SimpleStringProperty(gender); 
            this.passport = new SimpleStringProperty(passport);  
            this.card = new SimpleStringProperty(card);           
        }
        
         public String getName() {
            return name.get();
        }

        public String getAddress() {
            return address.get();
        }

        public String getGender() {
            return gender.get();
        }

        public String getPassport() {
            return passport.get();
        }

        public String getCard() {
            return card.get();
        }

       
        public String getId() {
            return id.get();
        }
     }
    
}
